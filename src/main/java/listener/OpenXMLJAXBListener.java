package listener;

import football.Football;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class OpenXMLJAXBListener implements ActionListener {
    private JFileChooser fileChooser;
    private Football football;
    private JFrame frame;
    private JTextField numberField;
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField  positionField;

    public OpenXMLJAXBListener() {
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

    }
}
