<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/styles.css"/>
    <title>New Match</title>
</head>
<body>
    <div class="container">
        <div class="input_form_box">
            <form class="form_new_players" action="new-match" method="post">
                <label for="player_name_1">Имя игрока 1</label>
                <input type="text" id="player_name_1" name="player_name_1" placeholder="Имя игрока 1" />
                <label for="player_name_2">Имя игрока 2</label>
                <input type="text" id="player_name_2" name="player_name_2" placeholder="Имя игрока 2"/>
                <button type="submit"> Отправить</button>
            </form>
            <div class="error-box">
                <% String error = (String) request.getAttribute("errorMessage");%>
                <% if(error!=null){ %>
                    <%=error%>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>