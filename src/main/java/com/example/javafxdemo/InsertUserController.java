package com.example.javafxdemo;

import entities.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import repositories.DBUser;

import java.util.ArrayList;

public class InsertUserController {

    @FXML
    private TextField name;
    @FXML
    private TextField lastName;
    @FXML
    private TextField age;
    @FXML
    private Label fullName;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, Integer> ageColumn;

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        DBUser db = new DBUser();
        ArrayList<User> usersDB = db.getData();
        this.convertData();
        userList.addAll(usersDB);
        tableView.setItems(userList);
    }

    @FXML
    protected void onSendDataUserClick(){
        User newUser = new User(name.getText(), lastName.getText(), Integer.parseInt(age.getText()));
        fullName.setText("Usuario registrado: " + newUser.getFullName());
        this.convertData();
        userList.add(newUser);
        DBUser db = new DBUser(newUser);
        db.addData();
        name.setText("");
        lastName.setText("");
        age.setText("");
    }

    @FXML
    protected void onDeleteDataUserClick(){
        User userSelected = tableView.getSelectionModel().getSelectedItem();
        if (userSelected != null){
            userList.remove(userSelected);
            tableView.getSelectionModel().clearSelection();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un usuario para eliminar");
            alert.showAndWait();
        }
    }

    private void convertData() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        ageColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
    }
}
