package com.indeed.producer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class ProducerTask implements Runnable{
    private static final int MAX_ITEMS = 50;

    BlockingQueue<Integer> blockingQueue;

    @Override
    public void run() {
        int producedItems = 0;
        try {
            int value = 0;
            while (producedItems++ <= MAX_ITEMS) {
                blockingQueue.put(value);
                log.info("Produced {} from total of {} " ,value, MAX_ITEMS);
                value++;
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
