package ovh.nassimbahri.students;

import java.sql.*;

public class Etudiant {

    private int id;
    private String nom;
    private String classe;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Etudiant() {
    }

    public Etudiant(String nom, String classe) {
        this.nom = nom;
        this.classe = classe;
    }

    public Etudiant(int id, String nom, String classe) {
        this.id = id;
        this.nom = nom;
        this.classe = classe;
    }

    /**
     * Méthode qui permet de récupérer la liste des étudiants
     * @param statement objet de type statement
     * @return liste des étudiants
     */
    public static ResultSet getAll(Statement statement){
        String sql = "SELECT * FROM etudiant";
        try {
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int add(Connection connexion){
        String sql = "INSERT INTO etudiant(nom, classe) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, classe);
            int n = preparedStatement.executeUpdate();
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
