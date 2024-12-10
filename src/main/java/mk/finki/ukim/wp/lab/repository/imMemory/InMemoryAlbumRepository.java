package mk.finki.ukim.wp.lab.repository.imMemory;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Genre;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlbumRepository {
    List<Album> albums;
    
    public InMemoryAlbumRepository(AlbumRepository repository) {

        albums = new ArrayList<>();
        if (repository.count() != 0)
            return;

        albums.add(new Album("Echoes of Eternity", Genre.SYNTHWAVE,"2023"));
        albums.add(new Album("Moonlit Horizons",Genre.JAZZ,"2020"));
        albums.add(new Album("Fading into the Dawn",Genre.METAL,"2019"));
        albums.add(new Album("Beyond the Neon", Genre.RETRO_POP,"2018"));
        albums.add(new Album("Cosmic",Genre.DUBSTEP,"2020"));
        repository.saveAll(albums);
    }

    public List<Album> findAll() {
        return albums;
    }
}
