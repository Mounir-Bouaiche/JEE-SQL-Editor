<%@ page import="estm.dsic.sqleditor.service.AuthService" %>
<%@ page import="java.sql.SQLException" %>
<%--
  User: Mounir Bouaiche
  Date: 28/01/2021
  Time: 18:03
--%>
<%
    if (request.getParameter("logout") != null) {
        out.println("Ok");
        try {
            AuthService.logout();
        } catch (SQLException ignored) {
        }
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath() + "/auth");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/atelier.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/my-icons/myicons.css">
    <style>
        body {
            background-image: url(${pageContext.request.contextPath}/assets/Pattern-Randomized.svg);
            background-attachment: fixed;
            background-size: cover;
            min-height: 100vh;
        }

        header form {
            height: 100%;
            display: flex !important;
            align-items: center !important;
            justify-content: center !important;
        }
    </style>
    <title>SQL Editor</title>
</head>
<body>

<header class="w3-container blue w3-display-container w3-border-bottom">
    <h1 class="w3-center">SQL Editor</h1>
    <form class="w3-display-topright w3-btn" onclick="this.submit()">
        <i class="ic-disconnect"></i>
        <span>Déconnecter</span>
        <input name="logout" value="a" hidden>
    </form>
</header>
<main class="w3-panel">
    <form onsubmit="checkQuery(event)"
          class="w3-container white w3-content w3-padding w3-padding-16 w3-card-4 w3-border"
          action="editor" method="POST">
        <label><i class="ic-edit"></i>Requete</label>
        <textarea class="w3-input w3-border w3-margin-bottom" name="query"
                  placeholder="Entrer votre requete SQL ici." style="resize: none"></textarea>
        <div class="w3-right w3-container w3-btn teal">
            <i class="ic-run"></i><input type="submit" class="w3-btn w3-hover-none" value="Exécuter">
        </div>
    </form>
</main>

<%
    String result;
    if ((result = (String) request.getAttribute("result")) != null) {
%>
<div class="w3-content w3-panel w3-card-4 w3-animate-top" style="padding: 0; width: 96vw">
    <header class="w3-container blue">
        <h1 class="w3-center">Execution Resultat</h1>
    </header>
    <%=result%>
</div>
<% } %>
<script>
    const tx = document.querySelector('textarea');
    tx.style.height = tx.scrollHeight + 'px';
    tx.style.overflowY = 'hidden';
    tx.addEventListener("input", () => {
        tx.style.height = 'auto';
        tx.style.height = (tx.scrollHeight) + 'px';
    }, false);

    const regExCreate = /^CREATE\s+TABLE\s+('?)[^'\d\s]+\w*\1\s*\((\s*[^'\d\s]+\w*\s+(((VARCHAR2?|INTEGER|CHAR)(\(\d+\))?)|(DATE|DATETIME)|(NUMBER(\(\d+(,\d+)?\))?))(\s+DEFAULT\s+('?)\w+\12)?(\s+(NOT)?\s+NULL)?(\s+PRIMARY KEY)?\s*(,(?!$)|\);?$))+/gim;
    const regExSelectDelete = /^(SELECT\s+(\*|([A-Z_]+\w*\s*(,|\s+)?)+)|DELETE)\s+FROM\s+[A-Z_]+\w*(\s*;?$|\s+WHERE(\s+[A-Z_]+\w*\s*(=|<[>=]?|>=?|\s+LIKE\s+)\s*[A-Z_]+\w*(\s+(AND|OR)(?!$)|\s*;?$))+)/gim;
    const regExUpdate = /^UPDATE\s+[A-Z_]\w*\s+SET\s+(\s*[A-Z_]\w*\s*=\s*\w+\s*(,(?!$)|\s*(?=WHERE|.$)))+(WHERE(\s+[A-Z_]+\w*\s*(=|<[>=]?|>=?|\s+LIKE\s+)\s*\w+(\s+(AND|OR)(?!$)|\s*;?$))+)?/gim;

    function checkQuery(event) {
        const v = tx.value;
        if (v.match(regExCreate) || v.match(regExSelectDelete) || v.match(regExUpdate)) {
            tx.value = tx.value.replaceAll(";", "");
            return true;
        } else {
            const p = confirm("Cette requete peut generer une erreur. Voulez-vous executer cette requete ?");
            if (!p) event.preventDefault();
            return p;
        }
    }
</script>
</body>
</html>
