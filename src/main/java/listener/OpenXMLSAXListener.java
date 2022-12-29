package listener;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import football.Football;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenXMLSAXListener implements ActionListener {
    private JFileChooser fileChooser;
    private Football football;
    private JFrame frame;
    private JTextField numberField;
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField  positionField;

    public OpenXMLSAXListener() {
        fileChooser = new JFileChooser();
        numberField = new JTextField();
        ageField = new JTextField();
        salaryField = new JTextField();
        positionField = new JTextField();
    }

    public void setAgeField(JTextField ageField) {
        this.ageField = ageField;
    }

    public void setSalaryField(JTextField salaryField) {
        this.salaryField = salaryField;
    }

    public void setPositionField(JTextField positionField) {
        this.positionField = positionField;
    }

    public void setNumberField(JTextField numberField) {
        this.numberField = numberField;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public void setFootball(Football football) {
        this.football = football;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Football getFootball() {
        return football;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

   /* public int getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }*/

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getNumberField() {
        return numberField;
    }

    public JTextField getPositionField() {
        return positionField;
    }

    public JTextField getSalaryField() {
        return salaryField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String fileName = "Book.xml.txt";
        int result = fileChooser.showOpenDialog(frame);
        if (result== JFileChooser.APPROVE_OPTION) {
            File file1 = fileChooser.getSelectedFile();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Здесь мы определили анонимный класс, расширяющий класс DefaultHandler
            DefaultHandler handler = new DefaultHandler() {
                // Поле для указания, что тэг NAME начался
                boolean name = false;
                boolean number = false;
                boolean position = false;
                boolean salary = false;

                // Метод вызывается когда SAXParser "натыкается" на начало тэга
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    // Если тэг имеет имя NAME, то мы этот момент отмечаем - начался тэг NAME
                    if (qName.equalsIgnoreCase("Age")) {
                        name = true;
                    }
                    else if(qName.equalsIgnoreCase("Number"))
                        number = true;
                   else  if(qName.equalsIgnoreCase("Position"))
                        position = true;
                   else  if(qName.equalsIgnoreCase("Salary"))
                        salary = true;
                }

                // Метод вызывается когда SAXParser считывает текст между тэгами
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    // Если перед этим мы отметили, что имя тэга NAME - значит нам надо текст использовать.
                    if (name) {
                        System.out.println("Age: " + new String(ch, start, length));
                        String ageStr = new String(ch, start, length);
                        ageField.setText(ageStr);
                        name = false;
                    } else if(number) {
                        System.out.println("Number: " + new String(ch, start, length));
                        String numberStr = new String(ch, start, length);
                        numberField.setText(numberStr);
                        number = false;
                    }
                   else  if(position) {
                        System.out.println("Position: " + new String(ch, start, length));
                        String positionStr = new String(ch, start, length);
                        positionField.setText(positionStr);
                        position = false;
                    }
                   else  if(salary) {
                        System.out.println("Salary: " + new String(ch, start, length));
                        String salaryStr = new String(ch, start, length);
                        salaryField.setText(salaryStr);
                        salary = false;
                    }
                }



            };

            // Стартуем разбор методом parse, которому передаем наследника от DefaultHandler, который будет вызываться в нужные моменты
            saxParser.parse(file1, handler);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}}



