package pers.zyc.common.test;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YanchaoZhang
 * @date 2018/11/8 17:38
 */
public class StreamTest {

    class RcResource {
        private String url;

        RcResource() {
            this.url = (System.nanoTime() & 1) == 1 ? "System" : null;
        }

        String getUrl() {
            return url;
        }
    }

    @Test
    public void test() {
        List<RcResource> collect = Stream.generate(RcResource::new).limit(100).collect(Collectors.toList());
        List<RcResource> system = collect.stream().filter(rcResource -> Optional.ofNullable(rcResource.getUrl()).orElse("").equals("System")).collect(Collectors.toList());
        System.out.println(collect.size());
        System.out.println(system.size());
    }
}
