package com.openwjk.central;

import com.openwjk.central.commons.factory.CentralThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author wangjunkai
 * @description
 * @date 2024/9/9 16:08
 */
@Slf4j
public class TransmittableThreadTest {
    @Test
    void name() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 30L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3000), CentralThreadFactory.create("test", false), new ThreadPoolExecutor.CallerRunsPolicy());

//        executor.submit(() -> {
//            System.out.println(Thread.currentThread().getId() + "---" + ThreadLocalUtil.getTest());
//        });
//        executor.submit(() -> {
//            System.out.println(Thread.currentThread().getId() + "---" + ThreadLocalUtil.getTest());
//        });
//
//        System.out.println(Thread.currentThread().getId() + "---" + ThreadLocalUtil.getTest());

    }
}
