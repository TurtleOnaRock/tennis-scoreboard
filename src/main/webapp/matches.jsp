<%@ page import="dto.FinishedMatchDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.FinishedMatchesDTOWrapper" %>
<%@ page import="config.AppConfig" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css">
    <title>Matches</title>
</head>
<body class="all-screen-container">
<%  FinishedMatchesDTOWrapper matchesDTOWrapped = (FinishedMatchesDTOWrapper) request.getAttribute("matches");
    List<FinishedMatchDTO> matchesDTO = matchesDTOWrapped.getFinishedMatchesDto();
    int currentPage = matchesDTOWrapped.getCurrentPage();
    int totalPage = matchesDTOWrapped.getTotalPages();
    String filterPlayerName = matchesDTOWrapped.getFilterPlayerName();
%>

<div class="header">
    <div class="header-button-box">
        <a class="button-link" href="new-match">New match</a>
        <a class="button-link" href="matches">Matches</a>
    </div>
    <h1>Finished Matches</h1>
</div>

<div class="content-container">
    <div class="content-box">
        <form action="matches" method="get">
            <input class="search-match"
                   type="text"
                   maxlength="<%=AppConfig.getPlayerNameLength()%>"
                   placeholder="player's name"
                   name="filter_by_player_name">
            <button class="button-form-large" type="submit">Search</button>
        </form>
        <table class="table-matches">
            <thead>
                <th>id</th>
                <th>Player_1</th>
                <th>Player_2</th>
                <th>Winner</th>
            </thead>
            <tbody>
            <% if (matchesDTO.isEmpty()) { %>
                <tr>
                    <td colspan="4">There is no competed match of <%=filterPlayerName.isEmpty() ? "anybody." : filterPlayerName%>.</td>
                </tr>
            <% } else {%>
                <% for (FinishedMatchDTO matchDTO : matchesDTO) { %>
                <tr>
                    <td><%=matchDTO.getId()%>
                    </td>
                    <td><%=matchDTO.getNameOfPlayer1()%>
                    </td>
                    <td><%=matchDTO.getNameOfPlayer2()%>
                    </td>
                    <td><%=matchDTO.getNameOfWinner()%>
                    </td>
                </tr>
                <% } %>
            <% } %>
            </tbody>
        </table>
        <div class="page-box">
            <form>
                <input type="hidden"
                       name="page"
                       value="<%=currentPage-1%>">
                <input type="hidden"
                       name="filter_by_player_name"
                       value="<%=filterPlayerName%>">
                <button class="button-form-small"><</button>
            </form>
            <p><%=currentPage%>/<%=totalPage%></p>
            <form>
                <input type="hidden"
                       name="page"
                       value="<%=currentPage+1%>">
                <input type="hidden"
                       name="filter_by_player_name"
                       value="<%=filterPlayerName%>">
                <button class="button-form-small">></button>
            </form>
            <form>
                <input class="form-page"
                       type="number"
                       min="1"
                       max="<%=totalPage%>"
                       placeholder="page"
                       name="page">
                <input type="hidden"
                       name="filter_by_player_name"
                       value="<%=filterPlayerName%>">
                <button class="button-form-large">Page</button>
            </form>
        </div>
    </div>
</div>

<div class="footer">
    <p>made by <a href="author.jsp">A6e3iana</a></p>
</div>

</body>
</html>