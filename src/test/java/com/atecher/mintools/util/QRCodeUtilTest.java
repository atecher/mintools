package com.atecher.mintools.util;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by hanhongwei on 2017/9/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QRCodeUtilTest {
    @Ignore
    @Test
    public void encode() throws Exception {
        String text = "薯灯可分列式本上楞珂要瓜熟蒂落！000000000000000";
        QRCodeUtil.encode(text, "d:/a/md5.png", "d:/a/", true);
    }

}