package com.github.tanxinzheng.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Jeng on 15/10/31.
 */
@EnableWebMvc// 启用SpringMVC
@ComponentScan(basePackages = "com.github")// 配置包扫描路径
@Configuration// 启用注解式配置
public class ServletConfig extends WebMvcConfigurerAdapter {

    /**
     * 设置视图解析器，以及页面路径
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 设置静态资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    /**
     * 设置默认的servlet请求处理器
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer//.favorPathExtension(true)
                //.useJaf(false)
                //.favorParameter(true)
                //.parameterName("mediaType")
                //.ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                //.mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }
}
