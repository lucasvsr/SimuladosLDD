/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladounid2SAX;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author lucas
 */
public class Unid2Q2SAX extends DefaultHandler{
    
    private int contador =0;
    public static ArrayList<String> autores = new ArrayList<>();
    private String tagAtual;
    private static int contAutorRepetido;
    
    public static String contaAutor(){
        
    int contador = 0;
    int posicao;
    String autor = null;
    
    for(posicao = 0; posicao < autores.size(); posicao++) {
        
        String palavra = autores.get(posicao);
        
        for(int i = 0; i < autores.size(); i++) {
            if(autores.get(i).equals(palavra))
                contador++;
        }
        
        if(contador > contAutorRepetido) {
            contAutorRepetido = contador;
            autor = palavra;
        }
            
        
    }
        
    return autor;
}
    
     public ArrayList<String> fazerParsing(String caminho) throws MalformedURLException, IOException {
        SAXParserFactory fab = SAXParserFactory.newInstance();
        SAXParser parser;
        
        try {
            parser = fab.newSAXParser();
            
            parser.parse(new File(caminho), this);
            
        } catch (ParserConfigurationException | SAXException e) {
            
            StringBuffer msg = new StringBuffer();
            msg.append("Erro:\n");
            msg.append(e.getMessage()).append("\n");
            msg.append(e.toString());
            System.out.println(msg);
        }
        
        return autores;
    }

   @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("th")) {
            contador = 0;
        }
        if(qName.equals("td")) {
            tagAtual = qName;
            contador++;
        }
    }  
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      
        String texto = new String(ch, start, length);
        
        if(contador == 1) {
            if(tagAtual.equals("td"))
            autores.add(texto);
        }
        tagAtual = "";
        
        
    }
     
}
