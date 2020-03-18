package com.winn.aliyun.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Initialize {

    private static Log log = LogFactory.getLog(Initialize.class);

    public static void initProperties(String cfgPath) {
        try {
            if (null != cfgPath) {
                System.out.println("cfgPath:" + cfgPath);
                Constants.p.load(new FileInputStream(new File(cfgPath)));
            } else {
                String[] arrProFiles = {"winnpri.properties"};
                for (String arrProFile : arrProFiles) {
                    System.out.println("load Properties file:" + arrProFile);
                    Constants.p.load(Initialize.class.getClassLoader().getResourceAsStream(arrProFile));
                }
            }
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
    }
}
