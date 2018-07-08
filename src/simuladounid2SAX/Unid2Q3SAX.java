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
public class Unid2Q3SAX extends DefaultHandler{
    
    boolean tag = false;
    public ArrayList<String> titulos = new ArrayList<>();
    String atual;
    
    private ArrayList<String> pegarTitulosComBlue() {
    
        ArrayList<String> blue = new ArrayList<>();
        
            for(int i = 0; i < titulos.size(); i++) {
                if(titulos.get(i).contains("blue") || titulos.get(i).contains("Blue")) {
                    blue.add(titulos.get(i));
                }
            }
            
            return blue;
        
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
        
        return titulos;
    }

    @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("th")) {
            tag = true;
            atual = qName;
        }
       
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String texto = new String(ch, start, length);
        
        if(tag == true) {
            
          titulos.add(texto);
          tag = false;
        }
            
        
    }
   
}
