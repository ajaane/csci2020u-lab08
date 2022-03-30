package com.example.lab08;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HelloController {
    private String  currentFilename = Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + "mySave.csv";
    private FileChooser fc = new FileChooser();


    @FXML
    GridPane gPane;

    @FXML
    private TextField sidText;
    @FXML
    private TextField assignmentsText;
    @FXML
    private TextField midtermText;
    @FXML
    private TextField finalText;

    private ObservableList<StudentRecord> dataList = FXCollections.observableArrayList();

    @FXML
    private TableView<StudentRecord> tableView;

    @FXML
    private TableColumn<StudentRecord, String> IDColumn;

    @FXML
    private TableColumn<StudentRecord, Float> assignmentsColumn;

    @FXML
    private TableColumn<StudentRecord, Float> midtermColumn;

    @FXML
    private TableColumn<StudentRecord, Float> finalExamColumn;

    @FXML
    private TableColumn<StudentRecord, Float> finalMarkColumn;

    @FXML
    private TableColumn<StudentRecord, String> gradeColumn;

    @FXML
    private void initialize(){
        IDColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("studentID"));
        assignmentsColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("assignments"));
        midtermColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("midterm"));
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalExam"));
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalMark"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("grade"));
        tableView.setItems(DataSource.getAllMarks());
        dataList = tableView.getItems();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.setInitialDirectory(new File(currentPath));
    }

    @FXML
    private void clear(){
        tableView.getItems().clear();
        dataList.clear();
    }

    @FXML
    private void exit(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void save() throws Exception {
        File file = null;
        Writer writer = null;
        try {
            file = new File(currentFilename);
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord record : dataList) {

                String row = record.getStudentID() + "," + record.getAssignments() + "," +
                        record.getMidterm() + "," + record.getFinalExam() + "\n";

                writer.write(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    @FXML
    private void saveAs() throws Exception {
        // User selects path and name for file
        fc.setTitle("Save File");
        fc.setInitialFileName("mySave");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        File file = fc.showSaveDialog(null);
        if (file != null){
            currentFilename = file.getAbsolutePath();
        }
        System.out.println(currentFilename);
        save();
    }

    @FXML
    private void open() throws FileNotFoundException {
        fc.setTitle("Open");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        // User selects file to open
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            currentFilename = selectedFile.getName();
        }

        load(selectedFile);
    }

    private void load(File file) throws FileNotFoundException {
        String delimiter = ",";
        BufferedReader br;

        try {
            // Read in selected file
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);

            String line = "";
            // Add each row of the csv file as an item in dataList
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(delimiter, -1);
                StudentRecord record = new StudentRecord(cols[0], Float.parseFloat(cols[1]),
                        Float.parseFloat(cols[2]), Float.parseFloat(cols[3]));
                dataList.add(record);
            }
            // Add all items from dataList to table view
            tableView.setItems(dataList);
            fileReader.close();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addItem(){
        //Add item to listview
        tableView.getItems().add(new StudentRecord(sidText.getText(), Float.parseFloat(assignmentsText.getText()),
                Float.parseFloat(midtermText.getText()), Float.parseFloat(finalText.getText())));

        //Clear text box fields
        sidText.clear();
        assignmentsText.clear();
        midtermText.clear();
        finalText.clear();
    }
}

