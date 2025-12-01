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
        String sql = "SELECT * FROM etudiant ORDER BY id DESC";
        try {
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode qui permet d'ajouter un nouvel étudiant
     * @param connexion Objet représentant la connexion à la base de données
     * @return 1 si l'étudiant a bien été inséré
     */
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

    public int delete(Connection connexion){
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int n = preparedStatement.executeUpdate();
            return n;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Etudiant getById(Connection connexion, int id){
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        try {
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.id = result.getInt("id");
                etudiant.nom = result.getString("nom");
                etudiant.classe = result.getString("classe");
                return etudiant;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
