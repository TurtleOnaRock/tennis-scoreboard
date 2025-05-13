<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>New Match</title>
</head>
<body>
    <div class="container">
        <div class="input_form_box">
            <form class="form_new_players" action="/tennis-scoreboard/new-match.jsp" method="post">
                <label for="player_name_1">Имя игрока 1</label>
                <input type="text" id="player_name_1" name="player_name_1" maxlength="40">
                <label for="player_name_2">Имя игрока 2</label>
                <input type="text" id="player_name_2" name="player_name_2" maxlength="40">
                <button type="submit"> Отправить</button>
            </form>
            <div class="error-box">
                <p>${errorMessage}</p>
            </div>
        </div>
    </div>
</body>
</html>