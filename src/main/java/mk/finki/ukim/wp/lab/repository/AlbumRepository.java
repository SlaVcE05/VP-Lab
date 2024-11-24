package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository {
    List<Album> albums;
    public AlbumRepository() {
        albums = new ArrayList<>();
        albums.add(new Album("Echoes of Eternity","Synthwave","2023"));
        albums.add(new Album("Moonlit Horizons","Jazz","2020"));
        albums.add(new Album("Fading into the Dawn","Metal","2019"));
        albums.add(new Album("Beyond the Neon","Retro Pop","2018"));
        albums.add(new Album("Cosmic","Dubstep","2020"));
    }

    public List<Album> findAll() {
        return albums;
    }
}
