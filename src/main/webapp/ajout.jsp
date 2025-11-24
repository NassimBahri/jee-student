<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un étudiant</title>
</head>
<body>
<%

    if(session.getAttribute("message") != null){
        out.println(session.getAttribute("message"));
        session.removeAttribute("message");
    }
%>
    <h1>Ajouter un étudiant</h1>
    <form method="post" action="./">
        <p>
            <label for="nom">Nom de l'étudiant</label>
            <input type="text" name="nom" id="nom">
        </p>
        <p>
            <label for="classe">Classe de l'étudiant</label>
            <input type="text" name="classe" id="classe">
        </p>
        <p>
            <input type="submit" value="Ajouter">
            <input type="reset" value="Vider">
        </p>
    </form>
</body>
</html>