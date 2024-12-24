/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mghir
 */
public class Article {
    private int idArticle;
    private String titre;
    private String nomAuteur;
    private String mailAuteur;
    private String etat;
       private int commiteid;
   

    public Article(int idArticle, String titre, String nomAuteur, String mailAuteur, String etat) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.mailAuteur = mailAuteur;
        this.etat = etat;
      
    }

    public Article(int idArticle, String titre, String nomAuteur, String mailAuteur, String etat, int commiteid) {
        this.idArticle = idArticle;
        this.titre = titre;
        this.nomAuteur = nomAuteur;
        this.mailAuteur = mailAuteur;
        this.etat = etat;
        this.commiteid = commiteid;
    }

    public int getCommiteid() {
        return commiteid;
    }

    public void setCommiteid(int commiteid) {
        this.commiteid = commiteid;
    }
    

    Article(int idArticle, String titre, String auteur, String etat) {
         this.idArticle = idArticle;
        this.titre = titre;
        this.nomAuteur = auteur;
        this.etat = etat;
    }

 


    // Getters and setters
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public String getMailAuteur() {
        return mailAuteur;
    }

    public void setMailAuteur(String mailAuteur) {
        this.mailAuteur = mailAuteur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
    

