package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    List<Artist> artists;

    public ArtistRepository() {
        artists = new ArrayList<>();
        artists.add(new Artist("Liam","Carter","Synthwave architect"));
        artists.add(new Artist("Sophia","Tran","Jazz fusion expert"));
        artists.add(new Artist("Nina","Flores","Progressive rock virtuoso"));
        artists.add(new Artist("Brandon","Fox","Folk pop storyteller"));
        artists.add(new Artist("Sonny","Mckenzie","Ambient chill creator"));
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        return artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

}
