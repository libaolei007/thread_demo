package com.lbl.thread.t2.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * 
 * @description: spring配置类
 * @author: libl  
 * @date: 2019年4月10日
 */
@Configuration
@ComponentScan("com.lbl.thread.t2.spring;")
@EnableAsync
public class Config {

}
