package io.ziyi.m3u8;

import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.ParseException;
import com.iheartradio.m3u8.ParseExceptionType;
import com.iheartradio.m3u8.ParsingMode;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.MasterPlaylist;
import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.PlaylistType;
import com.iheartradio.m3u8.data.TrackData;
import com.iheartradio.m3u8.data.TrackInfo;
import io.ziyi.spider.http.HttpClient;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserMain {

    public static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("^0[x|X]([0-9A-F]+)$");

    public static List<Byte> parseHexadecimal(String hexString, String tag) {
        final List<Byte> bytes = new ArrayList<Byte>();
        final Matcher matcher = HEXADECIMAL_PATTERN.matcher(hexString.toUpperCase(Locale.US));

        if (matcher.matches()) {
            String valueString = matcher.group(1);

            for (char c : valueString.toCharArray()) {
                bytes.add(hexCharToByte(c));
            }

            return bytes;
        } else {
            System.out.println("Failed to parse hex: " + hexString);
            return null;
        }
    }

    private static byte hexCharToByte(char hex) {
        if (hex >= 'A') {
            return (byte) ((hex & 0xF) + 9);
        } else {
            return (byte) (hex & 0xF);
        }
    }

    private static final int AES_KEY_SIZE = 16;

    private static byte[] toBytes(List<Byte> iv) {

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

    public static void main(String[] args) throws Exception {
/*
        String[] ss = {
                "0x00000000000000000000000000000003",
                "0x0000000000000000000000000000000C",
                "0x00000000000000000000000000000012",
                "0x0000000000000000000000000000001A",
                "0x00000000000000000000000000000026",
                "0x0000000000000000000000000000003C",
                "0x000000000000000000000000000000A8",
                "0x000000000000000000000000000000DF",
                "0x00000000000000000000000000000574"
        };

        for ( String s : ss ) {
            List<Byte> bytes = parseHexadecimal(s, "");
            byte[] f = toBytes(bytes);
            System.out.println(s + ": " + bytes.size() + ": " + Hex.toHexString(f));
        }

        if ( System.currentTimeMillis() > 0L ) {
            return;
        }
*/
        HttpClient client = new HttpClient.ClientBuilder()
                .proxy(InetAddress.getByName("127.0.0.1"), 7890)
                .connectTimeout(30L)
                .readTimeout(300L)
                .build();

        FileInputStream fin = new FileInputStream("D:\\mc\\test.m3u8");

        try ( FileOutputStream fout = new FileOutputStream("D:\\mc\\f1.mp4") ) {
            Parser.parse(client, fin, fout);


            fin.close();
        }
    }
}
