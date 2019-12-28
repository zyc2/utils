package pers.zyc.common.collection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author zyc
 * Date:9/19/2019 13:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionTools {

    @SafeVarargs
    public static <E> Stream<E> stream(Collection<E> collection, E... extra) {
        if (collection == null) {
            if (extra == null) {
                return Stream.empty();
            } else {
                return Stream.of(extra);
            }
        } else if (extra != null) {
            collection.addAll(Arrays.asList(extra));
        }
        return collection.stream();
    }

    @SafeVarargs
    public static <E> Stream<E> streamNonNull(Collection<E> collection, E... extra) {
        return stream(collection, extra).filter(Objects::nonNull);
    }
}