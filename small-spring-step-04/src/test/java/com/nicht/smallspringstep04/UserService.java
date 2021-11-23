package com.nicht.smallspringstep04;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/16
 * @Link
 */
public class UserService {
    private String uId;
    private UserDao userDao;
    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
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

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
