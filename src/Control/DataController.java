package Control;


import DatenStruktur.Queue;
import Model.MedicationList;
import View.Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DataController {

    public JList<String> listInfo;

    public Queue<String> patientQueue;
    private Viewer view;
    private MedicationList medicationList;

   public int[] patient;


public DataController(Viewer view){
    this.view=view;
    medicationList= new MedicationList(view,this);
    this.patientQueue= new Queue<>();

   patient= new int[20];

}

    public Queue<String> getQueue() {
        return patientQueue;
    }

    public int checkNumberOfPatients(){

        Queue<String> hilfeQueue= new Queue<>();
        int counter =0;

        while(!patientQueue.isEmpty()){
            hilfeQueue.enqueue(patientQueue.front());
            patientQueue.dequeue();

            counter++;
        }

        while(!hilfeQueue.isEmpty()){

            patientQueue.enqueue(hilfeQueue.front());
            hilfeQueue.dequeue();
        }

        System.out.println(counter);
        return counter;



    }

    public int enqueuePatient(int check) {

        String patientInfo = "Name: " + view.getNameField().getText() +
                ", Alter: " + view.getAgeField().getText() +
                ", Krankheit: " + view.getDiseaseComboBox().getSelectedItem();

            patient[check] = view.getDiseaseComboBox().getSelectedIndex();

            patientQueue.enqueue(patientInfo);
          
            updateQueue();
            checkNumberOfPatients();

            return patient[check];
    }


public String checkDisease(int check) {
    Queue<String> patientQueueCopy = copyQueue(patientQueue);

    int count = 0;
    while (!patientQueueCopy.isEmpty()) {
        if (count == check - 1) {
            break;
        }
        count++;
        patientQueueCopy.dequeue();
    }
    if (patientQueueCopy.front() != null) {
        return patientQueueCopy.front();
    }
    return "Die Krankheit des Patienten konnte nicht identifiziert werden";
}

public String getDisease(){
    String selectedPatient = listInfo.getSelectedValue();
        if (selectedPatient != null) {
            String selectedDisease = medicationList.getDiseaseFromPatient(selectedPatient);
            if (Objects.equals(selectedDisease, "Husten")) {
                System.out.println("Husten");
            }
            if (Objects.equals(selectedDisease, "Bauchschmerzen")) {
                System.out.println("Bauchschmerzen");
            }
            if (Objects.equals(selectedDisease, "Allergie")) {
                System.out.println("Allergie");
            }
            if (Objects.equals(selectedDisease, "Andere")) {
                System.out.println("Andere");
            }
        }
        return "Die Krankheit des Patienten konnte nicht Identifiziert werden";
    }


public static <ContentType> Queue<ContentType> copyQueue(Queue<ContentType> queue) {
    Queue<ContentType> help = new Queue<>();
    Queue<ContentType> copy = new Queue<>();

    while (!queue.isEmpty()) {
        copy.enqueue(queue.front());
        help.enqueue(queue.front());
        queue.dequeue();
    }

    while(!help.isEmpty()){
        queue.enqueue(help.front());
        help.dequeue();
    }
    return copy;
}



    public void updateQueue() {

        if (patientQueue != null) {
            DefaultListModel<String> listModel = new DefaultListModel<>();

            //gehen durch unsere Queue durch und schicken unsere Elemnte zum Modell
            Queue<String> tempQueue = new Queue<>();
            while (!patientQueue.isEmpty()) {
                String patientInfo = patientQueue.front();
                listModel.addElement(patientInfo);
                tempQueue.enqueue(patientInfo);
                patientQueue.dequeue();
            }

            // Wiederherstelle die ursprüngliche Reihenfolge der Warteschlange
            while (!tempQueue.isEmpty()) {
                patientQueue.enqueue(tempQueue.front());
                tempQueue.dequeue();
            }

            listInfo = new JList<>(listModel);
            view.queueScrollPane.setViewportView(listInfo);
        }
        view.clearInputFields();
        view.enqueueConfirmation();

    }





    public void giveMedication() {
        String selectedPatient = listInfo.getSelectedValue();

        if (selectedPatient != null) {

            String selectedDisease = medicationList.getDiseaseFromPatient(selectedPatient);
            String medication = findMedication(selectedDisease);
            view.displayMedication(medication);
        }
    }




    public String findMedication(String disease) {
        if (medicationList != null) {
            medicationList.toFirst(); // Startet am Anfang der Medikamentenliste

            while (medicationList.hasAccess()) {

                String currentMedication =  (String) medicationList.getContent();
                String[] parts = currentMedication.split("-");

                // Überprüfen Sie, ob parts die erwartete Länge hat
                if (parts.length == 2) {
                    String currentDisease = parts[0].trim(); // Krankheitsname extrahieren und Leerzeichen entfernen
                    if (currentDisease.equalsIgnoreCase(disease.trim())) {
                        return parts[1].trim(); // Rückgabe des Medikaments
                    }
                }
                medicationList.next(); // Gehen zum nächsten Medikament in der Liste
            }
        }

        return "Kein passendes Medikament gefunden, nehmen Sie sich bitte platz bis der Arzt Sie aufruft";
    }

}
