package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Genre;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/","/songs"})
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(required = false) Long albumId,  @RequestParam(required = false) Genre genre, Model model){

        if (error != null){
            model.addAttribute("hasError", error);
        }



        if (albumId != null || genre != null){
            model.addAttribute("albumId",albumId);
            model.addAttribute("genre",genre);
            model.addAttribute("songs",songService.find(albumId,genre));
        }else model.addAttribute("songs",songService.listSongs());

        model.addAttribute("albums",albumService.findAll());
        model.addAttribute("genres",Genre.class.getEnumConstants());
        model.addAttribute("error", error);
        return "listSongs";
    }


    @GetMapping("/add")
    public String saveSong(Model model){
        model.addAttribute("genres",Genre.class.getEnumConstants());
        model.addAttribute("albums",albumService.findAll());
        return "add-song";
    }

    @PostMapping("/add")
    public String saveSong(@RequestParam String title, @RequestParam String trackId, @RequestParam Genre genre, @RequestParam String idAlbum, @RequestParam String releaseYear){
        songService.addSong(title,trackId,genre,Long.parseLong(idAlbum), Integer.parseInt(releaseYear));
        return "redirect:/songs";
    }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, Model model){
        Song song = songService.findBySongId(songId);
        if (song == null){
            return "redirect:/songs?error=Song Not Found!";
        }
        model.addAttribute("song", song);
        model.addAttribute("genres",Genre.class.getEnumConstants());
        model.addAttribute("albums",albumService.findAll());
        return "add-song";
    }

    @PostMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId, @RequestParam String title,@RequestParam String trackId,@RequestParam Genre genre, @RequestParam String idAlbum, @RequestParam String releaseYear){
        songService.editSong(songId, title, trackId, genre, Long.parseLong(idAlbum), Integer.parseInt(releaseYear));
        return "redirect:/songs";
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        songService.deleteSong(id);
        return "redirect:/songs";
    }

}
