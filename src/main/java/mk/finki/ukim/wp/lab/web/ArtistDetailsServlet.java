package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ArtistDetailsServlet",urlPatterns = "/servlet/artistDetails")
public class ArtistDetailsServlet extends HttpServlet {
    public final SpringTemplateEngine springTemplateEngine;
    public final ArtistService artistService;
    public final SongService songService;

    public ArtistDetailsServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String artistId = req.getParameter("artistId");

        if (artistId == null) {
            context.setVariable("artists",artistService.listArtists());
            springTemplateEngine.process("artistDetails.html", context, resp.getWriter());
            return;
        }

        long artistIdL = Long.parseLong(artistId);

        Artist artist = artistService.findById(artistIdL);

        context.setVariable("artist",artist);

        List<Song> songs = new ArrayList<>();

        for (Song s : songService.listSongs()){
            if (s.getPerformers().contains(artist))
                songs.add(s);
        }

        context.setVariable("songs",songs);

        springTemplateEngine.process("artistDetails.html", context, resp.getWriter());
    }
}
