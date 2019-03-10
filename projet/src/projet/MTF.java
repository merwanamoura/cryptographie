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
    static List<String> listeCaractere = new ArrayList<String>();
    static List<String> listeCaractereInit = new ArrayList<String>();
    static List<String> listeCaractereTriee = new ArrayList<String>();
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
    
    public MTF(String c){
        mot = c;
    }

    
    static void initListeCaractere(String m){
        if(listeCaractereInit.size() == 0){
            listeCaractere.add("A");listeCaractere.add("B");listeCaractere.add("C");listeCaractere.add("D");listeCaractere.add("E");
            listeCaractere.add("F");listeCaractere.add("G");listeCaractere.add("H");listeCaractere.add("I");listeCaractere.add("J");
            listeCaractere.add("K");listeCaractere.add("L");listeCaractere.add("M");listeCaractere.add("N");listeCaractere.add("O");
            listeCaractere.add("P");listeCaractere.add("Q");listeCaractere.add("R");listeCaractere.add("S");listeCaractere.add("T");
            listeCaractere.add("U");listeCaractere.add("V");listeCaractere.add("W");listeCaractere.add("X");listeCaractere.add("Y");
            listeCaractere.add("Z");
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
            for(int j = 0 ; j < listeCaractereTriee.size(); j++)
                if( listeCodeMot.get(i) == j )
                    listeCodeLettre.add(listeCaractereTriee.get(j));
        
        for(int i = 0 ; i < listeCodeLettre.size(); i++) codeLettre += listeCodeLettre.get(i);
        
        
    }
    
    static void compression(){
        if(mot.equals("")){
           Scanner sc = new Scanner(System.in);
           System.out.println("Veuillez saisir un mot : ");
           mot += sc.nextLine();   
        }

        initListeCaractere(mot.toUpperCase());
        //for(int i = 0 ; i < listeCaractereTriee.size() ; i++) System.out.println(listeCaractereTriee.get(i));
        for(int i = 0 ; i < listeCaractereTriee.size() ; i++) listeCaractereModif.add(listeCaractereTriee.get(i));

        initListeLettre();
        move_to_front();
        //System.out.println("chiffre codé -----> " + listeCodeMot);
        System.out.println("Code obtenu après MTF : " + codeLettre);
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
        
        //System.out.println("liste ------> " + listeChiffreDecompression);
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
        initListeCaractere(motADecompresser);
        for(int i = 0 ; i < listeCaractereTriee.size() ; i++) listeCaractereModifDecompression.add(listeCaractereTriee.get(i));
        createListeLettreDepartDecompression();
        initListeChiffreDecompression();
        getDecompression();
        System.out.println("Mot decompresser par MTF : " + motDecompresser);
    }
    
    
}
