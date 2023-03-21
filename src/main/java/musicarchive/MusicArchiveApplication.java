package musicarchive;

import musicarchive.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MusicArchiveApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusicArchiveApplication.class, args);

        StorageService storageService = context.getBean(StorageService.class);

        storageService.getSongFileNames();
    }

}
