package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artist")
public class ArtistServlet extends HttpServlet {
    public final SpringTemplateEngine springTemplateEngine;
    public final ArtistService artistService;
    public final SongService songService;

    public ArtistServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService, SongService songService) {
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

        String trackId = req.getParameter("trackId");
        if (trackId == null) {
            resp.sendRedirect("/listSongs?error=true");
            return;
        }

        context.setVariable("error", req.getParameter("error"));
        context.setVariable("trackId", trackId);
        context.setVariable("artists", artistService.listArtists());

        springTemplateEngine.process("artistsList.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        if (artistId == null) {
            resp.sendRedirect("/artist?trackId=" + trackId+"&error=true");
            return;
        }

        songService.addArtistToSong(artistService.findById(Long.valueOf(artistId)),songService.findByTrackId(trackId));

        resp.sendRedirect("/songDetails?trackId=" + trackId);
    }
}
