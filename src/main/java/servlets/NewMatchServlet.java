package servlets;

import config.AppConfig;
import exceptions.IncorrectParameterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.OngoingMatchFactory;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public static final String SELF_GAME_MESSAGE = "You can't play with yourself! Enter the name of second player.";
    public static final String ENTER_PLAYER = "Enter a player's name";
    public static final String FORBIDDEN_CHAR_MSG = "Player's name might not include followed characters: ";
    public static final String TOO_LONG_NAME_MSG_START = " is too long! Player's name should consist of less than ";
    public static final String TOO_LONG_NAME_MSG_END = " characters.";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player_name_1").trim();
        String name2 = req.getParameter("player_name_2").trim();

        try {
            validateParameter(name1, AppConfig.getPlayerNameLength(), AppConfig.getForbiddenChars());
            validateParameter(name2, AppConfig.getPlayerNameLength(), AppConfig.getForbiddenChars());
            checkSelfGame(name1, name2);
        } catch (IncorrectParameterException e) {
            resp.setStatus(400);
            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }

        UUID uuid = OngoingMatchFactory.create(name1, name2);
        resp.setStatus(200);
        resp.sendRedirect("match-score?uuid=" + uuid);
    }

    private void validateParameter(String parameter, int maxLength, String forbiddenChars) throws ServletException {
        if (ValidationUtils.isNothing(parameter)) {
            throw new IncorrectParameterException(ENTER_PLAYER);
        }
        if (ValidationUtils.isLonger(parameter, maxLength)) {
            throw new IncorrectParameterException(parameter + TOO_LONG_NAME_MSG_START + maxLength + TOO_LONG_NAME_MSG_END);
        }
        if (ValidationUtils.containsForbiddenChars(parameter, forbiddenChars)) {
            throw new IncorrectParameterException(FORBIDDEN_CHAR_MSG + forbiddenChars);
        }
    }

    private static void checkSelfGame(String player1, String player2) throws IncorrectParameterException {
        if (player1.equals(player2)) {
            throw new IncorrectParameterException(SELF_GAME_MESSAGE);
        }
    }
}
