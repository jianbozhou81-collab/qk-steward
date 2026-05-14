package com.qk.utils;

/**
 * 操作当前登录用户信息(其实本质就是一个map集合,一个线程对应一个key,从而获取对应的value)
 */
public class CurrentUserHoler {
    private static ThreadLocal<Integer> CURRENT_USER = new ThreadLocal<>();
    //设置当前用户
    public static void setCurrentUser(Integer userId) {
        CURRENT_USER.set(userId);
    }
    //获取当前用户
    public static Integer getCurrentUser() {
        return CURRENT_USER.get();
    }
    //删除当前用户
    public static void removeCurrentUser() {
        CURRENT_USER.remove();
    }
}
