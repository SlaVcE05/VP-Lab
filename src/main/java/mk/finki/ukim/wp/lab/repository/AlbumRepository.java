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
        albums.add(new Album("name","genre1","1.1.1970"));
        albums.add(new Album("name2","genre2","1.1.1970"));
        albums.add(new Album("name3","genre3","1.1.1970"));
        albums.add(new Album("name4","genre4","1.1.1970"));
        albums.add(new Album("name5","genre5","1.1.1970"));
    }

    public List<Album> findAll() {
        return albums;
    }
}
