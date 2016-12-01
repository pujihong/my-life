package com.pjh.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis扫描接口
 *
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早     经测试还是比MybatisConfig执行的早 所以这注解可以不加
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        //接口路径
        mapperScannerConfigurer.setBasePackage("com.pjh.dao");
        return mapperScannerConfigurer;
    }
}

