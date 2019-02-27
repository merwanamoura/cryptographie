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

/**
 *
 * @author ma522501
 */
public class Projet {
    
    
    static void burrow_wheeler_compression(){
        BW.compression();
    }
    
    static void burrow_wheeler_decompression(){
        BW.decompression(BW.code);
    }
    
    static void move_to_front_compression(){
        MTF.compression();
    }

    static void move_to_front_decompression(){
        MTF.decompression(MTF.codeLettre);
    }
    
    public static void main(String[] args) {
        /*
        burrow_wheeler_compression();
        burrow_wheeler_decompression();
        */
        
        move_to_front_compression();
        move_to_front_decompression();
    }
    
    
    
}
