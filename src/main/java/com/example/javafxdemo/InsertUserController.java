package com.example.javafxdemo;

import entities.User;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repositories.DBUser;
import javafx.fxml.FXMLLoader;

import javax.swing.*;
import java.io.IOException;
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

    private User oldUser;

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
            DBUser db = new DBUser(userSelected);
            db.deleteData();
            userList.remove(userSelected);
            tableView.getSelectionModel().clearSelection();
        }else{
            showAlert("Advertencia", "Por favor seleccione un usuario para eliminar");
        }
    }

    @FXML
    protected void onSelectUserClick(){
        User userSelected = tableView.getSelectionModel().getSelectedItem();
        if (userSelected != null) {
            name.setText(userSelected.getName());
            lastName.setText(userSelected.getLastName());
            age.setText(String.valueOf(userSelected.getAge()));
            this.oldUser = new User(userSelected.getName(), userSelected.getLastName(), userSelected.getAge());
        }
        else{
            showAlert("Advertencia", "Por favor seleccione un usuario para actualizar");
        }
    }

    @FXML
    protected void onUpdateDataUserClick() {
        User userSelected = tableView.getSelectionModel().getSelectedItem();
        if (userSelected != null) {
            User newUser = new User(name.getText(), lastName.getText(), Integer.parseInt(age.getText()));
            userSelected.setName(name.getText());
            userSelected.setLastName(lastName.getText());
            userSelected.setAge(Integer.parseInt(age.getText()));
            tableView.refresh();

            DBUser db = new DBUser(this.oldUser);
            boolean updated = db.updateData(newUser); // Método para actualizar datos en la base de datos
            if (updated)
            {
                JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente");
            }
            tableView.getSelectionModel().clearSelection();
        } else {
            showAlert("Advertencia", "Por favor seleccione un usuario para actualizar");
        }
    }

    private void convertData() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        ageColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
    }

    @FXML
    protected void onOpenSecondFormClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("second-form-view.fxml"));
            Parent root = fxmlLoader.load();

            SecondFormController controller = fxmlLoader.getController();
            controller.setUsers(this.userList);

            Stage stage = new Stage();
            stage.setTitle("Segundo Formulario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
