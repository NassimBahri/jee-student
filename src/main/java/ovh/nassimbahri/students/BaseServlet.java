package ovh.nassimbahri.students;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import ovh.nassimbahri.students.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseServlet extends HttpServlet {

    protected Connection connexion;
    protected Statement statement;

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

}
