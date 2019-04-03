package pers.zyc.common.test;

import pers.zyc.common.NumberUtils;

/**
 * @author YanchaoZhang
 * @date 2018/8/1 18:10
 */
public class RandomsTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String uuid32 = NumberUtils.getUUID32();
            String uuid24 = NumberUtils.getUUID24();
            String randomHex = NumberUtils.getRandomHex(32);
            String randomAlphanumeric = NumberUtils.getRandomAlphanumeric(32);
//            System.out.println(uuid32);
//            System.out.println(uuid24);
//            System.out.println(randomHex);
//            System.out.println(randomAlphanumeric);
//            System.out.println("--------------------------");
        }
        System.out.println(System.currentTimeMillis()-start+"millis");
    }

}
