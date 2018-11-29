package pers.zyc.learn;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author YanchaoZhang
 * @date 2018/11/29 14:01
 */
public class ShellSort {

    public static void main(String[] args) {
//        int[] ints = Stream.generate(() -> new Random().nextInt(100)).limit(100).mapToInt(value -> value).peek(value -> System.out.print(value + ",")).toArray();
////        int[] ints = Stream.of(9, 8, 7, 6, 5, 4, 3, 2, 1).mapToInt(value -> value).peek(value -> System.out.print(value + ",")).toArray();
//        System.out.println();
//        shellSort(ints);
//        Arrays.stream(ints).forEach(value -> System.out.print(value + ","));
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            test();
        }
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

    }

    private static void test() {
        Arrays.sort(Stream.generate(() -> new Random().nextInt(10000000)).limit(10000000).mapToInt(value -> value).toArray());
//        shellSort1(Stream.generate(() -> new Random().nextInt(10000000)).limit(10000000).mapToInt(value -> value).toArray());
    }

    private static void insertSort(int[] ints) {
        if (ints == null) return;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ints[j] > ints[i]) {
                    int temp = ints[i];
                    for (int k = i; k > j; ) {
                        ints[k] = ints[--k];
                    }
                    ints[j] = temp;
                    break;
                }
            }
        }

    }

    private static void shellSort(int[] ints) {
        if (ints == null) return;
        int temp;
        int length = ints.length;
        for (int step = length / 2; step >= 1; step = step / 2) {
            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (ints[j] > ints[i]) {
                        temp = ints[i];
                        for (int k = i; k > j; ) {
                            ints[k] = ints[--k];
                        }
                        ints[j] = temp;
                        break;
                    }
                }
//                Arrays.stream(ints).forEach(value -> System.out.print(value + ","));
//                System.out.println();
            }
        }
//        System.out.println();
    }

    private static void shellSort1(int[] list) {
        int gap = list.length / 2;
        while (1 <= gap) {
            // 把距离为 gap 的元素编为一个组，扫描所有组
            for (int i = gap; i < list.length; i++) {
                int j;
                int temp = list[i];
                // 对距离为 gap 的元素组进行排序
                for (j = i - gap; j >= 0 && temp < list[j]; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }
            gap = gap / 2; // 减小增量
        }
    }

}
