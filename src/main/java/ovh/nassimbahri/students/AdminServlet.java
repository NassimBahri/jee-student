package ovh.nassimbahri.students;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ovh.nassimbahri.students.models.Admin;
import ovh.nassimbahri.students.utils.Flash;

import java.io.IOException;

@WebServlet(name="AdminServlet", value="/user")
public class AdminServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("logout") != null){
            HttpSession session = request.getSession();
            session.removeAttribute("admin");
            response.sendRedirect("./");
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login.equals("") || password.equals("")) {
            Flash.create(request, "echec", "Veuillez saisir vos clés d'accès.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        Admin admin = Admin.connexion(connexion, login, password);
        if(admin == null){
            Flash.create(request, "echec", "Vérifier vos clés d'accès.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        else{
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);
            response.sendRedirect("./");
        }
    }
}
