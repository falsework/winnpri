package com.winn.aliyun.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.UUID;

public class Tools {

    private static Logger log = LogManager.getLogger(Tools.class);

    public static String getRandomUUID12() {
        return replaceStr(UUID.randomUUID().toString().replace("-", ""), 12);
    }

    public static String replaceStr(String str, int i) {
        return str.substring(0, 12);
    }

    public static void main(String[] args) {
        System.out.println(getRandomUUID12());
    }
}
