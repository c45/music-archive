package musicarchive;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Song {

    @Id
    private String id;

    private String fileName;

    private String title;
    private String artist;
    private boolean isFavorited;
}
