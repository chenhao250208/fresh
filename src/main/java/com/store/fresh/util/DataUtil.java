package com.store.fresh.util;

import java.util.Random;

/**
 * @Author: Liangwei
 * @Date: 2018/12/14 19:55
 */
public class DataUtil {

    /**
     * 功能描述: 生成len位随机编号：13位时间戳+(len-13)位随机数
     *
     * @param len 编号位数
     * @return 随机编号
     * @date 2018/9/18 13:47
     */

    public static String getRandomNo(int len) {
        len -= 13;
        long timeStamp = System.currentTimeMillis();
        Random random = new Random();
        int res = 1;
        for (int i = 0; i < len; i++) res *= 10;
        int randomInt = random.nextInt(res - 1 - (res / 10)) + res / 10;
        return String.valueOf(timeStamp) + String.valueOf(randomInt);
    }


}


