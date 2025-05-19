<%@ page import="dto.TennisMatchDTO" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tennis match score</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    TennisMatchDTO match = (TennisMatchDTO) request.getAttribute("match");
    String message = match.getConditionMessage().replace("\n", "<br>");
%>

<div class="all-screan-container">

    <div class="header">
        <a class="link-button-big" href="new-match">New match</a>
        <div class="flex-box-header">
            <h1>Match</h1>
            <h1><%=match.getPlayer1().getName()%> VS <%= match.getPlayer2().getName()%></h1>
        </div>
        <a class="link-button-big" href="matches">Matches</a>
    </div>

    <div class="flex-container">
        <div class="box-main">
            <div class="output"><%=message%></div>
            <table>
                <tr>
                    <th colspan="2" class="player-1"><%= match.getPlayer1().getName() %></th>
                    <td colspan="2" class="player-2"><%= match.getPlayer2().getName() %></td>
                </tr>
                <tr>
                    <td>Point</td>
                    <td class="value player-1"><%= match.getPlayer1().getPoint()%></td>
                    <td class="value player-2"><%= match.getPlayer2().getPoint()%></td>
                    <td>Point</td>
                </tr>
                <tr>
                    <td>Game</td>
                    <td class="value player-1"><%= match.getPlayer1().getGame()%></td>
                    <td class="value player-2"><%= match.getPlayer2().getGame()%></td>
                    <td>Game</td>
                </tr>
                <tr>
                    <td>Set</td>
                    <td class="value player-1"><%= match.getPlayer1().getSet()%></td>
                    <td class="value player-2"><%= match.getPlayer2().getSet()%></td>
                    <td>Set</td>
                </tr>
                <% if (!match.isCompleted()) { %>
                <tr>
                    <td colspan="2" class="player-1">
                        <form action="match-score"  method="post">
                            <input type="hidden" name="pointWinner" value="<%= match.getPlayer1().getId() %>">
                            <input type="hidden" name="uuid" value="<%=match.getUuid() %>">
                            <button type="submit" class="button-small">point</button>
                        </form>
                    </td>
                    <td colspan="2" class="player-2">
                        <form action="match-score" method="post">
                            <input type="hidden" name="pointWinner" value="<%=match.getPlayer2().getId()%>">
                            <input type="hidden" name="uuid" value="<%=match.getUuid()%>">
                            <button type="submit" class="button-small">point</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>

    <div class="footer">
        <p>made by <a href="author.jsp">A6e3iana</a></p>
    </div>
</div>
</body>
</html>
