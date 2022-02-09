package com.nicht.smallspringstep07.springframework.beans.factory.support;

import com.nicht.smallspringstep07.springframework.beans.factory.DisposableBean;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/24
 * @Link
 */
public class AisposableBeanAdapter implements DisposableBean {
    /**
     * Bean 销毁之前调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("AisposableBeanAdapter");
    }
}
