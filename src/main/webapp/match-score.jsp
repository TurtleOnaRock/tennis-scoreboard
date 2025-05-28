<%@ page import="dto.OngoingMatchDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis match score</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="all-screen-container">
<%
    OngoingMatchDTO match = (OngoingMatchDTO) request.getAttribute("match");
    String message = match.getConditionMessage().replace("\n", "<br>");
%>


<div class="header">
    <div class="header-button-box">
        <a class="button-link" href="new-match">New match</a>
        <a class="button-link" href="matches">Matches</a>
    </div>
    <h1>Match</h1>
    <h1><%= match.getPlayer1().getName()%> VS <%=match.getPlayer2().getName()%>
    </h1>
</div>

<div class="content-container">
    <div class="content-box">
        <div class="output"><%=message%></div>
        <div class="box-table-score">
            <table class="table-scoreboard">
                <tr>
                    <th colspan="2" class="player-1"><%= match.getPlayer1().getName() %>
                    </th>
                    <th colspan="2" class="player-2"><%= match.getPlayer2().getName() %>
                    </th>
                </tr>
                <tr>
                    <td>Point</td>
                    <td class="value player-1"><%= match.getPlayer1().getPoint()%>
                    </td>
                    <td class="value player-2"><%= match.getPlayer2().getPoint()%>
                    </td>
                    <td>Point</td>
                </tr>
                <tr>
                    <td>Game</td>
                    <td class="value player-1"><%= match.getPlayer1().getGame()%>
                    </td>
                    <td class="value player-2"><%= match.getPlayer2().getGame()%>
                    </td>
                    <td>Game</td>
                </tr>
                <tr>
                    <td>Set</td>
                    <td class="value player-1"><%= match.getPlayer1().getSet()%>
                    </td>
                    <td class="value player-2"><%= match.getPlayer2().getSet()%>
                    </td>
                    <td>Set</td>
                </tr>
                <% if (!match.isCompleted()) { %>
                <tr>
                    <td colspan="2" class="player-1">
                        <form action="match-score" method="post">
                            <input type="hidden" name="pointWinner" value="<%= match.getPlayer1().getId() %>">
                            <input type="hidden" name="uuid" value="<%=match.getUuid() %>">
                            <button type="submit" class="button-form-large">point</button>
                        </form>
                    </td>
                    <td colspan="2" class="player-2">
                        <form action="match-score" method="post">
                            <input type="hidden" name="pointWinner" value="<%=match.getPlayer2().getId()%>">
                            <input type="hidden" name="uuid" value="<%=match.getUuid()%>">
                            <button type="submit" class="button-form-large">point</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>

<div class="footer">
    <p>made by <a href="author.jsp">A6e3iana</a></p>
</div>
</body>
</html>
