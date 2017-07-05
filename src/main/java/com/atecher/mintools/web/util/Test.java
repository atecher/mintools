package com.atecher.mintools.web.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * desc:
 *
 * @author hanhongwei
 *         2016/8/2.
 */
public class Test {

    public static void main(String[] args) {
        RestTemplate rest=new RestTemplate();
        ResponseEntity res= rest.getForEntity("http://www.mintools.net",String.class);
        HttpHeaders headers= res.getHeaders();
        System.out.println("Status Code:"+res.getStatusCode());
        Set<Map.Entry<String, List<String>>> set=headers.entrySet();
        for(Map.Entry<String, List<String>> e:set){
            System.out.println(e.getKey()+":"+e.getValue().toString());
        }
        System.out.println(res.getBody());
    }
}
