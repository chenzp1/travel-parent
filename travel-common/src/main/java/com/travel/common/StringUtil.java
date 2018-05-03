package com.travel.common;

import java.util.UUID;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-05-03
 */
public class StringUtil {

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
