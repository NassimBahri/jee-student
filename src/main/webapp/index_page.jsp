<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
</head>
<body>
<p>
    <a href="ajout.jsp">Ajouter un étudiant</a>
</p>

<h1>Liste des étudiants</h1>
<% ResultSet etudiants = (ResultSet) request.getAttribute("etudiants"); %>
<table border="1">
    <tr>
        <th>#</th>
        <th>Nom</th>
        <th>Classe</th>
    </tr>
    <% while(etudiants.next()){
    %>
    <tr>
        <td><%= etudiants.getInt("id") %></td>
        <td><%= etudiants.getString("nom") %></td>
        <td><%= etudiants.getString("classe") %></td>
    </tr>
    <%
    } %>
</table>
</body>
</html>