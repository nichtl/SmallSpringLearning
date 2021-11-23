package com.nicht.smallspringstep06.springframework.context;

import com.nicht.smallspringstep06.springframework.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    /*
    * 将bean的扫描 注册 实例化 以及一些hook方法全部 抽取为一个应用上下文,更加整体,方便使用和扩展
    * 刷新容器
    * @throws BeansException
    * */
    void refresh() throws BeansException;
    //具体的refresh 由不同实现类自己实现

    void registerShutdownHook();










}
