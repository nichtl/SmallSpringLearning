package com.nicht.smallspringstep08;

import com.nicht.smallspringstep08.springframework.beans.BeansException;
import com.nicht.smallspringstep08.springframework.beans.factory.*;
import com.nicht.smallspringstep08.springframework.context.ApplicationContext;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/29
 * @Link
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanName" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader"+classLoader);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         this.applicationContext =applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
    }
    public String queryUserInfo() {

        return userDao.queryUserName(uId)+company+location;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
