package com.nicht.smallspringstep09.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    //提供用于获取资源配置的流方法 接下来再实现 不同的流文件操作 classPath FileSystem URL
    InputStream getInputStream() throws IOException;
}
