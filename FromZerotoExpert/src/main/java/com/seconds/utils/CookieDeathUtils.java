package com.seconds.utils;

import java.util.Calendar;

public class CookieDeathUtils {

    public static int getCookieDeath(){
        // 获取当前时间戳
        long now = Calendar.getInstance().getTimeInMillis();

        // 通过 Calendar 手动设置时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        // 获取当日 23:59:59 的时间戳
        long death = calendar.getTimeInMillis();

        // 计算过期时间
        // 注意：cookie 过期时间的单位为秒
        int cookieMaxAge = (int) ((death-now)/1000);

        return cookieMaxAge;
    }
}
