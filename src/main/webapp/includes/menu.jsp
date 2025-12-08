<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ovh.nassimbahri.students.models.Admin" %><%
Admin admin = (Admin)session.getAttribute("admin");
%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">B.U</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="./">Accueil</a>
                    </li>
                    <li class="nav-item">
                        <% if (admin == null){ %>
                        <a class="nav-link active" aria-current="page" href="./user">Se connecter</a>
                        <% }else{ %>
                            <a class="nav-link active">
                                Bonjour <%= admin.getNom() %>
                                (<a href="./user?logout">Déconnexion</a>)
                            </a>
                        <% } %>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>