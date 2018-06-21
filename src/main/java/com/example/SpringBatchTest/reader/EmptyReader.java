package com.example.SpringBatchTest.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class EmptyReader implements ItemReader<String> {
    private Character ch = 'A';

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        synchronized (ch) {
            System.out.println(Thread.currentThread().getName()+" read:" + ch);
            if (ch <= 'Z') {
                return String.valueOf(ch++);
            } else {
                return null;
            }
        }
    }

}
