package Model;

import Control.ButtonController;
import Control.DataController;
import View.Viewer;

import javax.swing.*;

public class MainClass {

 private static MedicationList medicationList;
 private static DataController dataController;

 public static void main(String[] args) {

  SwingUtilities.invokeLater(() -> {
   try {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   } catch (Exception e) {
    e.printStackTrace();
   }

    Viewer view = new Viewer(medicationList,dataController);
    dataController = new DataController(view);
    medicationList= new MedicationList(view,dataController);
    ButtonController buttonController = new ButtonController(view,medicationList,dataController);

  });
 }
}