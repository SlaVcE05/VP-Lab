package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    public final SpringTemplateEngine springTemplateEngine;
    public final ArtistService artistService;
    public final SongService songService;

    public ArtistController(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String listArtist(@RequestParam(required = false) String trackId, @RequestParam(required = false) boolean error, Model model) {

        if (trackId == null) {
            return "redirect:/songs?error=Song not selected";
        }
        model.addAttribute("error", error);
        model.addAttribute("trackId", trackId);
        model.addAttribute("artists",artistService.listArtists());

        return "artistsList";
    }

    @PostMapping
    public String AddArtist(@RequestParam String trackId, @RequestParam(required = false) String artistId){

        if (artistId == null) {
            return "redirect:/artist?trackId=" + trackId+"&error=true";
        }
        songService.addArtistToSong(artistService.findById(Long.valueOf(artistId)),songService.findByTrackId(trackId));
        return "redirect:/songDetails?trackId=" + trackId;
    }

}
