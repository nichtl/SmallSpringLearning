package com.nicht.smallspringstep09.springframework.core.io;

import org.springframework.util.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */
public class DefaultResourceLoader implements  ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"location must not be null");
       if(location.startsWith(CLASSPATH_URL_PREFIX)){
           return  new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
       }else{
           try {
               URL url =new URL(location);
               return  new UrlResource(url);
           }catch (MalformedURLException e){
               return  new FileSystemResource(location);
           }
       }
    }
}
