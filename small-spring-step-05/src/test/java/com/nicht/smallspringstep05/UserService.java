package com.nicht.smallspringstep05;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/16
 * @Link
 */
public class UserService {
    private String  uId;
    private UserDao userDao;
    private String  company;
    private String  location;
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
