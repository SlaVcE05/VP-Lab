package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {

        Song song1 = songRepository.findById(song.getId()).orElseThrow(() -> new RuntimeException("Song does not exist"));
        if (!song1.getPerformers().contains(artist)){
            song1.getPerformers().add(artist);
        }
        songRepository.save(song1);

        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void addSong(String title, String trackId, String genre, long idAlbum, int releaseYear) {
        Album album = albumRepository.findById(idAlbum).orElseThrow(() -> new RuntimeException("Album does not exist"));
        Song song = new Song(trackId,title,genre,releaseYear,album);
        songRepository.save(song);
    }

    @Override
    public void editSong(Long songId, String title, String trackId, String genre, long idAlbum, int releaseYear) {

        Song song = songRepository.findById(songId).orElseThrow( () -> new RuntimeException("Song not found"));

        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);

        Album album = albumRepository.findById(idAlbum).orElse(null);
        song.setAlbum(album);

        song.setReleaseYear(releaseYear);
        songRepository.save(song);
    }

    @Override
    public Song findBySongId(Long songId) {
        return songRepository.findById(songId).orElseThrow( () -> new RuntimeException("Song not found"));
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public List<Song> findByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
