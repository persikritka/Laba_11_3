package listener;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveAsXMLDomListener implements ActionListener {
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
    private File fileToSave;

    public SaveAsXMLDomListener() {
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
            fileToSave = fileChooser.getSelectedFile();
            nameOfFile = fileToSave.getAbsolutePath();
            JOptionPane.showMessageDialog(frame,
                    "Файл " + fileChooser.getSelectedFile().getName() +
                            " сохранен");}
        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            addNewPlayer(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        }

    }

    private static void addNewPlayer(Document document) throws TransformerFactoryConfigurationError, DOMException {

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


        // Создаем новую книгу по элементам
        // Сама книга <Book>
        Element player = document.createElement("Player");
        // <Title>
        Element ageEl = document.createElement("age");
        // Устанавливаем значение текста внутри тега
        ageEl.setTextContent(String.valueOf(age));
        // <Author>
        Element numberEl = document.createElement("Number");
        numberEl.setTextContent(String.valueOf(number));
        // <Date>
        Element positionEl = document.createElement("Position");
        positionEl.setTextContent(String.valueOf(position));
        // <ISBN>
        Element salaryEl = document.createElement("Salary");
        salaryEl.setTextContent(String.valueOf(salary));

        // Добавляем внутренние элементы книги в элемент <Book>
        player.appendChild(numberEl);
        player.appendChild(ageEl);
        player.appendChild(positionEl);
        player.appendChild(salaryEl);
        // Добавляем книгу в корневой элемент
        document.appendChild(player);

        // Записываем XML в файл
        writeDocument(document);
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(nameOfFile + ".xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
            fos.close();
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}


