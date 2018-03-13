package com.groupdocs.conversion.examples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.groupdocs.conversion.domain.FileDescription;
import com.groupdocs.conversion.handler.input.IInputDataHandler;

public class AmazonInputDataHandler implements IInputDataHandler {
	 
    private static String bucketName = ""; //TODO: Put you bucketname here 
    private AmazonS3Client _client;
 
    public AmazonInputDataHandler(String accessKey, String secretKey) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        _client = new AmazonS3Client(credentials);
    }
 
    @Override
    public FileDescription getFileDescription(String guid) {
        FileDescription result = new FileDescription();
        GetObjectRequest request = new GetObjectRequest(bucketName, guid);
        String fileName;
        long size;
        S3Object response = _client.getObject(request);
        fileName = response.getKey();
        size = response.getObjectMetadata().getContentLength();
        result.setGuid(guid);
        result.setName(fileName);
        result.setSize(size);
        return result;
    }
 
    public com.aspose.ms.System.IO.Stream getFile(String guid) {
        GetObjectRequest request = new GetObjectRequest(bucketName, guid);
        Stream  result = null;
        S3Object response = _client.getObject(request);
        byte[] buffer = new byte[16384]; //16*1024
        int read;
        try {
            while ((read = response.getObjectContent().read(buffer, 0, buffer.length)) > 0) {
                ((ByteArrayOutputStream) result).write(buffer, 0, read);
            }
        } catch (IOException ex) {
            Logger.getLogger(AmazonInputDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (com.aspose.ms.System.IO.Stream) result;
    }
 
    @Override
    public com.aspose.ms.System.IO.Stream getFileInternal(String guid) {    
        return getFile(guid);
    }
}