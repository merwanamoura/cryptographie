/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static projet.MTF.mot;

/**
 *
 * @author amoura_merwan
 */
public class Huffman {
    
    int taille=0;
    
    static String mot="";
    static List<String> listeLettre = new ArrayList<String>();
    static List<String> listeLettreStatistique = new ArrayList<String>();
    static List<Double> FrequenceStatistique = new ArrayList<Double>();
    static List<Noeud> BdNoeud= new ArrayList<Noeud>();
    
    static String motCode="";
    
    public Huffman(String m){
        mot = m;
    }
    
    static void creerListeLettre(){
        for(int i = 0 ; i < mot.length() ; i++) listeLettre.add(Character.toString(mot.charAt(i)));
    }
    
    static void creerStatistique(){
        
        for(int i = 0 ; i < listeLettre.size() ; i++)
            for(int j = 0 ; j < listeLettre.size() ; j++)
                if(!isInListe(listeLettreStatistique,listeLettre.get(i)))
                    listeLettreStatistique.add(listeLettre.get(i));
        
        double cpt;
        for(int i = 0 ; i < listeLettreStatistique.size() ; i++){
            cpt = 0;
            for(int j = 0 ; j < listeLettre.size() ; j++)
                if(listeLettreStatistique.get(i).equals(listeLettre.get(j)))
                    cpt ++;
            
            double stat = cpt / mot.length();
            
            FrequenceStatistique.add(stat);
        }
        
    }
    
    static boolean isInListe(List<String> liste, String l){
        boolean bol = false;
        for(int i = 0 ; i < liste.size() ; i++)
            if( liste.get(i).equals(l) ) bol = true;
        return bol;
    }

    
    static void creerNoeud(){
        for(int i = 0 ; i < listeLettreStatistique.size() ; i++)
            BdNoeud.add(new Noeud(listeLettreStatistique.get(i), FrequenceStatistique.get(i)));
    }
    
    
    static Noeud getNoeudMin(){
        int cpt = 0;
        double valeur=1;
        for(int i = 0 ; i < BdNoeud.size() ; i++)
            if(BdNoeud.get(i).getPoids() <= valeur && !BdNoeud.get(i).estPris){
                valeur = BdNoeud.get(i).getPoids();
                cpt = i;
            }
        Noeud n = BdNoeud.get(cpt);
        n.setEstPris(true);
        return n;
    }
    

    
    static void huffman(){
        creerNoeud();

        while(BdNoeud.size() > 1){
            Noeud n1 = getNoeudMin();
            Noeud n2 = getNoeudMin();

            double poids = n1.getPoids() + n2.getPoids();

            Noeud noeud = new Noeud(poids);  
            noeud.setFilsGauche(n1);
            noeud.setFilsDroit(n2);
            
            BdNoeud.add(noeud);
            BdNoeud.remove(n1);
            BdNoeud.remove(n2);
        } 
        
    }
    
    static String getCode(Noeud n, String lettre, String code){
        
        String code1=null,code2=null;
        if(n.isEstUneFeuille())
        {
            if(n.getVal().equals(lettre))
            {
                return code;
            }
            else
            {
                return "";
            }
        }
        else{
            if( !n.isEstUneFeuille())
            {
                 code1 = getCode(n.getFilsGauche(),lettre,code+"0");
            }
            if(!n.isEstUneFeuille())
            {
                 code2 = getCode(n.getFilsDroit(),lettre,code+"1");
            }
            if(!code1.equals(""))return code1;
            if(!code2.equals(""))return code2;
            
            return "";
        }
    }
    
    static void compression(){
        if(mot.equals("")){
           Scanner sc = new Scanner(System.in);
           System.out.println("Veuillez saisir un mot : ");
           mot += sc.nextLine();   
        }
        
        creerListeLettre();
        creerStatistique();
        huffman();
        
        Noeud n = BdNoeud.get(0);
        
        for(int i = 0 ; i < listeLettreStatistique.size() ; i++) 
            System.out.println(listeLettreStatistique.get(i) + " : " + getCode(n,listeLettreStatistique.get(i),""));
        
        for(int i = 0 ; i < mot.length(); i++) {
            String lettre = Character.toString(mot.charAt(i));
            motCode +=getCode(n,lettre,"");
        }
        
        System.out.println("Code après Huffman : " + motCode);
        
        /*
        System.out.println(BdNoeud.get(0).affiche()); 
        System.out.println(BdNoeud.get(0).getFilsGauche().affiche());
        System.out.println(BdNoeud.get(0).getFilsDroit().affiche());
        */
        
        
    }
    
}
