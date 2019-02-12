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
 * @author ma522501
 */
public class Projet {
    
    static List<String> liste = new ArrayList<String>();
    static List<String> listeDepart = new ArrayList<String>();
    static List<String> listeTrier = new ArrayList<String>(); 

    static void createlisteDepart(){
        for(int i = 0 ; i < liste.size() ; i++){
            String str = "";
            for(int j = 0 ; j < liste.size() ; j++){
                str += liste.get(j);
            }
            String dernier = liste.get(liste.size()-1);
            liste.remove(liste.size()-1);
            liste.add(0, dernier);
            
            listeDepart.add(str);
        }
            
    }
    
    static void BTW(){
        for(int i = 0 ; i < listeDepart.size() ; i++) listeTrier.add("");
        
        int[] tab = new int[listeDepart.size()];
        for(int i = 0 ; i < tab.length ; i++) tab[i] = 0;
            
        int cpt = 0;
        
        for(int i = 0 ; i < listeDepart.size() ; i++)
            for(int j = 0 ; j < listeDepart.size() ; j++)
                if(j!=i && listeDepart.get(i).compareTo(listeDepart.get(j)) > 0 )  tab[i]++;
        
        //for(int i = 0 ; i < tab.length ; i++) listeTrier.add(tab[i],listeDepart.get(i));

        for(int i = 0 ; i < tab.length ; i++) System.out.println("tab["+i+"] : " + tab[i]);

    }
    
    public static void main(String[] args) {
        initListe("TEXTE");
        createlisteDepart();
        System.out.println("TEXTE\n");
        for(int i = 0 ; i< listeDepart.size() ; i++) System.out.println(listeDepart.get(i));
        System.out.println("");
        BTW();
        
        //for(int i = 0 ; i< listeTrier.size() ; i++) System.out.println(listeTrier.get(i));

        
        
    }
    
    static void initListe(String str){
        for(int i = 0; i<str.length() ; i++){
            liste.add( Character.toString(str.charAt(i)) );
        }
    }
    
}
