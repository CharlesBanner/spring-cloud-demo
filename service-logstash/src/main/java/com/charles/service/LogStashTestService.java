package com.charles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-04-30
 * Time: 16:22
 */
@Service
public class LogStashTestService {

    private static final Logger logger = LoggerFactory.getLogger(LogStashTestService.class);
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(5));

    @PostConstruct
    public void testLogStash(){
        while (true){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    LogStashTestService obj = new LogStashTestService();
                    try{
                        obj.divide();
                    }catch(ArithmeticException ex){
                        logger.error("大家好111!", ex);
                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void divide(){
        int i = 10 /0;
    }


}
