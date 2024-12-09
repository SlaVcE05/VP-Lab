package mk.finki.ukim.wp.lab.repository.imMemory;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {
    List<Artist> artists;

    public InMemoryArtistRepository(ArtistRepository artistRepository) {

        artists = new ArrayList<>();
        if (artistRepository.count() != 0)
            return;
        artists.add(new Artist("Liam","Carter","Synthwave architect"));
        artists.add(new Artist("Sophia","Tran","Jazz fusion expert"));
        artists.add(new Artist("Nina","Flores","Progressive rock virtuoso"));
        artists.add(new Artist("Brandon","Fox","Folk pop storyteller"));
        artists.add(new Artist("Sonny","Mckenzie","Ambient chill creator"));
        artistRepository.saveAll(artists);
    }

    public List<Artist> findAll() {
        return artists;
    }

    public Optional<Artist> findById(Long id) {
        return artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

}
