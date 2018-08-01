package pers.zyc.common.test;

import pers.zyc.common.PasswordTool;

/**
 * @author YanchaoZhang
 * @date 2018/8/1 18:51
 */
public class PasswordToolTest {
    public static void main(String[] args) {
        PasswordTool passwordTool = new PasswordTool(8);
        String password="askdowerkm;asdkansdlna-sa=";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String encode = passwordTool.encode(password);
            boolean matches = passwordTool.matches(password, encode);
//            System.out.println(encode);
//            System.out.println(matches);
        }
        System.out.println(System.currentTimeMillis()-start+"millis");
    }
}
