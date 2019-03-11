/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author amoura_merwan
 */
public class MTF {
    
    static String mot;
    static String codeChiffre;
    static String codeLettre;
    static List<String> listeCaractere;
    static List<String> listeCaractereInit;
    static List<String> listeCaractereTriee;
    static List<String> listeCaractereModif;
    static List<String> listeLettreMot;
    static List<Integer> listeCodeMot;
    static List<String> listeCodeLettre;
    
    static List<String> listeLettreDepart;
    static List<String> listeLettreDecompression;
    static List<Integer> listeChiffreDecompression;
    static List<String> listeCaractereModifDecompression;
    static String motDecompresser;
    static String motADecompresser;
    
    static String etapeCompression;
    static String etapeDecompression;
    
    public MTF(){
        mot="";
        codeChiffre="";
        codeLettre="";
        listeCaractere = new ArrayList<String>();
        listeCaractereInit = new ArrayList<String>();
        listeCaractereTriee = new ArrayList<String>();
        listeCaractereModif = new ArrayList<String>();
        listeLettreMot = new ArrayList<String>();
        listeCodeMot = new ArrayList<Integer>();
        listeCodeLettre = new ArrayList<String>();

        listeLettreDepart = new ArrayList<String>();
        listeLettreDecompression = new ArrayList<String>();
        listeChiffreDecompression = new ArrayList<Integer>();
        listeCaractereModifDecompression = new ArrayList<String>();
        motDecompresser="";
        motADecompresser="";
        etapeCompression="";etapeDecompression="";
    }

    
    static void initListeCaractere(String m){
        if(listeCaractereInit.size() == 0){
            listeCaractere.add("a");listeCaractere.add("b");listeCaractere.add("c");listeCaractere.add("d");listeCaractere.add("e");
            listeCaractere.add("f");listeCaractere.add("g");listeCaractere.add("h");listeCaractere.add("i");listeCaractere.add("j");
            listeCaractere.add("k");listeCaractere.add("l");listeCaractere.add("m");listeCaractere.add("n");listeCaractere.add("o");
            listeCaractere.add("p");listeCaractere.add("q");listeCaractere.add("r");listeCaractere.add("s");listeCaractere.add("t");
            listeCaractere.add("u");listeCaractere.add("v");listeCaractere.add("w");listeCaractere.add("x");listeCaractere.add("y");
            listeCaractere.add("z");
        }
        if(listeCaractereTriee.size() == 0){    
            
            for(int i = 0 ; i < m.length() ; i++)
                for(int j = 0 ; j < listeCaractere.size() ; j++) 
                    if(Character.toString(m.charAt(i)).equals(listeCaractere.get(j)))
                        if(!isInListe(listeCaractereInit,Character.toString(m.charAt(i))))
                            listeCaractereInit.add(listeCaractere.get(j));
            
            int[] tab = new int[listeCaractereInit.size()];
            for(int i = 0 ; i < tab.length ; i++) tab[i] = 0;
            
            for(int i = 0 ; i < listeCaractereInit.size() ; i++)
                for(int j = 0 ; j < listeCaractereInit.size() ; j++)
                    if(j!=i && listeCaractereInit.get(i).compareTo(listeCaractereInit.get(j)) > 0 )  tab[i]++;
                                   
            for(int i = 0 ; i < listeCaractereInit.size() ; i++) 
                for(int j = 0 ; j < tab.length ; j++)
                    if(tab[j] == i)listeCaractereTriee.add(i,listeCaractereInit.get(j));
        }        
        
    }
    
    static boolean isInListe(List<String> liste, String l){
        boolean bol = false;
        for(int i = 0 ; i < liste.size() ; i++)
            if( liste.get(i).equals(l) ) bol = true;
        return bol;
    }
    
    static void initListeLettre(){
        for(int i = 0 ; i < mot.length() ; i++) listeLettreMot.add( Character.toString( mot.charAt(i) ) );
    }
    
    static void move_to_front(){
        String caractere;
        int chiffre;
        for(int i = 0 ; i < listeLettreMot.size(); i++)
            for(int j = 0 ; j < listeCaractereModif.size() ; j++)
            {
                if(listeLettreMot.get(i).equals(listeCaractereModif.get(j)) ){
                    caractere = listeCaractereModif.get(j);
                    chiffre = j;
                    listeCaractereModif.remove(j);
                    listeCaractereModif.add(0,caractere);
                    for(int k = 0 ; k < listeCaractereModif.size() ; k++) etapeCompression +=listeCaractereModif.get(k) + " ";
                    etapeCompression += " - on veut lire le caractere : " + caractere;
                    etapeCompression += " - position : " + chiffre + "\n";
                    listeCodeMot.add(chiffre);
                }
            }
        
        for(int i = 0 ; i < listeCodeMot.size(); i++) codeChiffre += listeCodeMot.toString();
        
        for(int i = 0 ; i < listeCodeMot.size(); i++) 
            for(int j = 0 ; j < listeCaractereTriee.size(); j++)
                if( listeCodeMot.get(i) == j )
                    listeCodeLettre.add(listeCaractereTriee.get(j));
        
        for(int i = 0 ; i < listeCodeLettre.size(); i++) codeLettre += listeCodeLettre.get(i);
        
        
    }
    
    static void compression(String str){
        
        mot = str;

        initListeCaractere(mot);
        for(int i = 0 ; i < listeCaractereTriee.size() ; i++) listeCaractereModif.add(listeCaractereTriee.get(i));

        initListeLettre();
        move_to_front();
    }
    
    /*********************************************************************************************************************/
    /*********************************************************************************************************************/
    /*********************************************************************************************************************/
    
    static void createListeLettreDepartDecompression(){
        if(listeCodeLettre.size() == 0){
            for(int i = 0 ; i < motADecompresser.length(); i++) 
                        listeLettreDepart.add( Character.toString(motADecompresser.charAt(i)) );   
        }else{
            for(int i = 0 ; i < listeCodeLettre.size() ; i++)
                listeLettreDepart.add(listeCodeLettre.get(i));
        }
    }
    
    static void initListeChiffreDecompression(){
        for(int i = 0 ; i < listeLettreDepart.size() ; i++)
            for(int j = 0 ; j < listeCaractereTriee.size() ; j++)
                if(listeLettreDepart.get(i).equals(listeCaractereTriee.get(j))) listeChiffreDecompression.add(j);
        
    }
    
    static void getDecompression(){
        String caractere;
        for(int i = 0 ; i < listeChiffreDecompression.size() ; i++)
            for(int j = 0 ; j < listeCaractereModifDecompression.size() ; j++)
                if(listeChiffreDecompression.get(i) == j)
                {
                    
                    caractere = listeCaractereModifDecompression.get(j);
                    listeCaractereModifDecompression.remove(j);
                    listeCaractereModifDecompression.add(0,caractere);
                    listeLettreDecompression.add(caractere);
                    
                    for(int k = 0 ; k < listeCaractereModifDecompression.size() ; k++) etapeDecompression +=listeCaractereModifDecompression.get(k) + " ";
                    etapeDecompression += " - on veut lire le caractere en position : " + listeChiffreDecompression.get(i);
                    etapeDecompression += " - il s'agit du : " + caractere + "\n";
                }
        
        for(int i = 0 ; i < listeLettreDecompression.size(); i++) motDecompresser += listeLettreDecompression.get(i);
    }
    
    static void decompression(String str){
        motADecompresser = str;
        initListeCaractere(motADecompresser);
        for(int i = 0 ; i < listeCaractereTriee.size() ; i++) listeCaractereModifDecompression.add(listeCaractereTriee.get(i));
        createListeLettreDepartDecompression();
        initListeChiffreDecompression();
        getDecompression();
    }

    public static String getEtapeCompression() {
        return etapeCompression;
    }

    public static void setEtapeCompression(String etapeCompression) {
        MTF.etapeCompression = etapeCompression;
    }

    public static String getEtapeDecompression() {
        return etapeDecompression;
    }

    public static void setEtapeDecompression(String etapeDecompression) {
        MTF.etapeDecompression = etapeDecompression;
    }
    
    
}
