package pers.zyc.common;

import java.util.Random;
import java.util.UUID;

/**
 * @author YanchaoZhang
 * @date 2018/8/1 17:09
 */
public class NumberUtils {

    private NumberUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Random RANDOM = new Random();

    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
            , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            , 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(hex[(b >> 4) & 0xf]).append(hex[b & 0xf]);
        }
        return sb.toString();
    }


    public static String getUUID32() {
        UUID uuid = UUID.randomUUID();
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        return (digits(mostSigBits >> 32, 8) +
                digits(mostSigBits >> 16, 4) +
                digits(mostSigBits, 4) +
                digits(leastSigBits >> 48, 4) +
                digits(leastSigBits, 12));
    }

    public static String getUUID24() {
        String uuid32 = getUUID32();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; ) {
            int org = Integer.parseInt(uuid32.substring(i, i += 4), 16);
            int cur = sb.length();
            while (org > 0) {
                sb.insert(cur, table[org % 62]);
                org = org / 62;
            }
            while (sb.length() < cur + 3) {
                sb.insert(cur, '0');
            }
        }
        return sb.toString();
    }

    public static String getRandomHex(int length) {
        return randomStringFromCharSet(length, hex);
    }

    public static String getRandomAlphanumeric(int length) {
        return randomStringFromCharSet(length, table);
    }

    /**
     * 抄的这个{@link UUID#digits(long, int)}
     */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }


    private static String randomStringFromCharSet(int length, char[] set) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(set[RANDOM.nextInt(set.length)]);
        }
        return sb.toString();
    }


}
