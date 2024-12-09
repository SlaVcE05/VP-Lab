package mk.finki.ukim.wp.lab.repository.imMemory;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySongRepository {
    List<Song> songs;

    private final AlbumService albumService;

    public InMemorySongRepository(AlbumService albumService, SongRepository songRepository) {
        this.albumService = albumService;

        List<Album> albums = albumService.findAll();

        songs = new ArrayList<>();
        if (songRepository.count() != 0)
            return;

        songs.add(new Song("0","Echoes of Tomorrow","Synthwave",2023,albums.get(0)));
        songs.add(new Song("1","Moonlit Melodies","Jazz",2020,albums.get(1)));
        songs.add(new Song("2","Whispers in the Rain","Metal",2019,albums.get(2)));
        songs.add(new Song("3","Neon Nights","Retro Pop",2018,albums.get(3)));
        songs.add(new Song("4","Solar Flare","Dubstep",2020,albums.get(4)));
        songRepository.saveAll(songs);
    }

    public List<Song> findAll() {
        return songs;
    }

    public Song findByTrackId(String trackId) {
        return songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }

    public Song findBySongId(Long songId) {
        return songs.stream().filter(song -> song.getId().equals(songId)).findFirst().orElse(null);
    }

    public Artist addArtistToSong(Artist artist, Song song) {
        if (!song.getPerformers().contains(artist))
            song.getPerformers().add(artist);
        return artist;
    }

    public void addSong(String title, String trackId, String genre, long idAlbum, int releaseYear) {
        Album album = albumService.findAll().stream().filter(a -> a.getId().equals(idAlbum)).findFirst().orElse(null);
        songs.add(new Song(trackId,title,genre,releaseYear,album));
    }


    public void editSong(Long songId, String title, String trackId, String genre, long idAlbum, int releaseYear) {
        Song song = findBySongId(songId);
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(albumService.findAll().stream().filter(album -> album.getId().equals(idAlbum)).findFirst().orElse(null));
    }

    public void deleteSong(Long id) {
        songs.removeIf(song -> song.getId().equals(id));
    }
}
