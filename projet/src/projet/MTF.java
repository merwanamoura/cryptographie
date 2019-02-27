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
    
    static String mot="";
    static String codeChiffre="";
    static String codeLettre="";
    static List<String> listeCaractereInit = new ArrayList<String>();
    static List<String> listeCaractereModif = new ArrayList<String>();
    static List<String> listeLettreMot = new ArrayList<String>();
    static List<Integer> listeCodeMot = new ArrayList<Integer>();
    static List<String> listeCodeLettre = new ArrayList<String>();
    
    static List<String> listeLettreDepart = new ArrayList<String>();
    static List<String> listeLettreDecompression = new ArrayList<String>();
    static List<Integer> listeChiffreDecompression = new ArrayList<Integer>();
    static List<String> listeCaractereModifDecompression = new ArrayList<String>();
    static String motDecompresser="";
    static String motADecompresser="";

    
    static void initListeCaractere(){
        if(listeCaractereInit.size() == 0){
            listeCaractereInit.add("A");listeCaractereInit.add("B");listeCaractereInit.add("C");listeCaractereInit.add("D");listeCaractereInit.add("E");
            listeCaractereInit.add("F");listeCaractereInit.add("G");listeCaractereInit.add("H");listeCaractereInit.add("I");listeCaractereInit.add("J");
            listeCaractereInit.add("K");listeCaractereInit.add("L");listeCaractereInit.add("M");listeCaractereInit.add("N");listeCaractereInit.add("O");
            listeCaractereInit.add("P");listeCaractereInit.add("Q");listeCaractereInit.add("R");listeCaractereInit.add("S");listeCaractereInit.add("T");
            listeCaractereInit.add("U");listeCaractereInit.add("V");listeCaractereInit.add("W");listeCaractereInit.add("X");listeCaractereInit.add("Y");
            listeCaractereInit.add("Z");
        }        
        
    }
    
    static void initListeLettre(){
        for(int i = 0 ; i < mot.length() ; i++) listeLettreMot.add( Character.toString( mot.charAt(i) ).toUpperCase() );
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
                    listeCodeMot.add(chiffre);
                }
            }
        
        for(int i = 0 ; i < listeCodeMot.size(); i++) codeChiffre += listeCodeMot.toString();
        
        for(int i = 0 ; i < listeCodeMot.size(); i++) 
            for(int j = 0 ; j < listeCaractereInit.size(); j++)
                if( listeCodeMot.get(i) == j )
                    listeCodeLettre.add(listeCaractereInit.get(j));
        
        for(int i = 0 ; i < listeCodeLettre.size(); i++) codeLettre += listeCodeLettre.get(i);
        
        
    }
    
    static void compression(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot : ");
        mot += sc.nextLine();
        initListeCaractere();
        for(int i = 0 ; i < listeCaractereInit.size() ; i++) listeCaractereModif.add(listeCaractereInit.get(i));

        initListeLettre();
        move_to_front();
        System.out.println("chiffre codé -----> " + listeCodeMot);
        System.out.println("tableau codé ------> " + codeLettre);
    }
    
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
            for(int j = 0 ; j < listeCaractereInit.size() ; j++)
                if(listeLettreDepart.get(i).equals(listeCaractereInit.get(j))) listeChiffreDecompression.add(j);
        
        System.out.println("liste ------> " + listeChiffreDecompression);
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
                }
        
        for(int i = 0 ; i < listeLettreDecompression.size(); i++) motDecompresser += listeLettreDecompression.get(i);
    }
    
    static void decompression(String str){
        motADecompresser = str;
        initListeCaractere();
        for(int i = 0 ; i < listeCaractereInit.size() ; i++) listeCaractereModifDecompression.add(listeCaractereInit.get(i));
        createListeLettreDepartDecompression();
        initListeChiffreDecompression();
        getDecompression();
        System.out.println("----->" + motDecompresser);
    }
    
    
}
