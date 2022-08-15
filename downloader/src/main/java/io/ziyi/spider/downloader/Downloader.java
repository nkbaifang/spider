package io.ziyi.spider.downloader;

import io.ziyi.spider.http.HttpClient;
import io.ziyi.spider.http.HttpException;
import io.ziyi.spider.util.MapUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.Map;

public class Downloader {

    private static byte[] readFile(File file) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try ( FileInputStream fin = new FileInputStream(file) ) {
            byte[] buffer = new byte[1024];
            int read;
            while ( (read = fin.read(buffer)) > 0L ) {
                bout.write(buffer, 0, read);
            }
        }
        return bout.toByteArray();
    }

    public static boolean download(HttpClient client, String url, File target) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        boolean result = false;
        HttpClient.Response<Long> response = client.download(url, bout);
        if ( response.ok() ) {
            long length = response.data();
            if ( length > 0L ) {
                try ( FileOutputStream fout = new FileOutputStream(target) ) {
                    fout.write(bout.toByteArray());
                    result = true;
                }
            }
        }
        System.out.printf("Download result: url=%s, code=%d, result=%s\n", url, response.code(), result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> headers = MapUtils.singleMap("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");

        HttpClient.ClientBuilder builder = new HttpClient.ClientBuilder()
                .headers(headers)
                .proxy(InetAddress.getByName("127.0.0.1"), 7890)
                .connectTimeout(20L)
                .readTimeout(30L)
                .useLogger(false);
        HttpClient client = builder.build();

        String url = "https://za-jnbteraco-02-napafrica.cdn.showmax.com/u/b1b79945-8e1d-46b9-9923-caed32b769ba/14378f1d-845f-42af-a326-9ca07936d7ca.ism";

        File ad = new File("C:\\Users\\ASUS\\Desktop\\showmax\\mpd\\audio\\.dash");
        download(client, url + "/dash/14378f1d-845f-42af-a326-9ca07936d7ca-audio_1=64000.dash", ad);

        int num = 1;
        boolean ok = true;
        while ( ok ) {
            File m4s = new File("C:\\Users\\ASUS\\Desktop\\showmax\\mpd\\audio\\" + num + ".m4s");
            ok = download(client, url + "/dash/14378f1d-845f-42af-a326-9ca07936d7ca-audio_1=64000-" + num + ".m4s", m4s);
            System.out.println("Download audio: num=" + num + ", ok=" + ok);
            num++;
        }

        File vd = new File("C:\\Users\\ASUS\\Desktop\\showmax\\mpd\\video\\.dash");
        download(client, url + "/dash/14378f1d-845f-42af-a326-9ca07936d7ca-video=1067196.dash", vd);

        num = 1;
        ok = true;
        while ( ok ) {
            File m4s = new File("C:\\Users\\ASUS\\Desktop\\showmax\\mpd\\video\\" + num + ".m4s");
            ok = download(client, url + "/dash/14378f1d-845f-42af-a326-9ca07936d7ca-video=1067196-" + num + ".m4s", m4s);
            System.out.println("Download audio: num=" + num + ", ok=" + ok);
            num++;
        }

    }

}
