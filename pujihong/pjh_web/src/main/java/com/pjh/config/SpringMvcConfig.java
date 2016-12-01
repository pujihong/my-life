package com.pjh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
  @Bean
  public ViewResolver viewResolver() {
	  InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	  viewResolver.setPrefix("/WEB-INF/view/");
	  viewResolver.setSuffix(".jsp");
	  return viewResolver;
  }
  @Bean 
  public CharacterEncodingFilter characterEncodingFilter(){
	  CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	  characterEncodingFilter.setEncoding("utf-8");
	  return characterEncodingFilter;
  }
  //静态资源
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }
  //统一管理
  @Override
  public void addViewControllers(ViewControllerRegistry registry){
	  registry.addViewController("/pujihong").setViewName("/index");
	 // registry.addViewController("/pujihong/daily/index").setViewName("/daily/index");
  }

}
