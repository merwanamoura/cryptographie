/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amoura_merwan
 */
public class Noeud {
    
    double poids;
    String val="";
    Noeud filsGauche;
    Noeud filsDroit;
    boolean estPris;
    boolean estUneFeuille;

    

    public Noeud(double poids) {
        this.poids = poids;
        estPris = false;
        filsDroit=null;
        filsGauche=null;
        estUneFeuille=false;
    }
    
    public Noeud(String valeur, double pds){
        val = valeur;
        poids = pds;
        estPris = false;
        estUneFeuille=true;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Noeud getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(Noeud filsGauche) {
        this.filsGauche = filsGauche;
    }

    public Noeud getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(Noeud filsDroit) {
        this.filsDroit = filsDroit;
    }
    
    
    public boolean isEstPris() {
        return estPris;
    }

    public void setEstPris(boolean estPris) {
        this.estPris = estPris;
    }

    public boolean isEstUneFeuille() {
        return estUneFeuille;
    }

    public void setEstUneFeuille(boolean estUneFeuille) {
        this.estUneFeuille = estUneFeuille;
    }
    
    
    
    public String affiche(){
        String res = "";
        if(estUneFeuille){
            res += "feuille de poids : " + poids;
            res += "\nvaleur : " + val;
        }else{
            res += "\nnoeuds de poids : " + poids + "\n";
            
            if(filsGauche.estUneFeuille){
                res += "mon fils gauche est une feuille de poids : " + filsGauche.poids + "\n";
                res += "valeur : " + filsGauche.val + "\n";
            }else{
                res += "mon fils gauche est un noeuds de poids : " + filsGauche.poids + "\n";
            }
            
            if(filsDroit.estUneFeuille){
                res += "mon fils droit est une feuille de poids : " + filsDroit.poids + "\n";
                res += "valeur : " + filsDroit.val + "\n";
            }else{
                res += "mon fils droit est un noeuds de poids : " + filsDroit.poids + "\n";
            }
            
        }
        
        return res;
    }

    
    
}
