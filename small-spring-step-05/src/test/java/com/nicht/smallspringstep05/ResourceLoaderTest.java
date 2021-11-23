package com.nicht.smallspringstep05;

import cn.hutool.core.io.IoUtil;
import com.nicht.smallspringstep05.springframework.core.io.DefaultResourceLoader;

import com.nicht.smallspringstep05.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 测试资源加载器
 * @Author Administrator
 * @Time 2021/11/18
 * @Link
 */
@SpringBootTest
public class ResourceLoaderTest {
private DefaultResourceLoader resourceLoader  = new DefaultResourceLoader();


      @Test
    public  void test_classpath() throws IOException{
          Resource resource = resourceLoader.getResource("classpath:application.properties");
          InputStream inputStream = resource.getInputStream();
          String content = IoUtil.readUtf8(inputStream);
          System.out.println(content);
      }


    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

    }
    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }




}
