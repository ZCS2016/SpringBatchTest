package com.example.SpringBatchTest.config;

import com.example.SpringBatchTest.processor.EmptyProcessor;
import com.example.SpringBatchTest.reader.EmptyReader;
import com.example.SpringBatchTest.writer.EmptyWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.SimpleJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobRepository jobRepository;

    @Bean
    public TaskExecutor getTaskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    //////////////////////////////////////////////////////////////////////
    public EmptyReader getEmptyReader(){
        return new EmptyReader();
    }

    public EmptyProcessor getEmptyProcessor(){
        return new EmptyProcessor();
    }

    public EmptyWriter getEmptyWriter(){
        return new EmptyWriter();
    }

    @Bean
    public Step emptyJobStep(TaskExecutor taskExecutor){
        return stepBuilderFactory.get("emptyJobStep")
                .<String,String>chunk(1)
                .reader(getEmptyReader())
                .processor(getEmptyProcessor())
                .writer(getEmptyWriter())
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean
    public Job categoriesJob(Step emptyJobStep){
        return jobBuilderFactory.get("emptyJob")
                .incrementer(new RunIdIncrementer())
                .flow(emptyJobStep)
                .end()
                .build();
    }

    //////////////////////////////////////////////////////////////////////
}
