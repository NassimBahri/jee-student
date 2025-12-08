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

        <form method="post" action="./user">
            <div class="mb-3">
                <label for="login" class="form-label">Login</label>
                <input type="text" class="form-control" id="login" name="login">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mot de passe</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <button type="submit" class="btn btn-primary">Connexion</button>
        </form>

    </div>
</main>

</body>
</html>