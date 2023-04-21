package musicarchive.controller;


import lombok.extern.slf4j.Slf4j;
import musicarchive.model.Song;
import musicarchive.repository.SongRepository;
import musicarchive.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final StorageService storageService;
    private final SongRepository songRepo;

    @Autowired
    public SongController(StorageService storageService, SongRepository songRepo) {
        this.storageService = storageService;
        this.songRepo = songRepo;
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok(songRepo.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Song> getSong(@PathVariable String id) {
        Optional<Song> song = songRepo.findById(id);

        if (song.isPresent()) {
            return ResponseEntity.ok(song.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Song> createSong(@RequestPart("song") Song song, @RequestPart("file")MultipartFile file)
        throws IOException {
        if (songRepo.existsSongByFileNameEquals(file.getOriginalFilename())
                || songRepo.existsSongByTitleEquals(song.getTitle())) {
            return ResponseEntity.badRequest().build();
        } else {
            log.info("Uploading the file");
            storageService.uploadSong(file);

            song.setFileName(file.getOriginalFilename());
            Song selectedSong = songRepo.insert(song);

            return new ResponseEntity<>(selectedSong, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id, @RequestBody Song songData) {

        Optional<Song> songOptional = songRepo.findById(id);

        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            if (songData.getTitle() != null) {
                song.setTitle(song.getTitle());
            }
            if (songData.getArtist() != null) {
                song.setArtist(songData.getArtist());
            }
            song.setFavorited(songData.isFavorited());
            songRepo.save(song);
            return ResponseEntity.ok(song);
        } else {
            return ResponseEntity.notFound().build();
        }
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable String id) {
        if (songRepo.existsById(id)) {
            songRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
     }
}
