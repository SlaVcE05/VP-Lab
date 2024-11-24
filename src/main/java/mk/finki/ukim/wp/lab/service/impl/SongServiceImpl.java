package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void addSong(String title, String trackId, String genre, long idAlbum, int releaseYear) {
        songRepository.addSong(title,trackId,genre,idAlbum,releaseYear);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, long idAlbum, int releaseYear) {
        songRepository.editSong(songId, title, trackId, genre, idAlbum, releaseYear);
    }

    @Override
    public Song findBySongId(Long songId) {
        return songRepository.findBySongId(songId);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteSong(id);
    }
}
