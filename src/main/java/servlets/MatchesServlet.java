package servlets;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FinishedMatchesDTOWrapper matchesDTOWrapped;

        int page = getPageNumber(req.getParameter("page"));
        String filterName = req.getParameter("filter_by_player_name");

        if (filterName == null || filterName.isEmpty()) {
            matchesDTOWrapped = new FinishedMatchesService().getAll(page);
            matchesDTOWrapped.setFilterPlayerName("");
        } else {
            matchesDTOWrapped = new FinishedMatchesService().getByName(filterName, page);
            matchesDTOWrapped.setFilterPlayerName(filterName);
        }

        req.setAttribute("matches", matchesDTOWrapped);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

    private int getPageNumber(String pageParameter){
        if(pageParameter == null || pageParameter.isEmpty()){
            return 1;
        }
        if(!onlyDigits(pageParameter)){
            return 1;
        }
        return Integer.parseInt(pageParameter);
    }

    private boolean onlyDigits(String parameter){
        return parameter.matches("[0123456789]+");
    }

}
