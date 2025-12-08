<%@ page import="ovh.nassimbahri.students.utils.Flash" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un étudiant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

</head>
<body>
<%@ include file="includes/menu.jsp"%>

<main style="margin-top: 90px;">
    <div class="container">
        <%= Flash.show(session) %>
        <h1>Ajouter un étudiant</h1>
        <form method="post" action="./">
            <div>
                <label for="nom" class="form-label">Nom de l'étudiant</label>
                <input type="text" class="form-control" name="nom" id="nom">
            </div>
            <div>
                <label for="classe" class="form-label">Classe de l'étudiant</label>
                <input type="text" class="form-control" name="classe" id="classe">
            </div>
            <div>
                <input type="submit" class="btn btn-primary" value="Ajouter">
                <input type="reset" class="btn btn-danger" value="Vider">
            </div>
        </form>
    </div>
</main>
</body>
</html>