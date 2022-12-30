package listener;

import com.google.gson.Gson;
import football.Football;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveListener implements ActionListener {
    private JTextField numberField;
    private JTextField positionField;
    private JFileChooser fileChooser;
    private JTextField salaryField;
    private JTextField ageField;
    private JTextField fileField;
    private int number;
    private int age;
    private int salary;
    private String position;
    private String nameOfFile;
    private JFrame frame;
    private File file;

    public SaveListener() {
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
    public void actionPerformed(ActionEvent arg0) {

        //fileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
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
        System.out.println ("ActionListener.actionPerformed : save");
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
        //nameOfFile = fileField.getText();

        Football player = new Football();
        player.setSalary(salary);
        player.setPosition(position);
        player.setNumber(number);
        player.setAge(age);
        Gson gson = new Gson();
        try {
            FileWriter file = new FileWriter(nameOfFile+".json");
            gson.toJson(player, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
