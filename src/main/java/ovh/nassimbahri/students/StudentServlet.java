package ovh.nassimbahri.students;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            out.println("Veuillez remplir tous les champs");
        }
        else{
            Etudiant etudiant = new Etudiant(nom, classe);
            int n = etudiant.add(connexion);
            if(n == 1){
                out.println("Bravo! ajout avec succès.");
            }
            else{
                out.println("Echec!!!");
            }
        }
    }
}
