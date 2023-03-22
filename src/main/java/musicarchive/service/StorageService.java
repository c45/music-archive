package musicarchive.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import io.github.cdimascio.dotenv.Dotenv;
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
    Dotenv dotenv = Dotenv.configure().load();

    public StorageService() {

        AWSCredentialsProvider credentials = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(dotenv.get("ACCESS_KEY"), dotenv.get("SECRET_KEY"))
        );

        bucket = AmazonS3ClientBuilder
                .standard()
                .withCredentials(credentials)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                dotenv.get("S3_URL"), dotenv.get("REGION")
                        )
                )
                .build();
    }

    public List<String> getSongFileNames() {

        ListObjectsV2Result result = bucket.listObjectsV2(dotenv.get("BUCKET_NAME"));
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    public void uploadSong(MultipartFile file) throws IOException {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            bucket.putObject(new PutObjectRequest(
                    dotenv.get("BUCKET_NAME"),
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
    }

