package com.pjh.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pjh.model.User;
//在spring boot中添加自己的Servlet有两种方法，代码注册Servlet和注解自动注册（Filter和Listener也是如此）。 
//一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。 
//也可以通过实现 ServletContextInitializer 接口直接注册。
//
//二、在 SpringBootApplication 上使用@ServletComponentScan注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
/**
 * 功能：必须登录后才能访问其他页面
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 * @author Administrator
 *
 */

@WebFilter(filterName="loginFilter",urlPatterns="/*")
public class LoginFilter implements Filter{
    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("初始化登录过滤器");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 // 获得在下面代码中要用的request,response,session对象
         HttpServletRequest servletRequest = (HttpServletRequest) request;
         HttpServletResponse servletResponse = (HttpServletResponse) response;
         HttpSession session = servletRequest.getSession();
 
         // 获得用户请求的URI
         String url = servletRequest.getRequestURI();
         System.out.println(url);
         //不拦截静态文件 .js .css ==
         if(url.indexOf(".") > -1){  
        	 chain.doFilter(servletRequest, servletResponse);
             return;  
         } 

         //首页无需过滤
         if(url.equals("/pujihong")) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
         // 从session里取员工工号信息
         User user = (User) session.getAttribute("user");
         // 判断有没有登录,没有就跳转到登陆页面
         if (user == null){
             // 跳转到登陆页面
             servletResponse.sendRedirect("/pujihong");
         } else {
             // 已经登陆,继续此次请求
             chain.doFilter(request, response);
         }
		 
		
	}

	@Override
	public void destroy() {
		logger.info("销毁登录过滤器");
	}

}
