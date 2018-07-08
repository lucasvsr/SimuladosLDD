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
 * @author ALUNO
 */
public class Unid2Q1SAX extends DefaultHandler {

    private String tagAtual;
    public ArrayList<String> paises = new ArrayList<>();
    private int contador = 0;
    private static int contPaisRepetido;
    private static String pais;
    
    public Unid2Q1SAX() {
        super();
    }
    
    
    public String contaNacionalidade(){
    int contador = 0;
    int posicao;
    
    for(posicao = 0; posicao < paises.size(); posicao++) {
        
        String palavra = paises.get(posicao);
        
        for(int i = 0; i < paises.size(); i++) {
            if(paises.get(i).equals(palavra))
                contador++;
        }
        
        if(contador > contPaisRepetido) {
            contPaisRepetido = contador;
            pais = palavra;
        }
            
        
    }
        
    return pais;
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
        
        return paises;
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
        
        if(contador == 2) {
            if(tagAtual.equals("td"))
            paises.add(texto);
        }
        tagAtual = "";
        
        
    }
   
}