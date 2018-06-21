package com.example.SpringBatchTest.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class EmptyWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {

    }
}
