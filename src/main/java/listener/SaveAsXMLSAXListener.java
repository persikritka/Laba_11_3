package listener;

import football.Football;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.System.out;

public class SaveAsXMLSAXListener  implements ActionListener {
    private static JTextField numberField;
    private static JTextField positionField;
    private static JTextField salaryField;
    private static JTextField ageField;
    private static JTextField fileField;
    private static int number;
    private static int age;
    private static int salary;
    private static String position;
    private static String nameOfFile;
    private static JFrame frame;
    private JFileChooser fileChooser;
    private File file;

    public SaveAsXMLSAXListener() {
        ageField = new JTextField();
        salaryField = new JTextField();
        numberField = new JTextField();
        fileField = new JTextField();
        positionField = new JTextField();
        fileChooser = new JFileChooser();
    }
    public void setAgeField(JTextField ageField) {
        this.ageField = ageField;
    }

    public void setNumberField(JTextField numberField) {
        this.numberField = numberField;
    }

    public void setPositionField(JTextField positionField) {
        this.positionField = positionField;
    }

    public void setSalaryField(JTextField salaryField) {
        this.salaryField = salaryField;
    }

    public void setFileField(JTextField fileField) {
        this.fileField = fileField;
    }


    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(frame);
        // Если файл выбран, то представим его в сообщении
        if (result == JFileChooser.APPROVE_OPTION ) {
            file = fileChooser.getSelectedFile();
            nameOfFile = file.getAbsolutePath();
            JOptionPane.showMessageDialog(frame,
                    "Файл " + fileChooser.getSelectedFile().getName() +
                            " сохранен");
        }
        try {
            number = Integer.parseInt(numberField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please, enter the correct number");
        }
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please, enter the correct age");
        }
        try {
            salary = Integer.parseInt(salaryField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please, enter the correct salary");
        }
        position = positionField.getText();

/*
        ArrayList<User> users = new ArrayList<>();
       LocalDate date1 = LocalDate.of(2000, Month.MARCH, 21);
        LocalDate date2 = LocalDate.of(2012, Month.OCTOBER, 30);
        LocalDate date3 = LocalDate.of(2011, Month.JANUARY, 1);
        users.add(new User("John Smith", 22, date1));
        users.add(new User("James Brown", 31, date2));
        users.add(new User("Tom Hanks", 16, date3));*/
        Football player = new Football();
        player.setAge(age);
        player.setPosition(position);
        player.setNumber(number);
        player.setSalary(salary);

        // writes the users
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = null;
        try {
            xsw = xof.createXMLStreamWriter(new FileWriter(nameOfFile+".xml"));
            xsw.writeStartDocument();
            xsw.writeStartElement("Player");

                xsw.writeStartElement("Player");
                xsw.writeStartElement("number");
                xsw.writeCharacters(Integer.toString(player.getNumber()));
                xsw.writeEndElement();
                xsw.writeStartElement("age");
                xsw.writeCharacters(Integer.toString(player.getAge()));
                xsw.writeEndElement();
                xsw.writeStartElement("position");
                xsw.writeCharacters(player.getPosition());
                xsw.writeEndElement();
                xsw.writeStartElement("salary");
                xsw.writeCharacters(Integer.toString(player.getSalary()));
                xsw.writeEndElement();


            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
        } catch (Exception ex) {
            System.err.println("Unable to write the file: " + ex.getMessage());
        } finally {
            try {
                if (xsw != null) {
                    xsw.close();
                }
            } catch (Exception ex) {
                System.err.println("Unable to close the file: " + ex.getMessage());
            }
        }

        try {
            format(nameOfFile+".xml");
        } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
            ex.printStackTrace();
        }
    }

    private static void format(String file) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));

        // Gets a new transformer instance
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        // Sets XML formatting
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        // Sets indent
        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(file));
        xformer.transform(source, result);
    }

    }

