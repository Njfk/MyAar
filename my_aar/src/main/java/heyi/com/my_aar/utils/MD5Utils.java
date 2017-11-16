package heyi.com.my_aar.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sev-14 on 2016/10/10.
 */

public class MD5Utils {
    private static final char[] HEX_EXCHANGE = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String getMD5Str(String str) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();

    }
    public static String toHexString(byte[] d, int s, int n) {
        // TODO 转换为十六进制形式的字符串
        final char[] ret = new char[n * 2];
        final int e = s + n;
        int x = 0;
        for (int i = s; i < e; ++i) {
            final byte v = d[i];
            ret[x++] = HEX_EXCHANGE[0x0F & (v >> 4)];
            ret[x++] = HEX_EXCHANGE[0x0F & v];
        }
        return new String(ret);
    }
}
