package com.winn.aliyun.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Constants extends Properties {

    private static final long serialVersionUID = 8553986739646805936L;

    private static Log log = LogFactory.getLog(Constants.class);

    public static Constants p = null;

    public final static String TRACE_ID = "TRACE_ID";

    static {
        init(null);
    }

    private static void init(String cfgPath) {
        if (null == p) {
            p = new Constants();
            Initialize.initProperties(cfgPath);
        }
    }

    public static String get(String key) {
        String value = "";
        try {
            if (null == key) {
                return "";
            } else if (p.containsKey(key)) {
                value = new String(p.getProperty(key).getBytes("UTF-8"), "UTF-8");
            } else {
                value = "";
            }
        } catch (UnsupportedEncodingException e) {
            log.info(e.getMessage(), e);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(get("TEST"));
    }
}
