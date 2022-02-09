package com.nicht.smallspringstep13.springframework.core.io;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 使用Spring的ClassPathResource来读取maven项目resource下的文件
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */

public class ClassPathResource implements Resource {
    /*
     *一般来说，我们项目的配置文件及静态资源都会放置在resources目录下。有时我们在项目中使用到resources目录下的文件，
     * 这时我们可以使用Spring下的Resouce接口来读取。具体代码如下
     *Resource resource = new ClassPathResource(“static/Std_Resource_Train_Model.xls”);
     *因为Resouce是一个接口 所以我们可以使用它的实现类ClassPathResource来new一个对象。
     * 而构造方法的参数便是resources目录下的文件路径，注意这里是使用的相对路径(相对于resouces目录而言的)。
     *我们获取到resource对象后，变可以调用resouce.getFile()方法来获取文件。
     * */
    private final String path;
    // java ClassLoader 获取资源流
    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException(
                    this.path + "cannot be opened because it does not exist");
        }
        return is;
    }


}
