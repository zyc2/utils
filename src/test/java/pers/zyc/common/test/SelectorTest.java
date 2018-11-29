package pers.zyc.common.test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author YanchaoZhang
 * @date 2018/11/13 10:19
 */
public class SelectorTest {

    @Test
    public void a() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(80));
        Selector selector = Selector.open();
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        System.out.println(Integer.toBinaryString(selectionKey.interestOps()));
        selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_READ);
        System.out.println(Integer.toBinaryString(selectionKey.interestOps()));
        selectionKey.isReadable();
        int select = selector.select();
//        int a = -1;
//        System.out.printf("%b, %o", a, a);
    }

    @Test
    public void t() {
        System.out.println(new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)));
    }

}
