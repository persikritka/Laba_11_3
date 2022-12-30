package listener;

import football.Football;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.swing.*;
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
        Football player = new Football();
        player.setSalary(salary);
        player.setPosition(position);
        player.setNumber(number);
        player.setAge(age);
        try {
            JAXBContext context = null;
                context = JAXBContext.newInstance(Football.class);
                Marshaller mar = context.createMarshaller();
                mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                mar.marshal(player, new File(nameOfFile + ".xml"));
            } catch (JAXBException ex) {
                throw new RuntimeException(ex);
            }
    }
}
