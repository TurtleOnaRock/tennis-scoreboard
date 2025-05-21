package servlets;

import dto.TennisMatchDTO;
import dto.TennisMatchMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TennisMatch;
import services.MatchScoreCalculatorService;
import services.TennisMatchesStorage;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        TennisMatch match = TennisMatchesStorage.getInstance().getMatch(uuid);

        TennisMatchDTO matchDTO = TennisMatchMapper.toMatchDTO(match, uuid);

        req.setAttribute("match", matchDTO);
        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        long pointWinnerId = Long.parseLong(req.getParameter("pointWinner"));

        TennisMatchDTO matchDTO = new MatchScoreCalculatorService().addPoint(uuid, pointWinnerId);

        req.setAttribute("match", matchDTO);
        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
