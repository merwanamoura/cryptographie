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
    
    int taille;
    
    static String mot;
    static List<String> listeLettre;
    static List<String> listeLettreStatistique;
    static List<Double> FrequenceStatistique;
    static List<Noeud> BdNoeud;
    static List<String> listeCode;

    
    static String motCode;
    static String motDecode;
    static List<String> listeADecompresser;
    
    static String etapeCompression;
    static String etapeDecompression;
    
    public Huffman(){
        taille=0;

        mot="";
        listeLettre = new ArrayList<String>();
        listeLettreStatistique = new ArrayList<String>();
        FrequenceStatistique = new ArrayList<Double>();
        BdNoeud= new ArrayList<Noeud>();
        listeCode = new ArrayList<String>();


        motCode="";
        motDecode="";
        listeADecompresser = new ArrayList<String>();
        etapeCompression="";
        etapeDecompression="";
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
    
    static void compression(String m){
        mot = m;
        
        creerListeLettre();
        creerStatistique();
        huffman();
        
        Noeud n = BdNoeud.get(0);
        
        for(int i = 0 ; i < listeLettreStatistique.size() ; i++) 
            listeCode.add(getCode(n,listeLettreStatistique.get(i),""));
        
        for(int i = 0 ; i < listeLettreStatistique.size() ; i++) 
            etapeCompression += listeLettreStatistique.get(i) + " : " + listeCode.get(i) + "\n";
        
        for(int i = 0 ; i < mot.length(); i++) {
            String lettre = Character.toString(mot.charAt(i));
            motCode +=getCode(n,lettre,"");
        }
         
    }
    
    /****************************************************************************************************************/
    
    static void initListeAdecompresser(String code){
        for(int i = 0 ; i < code.length() ; i++) listeADecompresser.add(Character.toString(code.charAt(i)));
    }
    
    static String getLettreFromCode(){
        String code="";
        String lettre="";
        while(!isInListe(listeCode,code)){
            code += listeADecompresser.get(0);
            listeADecompresser.remove(0);
        }
        
        etapeDecompression += "\nOn repère dans le code " + code;
        
        for(int i = 0 ; i < listeCode.size() ; i++){
            if(code.equals(listeCode.get(i))) lettre += listeLettreStatistique.get(i);
        }
        
        etapeDecompression += "\nCe code correspond à la lettre " + lettre + "\n";
         
        return lettre;
    }
    
    static void getCode(){
        while(listeADecompresser.size() != 0){
            motDecode += getLettreFromCode();
        }
    }
    
    static void decompression(String code){
        initListeAdecompresser(code);
        getCode();
    }

    public static String getEtapeCompression() {
        return etapeCompression;
    }

    public static void setEtapeCompression(String etapeCompression) {
        Huffman.etapeCompression = etapeCompression;
    }

    public static String getEtapeDecompression() {
        return etapeDecompression;
    }

    public static void setEtapeDecompression(String etapeDecompression) {
        Huffman.etapeDecompression = etapeDecompression;
    }
    
    
    
}
