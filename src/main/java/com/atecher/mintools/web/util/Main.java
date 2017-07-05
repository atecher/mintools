package com.atecher.mintools.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

/**
 * Created by hanhongwei on 2016/7/30.
 */
public class Main {

//    public  static  void  main(String[] args) throws IOException {
//        RestTemplate restTemplate = new RestTemplate();
//        StringBuffer buffer=new StringBuffer();
//        for(int m=1;m<=52;m++){
//            String str = restTemplate.getForObject("http://api.atool.org/extlink.php?n=50&p="+m,String.class);
//            str=str.replaceFirst("jsonCallback\\(","");
//            str= str.substring(0,str.lastIndexOf(")"));
//            JSONObject json=JSON.parseObject(str);
//            JSONArray jsonArray=json.getJSONArray("l");
//            for(int i=1;i<jsonArray.size();i++){
//                buffer.append("insert into sys_extlink(extlink,embody_time) values('"+jsonArray.getString(i).replaceFirst("\\{domain2promote\\}","{0}")+"',now());\r\n");
//            }
//        }
//        FileUtils.write(new File("d:/ext.sql"),buffer.toString());
//
//    }

    //7 8 9 15 16 17 18 24-27 33-35 41-42
public  static  void  main(String[] args) throws IOException {
    RestTemplate restTemplate = new RestTemplate();
    StringBuffer buffer=new StringBuffer();
    for(int m=41;m<=42;m++){
        try{
            String str = restTemplate.getForObject("http://tool.lu/extlink/ajax.html?url=http%3A%2F%2Fwww.mintools.net&page="+m,String.class);
            JSONObject json=JSON.parseObject(str);
            JSONArray jsonArray=json.getJSONObject("text").getJSONArray("data");
            System.out.println(jsonArray.size());
            for(int i=0;i<jsonArray.size();i++){
                buffer.append("insert into sys_extlink(extlink,embody_time) values('"+jsonArray.getString(i).replaceFirst("www.mintools.net","{0}").replace("%253A%252F%252F","://")+"',now());\r\n");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    FileUtils.write(new File("d:/ext.sql"),buffer.toString());

}

}
