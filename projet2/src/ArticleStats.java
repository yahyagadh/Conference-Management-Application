
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mghir
 */
class ArticleStats {
   private final IntegerProperty accepter;
    private final DoubleProperty taux;

    public ArticleStats(int accepter, double taux) {
        this.accepter = new SimpleIntegerProperty(accepter);
        this.taux = new SimpleDoubleProperty(taux);
    }

    public int getAccepter() {
        return accepter.get();
    }

    public IntegerProperty accepterProperty() {
        return accepter;
    }

    public void setAccepter(int accepter) {
        this.accepter.set(accepter);
    }

    public double getTaux() {
        return taux.get();
    }

    public DoubleProperty tauxProperty() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux.set(taux);
    }
}
