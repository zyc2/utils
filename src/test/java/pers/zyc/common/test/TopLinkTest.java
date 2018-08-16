package pers.zyc.common.test;

import pers.zyc.common.TopLink;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author YanchaoZhang
 * @date 2018/8/16 17:40
 */
public class TopLinkTest {

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(5);
            list.add(r);
            System.out.print(r + ",");
        }
        Integer topRankValue = TopLink.topRankValue(list, 3);
        System.out.println("\n" + topRankValue);
        TopLink<Integer> topLink = TopLink.topRankLink(list, 10);
        while (topLink != null) {
            System.out.print(topLink.value + ",");
            topLink = topLink.next;
        }
        Integer bottomRankValue = TopLink.bottomRankValue(list, 3);
        System.out.println("\n" + bottomRankValue);
        TopLink<Integer> bottomLink = TopLink.bottomRankLink(list, 10);
        while (bottomLink != null) {
            System.out.print(bottomLink.value + ",");
            bottomLink = bottomLink.next;
        }
    }
}
