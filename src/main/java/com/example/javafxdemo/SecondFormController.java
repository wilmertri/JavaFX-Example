package com.example.javafxdemo;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
public class SecondFormController {

    @FXML
    private Label label;

    @FXML
    private ComboBox<User> users;

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    public void initialize() {
        label.setText("Bienvenido al segundo formulario!");
        users.setItems(userList);
        users.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getFullName() : "";
            }

            @Override
            public User fromString(String string) {
                return null; // No necesario
            }
        });

        users.setOnAction(event -> {
            User selectedUser = users.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                label.setText("Usuario seleccionado: " + selectedUser.getFullName());
            }
        });
    }

    public void setUsers(ObservableList<User> users) {
        userList.setAll(users);
    }
}
