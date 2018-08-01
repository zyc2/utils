package pers.zyc.common.test;

import pers.zyc.common.Randoms;

/**
 * @author YanchaoZhang
 * @date 2018/8/1 18:10
 */
public class RandomsTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String uuid32 = Randoms.getUUID32();
            String uuid24 = Randoms.getUUID24();
            String randomHex = Randoms.getRandomHex(32);
            String randomAlphanumeric = Randoms.getRandomAlphanumeric(32);
//            System.out.println(uuid32);
//            System.out.println(uuid24);
//            System.out.println(randomHex);
//            System.out.println(randomAlphanumeric);
//            System.out.println("--------------------------");
        }
        System.out.println(System.currentTimeMillis()-start+"millis");
    }

}
