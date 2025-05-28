package servlets;

import config.AppConfig;
import dto.FinishedMatchesDTOWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.FinishedMatchesService;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final String EMPTY_STRING = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FinishedMatchesDTOWrapper matchesDTOWrapped;

        int page = ValidationUtils.getIntNumberOrDefault(req.getParameter("page"), DEFAULT_PAGE_NUMBER);
        String filterName = req.getParameter("filter_by_player_name");

        if (EmptyOrIncorrectParameter(filterName)) {
            matchesDTOWrapped = new FinishedMatchesService().getAll(page);
            matchesDTOWrapped.setFilterPlayerName(EMPTY_STRING);
        } else {
            matchesDTOWrapped = new FinishedMatchesService().getByName(filterName, page);
            matchesDTOWrapped.setFilterPlayerName(filterName);
        }

        resp.setStatus(200);
        req.setAttribute("matches", matchesDTOWrapped);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

    private boolean EmptyOrIncorrectParameter(String parametr) {
        if (ValidationUtils.isNothing(parametr)) {
            return true;
        }
        if (ValidationUtils.isLonger(parametr, AppConfig.getPlayerNameLength())) {
            return true;
        }
        if (ValidationUtils.containsForbiddenChars(parametr, AppConfig.getForbiddenChars())) {
            return true;
        }
        return false;
    }
}
