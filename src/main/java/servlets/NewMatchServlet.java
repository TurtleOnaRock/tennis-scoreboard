package servlets;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import entities.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/new-match.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName1 = req.getParameter("player_name_1");
        String playerName2 = req.getParameter("player_name_2");

        PlayerDao dao = new PlayersDAOImpl();
        dao.save(playerName1);
        dao.save(playerName2);
        List<Player> players = dao.getPlayers();
        resp.setStatus(200);
        resp.setContentType("text/html");
        resp.getWriter().println(players.toString());
    }
}
