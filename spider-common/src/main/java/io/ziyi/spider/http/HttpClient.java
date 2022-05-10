package io.ziyi.spider.http;

import com.fasterxml.jackson.core.type.TypeReference;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.util.JsonUtils;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HttpClient {

    private static class CommonHeadersInterceptor implements Interceptor {

        private final Map<String, String> headers;

        private CommonHeadersInterceptor(Map<String, String> headers) {
            Objects.requireNonNull(headers);
            this.headers = new LinkedHashMap<>(headers);
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request.Builder builder = chain.request().newBuilder();
            headers.forEach(builder::addHeader);
            return chain.proceed(builder.build());
        }
    }

    private static class CommonLoggerInterceptor implements Interceptor {

        private CommonLoggerInterceptor() {
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Request request = chain.request();
            String url = request.url().toString();
            RequestBody requestBody = request.body();
            String requestText = "";
            if ( requestBody != null ) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                requestText = buffer.readString(StandardCharsets.UTF_8);
            }
            logger.info("Request begin", "[{}]: method={}, url={}, data={}", request.tag(), request.method(), url, requestText);

            okhttp3.Response result = null;
            long start = System.currentTimeMillis();
            try ( okhttp3.Response response = chain.proceed(request) ) {
                long end = System.currentTimeMillis();

                okhttp3.ResponseBody responseBody = response.body();
                String text = responseBody == null ? "" : responseBody.string();
                logger.info("Request finished", "[{}]: time={}ms, status={}, response={}", request.tag(), end - start, response.code(), text);

                if ( responseBody != null ) {
                    ResponseBody copy = ResponseBody.Companion.create(text, responseBody.contentType());
                    result = response.newBuilder().body(copy).build();
                } else {
                    result = response;
                }
            }
            return result;
        }
    }

    public static class ClientBuilder {
        private Map<String, String> headers;
        private Proxy proxy;
        private long connectTimeout;
        private long readTimeout;
        private boolean useLogger;

        public ClientBuilder headers(Map<String, String> headers) {
            this.headers = Collections.unmodifiableMap(headers);
            return this;
        }

        public ClientBuilder proxy(InetAddress address, int port) {
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(address, port));
            return this;
        }

        public ClientBuilder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public ClientBuilder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public ClientBuilder useLogger(boolean useLogger) {
            this.useLogger = useLogger;
            return this;
        }

        public HttpClient build() {
            return new HttpClient(headers, proxy, connectTimeout, readTimeout, useLogger);
        }

    }

    public static class Request {

        private final okhttp3.Request request;

        private Request(okhttp3.Request.Builder builder) {
            this.request = builder.tag(newTag()).build();
        }

        public String url() {
            return request.url().toString();
        }
    }

    public static class Response<T> {
        private final int code;
        private final T data;

        private Response(int code, T data) {
            this.code = code;
            this.data = data;
        }

        public final boolean ok() {
            return code >= 200 && code <= 299;
        }

        public int code() {
            return code;
        }

        public T data() {
            return data;
        }
    }

    public static class RequestBuilder {

        private String url;
        private final Map<String, String> headers = new LinkedHashMap<>();
        private final Map<String, Set<String>> queries = new LinkedHashMap<>();
        private final Map<String, Set<String>> form = new LinkedHashMap<>();

        private final Map<String, Set<File>> files = new LinkedHashMap<>();
        private Object body;

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public RequestBuilder headers(Map<String, String> headers) {
            this.headers.clear();
            if ( headers != null ) {
                this.headers.putAll(headers);
            }
            return this;
        }

        public RequestBuilder header(String name, String value) {
            if ( value != null ) {
                this.headers.put(name, value);
            } else {
                this.headers.remove(name);
            }
            return this;
        }

        public RequestBuilder query(Map<String, String> queries) {
            this.queries.clear();
            if ( queries != null ) {
                queries.forEach((name, value) -> this.queries.put(name, Collections.singleton(value)));
            }
            return this;
        }

        public RequestBuilder addQuery(String name, String... values) {
            if ( values != null ) {
                Set<String> set = this.queries.computeIfAbsent(name, k -> new LinkedHashSet<>());
                set.addAll(Arrays.asList(values));
            }
            return this;
        }

        public RequestBuilder removeQuery(String name, String... values) {
            if ( values.length > 0 ) {
                Set<String> set = this.queries.computeIfAbsent(name, k -> new LinkedHashSet<>());
                Arrays.asList(values).forEach(set::remove);
            } else {
                this.queries.remove(name);
            }
            return this;
        }

        public RequestBuilder form(Map<String, String> form) {
            this.form.clear();
            if ( form != null && form.size() > 0 ) {
                form.forEach((name, value) -> this.form.put(name, Collections.singleton(value)));
            }
            return this;
        }

        public RequestBuilder addForm(String name, String... values) {
            if ( values.length > 0 ) {
                Set<String> set = this.form.computeIfAbsent(name, k -> new LinkedHashSet<>());
                set.addAll(Arrays.asList(values));
            }
            return this;
        }

        public RequestBuilder removeForm(String name, String... values) {
            if ( values.length > 0 ) {
                Set<String> set = this.form.computeIfAbsent(name, k -> new LinkedHashSet<>());
                Arrays.asList(values).forEach(set::remove);
            } else {
                this.form.remove(name);
            }
            return this;
        }

        public RequestBuilder files(Map<String, File> files) {
            this.files.clear();
            if ( files != null ) {
                files.forEach((name, file) -> this.files.put(name, Collections.singleton(file)));
            }
            return this;
        }

        public RequestBuilder addFiles(String name, File... files) {
            if ( files.length > 0 ) {
                Set<File> set = this.files.computeIfAbsent(name, k -> new LinkedHashSet<>());
                set.addAll(Arrays.asList(files));
            }
            return this;
        }

        public RequestBuilder removeFiles(String name, File... files) {
            if ( files.length > 0 ) {
                Set<File> set = this.files.computeIfAbsent(name, k -> new LinkedHashSet<>());
                Arrays.asList(files).forEach(set::remove);
            } else {
                this.files.remove(name);
            }
            return this;
        }

        private okhttp3.Request.Builder builder() {
            HttpUrl httpUrl = HttpUrl.parse(url);
            if ( httpUrl == null ) {
                throw new IllegalArgumentException("Invalid url: " + url);
            }
            HttpUrl.Builder ub = httpUrl.newBuilder();
            buildParams(this.queries, ub::addEncodedQueryParameter);

            okhttp3.Headers.Builder hb = new okhttp3.Headers.Builder();
            headers.forEach(hb::add);

            return new okhttp3.Request.Builder()
                    .url(ub.build())
                    .headers(hb.build());
        }

        public Request delete() {
            return new Request(builder().delete());
        }

        public Request get() {
            return new Request(builder().get());
        }

        public Request form() {
            FormBody.Builder bb = new FormBody.Builder();
            buildParams(this.form, bb::add);
            return new Request(builder().post(bb.build()));
        }

        public Request json() {
            String json = body == null ? "" : JsonUtils.json(body);
            RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));
            return new Request(builder().post(requestBody));
        }

        public Request multipart() {
            MultipartBody.Builder mb = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);

            buildParams(form, mb::addFormDataPart);
            files.forEach((name, files) -> {
                files.forEach(file -> {
                    RequestBody body = RequestBody.Companion.create(file, MediaType.parse("application/octet-stream"));
                    mb.addFormDataPart(name, file.getName(), body);
                });
            });
            return new Request(builder().post(mb.build()));
        }
    }

    private static final CommonLogger logger = CommonLogger.getLogger(HttpClient.class);

    private static final Random random;

    static {
        random = new SecureRandom();
        random.setSeed(System.currentTimeMillis() ^ 0x47b82a696d8fdff9L);
    }

    private final OkHttpClient client;

    private HttpClient(Map<String, String> commonHeaders, long connectTimeout, long readTimeout, boolean useLogger) {
        this(commonHeaders, null, connectTimeout, readTimeout, useLogger);
    }

    private HttpClient(Map<String, String> commonHeaders, Proxy proxy, long connectTimeout, long readTimeout, boolean useLogger) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if ( commonHeaders != null ) {
            builder.addInterceptor(new CommonHeadersInterceptor(commonHeaders));
        }
        if ( proxy != null ) {
            builder.proxy(proxy);
        }
        if ( useLogger ) {
            builder.addInterceptor(new CommonLoggerInterceptor());
        }
        this.client = builder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .build();
    }

    private static String newTag() {
        byte[] data = new byte[12];
        random.nextBytes(data);
        return Base64.getUrlEncoder().encodeToString(data);
    }

    private <R> Response<R> execute(okhttp3.Request request, Function<ResponseBody, R> parser) throws IOException {
        try ( okhttp3.Response response = client.newCall(request).execute() ) {
            int code = response.code();
            R data;
            if ( response.isSuccessful() ) {
                data = parser.apply(response.body());
            } else {
                //throw new HttpException(response.code(), response.message());
                logger.warn("HTTP Error", "[{}]: code={}, message={}, url={}", request.tag(), code, response.message(), request.url().toString());
                data = null;
            }
            return new Response<>(code, data);
        }
    }

    private Response<Long> execute(okhttp3.Request request, final OutputStream out) throws IOException {
        return execute(request, (body) -> {
            if ( body == null ) {
                logger.warn("HTTP response error", "Empty response body");
                return 0L;
            }

            long result;
            try {
                InputStream in = body.byteStream();
                byte[] buffer = new byte[1024];
                int read;
                result = 0L;
                while ( (read = in.read(buffer)) > 0 ) {
                    out.write(buffer, 0, read);
                    result += read;
                }
            } catch ( IOException ex ) {
                logger.warn(ex, "HTTP response error", "Failed to write response body");
                result = -1L;
            }
            return result;
        });
    }

    public <RSP> Response<RSP> execute(Request request, TypeReference<RSP> type) throws IOException {
        return execute(request.request, (body) -> {
            RSP result = null;
            if ( body != null ) {
                try {
                    String text = body.string();
                    result = JsonUtils.parse(text, type);
                } catch ( IOException ex ) {
                    logger.warn(ex, "HTTP response error", "Failed to parse response body.");
                }
            } else {
                logger.warn("HTTP response error", "Empty response body");
            }
            return result;
        });
    }

    public Response<Long> execute(Request request, OutputStream out) throws IOException {
        return execute(request.request, out);
    }

    private static <E> void buildParams(Map<String, ? extends Collection<String>> params, BiFunction<String, String, E> func) {
        if ( params != null && params.size() > 0 ) {
            params.forEach((name, values) -> {
                if ( values.isEmpty() ) {
                    func.apply(name, "");
                } else if ( values.size() == 1 ) {
                    func.apply(name, values.iterator().next());
                } else {
                    values.forEach(value -> func.apply(name, value));
                }
            });
        }
    }

    public <T> Response<T> get(String url, Map<String, String> query, TypeReference<T> type) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .get();
        return execute(request, type);
    }

    public <T> Response<T> delete(String url, Map<String, String> query, TypeReference<T> type) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .delete();
        return execute(request, type);
    }

    public <T> Response<T> form(String url, Map<String, String> query, Map<String, String> params, TypeReference<T> type) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .form(params)
                .form();
        return execute(request, type);
    }

    public <T> Response<T> json(String url, Map<String, String> query, Object data, TypeReference<T> type) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .body(data)
                .json();
        return execute(request, type);
    }

    public <T> Response<T> multipart(String url, Map<String, String> query, Map<String, File> files, Map<String, String> params, TypeReference<T> type) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .files(files)
                .form(params)
                .multipart();
        return execute(request, type);
    }

    public Response<Long> download(String url, Map<String, String> query, OutputStream output) throws IOException {
        Request request = new RequestBuilder()
                .url(url)
                .query(query)
                .get();
        return execute(request, output);
    }

    public Response<Long> download(String url, OutputStream output) throws IOException {
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        return execute(request, output);
    }
}
