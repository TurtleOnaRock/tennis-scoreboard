<%@ page import="dto.FinishedMatchDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css">
    <title>Matches</title>
</head>
<body>
<% List<FinishedMatchDTO> matches = (List<FinishedMatchDTO>) request.getAttribute("matches"); %>
<div class="all-screan-container">

    <div class="header">
        <a class="link-button-big" href="new-match">New match</a>
        <h1>Matches</h1>
        <a class="link-button-big" href="matches">Matches</a>
    </div>

    <div class="flex-container">
        <div class="box-main">
            <h1>
                <% if(matches.isEmpty()) { %>
                    <p>There is no any competed match.</p>
                <% } else {%>
                <form action="matches" method="get">
                    <input type="text"
                           placeholder="search by player name"
                           name="filter_by_player_name">
                    <button type="submit">Search</button>
                </form>
                <table class="table-matches">
                    <thead>
                        <th>id</th>
                        <th>Player_1</th>
                        <th>Player_2</th>
                        <th>Winner</th>
                    </thead>
                    <tbody>
                   <% for (FinishedMatchDTO matchDTO : matches) { %>
                    <tr>
                        <td><%=matchDTO.getId()%></td>
                        <td><%=matchDTO.getNameOfPlayer1()%></td>
                        <td><%=matchDTO.getNameOfPlayer2()%></td>
                        <td><%=matchDTO.getNameOfWinner()%></td>
                    </tr>
                    <% } %>
                    </tbody>
                <% } %>
                </table>
            </h1>
        </div>
    </div>

    <div class="footer">
        <p>made by <a href="author.jsp">A6e3iana</a></p>
    </div>

</div>
</body>
</html>