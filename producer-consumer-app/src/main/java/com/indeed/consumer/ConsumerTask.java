package com.indeed.consumer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class ConsumerTask implements Runnable{

    BlockingQueue<Integer> blockingQueue;

    @Override
    public void run() {
        UUID id = UUID.randomUUID();
        int consumedItems = 0;
        try {
            while (true) {
                Integer value = blockingQueue.poll(5, TimeUnit.SECONDS);
                if (value == null) {
                    log.info("Consumer [{}] No more to consume and will end", id);
                    break;
                }
                log.info("Consumer [{}] took {}  ", id, value );
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
