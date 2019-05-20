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


    /**
     * 例：105000<br>
     * 十万五千
     *
     * @param org 原始数字
     * @return 中文计数法
     */
    public static String convertToChineseInteger(int org) {
        return convert(org, NUMS, true, false);
    }
    /**
     * 例：101000<br>
     * 十万零五千
     *
     * @param org 原始数字
     * @return 中文计数法
     */
    public static String convertToChineseIntegerAdd0(int org) {
        return convert(org, NUMS, true, true);
    }
    /**
     * 例：101000<br>
     * 壹拾万伍仟
     *
     * @param org 原始数字
     * @return 中文计数法
     */
    public static String convertToChineseIntegerPlus(int org) {
        return convert(org, PLUS, false, false);
    }
    /**
     * 例：101000<br>
     * 壹拾万零伍仟
     *
     * @param org 原始数字
     * @return 中文计数法
     */
    public static String convertToChineseIntegerPlusAdd0(int org) {
        return convert(org, PLUS, false, true);
    }


    private static final char[] NUMS = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '百', '千', '万', '亿'};
    private static final char[] PLUS = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖', '拾', '佰', '仟', '万', '亿'};

    /**
     * 转换数字到中文计数法
     * @param org 原始数字
     * @param delete1 是否删除一十开头的一
     * @param add0 亿万位为零是否提示
     * @return 中文计数法
     */
    private static String convert(int org, char[] arr, boolean delete1, boolean add0) {
        if (org == 0) return "零";
        StringBuilder sb = new StringBuilder();
        int revert = org < 0 ? -1 : 1;
        int i = 0;
        int last = 0;
        int cursor = -1;
        do {
            int first = i % 4;
            int num = org % 10 * revert;
            boolean step4 = i == 4 || i == 8;
            if (step4) {
                if (cursor == sb.length()) {
                    sb.deleteCharAt(cursor - 1);
                }
                if (add0 && num == 0 && last != 0) {
                    sb.append(arr[0]);
                }
                sb.append(arr[12 + i / 4]);
                cursor = sb.length();
            }
            if (num != 0 && first > 0) {
                sb.append(arr[9 + first]);
            }
            if (num != 0 || (last != 0 && !step4)) {
                sb.append(arr[num]);
            }
            last = num;
            i++;
        } while ((org /= 10) != 0);
        last = sb.length() - 1;
        if (delete1 && last > 1 && sb.charAt(last) == arr[1] && sb.charAt(last - 1) == arr[10]) {
            sb.deleteCharAt(last);
        }
        if (revert == -1) {
            sb.append('负');
        }
        return sb.reverse().toString();
    }


}
