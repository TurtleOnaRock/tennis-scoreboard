package servlets;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import entities.Player;
import exceptions.WrongPlayerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TennisGame;
import services.TennisGames;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/match-score*")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        TennisGame game = TennisGames.getInstance().getGame(uuid);
        // TO DO transfer "game" from servlet to JSP for rendering page. maybe game should be JavaBean object?
        getServletContext().getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        int pointWinnerId = Integer.valueOf(req.getParameter("pointWinner"));
        TennisGame game = TennisGames.getInstance().getGame(uuid);
        try{
            game.addPointTo(pointWinnerId);
        } catch (WrongPlayerException e){
            resp.getWriter().println(e.getMessage());
            resp.setStatus(300);
        }
    }
}
