package io.ziyi.m3u8;

import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.ParseException;
import com.iheartradio.m3u8.ParsingMode;
import com.iheartradio.m3u8.PlaylistException;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.MasterPlaylist;
import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.PlaylistType;
import com.iheartradio.m3u8.data.TrackData;
import io.ziyi.spider.http.HttpClient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void parse(HttpClient client, InputStream in, OutputStream out) throws Exception {

        PlaylistParser parser = new PlaylistParser(in, Format.EXT_M3U, Encoding.UTF_8, ParsingMode.LENIENT);
        Playlist playlist = parser.parse();

        boolean hasMediaPlaylist = playlist.hasMediaPlaylist();
        if ( hasMediaPlaylist ) {
            MediaPlaylist mediaPlaylist = playlist.getMediaPlaylist();

            List<TrackData> tracks = mediaPlaylist.getTracks();
            System.out.println("Found tracks: " + tracks.size());

            int sn = mediaPlaylist.getMediaSequenceNumber();
            for ( TrackData track : tracks ) {

                System.out.printf("Downloading track[%d]: %s\n", sn, track.getUri());
                byte[] data = null;
                int retry = 0;
                boolean success;
                Exception error = null;
                do {
                    try {
                        ByteArrayOutputStream bout = new ByteArrayOutputStream();
                        download(client, track.getUri(), bout);
                        data = bout.toByteArray();
                        success = true;
                    } catch ( Exception ex ) {
                        error = ex;
                        retry++;
                        success = false;
                    }
                } while ( !success && retry < 3 );
                if ( !success ) {
                    throw error;
                }

                if ( track.isEncrypted() ) {
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    EncryptionData encryptionData = track.getEncryptionData();
                    String uri = encryptionData.getUri();
                    download(client, uri, bout);
                    SecretKey key = new SecretKeySpec(bout.toByteArray(), "AES");
                    byte[] ivd = loadIV(encryptionData.getInitializationVector());
                    IvParameterSpec iv = new IvParameterSpec(ivd);
                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
                    try {
                        cipher.init(Cipher.DECRYPT_MODE, key, iv);
                    } catch ( Exception ex ) {
                        System.out.println("Invalid algorithm: iv=" + Hex.toHexString(ivd));
                        throw ex;
                    }
                    data = cipher.doFinal(data);
                }

                out.write(data);

                System.out.printf("Track downloaded [%d]\n", sn);
                sn++;

                int duration = (int)(track.getTrackInfo().duration);
                System.out.printf("Sleep %d\n", duration);
                Thread.sleep(duration * 1000L);
            }
        }
    }

    private static final int AES_KEY_SIZE = 16;

    private static byte[] loadIV(List<Byte> iv) {
        if ( iv == null ) {
            return new byte[16];
        }
        if ( iv.size() != AES_KEY_SIZE * 2 ) {
            throw new IllegalArgumentException("Invalid IV size");
        }
        byte[] data = new byte[AES_KEY_SIZE];
        for ( int i = 0; i < AES_KEY_SIZE; i++ ) {
            Byte b1 = iv.get(i * 2);
            Byte b2 = iv.get(i * 2 + 1);
            data[i] = (byte) ((b1 << 4) | ( b2 & 0x0F ));
        }
        return data;
    }

    private static long download(HttpClient client, String uri, OutputStream out) throws IOException {
        long result = -1;
        try ( ByteArrayOutputStream bout = new ByteArrayOutputStream() ) {
            HttpClient.Response<Long> response = client.download(uri, bout);
            if ( response.ok() ) {
                out.write(bout.toByteArray());
                result = response.data();
            } else {
                throw new IOException("HTTP " + response.code());
            }
        }
        return result;
    }

}
