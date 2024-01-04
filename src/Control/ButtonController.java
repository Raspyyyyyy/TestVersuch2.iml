package Control;

import Model.MedicationList;
import View.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController {

    private Viewer view;
    private DataController dataController;
    private MedicationList medicationList;


    private JButton enqueueButton;
    private JButton medicationButton;


    public ButtonController(Viewer view,MedicationList medicationList,DataController dataController) {
        this.view = view;
        this.dataController=dataController;
        this.medicationList=medicationList;

        createEnqueueButton();
        createMedicationButton();
        addListeners();
    }

    public void createEnqueueButton() {
        enqueueButton = new JButton("Enqueue");
        view.frame.add(enqueueButton, BorderLayout.SOUTH);
    }

    public void createMedicationButton() {
        medicationButton = new JButton("Medikament nehmen");
        view.frame.add(medicationButton, BorderLayout.EAST);
        medicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataController.giveMedication();
            }
        });
    }


    public void addListeners() {
        enqueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataController.enqueuePatient(dataController.checkNumberOfPatients());

            }
        });


    }





}