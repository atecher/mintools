package com.atecher.mintools.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.domain.model.v20160511.GetWhoisInfoRequest;
import com.aliyuncs.domain.model.v20160511.GetWhoisInfoResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created by hanhongwei on 2016/6/21.
 */
public class WhoisUtils {
    private static final String aliyun_app_regionId="cn-hangzhou";
    private static final String aliyun_app_accessKeyId="2l7Jg3feKTZrp4yp";
    private static final String aliyun_app_secret="OAJF9Y27bhaJzh2NlmKi6c6mdEesxf";
    public static GetWhoisInfoResponse getWhoisInfo(String domainName) throws Exception {
        GetWhoisInfoRequest request=new GetWhoisInfoRequest();
        request.setDomainName(domainName);
        IClientProfile profile = DefaultProfile.getProfile(aliyun_app_regionId, aliyun_app_accessKeyId,aliyun_app_secret);
        IAcsClient client=new DefaultAcsClient(profile);
        GetWhoisInfoResponse response= client.getAcsResponse(request);
        System.out.println(response.toString());

        return response;

    }

    public static void main(String[] args) throws Exception {
        getWhoisInfo("mintools.net");
    }





}
