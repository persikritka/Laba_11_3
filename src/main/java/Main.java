import football.Football;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import menu.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String args[]) throws JAXBException, FileNotFoundException {
        new Menu();
       /* Football player = new Football();
        player.setSalary(1234);
        player.setPosition("top");
        player.setNumber(10);
        player.setAge(23);

            JAXBContext context = JAXBContext.newInstance(Football.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(player, new File("./book.xml"));
        Football unmarshal = (Football) context.createUnmarshaller()
                .unmarshal(new FileReader("./book.xml"));
        System.out.println(unmarshal);*/

    } }





