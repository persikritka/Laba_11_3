import com.google.gson.Gson;
import football.Football;
//import frame.Frame;
import menu.Menu;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String args[]) {
        new Menu();
       // new SAX();
       /* final String fileName = "Book.xml.txt";

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Здесь мы определили анонимный класс, расширяющий класс DefaultHandler
            DefaultHandler handler = new DefaultHandler() {
                // Поле для указания, что тэг NAME начался
                boolean name = false;
                boolean number = false;

                // Метод вызывается когда SAXParser "натыкается" на начало тэга
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Если тэг имеет имя NAME, то мы этот момент отмечаем - начался тэг NAME
                    if (qName.equalsIgnoreCase("Age")) {
                        name = true;
                    }
                    if(qName.equalsIgnoreCase("Number"))
                        number = true;
                }

                // Метод вызывается когда SAXParser считывает текст между тэгами
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    // Если перед этим мы отметили, что имя тэга NAME - значит нам надо текст использовать.
                    if (name) {
                        System.out.println("Age: " + new String(ch, start, length));
                        name = false;
                    } if(number) {
                        System.out.println("Number: " + new String(ch, start, length));
                        number = false;
                    }
                }



            };

            // Стартуем разбор методом parse, которому передаем наследника от DefaultHandler, который будет вызываться в нужные моменты
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Football.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
        Unmarshaller um = null;
        try {
            um = context.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
        Football bookstore = null;
        try {
            bookstore = (Football) um.unmarshal(new InputStreamReader(
                    new FileInputStream("test4.xml"), StandardCharsets.UTF_8));
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println(bookstore);


    } }





