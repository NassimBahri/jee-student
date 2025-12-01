package ovh.nassimbahri.students;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name="StudentServlet", value="/")
public class StudentServlet extends HttpServlet {

    private Connection connexion;
    private Statement statement;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://" + Config.URL + "/" + Config.DATABASE, Config.USER, Config.PASSWORD);
            statement = connexion.createStatement();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            statement.close();
            connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("del") != null){
           doDelete(request, response);
        }else if(request.getParameter("update") != null){
            doPut(request, response);
        }else if(request.getParameter("edit") != null){
            modifier(request, response);
        } else if(request.getMethod().equals("POST")){
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
        HttpSession session = request.getSession();
        if(nom == null || nom.equals("") || classe == null || classe.equals("")) {
            session.setAttribute("message", "<div style=\"color:red;border: solid 1px red; padding: 10px;\">Erreur! Veuillez remplir tous les champs.</div>");
            response.sendRedirect("./ajout.jsp");
        }
        else{
            Etudiant etudiant = new Etudiant(nom, classe);
            int n = etudiant.add(connexion);
            if(n == 1){
                session.setAttribute("message", "<div style=\"color:green;border: solid 1px green; padding: 10px;\">Bravo! cet étudiant a bien été ajouté.</div>");
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
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if(n == 1){
            session.setAttribute("message", "<div style=\"color:green;border: solid 1px green; padding: 10px;\">Bravo! cet étudiant a bien été supprimé.</div>");
            response.sendRedirect("./");
        }
        else{
            out.println("Echec!!!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = Etudiant.getById(connexion, id);
        if(etudiant == null){
            System.out.println("non existant!");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("etudiant", etudiant);
        dispatcher.forward(request, response);
    }

    protected void modifier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = Etudiant.getById(connexion, id);
        if(etudiant == null){
            System.out.println("non existant!");
            return;
        }
        String nom = request.getParameter("nom");
        String classe = request.getParameter("classe");
        if(nom == null || nom.equals("") || classe == null || classe.equals("")) {
            session.setAttribute("message", "<div style=\"color:red;border: solid 1px red; padding: 10px;\">Erreur! Veuillez remplir tous les champs.</div>");
            response.sendRedirect("./?update&id=" + etudiant.getId());
        }
        else{
            etudiant.setNom(nom);
            etudiant.setClasse(classe);
            int n = etudiant.update(connexion);
            if(n == 1){
                session.setAttribute("message", "<div style=\"color:green;border: solid 1px green; padding: 10px;\">Bravo! cet étudiant a bien été modifié.</div>");
                response.sendRedirect("./");
            }
            else{
                out.println("Echec!!!");
            }
        }
    }
}
