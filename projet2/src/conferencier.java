/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mghir
 */
public class conferencier {
    
    private String nom;
    private String prenom;
    private String paysOrigine;
    private String institution;
    private String titrePresentation;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getTitrePresentation() {
        return titrePresentation;
    }

    public void setTitrePresentation(String titrePresentation) {
        this.titrePresentation = titrePresentation;
    }

    public conferencier( String nom, String prenom, String paysOrigine, String institution, String titrePresentation) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.paysOrigine = paysOrigine;
        this.institution = institution;
        this.titrePresentation = titrePresentation;

         
    }

 


 

 

  
    
    
}
