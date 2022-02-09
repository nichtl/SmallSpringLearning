package com.nicht.smallspringstep09.springframework.beans.factory;

public interface DisposableBean {
    /**
     * Bean 销毁之前调用
     * @throws Exception
     */
    void destroy() throws Exception;

}
