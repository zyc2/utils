package pers.zyc.common;

/**
 * string工具类
 *
 * @author YanchaoZhang
 * @date 2018/11/5 18:32
 */
public class Strings {

    /**
     * 字符串长度检校,有null直接返回false
     *
     * @param vars string,int,string,int...以此类推
     * @return boolean
     */
    public static boolean limit(Object... vars) {
        int len;
        if (vars == null || (len = vars.length) < 2 || (len & 1) == 1) return false;
        for (int i = 0; i < len; ) {
            Object str = vars[i++];
            Object lim = vars[i++];
            if (str instanceof String && lim instanceof Integer) {
                if (((String) str).length() > (int) lim) return false;
            } else return false;
        }
        return true;
    }

    /**
     * 截取部分字符串
     *
     * @param s   字符串
     * @param max 最大值
     * @return 新字符串
     */
    public static String truncate(String s, int max) {
        if (s == null) return "";
        int len = s.length();
        return s.substring(0, len > max ? max : len);
    }

    /**
     * 根据长度获取自适应索引
     *
     * @param number 索引
     * @param size   大小
     * @return sub函数可用索引
     */
    public static int fixIndexBySize(int number, int size) {
        if (number > size) return size - 1;
        if (number < 1) number = size + number;
        if (number < 0) return 0;
        return number - 1;
    }

}
