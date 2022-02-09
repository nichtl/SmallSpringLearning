package com.nicht.smallspringstep07;

import com.nicht.smallspringstep07.springframework.beans.factory.DisposableBean;
import com.nicht.smallspringstep07.springframework.beans.factory.InitializingBean;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/16
 * @Link
 */
public class UserService implements InitializingBean, DisposableBean {
    private String  uId;
    private UserDao userDao;
    private String  company;
    private String  location;

    /**
     * Bean 销毁之前调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    /**
     * Bean 处理了属性填充后调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String queryUserInfo() {

       return userDao.queryUserName(uId)+company+location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
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

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
