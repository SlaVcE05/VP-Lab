package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    public Song findByTrackId(String trackId);

    void addSong(String title, String trackId, String genre, long idAlbum, int releaseYear);

    void editSong(Long songId, String title, String trackId, String genre, long idAlbum, int releaseYear);

    Object findBySongId(Long songId);

    void deleteSong(Long id);
}
