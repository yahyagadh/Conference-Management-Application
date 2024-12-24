
import java.time.LocalDate;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mghir
 */
public class ConferenceData {

   private  String inst;
  private  String president;
  private  String titre;
  private  Date date;
  private  String lieu;
  private  String topics;
  private  String memrecomsc;
  private String membrecomorg;
  private  Date datelimsoum;
  private   Date datelimins;
  private  String montant;


    public ConferenceData(String inst, String president, String titre, Date date, String lieu, String topics, String memrecomsc, String membrecomorg, Date datelimsoum, Date datelimins, String montant) {
        this.inst = inst;
        this.president = president;
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.topics = topics;
        this.memrecomsc = memrecomsc;
        this.membrecomorg = membrecomorg;
        this.datelimsoum = datelimsoum;
        this.datelimins = datelimins;
        this.montant = montant;
    }





    

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getMemrecomsc() {
        return memrecomsc;
    }

    public void setMemrecomsc(String memrecomsc) {
        this.memrecomsc = memrecomsc;
    }

    public String getMembrecomorg() {
        return membrecomorg;
    }

    public void setMembrecomorg(String membrecomorg) {
        this.membrecomorg = membrecomorg;
    }

    public Date getDatelimsoum() {
        return datelimsoum;
    }

    public void setDatelimsoum(Date datelimsoum) {
        this.datelimsoum = datelimsoum;
    }

    public Date getDatelimins() {
        return datelimins;
    }

    public void setDatelimins(Date datelimins) {
        this.datelimins = datelimins;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }



  

}
    

