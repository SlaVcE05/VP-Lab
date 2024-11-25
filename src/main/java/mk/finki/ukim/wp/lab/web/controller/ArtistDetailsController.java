package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/artistDetails")
public class ArtistDetailsController {
    public final ArtistService artistService;
    public final SongService songService;

    public ArtistDetailsController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String ShowDetails(@RequestParam(required = false) String artistId, Model model){

        if (artistId == null) {
            model.addAttribute("artists",artistService.listArtists());
            return "artistDetails";
        }

        long artistIdL = Long.parseLong(artistId);

        Artist artist = artistService.findById(artistIdL);

        model.addAttribute("artist",artist);

        List<Song> songs = new ArrayList<>();

        for (Song s : songService.listSongs()){
            if (s.getPerformers().contains(artist))
                songs.add(s);
        }
        model.addAttribute("songs",songs);

        return "artistDetails";
    }

}
