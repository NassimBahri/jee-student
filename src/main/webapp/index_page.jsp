<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
</head>
<body>

<%

if(session.getAttribute("message") != null){
    out.println(session.getAttribute("message"));
    session.removeAttribute("message");
}
%>


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
        <th>Supprimer</th>
        <th>Modifier</th>
    </tr>
    <% while(etudiants.next()){
    %>
    <tr>
        <td><%= etudiants.getInt("id") %></td>
        <td><%= etudiants.getString("nom") %></td>
        <td><%= etudiants.getString("classe") %></td>
        <td><a href="./?del&id=<%= etudiants.getInt("id") %>" onclick="return confirm('êtes vous sûr ?')">Supprimer</a></td>
        <td><a href="./?update&id=<%= etudiants.getInt("id") %>">Modifier</a></td>
    </tr>
    <%
    } %>
</table>
</body>
</html>