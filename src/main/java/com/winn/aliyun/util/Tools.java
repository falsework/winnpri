package com.winn.aliyun.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Description: 对象转string
     *
     * @Date: 2020/6/12
     */
    public static String ts(Object obj) {
        String str = "";
        if (null != obj) {
            str = String.valueOf(obj).trim();
        }
        return str;
    }

    /**
     * Description: list等长拆分
     *
     * @param list     原list集合
     * @param pageSize 子集合长度
     * @Date: 2020/6/12
     */
    public static <T> List<List<T>> groupList(List<T> list, int pageSize) {
        List<List<T>> listGroup = new ArrayList<List<T>>();
        int listSize = list.size();
        for (int i = 0; i < listSize; i += pageSize) {
            if (i + pageSize > listSize) {
                pageSize = listSize - i;
            }
            List<T> newList = list.subList(i, i + pageSize);
            listGroup.add(newList);
        }
        return listGroup;
    }
}
