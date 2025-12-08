package ovh.nassimbahri.students;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ovh.nassimbahri.students.models.Admin;
import ovh.nassimbahri.students.models.Etudiant;
import ovh.nassimbahri.students.utils.Flash;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="StudentServlet", value="/")
public class StudentServlet extends BaseServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(request.getParameter("del") != null && admin.is("admin")){
           doDelete(request, response);
        }else if(request.getParameter("update") != null && admin.is("admin")){
            doPut(request, response);
        }else if(request.getMethod().equals("POST") && admin.isLogged()){
            doPost(request, response);
        }
        else{
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultSet result = Etudiant.getAll(statement);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index_page.jsp");
        request.setAttribute("etudiants", result);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String classe = request.getParameter("classe");
        PrintWriter out = response.getWriter();
        if(nom == null || nom.equals("") || classe == null || classe.equals("")) {
            Flash.create(request, "echec", "Erreur! Veuillez remplir tous les champs.");
            response.sendRedirect("./ajout.jsp");
        }
        else{
            Etudiant etudiant = new Etudiant(nom, classe);
            int n = etudiant.add(connexion);
            if(n == 1){
                Flash.create(request, "success", "Bravo! cet étudiant a bien été ajouté.");

                response.sendRedirect("./");
            }
            else{
                out.println("Echec!!!");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(Integer.parseInt(request.getParameter("id")));
        int n = etudiant.delete(connexion);
        PrintWriter out = response.getWriter();
        if(n == 1){
            Flash.create(request, "success", "Bravo! cet étudiant a bien été supprimé");
            response.sendRedirect("./");
        }
        else{
            out.println("Echec!!!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = Etudiant.getById(connexion, id);
        if(etudiant == null){
            System.out.println("non existant!");
            return;
        }
        if (request.getMethod().equals("POST")){
            String nom = request.getParameter("nom");
            String classe = request.getParameter("classe");
            if(nom == null || nom.equals("") || classe == null || classe.equals("")) {
                Flash.create(request, "echec", "Erreur! Veuillez remplir tous les champs.");
            }
            else{
                etudiant.setNom(nom);
                etudiant.setClasse(classe);
                int n = etudiant.update(connexion);
                if(n == 1){
                    Flash.create(request, "success", "Bravo! cet étudiant a bien été modifié.");
                    response.sendRedirect("./");
                    return;
                }
                else{
                    out.println("Echec!!!");
                }
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("etudiant", etudiant);
        dispatcher.forward(request, response);
    }
}
