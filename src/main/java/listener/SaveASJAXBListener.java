package listener;

import football.Football;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveASJAXBListener implements ActionListener {
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
    private Football football;

    public SaveASJAXBListener() {
        ageField = new JTextField();
        salaryField = new JTextField();
        numberField = new JTextField();
        fileField = new JTextField();
        positionField = new JTextField();
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
        nameOfFile = fileField.getText();
        football = new Football(number, position, salary, age);
        //File file = new File(nameOfFile);
   /*     try {
            personToXMLExample(nameOfFile, football);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }*/

    }
/*    private static  void personToXMLExample(String filename, Football person) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(Football.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(person, file);
        jaxbMarshaller.marshal(person, System.out);
    }*/
}
