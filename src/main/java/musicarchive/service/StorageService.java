package musicarchive.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StorageService {

    private final AmazonS3 bucket;

    public StorageService() {
        AWSCredentialsProvider credentials = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("ACCESS_KEY", "SECRET_KEY")
        );

        bucket = AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentials)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "url", "region"
                        )
                )
                .build();
    }

    public void getSongFileNames() {

        ListObjectsV2Result result = bucket.listObjectsV2("STORAGE_NAME");
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        objects.stream()
                .forEach(s3ObjectSummary -> {
                    log.info(s3ObjectSummary.toString());
                        });
    }
}
