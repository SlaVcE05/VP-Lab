package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String trackId;
    String title;
    @Enumerated(EnumType.STRING)
    Genre genre;
    int releaseYear;
    @ManyToMany
    List<Artist> performers;

    @ManyToOne
    private Album album;

    public Song(String trackId, String title, Genre genre, int releaseYear, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();
        this.album = album;
    }
}
