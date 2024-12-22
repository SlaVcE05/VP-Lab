package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();
    Artist findById(Long id);
    List<Artist> find(String firstName, String lastName, String bio);
}
