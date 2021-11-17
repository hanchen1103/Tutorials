package com.demo.newproject.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 文件防止路径,鉴别操作系统，如果是windows系统则放置在D://picture/路径下
     * 如果是linux系统则放置在/usr/img/路径下
     * 映射url为url/image/文件名称
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");

        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/image/**")
                    // /app_file/**表示在磁盘filePathWindow目录下的所有资源会被解析为以下的路径
                    .addResourceLocations("file:D://picture/");
        } else {  //linux 和mac
            registry.addResourceHandler("/image/**")
                    .addResourceLocations("file:/usr/local/img/") ;
        }
        super.addResourceHandlers(registry);
    }
}
