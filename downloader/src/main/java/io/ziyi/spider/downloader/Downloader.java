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

    private static byte[] readFile(String file) throws IOException {
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

        File target = new File("D:\\dev\\spider\\downloader\\data\\video\\video.mp4");
        try ( FileOutputStream fout = new FileOutputStream(target) ) {
            byte[] data = readFile("D:\\dev\\spider\\downloader\\data\\video\\.dash");
            fout.write(data);
            for ( int i = 1; i < 926; i++ ) {
                data = readFile("D:\\dev\\spider\\downloader\\data\\video\\" + i + ".mp4");
                fout.write(data);
            }
        }

    }

}
