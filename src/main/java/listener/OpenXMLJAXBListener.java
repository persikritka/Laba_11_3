package listener;

import football.Football;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class OpenXMLJAXBListener implements ActionListener {
    private JFileChooser fileChooser;
    private Football football;
    private JFrame frame;
    private JTextField numberField;
    private JTextField ageField;
    private JTextField salaryField;
    private JTextField positionField;

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
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file1 = fileChooser.getSelectedFile();
            try {
                JAXBContext context = JAXBContext.newInstance(Football.class);
                Marshaller mar = context.createMarshaller();
                mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                Football unmarshal = (Football) context.createUnmarshaller()
                        .unmarshal(new FileReader(file1));
                numberField.setText(Integer.toString(unmarshal.getNumber()));
                ageField.setText(Integer.toString(unmarshal.getAge()));
                salaryField.setText(Integer.toString(unmarshal.getSalary()));
                positionField.setText(unmarshal.getPosition());
            } catch (JAXBException | FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
