<%--
  User: Mounir Bouaiche
  Date: 28/01/2021
  Time: 17:44
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/atelier.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/my-icons/myicons.css">
    <title>Authentification</title>
    <style>
        body {
            background-image: url("${pageContext.request.contextPath}/assets/Abstract-Envelope.svg");
            background-size: cover;
            background-attachment: fixed;
            min-height: calc(100vh - 12px);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 6px;
        }

        main {
            width: 95vw !important;
            max-width: 400px;
        }
    </style>
</head>
<body>
<main class="w3-card-4 white">
    <div class="w3-container w3-border-bottom">
        <h3>Authentification</h3>
    </div>
    <form class="w3-panel" action="auth" method="POST">
        <label class="required">
            <i class='ic-user'></i>
            Nom d'Utilisateur
        </label>
        <input class="w3-input w3-border w3-margin-bottom" name="username"
               placeholder="Entrer nom d'utilisateur" required>
        <label class="required">
            <i class='ic-key'></i>
            Mot de passe
        </label>
        <input type="password" class="w3-input w3-border w3-margin-bottom" name="password"
               placeholder="Entrer le mot de passe" required>
        <div class="blue w3-container w3-btn" style="display: inline-block">
            <i class='ic-connect'></i>
            <input class="w3-btn w3-hover-none" type="submit" value="Connecter">
        </div>
    </form>
</main>
</body>
</html>
