package com.pjh.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * mybatis基础配置
 * @author Administrator
 *
 */
@Configuration
public class MybatisConfig implements TransactionManagementConfigurer{
	    @Autowired //2
	    private DataSource dataSource;
	    
	    @Bean(name = "sqlSessionFactory")//3
	    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
	        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	        bean.setDataSource(dataSource);
	        //一般对应我们的实体类所在的包
	        bean.setTypeAliasesPackage("com.pjh.model");
	        //分页插件 //4
//	        PageHelper pageHelper = new PageHelper();
//	        Properties properties = new Properties();
//	        properties.setProperty("reasonable", "true");
//	        properties.setProperty("supportMethodsArguments", "true");
//	        properties.setProperty("returnPageInfo", "check");
//	        properties.setProperty("params", "count=countSql");
//	        pageHelper.setProperties(properties);
	        //添加插件
//	        bean.setPlugins(new Interceptor[]{pageHelper});

	        //添加XML目录
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        try {
	        	//如果接口和mapper.xml在同一目录可以不用设置
	            bean.setMapperLocations(resolver.getResources("classpath:com/pjh/mapping/*.xml"));
	            return bean.getObject();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }

	    @Bean
	    public PlatformTransactionManager annotationDrivenTransactionManager() {
	        return new DataSourceTransactionManager(dataSource);
	    }
   
}
