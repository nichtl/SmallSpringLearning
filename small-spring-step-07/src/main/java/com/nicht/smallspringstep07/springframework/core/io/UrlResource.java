package com.nicht.smallspringstep07.springframework.core.io;

import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements  Resource{
    private  final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"url must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try{
            return con.getInputStream();
        }catch (Exception e){
            if(con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw  e;
        }
    }
}
