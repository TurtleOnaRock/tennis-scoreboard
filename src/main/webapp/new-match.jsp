<%@ page import="config.AppConfig" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css"/>
    <title>New Match</title>
</head>
<% int nameLength = AppConfig.getPlayerNameLength();%>
<body class="all-screen-container">

<div class="header">
    <div class="header-button-box">
        <a class="button-link" href="new-match">New match</a>
        <a class="button-link" href="matches">Matches</a>
    </div>
    <h1>Registration</h1>
</div>

<div class="content-container">
    <div class="content-box">
        <form class="form_new_players" action="new-match" method="post">
            <label for="player_name_1">Player 1</label>
            <input type="text"
                   maxlength="<%=nameLength%>"
                   id="player_name_1"
                   name="player_name_1"
                   placeholder="player's name"/>
            <label for="player_name_2">Player 2</label>
            <input type="text"
                   maxlength="<%=nameLength%>"
                   id="player_name_2"
                   name="player_name_2"
                   placeholder="player's name"/>
            <button class="button-form-large" type="submit">Send</button>
        </form>
        <div class="error-box">
            <% String error = (String) request.getAttribute("errorMessage");%>
            <% if (error != null) { %>
            <%=error%>
            <% } %>
        </div>
    </div>
</div>

<div class="footer">
    <p>made by <a href="author.jsp">A6e3iana</a></p>
</div>

</body>
</html>