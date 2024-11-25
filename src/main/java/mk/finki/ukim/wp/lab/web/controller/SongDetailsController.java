package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {
    private final SongService songService;

    public SongDetailsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public String ShowDetails(@RequestParam String trackId, Model model){
        model.addAttribute("song", songService.findByTrackId(trackId));
        return "songDetails";
    }

}
