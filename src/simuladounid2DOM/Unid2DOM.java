/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladounid2DOM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lucas
 */
public class Unid2DOM {
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("table-gallery.html"));
            Node tbody = doc.getElementsByTagName("tbody").item(0);
           
            if(tbody.hasChildNodes()){
                List<String> nacionalidades = pegarNacionalidades(tbody);
                List<String> autores = pegarAutores(tbody);
                List<String> estilos = pegarEstilos(tbody);
                
                //QUESTÃO 1
                System.out.println("Nacionalidade da maioria dos autores: "+ retornarNacionalidadePredominante(nacionalidades));
                //QUESTÃO 2
                System.out.println("Autor que postou mais estilos: "+retornarAutorPredominante(autores));
                //QUESTÃO 3
                System.out.println("Quantidade de estilos com 'blue': "+retornarQuantidadesdeBlues(estilos).size());
                System.out.println("Quais são: "+retornarQuantidadesdeBlues(estilos));
                //QUESTÃO 4
                System.out.println("Estilos de autores do 'Brazil': "+retornarEstilosBrasileiros(nacionalidades, estilos));
            }
            
            
           
    }

    private static List<String> pegarNacionalidades(Node tbody) {
        Element doc = (Element) tbody;
        NodeList tr = doc.getElementsByTagName("tr");
        List<String> nacionalidades = new ArrayList<>();
        
            for(int i = 0; i < tr.getLength(); i++){
                nacionalidades.add(tr.item(i).getChildNodes().item(5).getTextContent());
            }
            
            return nacionalidades;
    }

    private static String retornarNacionalidadePredominante(List<String> nacionalidades) {
        int contador = 0;
        int posicao;
        int contPaisRepetido = 0;
        String pais = null;
    
    for(posicao = 0; posicao < nacionalidades.size(); posicao++) {
        
        String palavra = nacionalidades.get(posicao);
        
        for(int i = 0; i < nacionalidades.size(); i++) {
            if(nacionalidades.get(i).equals(palavra))
                contador++;
        }
        
        if(contador > contPaisRepetido) {
            contPaisRepetido = contador;
            pais = palavra;
        }
    }
    
    return pais;
}

    private static List<String> pegarAutores(Node tbody) {
        Element doc = (Element) tbody;
        NodeList tr = doc.getElementsByTagName("tr");
        List<String> autores = new ArrayList<>();
        
            for(int i = 0; i < tr.getLength(); i++){
                autores.add(tr.item(i).getChildNodes().item(3).getTextContent());
            }
            
            return autores;
    }

    private static String retornarAutorPredominante(List<String> autores) {
        int contador = 0;
        int posicao;
        int contPaisRepetido = 0;
        String autor = null;
    
    for(posicao = 0; posicao < autores.size(); posicao++) {
        
        String palavra = autores.get(posicao);
        
        for(int i = 0; i < autores.size(); i++) {
            if(autores.get(i).equals(palavra))
                contador++;
        }
        
        if(contador > contPaisRepetido) {
            contPaisRepetido = contador;
            autor = palavra;
        }
    }
    
        return autor;    
    }

    private static List<String> pegarEstilos(Node tbody) {
        Element doc = (Element) tbody;
        NodeList tr = doc.getElementsByTagName("tr");
        List<String> estilos = new ArrayList<>();
        
            for(int i = 0; i < tr.getLength(); i++){
                
                estilos.add(tr.item(i).getChildNodes().item(1).getTextContent());
            }
            
            return estilos;
    }

    private static List<String> retornarQuantidadesdeBlues(List<String> estilos) {
        List<String> blues = new ArrayList<>();
        String blue = "Blue";
        
        for(int i = 0; i < estilos.size(); i++){
            if(estilos.get(i).contains(blue)){
                blues.add(estilos.get(i));
            }
        }
        
        return blues;
   }

    private static List<String> retornarEstilosBrasileiros(List<String> nacionalidades, List<String> estilos) {
        ArrayList<Integer> posicoes = retornarIndices(nacionalidades);
        List<String> estilosBrasileiros = new ArrayList<>();
        
        for(int i = 0; i < posicoes.size(); i++){
            estilosBrasileiros.add(estilos.get(posicoes.get(i)));
        }
        
        return estilosBrasileiros;
    }

    private static ArrayList<Integer> retornarIndices(List<String> nacionalidades) {
        
       ArrayList<Integer> posicoes = new ArrayList<>();
       
        for(int i = 0; i < nacionalidades.size(); i++){
            if(nacionalidades.get(i).equals("Brazil")){
                posicoes.add(i);
            }
        }
        
        return posicoes;
    }
}
