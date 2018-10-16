package com.atecher.mintools.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.domain.model.v20160511.GetWhoisInfoRequest;
import com.aliyuncs.domain.model.v20160511.GetWhoisInfoResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Created by atecher on 2016/6/21.
 */
public class WhoisUtils {
    private static final String ALIYUN_APP_REGIONID = "cn-hangzhou";
    private static final String ALIYUN_APP_ACCESSKEYID = "2l7Jg3feKTZrp4yp";
    private static final String ALIYUN_APP_SECRET = "OAJF9Y27bhaJzh2NlmKi6c6mdEesxf";

    public static GetWhoisInfoResponse getWhoisInfo(String domainName) throws Exception {
        GetWhoisInfoRequest request = new GetWhoisInfoRequest();
        request.setDomainName(domainName);
        IClientProfile profile = DefaultProfile.getProfile(ALIYUN_APP_REGIONID, ALIYUN_APP_ACCESSKEYID, ALIYUN_APP_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        return client.getAcsResponse(request);

    }

//    public static void main(String[] args) throws Exception {
//        getWhoisInfo("mintools.net");
//    }


}
