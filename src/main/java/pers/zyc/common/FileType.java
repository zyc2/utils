package pers.zyc.common;

import java.util.Comparator;
import java.util.HashMap;

/**
 * @author YanchaoZhang
 * @date 2018/11/6 11:07
 * @see <a href='https://blog.csdn.net/fengqilove520/article/details/53303931'>类型照这个基础更改</a>
 */
public class FileType {
    private final static HashMap<String, String> typeMap = new HashMap<>();
    private final static int[] len;
    public final static int minLength;

    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getType(byte[] src) {
        if (src == null || src.length < minLength) return null;
        char[] chars = new char[len[0]];
        int count = 0;
        for (byte b : src) {
            int i1 = b & 0x0f;
            chars[count++] = hex[(b & 0xf0) >> 4];
            chars[count++] = hex[i1];
            if (count >= len[0]) break;
        }
        for (int i : len) {
            String s = typeMap.get(new String(chars, 0, i));
            if (s != null) return s;
        }
        return null;
    }

    static {
        typeMap.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
        typeMap.put("89504e470d0a1a0a0000", "png"); //PNG (png)
        typeMap.put("47494638396126026f01", "gif"); //GIF (gif)
        typeMap.put("49492a00227105008037", "tif"); //TIFF (tif)
        typeMap.put("424d228c010000000000", "bmp"); //16色位图(bmp)
        typeMap.put("424d8240090000000000", "bmp"); //24位位图(bmp)
        typeMap.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
        typeMap.put("41433130313500000000", "dwg"); //CAD (dwg)
        typeMap.put("3c21444f435459504520", "html"); //HTML (html)
        typeMap.put("3c21646f637479706520", "htm"); //HTM (htm)
        typeMap.put("48544d4c207b0d0a0942", "css"); //css
        typeMap.put("696b2e71623d696b2e71", "js"); //js
        typeMap.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
        typeMap.put("38425053000100000000", "psd"); //Photoshop (psd)
        typeMap.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
        typeMap.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
        typeMap.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图
        typeMap.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
        typeMap.put("252150532D41646F6265", "ps");
        typeMap.put("255044462d312e350d0a", "pdf"); //Adobe Acrobat (pdf)
        typeMap.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
        typeMap.put("464c5601050000000900", "flv"); //flv与f4v相同
        typeMap.put("00000020667479706d70", "mp4");
        typeMap.put("49443303000000002176", "mp3");
        typeMap.put("000001ba210001000180", "mpg"); //
        typeMap.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
        typeMap.put("52494646e27807005741", "wav"); //Wave (wav)
        typeMap.put("52494646d07d60074156", "avi");
        typeMap.put("4d546864000000060001", "mid"); //MIDI (mid)
        typeMap.put("504b0304140000000800", "zip");
        typeMap.put("526172211a0700cf9073", "rar");
        typeMap.put("235468697320636f6e66", "ini");
        typeMap.put("504b03040a0000000000", "jar");
        typeMap.put("4d5a9000030000000400", "exe");//可执行文件
        typeMap.put("3c25402070616765206c", "jsp");//jsp文件
        typeMap.put("4d616e69666573742d56", "mf");//MF文件
        typeMap.put("3c3f786d6c2076657273", "xml");//xml文件
        typeMap.put("494e5345525420494e54", "sql");//xml文件
        typeMap.put("7061636b616765207065", "java");//java文件
        typeMap.put("406563686f206f66660d", "bat");//bat文件
        typeMap.put("1f8b0800000000000000", "gz");//gz文件
        typeMap.put("6c6f67346a2e726f6f74", "properties");//bat文件
        typeMap.put("cafebabe0000002e0041", "class");//bat文件
        typeMap.put("49545346030000006000", "chm");//bat文件
        typeMap.put("04000000010000001300", "mxp");//bat文件
        typeMap.put("504b0304140006000800", "docx");//docx文件
        typeMap.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
        typeMap.put("6431303a637265617465", "torrent");
        typeMap.put("3c68746d6c20786d6c6e", "htm");//猎聘、智联简历。htm
        typeMap.put("46726f6d3a3cd3c920cd", "mht");//51job简历。mht
        typeMap.put("D0CF11E0".toLowerCase(), "xls");//excel2003版本文件
        typeMap.put("504B0304".toLowerCase(), "xlsx");//excel2007以上版本文件
        typeMap.put("6D6F6F76".toLowerCase(), "mov"); //Quicktime (mov)
        typeMap.put("FF575043".toLowerCase(), "wpd"); //WordPerfect (wpd)
        typeMap.put("CFAD12FEC5FD746F".toLowerCase(), "dbx"); //Outlook Express (dbx)
        typeMap.put("2142444E".toLowerCase(), "pst"); //Outlook (pst)
        typeMap.put("AC9EBD8F".toLowerCase(), "qdf"); //Quicken (qdf)
        typeMap.put("E3828596".toLowerCase(), "pwl"); //Windows Password (pwl)
        typeMap.put("2E7261FD".toLowerCase(), "ram"); //Real Audio (ram)
        typeMap.keySet().forEach(s -> s = s.toLowerCase());
        len = typeMap.keySet().stream().map(String::length).distinct().sorted(Comparator.reverseOrder()).mapToInt(v -> v).toArray();
        minLength = len[0] / 2;
    }

}
