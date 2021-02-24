package com.wgu.edu.utils;

import com.wgu.edu.request.CGStudent;
import com.wgu.edu.constants.Constants;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/*The BannerUtility Class takes the GET and POST
call from the Abstract layer (SpringBoot Application)*/

public class BannerUtility {
    private static final Logger logger = LoggerFactory.getLogger( BannerUtility.class);
    public static JSONObject callCGStudentExternalApi (CGStudent cgStudent) {
        logger.info("Start callCGStudentExternalApi");
        logger.info("cgStudent"+cgStudent);
        JSONObject responseJson = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet( "https://www.google.com/");
        try {
            //httpGet.addHeader(Constant.AUTHORIZATION, "");
            httpGet.addHeader( Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
            CloseableHttpResponse response = client.execute(httpGet);
            response.getStatusLine().getStatusCode();
            //responseJson = new JSONObject( EntityUtils.toString(response.getEntity()));
            logger.info("responseJson -> ->"+response.getStatusLine().toString());
            client.close();
            logger.info("End callCGStudentExternalApi");
        } catch (IOException e) {
            logger.error( "Error while calling callCGStudentExternalApi:"+e.getMessage() );
        }
        return responseJson;
    }
}
