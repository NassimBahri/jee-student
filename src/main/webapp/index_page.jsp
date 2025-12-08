<%@ page import="java.sql.ResultSet" %>
<%@ page import="ovh.nassimbahri.students.utils.Flash" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
    <link rel="stylesheet" href="assets/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>

<%@ include file="includes/menu.jsp"%>

<main style="margin-top: 90px;">
    <div class="container">


        <%= Flash.show(session) %>

        <% if (admin.isLogged() ){ %>
        <p>
            <a href="ajout.jsp" class="btn btn-primary">Ajouter un étudiant</a>
        </p>
        <% } %>

        <h1>Liste des étudiants</h1>
        <% ResultSet etudiants = (ResultSet) request.getAttribute("etudiants"); %>
        <table class="table table-striped">
            <tr>
                <th>#</th>
                <th>Nom</th>
                <th>Classe</th>
                <% if(admin.is("admin")){ %>
                <th>Supprimer</th>
                <th>Modifier</th>
                <% } %>
            </tr>
            <% while(etudiants.next()){
            %>
            <tr>
                <td><%= etudiants.getInt("id") %></td>
                <td><%= etudiants.getString("nom") %></td>
                <td><%= etudiants.getString("classe") %></td>
                <% if(admin.is("admin")){ %>
                <td><a href="./?del&id=<%= etudiants.getInt("id") %>" onclick="return confirm('êtes vous sûr ?')">Supprimer</a></td>
                <td><a href="./?update&id=<%= etudiants.getInt("id") %>">Modifier</a></td>
                <% } %>
            </tr>
            <%
                } %>
        </table>
    </div>
</main>

</body>
</html>