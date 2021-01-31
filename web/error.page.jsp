<%@ page import="static estm.dsic.sqleditor.model.Error.ErrorCode.AUTH_ERROR" %>
<%@ page import="estm.dsic.sqleditor.model.Error" %>
<%@ page import="static estm.dsic.sqleditor.model.Error.ErrorCode.SQL_ERROR" %>
<%--
  User: Mounir Bouaiche
  Date: 28/01/2021
  Time: 18:02
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Error error;
    if ((error = (Error) request.getAttribute("error")) == null) {
        response.sendRedirect("auth");
        return;
    }

    String title = "Error de " + (error.getCode() == SQL_ERROR ? "syntaxe" : "connexion");
%>
<html>
<head>
    <style>
        .w3-modal-content {
            background-color: #ff5252 !important;
            color: white !important;
        }
        span.w3-button:hover {
            background-color: #fff !important;
        }
    </style>
    <title><%= title %></title>
</head>
<body>
<% if (error.getCode() == AUTH_ERROR) { %>
<jsp:include page="/WEB-INF/auth.page.jsp"/>
<% } else { %>
<jsp:include page="/WEB-INF/sql.page.jsp"/>
<% } %>


<div id="error" class="w3-modal">
    <div class="w3-modal-content w3-animate-top w3-card-4">
        <header class="w3-container">
            <span onclick="document.getElementById('error').style.display='none'"
                  class="w3-button w3-display-topright">&times;</span>
            <h2><i class="ic-error"></i><%= title %></h2>
        </header>
        <div class="w3-container">
            <p><%= error.getMessage() %></p>
        </div>
    </div>
</div>

<script> document.getElementById('error').style.display = 'block'; </script>
</body>
</html>
