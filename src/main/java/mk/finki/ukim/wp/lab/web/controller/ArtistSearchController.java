package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/artistSearch")
public class ArtistSearchController {
    private final ArtistService artistService;

    public ArtistSearchController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String search(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestParam(required = false) String bio, Model model){

        if(firstName.isEmpty() && lastName.isEmpty() && bio.isEmpty()){
            return "redirect:/songs";
        }

        List<Artist> artists = artistService.listArtists().stream().filter(artist -> {
            boolean found = true;
            if (!firstName.isEmpty() && !artist.getFirstName().equals(firstName.trim()))
                found = false;
            if (!lastName.isEmpty() && !artist.getLastName().equals(lastName.trim()))
                found = false;
            if (!bio.isEmpty() && !artist.getBio().equals(bio.trim()))
                found = false;
            return found;
        }).toList();

        model.addAttribute("artists",artists);

        return "artistSearch";
    }

}
