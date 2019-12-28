package pers.zyc.common.safe;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

/**
 * @author zyc
 * Date:2019/11/26 8:05 PM
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SafeBox<T> {
    private final T value;

    public static <T> SafeBox<T> of(Callable<T> callable) {
        try {

            return new SafeBox<>(callable.call());
        } catch (Exception ignore) {
            return new SafeBox<>(null);
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }
}
