package Model;

import Control.DataController;
import DatenStruktur.List;
import View.Viewer;

public class MedicationList extends List<String>{
    public List<String> medicationList;
    private Viewer view;
    private DataController dataController;

    public MedicationList(Viewer view,DataController dataController){
        this.view = view;
        this.dataController=dataController;
        medicationList = new List<>(); // Neue Liste für Medikamente initialisiert
        medicationList.append("Bauchschmerzen-Medikament");
        medicationList.append("Allergie-Medikament");
        medicationList.append("Husten-Medikament");
        medicationList.append("andere-Medikament");


    }


    public void addMedication(String medication) {

        medicationList.append(medication);
    }


    public String getDiseaseFromPatient(String patientInfo) {
            System.out.println(dataController.checkDisease(view.queueScrollPane.getComponentCount()));
        return null;
    }
}