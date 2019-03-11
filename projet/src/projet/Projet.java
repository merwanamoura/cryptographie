/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static projet.BW.mot;
import static projet.MTF.mot;
import static projet.MTF.motDecompresser;

/**
 *
 * @author ma522501
 */
public class Projet {
    
    static MTF move;
    static Huffman huff;
    static String after_bw="";
    static String after_mtf="";
    static String after_huff="";
    
    static String decompression_bw="";
    static String decompression_mtf="";
    static String decompression_huff="";
    
    /*
    public static void main(String[] args) {
               
        // compression 
        BW.compression("");
        after_bw = BW.code;
        
        move = new MTF(after_bw);
        move.compression();
        after_mtf=MTF.codeLettre;
        
        huff = new Huffman(after_mtf);
        huff.compression();
        after_huff=huff.motCode;
        
        //decompression
        huff.decompression(after_huff);
        decompression_huff = huff.motCode;
        
        move.decompression(decompression_huff);
        decompression_mtf=MTF.motDecompresser;
        
        BW.decompression(decompression_mtf);
        
        
    }*/
    
    
    
}
