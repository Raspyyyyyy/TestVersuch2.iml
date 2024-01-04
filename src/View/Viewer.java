package View;

import Control.ButtonController;
import Control.DataController;
import Model.MedicationList;

import javax.swing.*;
import java.awt.*;

public class Viewer {
    public JFrame frame;

    private JTextField ageField;
    private JComboBox<String> diseaseComboBox;
    public JButton takeMedicationButton;

    public JScrollPane queueScrollPane;
    private JTextField nameField;

    public JPanel infoPanel;
    private ButtonController controller;
    private DataController dataController;
    private MedicationList medicationList;

    public Viewer(MedicationList medicationList,DataController dataController) {
        this.medicationList=medicationList;
        this.dataController=dataController;

        frame = new JFrame("Queue Application");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        queueScrollPane = new JScrollPane();
        queueScrollPane.
        frame.add(queueScrollPane, BorderLayout.CENTER);

        infoPanel = new JPanel(new GridLayout(4, 2));
        nameField = new JTextField();
        ageField = new JTextField();
        String[] diseases = {"Bauchschmerzen", "Allergie", "Husten", "andere"};
        diseaseComboBox = new JComboBox<>(diseases);

        infoPanel.add(new JLabel("Name:"));
        infoPanel.add(nameField);
        infoPanel.add(new JLabel("Alter:"));
        infoPanel.add(ageField);
        infoPanel.add(new JLabel("Krankheit:"));
        infoPanel.add(diseaseComboBox);

        frame.add(infoPanel, BorderLayout.WEST);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);


        controller = new ButtonController(this,medicationList,dataController); // Controller erst nach Initialisierung der Medikamentenliste erstellen
    }


    /*
    enqueueButton.addActionListener(e -> {
        String patientInfo = "Name: " + nameField.getText() + ", Alter: " + ageField.getText() +
                ", Krankheit: " + diseaseComboBox.getSelectedItem();
        int selectedDiseaseIndex = diseaseComboBox.getSelectedIndex();
       // model.enqueue(patientInfo, selectedDiseaseIndex);
        //updateQueueList();
        //dequeueNextPatient();
       // clearInputFields();
    });
    */

    

    public void clearInputFields() {
        nameField.setText("");
        ageField.setText("");
        diseaseComboBox.setSelectedIndex(0);
    }

    public void enqueueConfirmation(){

JOptionPane.showMessageDialog(frame,"Patient wurde erfolgreich angestellt");


    }


    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JComboBox<String> getDiseaseComboBox() {
        return diseaseComboBox;
    }




    //   zur Anzeige von Medikamenten
    public void displayMedication(String medication) {
        JOptionPane.showMessageDialog(frame, "Medikament: " + medication);
    }
}












