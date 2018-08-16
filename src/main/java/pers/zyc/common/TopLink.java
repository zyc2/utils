package pers.zyc.common;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author YanchaoZhang
 * @date 2018/8/16 12:52
 */
public class TopLink<C extends Comparable<? super C>> {
    public C value;
    public TopLink<C> next;

    private TopLink(C value) {
        this.value = value;
    }

    private static <T extends Comparable<? super T>> TopLink<T> getRankLink(Collection<T> collection, int rank, boolean invert) {
        if (rank < 1 || collection == null || collection.size() == 0 || rank > collection.size()) {
            throw new IllegalArgumentException("集合为空或rank值不合理");
        }
        int count = 1;
        Iterator<T> iterator = collection.iterator();
        TopLink<T> head = new TopLink<>(iterator.next());
        while (iterator.hasNext()) {
            T comparable = iterator.next();
            if (comparable.compareTo(head.value) > 0 ^ invert) {
                TopLink<T> temp = head;
                while (temp.next != null && comparable.compareTo(temp.next.value) > 0 ^ invert) {
                    temp = temp.next;
                }
                TopLink<T> topLink = new TopLink<>(comparable);
                topLink.next = temp.next;
                temp.next = topLink;
                if (count >= rank) {
                    head = head.next;
                }
            } else if (count < rank) {
                TopLink<T> topLink = new TopLink<>(comparable);
                topLink.next = head;
                head = topLink;
            }
            count++;
        }
        return head;
    }

    public static <T extends Comparable<? super T>> T topRankValue(Collection<T> collection, int rank) {
        return topRankLink(collection, rank).value;
    }

    public static <T extends Comparable<? super T>> T bottomRankValue(Collection<T> collection, int rank) {
        return bottomRankLink(collection, rank).value;
    }

    public static <T extends Comparable<? super T>> TopLink<T> topRankLink(Collection<T> collection, int rank) {
        return getRankLink(collection, rank, false);
    }

    public static <T extends Comparable<? super T>> TopLink<T> bottomRankLink(Collection<T> collection, int rank) {
        return getRankLink(collection, rank, true);
    }

}
