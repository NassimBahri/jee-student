package ovh.nassimbahri.students.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {

    private int id;
    private String nom;
    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
    }

    public Admin(String password, String login, String nom, int id) {
        this.password = password;
        this.login = login;
        this.nom = nom;
        this.id = id;
    }

    public static Admin connexion(Connection connexion, String login, String password){
        String sql = "SELECT * FROM admin WHERE login = ? AND password = sha1(?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                Admin admin = new Admin();
                admin.id = result.getInt("id");
                admin.nom = result.getString("nom");
                admin.login = result.getString("login");
                admin.password = result.getString("password");
                return admin;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
