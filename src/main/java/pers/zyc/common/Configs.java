package pers.zyc.common;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YanchaoZhang
 * @date 2018/11/29 10:37
 */
public class Configs {
    private static Properties properties = new Properties();
    private static Lock lock = new ReentrantLock();
    private static long lastTime = 0;
    private static final long step = 10 * 60 * 1000;
    private static final String resourceName = "config.properties";

    private static void loadProperties() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastTime < step) return;
        try {
            lock.lock();
            if (currentTimeMillis - lastTime < step) return;
            lastTime = currentTimeMillis;
            properties.load(Configs.class.getResourceAsStream(resourceName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static String getString(String key) {
        loadProperties();
        return properties.getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        String string = getString(key);
        return string == null ? defaultValue : string;
    }
}
