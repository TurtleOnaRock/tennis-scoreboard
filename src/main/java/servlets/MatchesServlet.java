package servlets;

import dto.FinishedMatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.FinishedMatchesService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FinishedMatchDTO> matches;

//        int page = Integer.parseInt(req.getParameter("page"));
        String filterName = req.getParameter("filter_by_player_name");

        if (filterName == null || filterName.isEmpty()) {
            matches = new FinishedMatchesService().getAll();
        } else {
            matches = new FinishedMatchesService().getByName(filterName);
        }

        req.setAttribute("matches", matches);
        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
