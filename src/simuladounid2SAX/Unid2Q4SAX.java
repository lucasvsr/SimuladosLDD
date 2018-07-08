/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladounid2SAX;

import java.io.IOException;
import java.util.ArrayList;

public class Unid2Q4SAX {
    
    public static void main(String[] args) throws IOException{

        Unid2Q1SAX paises = new Unid2Q1SAX();
        ArrayList<String> nacionalidades = paises.fazerParsing("table-gallery.html");
        ArrayList<Integer> posicoes = new ArrayList<>();
        
        Unid2Q3SAX titulos = new Unid2Q3SAX();
        ArrayList<String> estilos = titulos.fazerParsing("table-gallery.html");
        
        System.out.print(nacionalidades+"\n");
        System.out.print(estilos+"\n");
        
        for(int i = 0; i < nacionalidades.size(); i++){
            if(nacionalidades.get(i).equals("Brazil")){
                posicoes.add(i);
            }
        }
        
        for(int j = 0; j < posicoes.size(); j++){
            System.out.println(estilos.get(6+posicoes.get(j)));
        }
        
       
    }
}
