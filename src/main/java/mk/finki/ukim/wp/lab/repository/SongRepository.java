package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Genre;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbum_Id(Long albumId);
    Song findByTrackId(String trackId);
    Song findByGenre(Genre genre);
    List<Song> findAll(Specification<Song> specification);
}
