package fodorpapbalazsdev.gcloudexample.storage;

import com.google.cloud.storage.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Log4j2
public class StorageDemo {

    private final static String PROJECT_ID = "cloud-computing-class-306811";

    private final Storage storage;

    public StorageDemo() {
        this.storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    }

    public void createBucket() {

        String bucketName = "bucket-from-java";

        log.info("CreateBucket example started");

        // Check if already exists
        Bucket existingBucket = storage.get(bucketName);
        if (existingBucket == null) {
            // Create the Bucket
            storage.create(BucketInfo.newBuilder(bucketName).build());
            log.info("Bucket created with name: {}", bucketName);
        } else {
            log.warn("Bucket already exists with name: {}", bucketName);
        }

    }

    public void getBucket() {

        String bucketName = "bucket-from-java";

        log.info("GetBucket example started");

        // Check if already exists
        Bucket bucket = storage.get(bucketName);
        if (bucket != null) {
            log.info("Bucket retrieved: {}", bucket);
        } else {
            log.warn("Bucket not exists with name: {}", bucketName);
        }
    }

    public void getBlob() {

        String bucketName = "bucket-from-java";

        log.info("GetBucket example started");

        // Check if already exists
        Blob blob = storage.get(bucketName, "words.txt");
        if (blob != null) {
            log.info("Blob retrieved: {}", blob);
            String fileContent = new String(blob.getContent());
            log.info(fileContent);
        } else {
            log.warn("Blob not exists with name: {}", bucketName);
        }
    }

    public void deleteBucket() {

        String bucketName = "bucket-from-java";

        log.info("DeleteBucket example started");

        try {
            boolean success = storage.delete(bucketName);
            if (success) {
                log.info("Bucket deleted with name: {}", bucketName);
            } else {
                log.warn("Problem occurred during deletion: {}. Maybe the bucket is not exist with the given name.", bucketName);
            }
        } catch (StorageException e){
            log.error(e.getMessage());
        }

    }
}
