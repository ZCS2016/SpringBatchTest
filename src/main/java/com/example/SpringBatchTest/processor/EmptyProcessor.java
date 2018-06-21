package com.example.SpringBatchTest.processor;

import org.springframework.batch.item.ItemProcessor;

public class EmptyProcessor implements ItemProcessor<String,String> {
    @Override
    public String process(String s) throws Exception {
        System.out.println(Thread.currentThread().getName()+" process:"+s);
        return s;
    }
}
