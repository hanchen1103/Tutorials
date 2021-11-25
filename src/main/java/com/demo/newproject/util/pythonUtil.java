package com.demo.newproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class pythonUtil {

    public static final Logger logger = LoggerFactory.getLogger(pythonUtil.class);

    public static void loadScript(String name) throws IOException, InterruptedException {
        String[] args1 = new String[]{"python3", "/Users/hanchenzhu/projects/spring_branch/src/main/resources/script/spider_weather.py", name};
        logger.info(Arrays.toString(args1));
        logger.info("spider start");
        Process pr = Runtime.getRuntime().exec(args1);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "gb2312"));
        String line;
        while((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
        pr.waitFor();
        logger.info("spider end");
    }

}
