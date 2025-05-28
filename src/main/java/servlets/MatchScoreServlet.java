package servlets;

import dto.OngoingMatchDTO;
import dto.OngoingMatchMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.MatchScoreService;
import services.OngoingMatch;
import services.OngoingMatchesStorage;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch match = OngoingMatchesStorage.getInstance().getMatch(uuid);

        OngoingMatchDTO matchDTO = OngoingMatchMapper.toMatchDTO(match, uuid);

        resp.setStatus(200);
        req.setAttribute("match", matchDTO);
        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        long pointWinnerId = Long.parseLong(req.getParameter("pointWinner"));

        OngoingMatchDTO matchDTO = new MatchScoreService().addPoint(uuid, pointWinnerId);

        resp.setStatus(200);
        req.setAttribute("match", matchDTO);
        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
