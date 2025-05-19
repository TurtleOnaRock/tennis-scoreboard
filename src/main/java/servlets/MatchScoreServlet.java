package servlets;

import dto.TennisMatchDTO;
import dto.TennisMatchMapper;
import exceptions.WrongPlayerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TennisMatch;
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
        getServletContext().getRequestDispatcher( "/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        int pointWinnerId = Integer.valueOf(req.getParameter("pointWinner"));
        TennisMatch match = TennisMatchesStorage.getInstance().getMatch(uuid);

        try{
            match.addPointTo(pointWinnerId);
        } catch (WrongPlayerException e){
            resp.getWriter().println(e.getMessage());
            resp.setStatus(300);
        }

        if(match.isCompleted()){
//            TO DO : save result to dao
        }

        TennisMatchDTO matchDTO = TennisMatchMapper.toMatchDTO(match, uuid);
        req.setAttribute("match", matchDTO);
        resp.setStatus(200);
        getServletContext().getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }
}
