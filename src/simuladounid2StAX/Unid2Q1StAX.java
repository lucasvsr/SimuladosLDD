/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladounid2StAX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author lucas
 */
public class Unid2Q1StAX {
    
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        
        
        System.out.println("A nacionalidade da maioria dos autores: "+retornarNacionalidadePredominante(pegarNacionalidades()));
        System.out.println("O ator que mais publicou estilos: "+retornarAutorPredominante(pegarAutores()));
        System.out.println(retornarBlues(retornarEstilos()).size()+" estilos tem 'blue', e são: "+retornarBlues(retornarEstilos()));
        System.out.println("Estilos publicados por autores brasileiros: "+retornarEstilosBrasileiros(retornarEstilos(), pegarNacionalidades()));
    }

    private static ArrayList<String> pegarNacionalidades() throws FileNotFoundException, XMLStreamException {
            XMLInputFactory fabrica = XMLInputFactory.newInstance();
            XMLStreamReader leitor = fabrica.createXMLStreamReader(new FileInputStream("table-gallery.html"));
            
            boolean tbody = false;
            boolean tr = false;
            int td = 0;
           ArrayList<String> nacionalidades = new ArrayList<>();
            
            while(leitor.hasNext()){
                int tipo = leitor.next();
                
                switch(tipo){
                    case XMLStreamReader.START_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = true;
                                
                            case "tr":
                                if(tbody == true){
                                    tr = true;
                                    
                                }
                                    td = 0;
                                    
                            case "td":
                                if(tr == true){
                                    td++;
                                }
                        }
                        break;
                     
                    case XMLStreamReader.CHARACTERS:
                        
                            if(td == 3){
                                nacionalidades.add(leitor.getText());
                                
                            }
                          break;
                        
                        
                        
                    case XMLStreamReader.END_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = false;
                                break;
                            case "tr":
                                if(tbody == false)
                                    tr = false;
                                
                                break;
                        }
                }
            }
            
            ArrayList<String> ajustes = new ArrayList<>();
            
            for(int i = 0; i < nacionalidades.size(); i++){
                if(i == 0)
                    ajustes.add(nacionalidades.get(i));
                if(i >= 1 && i%2 == 0)
                    ajustes.add(nacionalidades.get(i++));
            }
            
            nacionalidades.clear();
            nacionalidades.addAll(ajustes);
            
            return nacionalidades;
    }

    private static String retornarNacionalidadePredominante(ArrayList<String> nacionalidades) {
    
        int contador = 0;
        int posicao;
        int contPaisRepetido = 0;
        String pais = null;
    
    for(posicao = 0; posicao < nacionalidades.size(); posicao++) {
        
        String palavra = nacionalidades.get(posicao);
        
        for(int i = 0; i < nacionalidades.size(); i++) {
            
            if(nacionalidades.get(i).equals(palavra))
                contador++;
                i++;//nao entendo o que está aontecendo, mas um espaço é capturado e adicionado ao array, este é o tratamento
        }
        
        if(contador > contPaisRepetido) {
            contPaisRepetido = contador;
            pais = palavra;
        }
    }
    
    return pais;
    }

    private static ArrayList<String> pegarAutores() throws FileNotFoundException, XMLStreamException{
        XMLInputFactory fabrica = XMLInputFactory.newInstance();
            XMLStreamReader leitor = fabrica.createXMLStreamReader(new FileInputStream("table-gallery.html"));
            
            boolean tbody = false;
            boolean tr = false;
            int td = 0;
           ArrayList<String> autores = new ArrayList<>();
            
            while(leitor.hasNext()){
                int tipo = leitor.next();
                
                switch(tipo){
                    case XMLStreamReader.START_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = true;
                                break;
                            case "tr":
                                if(tbody == true)
                                    tr = true;
                                break;
                            case "td":
                                if(tr == true){
                                    td++;
                                }
                        }
                        break;
                     
                    case XMLStreamReader.CHARACTERS:
                        
                            if(td == 1){
                                
                                autores.add(leitor.getText());
                                
                            }
                          break;
                        
                        
                        
                    case XMLStreamReader.END_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = false;
                                break;
                            case "tr":
                                tr = false;
                                td = 0;
                                break;
                        }
                }
            }
            
            ArrayList<String> ajustes = new ArrayList<>();
            
            for(int i = 0; i < autores.size(); i++){
                if(i == 0)
                    ajustes.add(autores.get(i));
                if(i >= 1 && i%2 == 0)
                    ajustes.add(autores.get(i++));
            }
            
            
            return autores;
    }
    
    private static String retornarAutorPredominante(ArrayList<String> autores) {
        int contador = 0;
        int posicao;
        int contPaisRepetido = 0;
        String autor = null;
    
    for(posicao = 0; posicao < autores.size(); posicao++) {
        
        String palavra = autores.get(posicao);
        
        for(int i = 0; i < autores.size(); i++) {
            
            if(autores.get(i).equals(palavra))
                contador++;
                i++;//nao entendo o que está aontecendo, mas um espaço é capturado e adicionado ao array, este é o tratamento
        }
        
        if(contador > contPaisRepetido) {
            contPaisRepetido = contador;
            autor = palavra;
        }
    }
    
        return autor;
    }
    
    private static ArrayList<String> retornarEstilos() throws FileNotFoundException, XMLStreamException{
        XMLInputFactory fabrica = XMLInputFactory.newInstance();
        XMLStreamReader leitor = fabrica.createXMLStreamReader(new FileInputStream("table-gallery.html"));
        ArrayList<String> estilos = new ArrayList<>();
          boolean tbody = false;
          boolean th = false;
          int a = 0;
          
            while(leitor.hasNext()){
                int tipo = leitor.next();
                
                switch(tipo){
                    
                    case XMLStreamReader.START_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = true;
                                break;
                            case "th":
                                if(tbody == true)
                                    th = true;
                                
                                break;
                        }
                        break;
                        
                    case XMLStreamReader.END_ELEMENT:
                        switch(leitor.getLocalName()){
                            case "tbody":
                                tbody = false;
                                break;
                            case "th":
                                th = false;
                                
                                break;
                        }
                        break;
                        
                    case XMLStreamReader.CHARACTERS:
                        if(th == true){
                            estilos.add(leitor.getText());
                        }
                            
                        break;
                }
            }
            
            return estilos;
    }

    private static ArrayList<String> retornarBlues(ArrayList<String> estilos) {
    
        ArrayList<String> blues = new ArrayList<>();
        String blue = "Blue";
        
        for(int i = 0; i < estilos.size(); i++){
            if(estilos.get(i).contains(blue))
                blues.add(estilos.get(i));
        }
        return blues;
    }

    private static ArrayList<String> retornarEstilosBrasileiros(ArrayList<String> estilos, ArrayList<String> nacionalidades) {
    
        ArrayList<Integer> posicoes = retornarPosicoes("Brazil", nacionalidades);
        ArrayList<String> estilosBrasileiros = new ArrayList<>();
        
        for(int i = 0; i < posicoes.size(); i++){
            estilosBrasileiros.add(estilos.get(posicoes.get(i)));
        }
        
        return estilosBrasileiros;
    }

    private static ArrayList<Integer> retornarPosicoes(String nacionalidade, ArrayList<String> nacionalidades) {
        ArrayList<Integer> posicoes = new ArrayList<>();
        
        for(int i = 0; i < nacionalidades.size(); i++){
            if(nacionalidades.get(i).equals(nacionalidade)){
                posicoes.add(i);
            }
        }
        return posicoes;
    }
    
    

}
