package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.ArtistRepository;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.finki.ukim.wp.lab.service.specifications.FieldFilterSpecification.*;

@Service
public class ArtistServiceImpl implements ArtistService {

    public final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> find(String firstName, String lastName, String bio) {

        Specification<Artist> specification = Specification
                .where(filterEquals(Artist.class,"firstName",firstName))
                .and(filterEquals(Artist.class,"lastName",lastName))
                .and(filterContainsText(Artist.class,"bio",bio));

        return artistRepository.findAll(specification);
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }
}
