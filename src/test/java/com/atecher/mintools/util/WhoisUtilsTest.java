package com.atecher.mintools.util;

import com.aliyuncs.domain.model.v20160511.GetWhoisInfoResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by hanhongwei on 2017/9/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WhoisUtilsTest {

    @Test
    public void getWhoisInfoTest() throws Exception {
        GetWhoisInfoResponse response = WhoisUtils.getWhoisInfo("mintools.net");
        Assert.assertTrue(response != null);
    }
//    @Test
//    public  void guavaCacheTest() throws ExecutionException {
//        LoadingCache<String, String> graphs = CacheBuilder.newBuilder().softValues().maximumSize(1000).expireAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String,String>(){
//            @Override
//            public String load(String s) throws Exception {
//                System.out.println("未命中");
//                return s+"_value";
//            }
//        });
//
//        System.out.println(graphs.get("222", new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println("未命中");
//                return null;
//            }
//        }));
//        System.out.println(graphs.get("222"));
//    }

}