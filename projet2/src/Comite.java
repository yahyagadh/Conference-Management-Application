import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comite {
    private StringProperty idComite;
    private StringProperty nom;
    private StringProperty emailAuteur;
    private StringProperty role;
    private StringProperty idArticle;

    public Comite(String idComite, String nom, String emailAuteur) {
        this.idComite = new SimpleStringProperty(idComite);
        this.nom = new SimpleStringProperty(nom);
        this.emailAuteur = new SimpleStringProperty(emailAuteur);
      
    }

    // Getters and setters for JavaFX properties
    public StringProperty getIdComiteProperty() {
        return idComite;
    }

    public String getIdComite() {
        return idComite.get();
    }

    public void setIdComite(String idComite) {
        this.idComite.set(idComite);
    }

    public StringProperty getNomProperty() {
        return nom;
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty getEmailAuteurProperty() {
        return emailAuteur;
    }

    public String getEmailAuteur() {
        return emailAuteur.get();
    }

    public void setEmailAuteur(String emailAuteur) {
        this.emailAuteur.set(emailAuteur);
    }

    public StringProperty getRoleProperty() {
        return role;
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public StringProperty getIdArticleProperty() {
        return idArticle;
    }

    public String getIdArticle() {
        return idArticle.get();
    }

    public void setIdArticle(String idArticle) {
        this.idArticle.set(idArticle);
    }
}