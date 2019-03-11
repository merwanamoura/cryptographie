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
public class BW {
    
    static List<String> liste;
    static List<String> listeDepart;
    static List<String> listeTrier;
    static String mot;
    static String code;
    
    static List<String> listeCode;
    static List<String> listeClasse; 
    static int chiffre;
    static String motDecompresser;
    static int sauvegarde;
    
    public BW(){
        liste= new ArrayList<String>();
        listeDepart = new ArrayList<String>();
        listeTrier = new ArrayList<String>(); 
        
        mot="";
        code="";
        
        listeCode = new ArrayList<String>();
        listeClasse = new ArrayList<String>();
        chiffre=0;
        
        motDecompresser="";
    }

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
    
    static void BWT(){

        int[] tab = new int[listeDepart.size()];
        for(int i = 0 ; i < tab.length ; i++) tab[i] = 0;
            
        int cpt = 0;
        
        for(int i = 0 ; i < listeDepart.size() ; i++)
            for(int j = 0 ; j < listeDepart.size() ; j++)
                if(j!=i && listeDepart.get(i).compareTo(listeDepart.get(j)) > 0 )  tab[i]++;
        
             
        for(int i = 0 ; i < listeDepart.size() ; i++) 
            for(int j = 0 ; j < tab.length ; j++)
                if(tab[j] == i)listeTrier.add(i,listeDepart.get(j));
 

    }
    
    static void initListe(String str){
        for(int i = 0; i<str.length() ; i++){
            liste.add( Character.toString(str.charAt(i)) );
        }
    }
    
    static void getCode(){
        
        for(int i = 0; i<listeTrier.size() ; i++){
            if(listeTrier.get(i).equals(mot)) sauvegarde += i+1;
        }
        
        for(int i = 0; i<listeTrier.size() ; i++){
            String str = listeTrier.get(i);              
            code +=  Character.toString(str.charAt(mot.length() - 1));
        }
        //System.out.println("\nCode obtenu après BW : " + code.toUpperCase());

    }
    
    static void compression(String str){
        if(!str.equals("")){
            mot = str;
            initListe(mot);
            createlisteDepart();        
            BWT();
            getCode();
        }
        
    }
    
    static void creerListes(String str){
        for(int i = 0 ; i < str.length() ; i++) listeCode.add(Character.toString(str.charAt(i)));

        int[] tab = new int[listeCode.size()];
        
        for(int i = 0 ; i < listeCode.size() ; i++)
            for(int j = 0 ; j < listeCode.size() ; j++)
                if(j!=i && listeCode.get(i).compareTo(listeCode.get(j)) > 0 )  tab[i]++;
        
        int cpt=0;
        for(int i = 0 ; i < tab.length ; i++){
            for(int j = 0 ; j < tab.length ; j++)
                if(i!=j && tab[i] == tab[j]){
                    cpt++;
                    tab[i]+=cpt; 
                }
            cpt = 0;
        }
           
        
        for(int i = 0 ; i < listeCode.size() ; i++) 
            for(int j = 0 ; j < tab.length ; j++)
                if(tab[j] == i)listeClasse.add(i,listeCode.get(j));

    }
    
    static void decompression(String str){
        creerListes(str);

        /******************************************************/
        //ON MET LA PREMIERE LETTRE DU MOT
        /******************************************************/
        
        int position = sauvegarde-1;
        int numeroDansLigneClasse;
        String lettre=listeClasse.get(position);
        motDecompresser += lettre;
        
        //System.out.println("on part avec le caractere en position : " + position);
        //System.out.println("lettre depart : " + lettre);
        
        /*******************************************************/
        
        while(motDecompresser.length() < listeCode.size()){
            numeroDansLigneClasse = 0;
            for(int i = 0 ; i < listeClasse.size() ; i++)
                if(lettre.compareTo(listeClasse.get(i)) == 0 && i <= position)  numeroDansLigneClasse++; 
            
            //System.out.println("ce " + lettre + " est le " + numeroDansLigneClasse + "eme de la ligne classé" );
            //System.out.println("on cherche donc le " + numeroDansLigneClasse + "eme " + lettre + " de la ligne codee" );
        
            int cpt=0;
            for(int i = 0 ; i < listeCode.size() ; i++)
                if(lettre.compareTo(listeCode.get(i)) == 0){
                    cpt++;
                    if(cpt == numeroDansLigneClasse) position = i;
                }

            //System.out.println("ce qui correspond a la position : " + position);
            
            lettre = listeClasse.get(position);
            //System.out.println("la nouvelle lettre est donc " + lettre);
            motDecompresser += lettre;
        }
        
        //System.out.println("mot decompresser par BW : "+motDecompresser);
        
    }
    
    
}
