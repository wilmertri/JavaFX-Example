package com.example.javafxdemo;

import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormDataUserController {

    private User user;

    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField age;

    @FXML
    private Label fullName;

    @FXML
    protected void onSendDataUserClick(){
        this.user = new User(name.getText(), lastName.getText(), Integer.parseInt(age.getText()));
        fullName.setText("Mi nombre es: " + this.user.getFullName());
        name.setText("");
        lastName.setText("");
    }

}
