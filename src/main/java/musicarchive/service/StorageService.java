package musicarchive.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getSongFileNames() {

        ListObjectsV2Result result = bucket.listObjectsV2("STORAGE_NAME");
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public void uploadSong(MultipartFile file) throws IOException {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            bucket.putObject(new PutObjectRequest(
                    "BUCKET_NAME",
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
    }

