package musicarchive;

import lombok.extern.slf4j.Slf4j;
import musicarchive.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class MusicArchiveApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusicArchiveApplication.class, args);

        StorageService storageService = context.getBean(StorageService.class);

        log.info(storageService.getSongFileNames().toString());
        storageService.getSongFileNames();

    }

}
