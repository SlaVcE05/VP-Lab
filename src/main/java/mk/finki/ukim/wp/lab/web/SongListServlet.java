package mk.finki.ukim.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongListServlet", urlPatterns = {"", "/listSongs"})
public class SongListServlet extends HttpServlet {
    public final SpringTemplateEngine springTemplateEngine;
    public final SongService songService;


    public SongListServlet(SpringTemplateEngine springTemplateEngine, SongService songService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("hasError",req.getParameter("hasError"));
        context.setVariable("songs",songService.listSongs());

        springTemplateEngine.process("listSongs.html", context, resp.getWriter());
    }
}
