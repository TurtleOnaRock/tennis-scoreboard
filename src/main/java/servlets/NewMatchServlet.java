package servlets;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import exceptions.IncorrectParameterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public static final int DEFAULT_MAX_LENGTH=50;
    public static final String DEFAULT_FORBIDDEN_CHARS = "!?\";({[)}]";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        String playerName1 = req.getParameter("player_name_1");
        String playerName2 = req.getParameter("player_name_2");

        try {
            ServletUtils.validateParameter(playerName1, DEFAULT_MAX_LENGTH, DEFAULT_FORBIDDEN_CHARS);
            ServletUtils.validateParameter(playerName2, DEFAULT_MAX_LENGTH, DEFAULT_FORBIDDEN_CHARS);
            checkSelfGame(playerName1, playerName2);
        } catch (IncorrectParameterException e){
            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }

        PlayerDao dao = new PlayersDAOImpl();
        dao.save(playerName1);
        dao.save(playerName2);

        resp.sendRedirect("match-score");
    }

    private static void checkSelfGame(String player1, String player2) throws IncorrectParameterException{
        if(player1.equals(player2)){
            throw new IncorrectParameterException("Игра с самим собой запрещена! Введите имя второго игрока.");
        }
    }

}
