package com.wgu.edu;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import java.util.Objects;

import com.wgu.edu.request.CGStudent;
import com.wgu.edu.utils.BannerUtility;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * BannerWriterLambda This lambda will process SQS.
 *
 * @author
 * @version 0.0.1
 */
public class BannerWriterlambda implements RequestHandler<SQSEvent, String> {

    private static final Logger logger = LoggerFactory.getLogger( BannerWriterlambda.class);

    /**
     * <p>
     * <b>Description</b> Entry point for Lambda operation.
     *
     * @param sqsEvent
     * @param context
     * @return
     * @see com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java.lang.Object,
     *      com.amazonaws.services.lambda.runtime.Context)
     *      </p>
     */
    @Override
    public String handleRequest(SQSEvent sqsEvent, Context context) {

        JSONObject bodyObject = null;
        logger.info("Body:"+sqsEvent.getRecords().get(0).getBody());

        String body = sqsEvent.getRecords().get(0).getBody();

        // 1. Validate json
        if (Objects.nonNull(body) && !body.isEmpty()) {
            bodyObject = new JSONObject(body);
            logger.info("Validated Json Object:"+bodyObject.toString());
        } else {
            logger.info("No SQS event received");
            return "No event received.";
        }

        // 2. Retrieve TopicARN

        if(Objects.nonNull( bodyObject )){

            String topicARN = bodyObject.optString( "TopicArn" );
            String message = bodyObject.optString( "Message" );

            retrievedDetail( message, topicARN );

        }

        return "handleRequest request processed successfully";

    }

    /**
     *
     * @param message
     * @param topicARN
     */
    private void retrievedDetail(String message, String topicARN){
        logger.info( "TopicARN"+topicARN );
        if(topicARN.endsWith( "CARE_SALESFORCE_CLIENT_SNS" )){
            JSONObject messageObject = new JSONObject( message );

            Integer PIDM = messageObject.optInt( "PIDM" );
            String firstName = messageObject.optString( "firstName" );
            String lastName = messageObject.optString( "lastName" );

            logger.info( "FirstName:"+firstName );
            logger.info( "LastName:"+lastName );

            CGStudent cgStudent = new CGStudent();
            cgStudent.setPIDM(PIDM);
            cgStudent.setFirstName(firstName);
            cgStudent.setLastName(lastName);
            BannerUtility.callCGStudentExternalApi(cgStudent);
        } else if(topicARN.endsWith( "" )){

        }
    }

}
