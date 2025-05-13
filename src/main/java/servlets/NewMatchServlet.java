package servlets;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import exceptions.IncorrectParameterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public static final int DEFAULT_MAX_LENGTH=50;
    public static final String DEFAULT_FORBIDDEN_CHARS = ";({[)]}";
    public static final String PROPERTIES_PATH = "config.properties";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        int maxParameterLength = DEFAULT_MAX_LENGTH;
        String forbiddenChars = DEFAULT_FORBIDDEN_CHARS;
        try {
            getProperties(maxParameterLength, forbiddenChars);
        } catch (IOException e){
        }

        String playerName1 = req.getParameter("player_name_1");
        String playerName2 = req.getParameter("player_name_2");

        try {
            ServletUtils.validateParameter(playerName1, maxParameterLength, forbiddenChars);
            ServletUtils.validateParameter(playerName2, maxParameterLength, forbiddenChars);
        } catch (IncorrectParameterException e){
            req.setAttribute("errorMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }

        PlayerDao dao = new PlayersDAOImpl();
        dao.save(playerName1);
        dao.save(playerName2);
        getServletContext().getRequestDispatcher("/match-score").forward(req, resp);
    }

    private static void getProperties(int maxParameterLength, String forbiddenChars) throws IOException{
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream(PROPERTIES_PATH)){
            properties.load(input);
            maxParameterLength = Integer.valueOf(properties.getProperty("player_name_max_length"));
            forbiddenChars = properties.getProperty("forbidden_chars");
        }
    }

}
