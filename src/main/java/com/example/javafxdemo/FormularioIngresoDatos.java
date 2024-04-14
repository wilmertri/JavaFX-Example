package com.example.javafxdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FormularioIngresoDatos extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Formulario de Ingreso de Datos");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        //Etiqueta y Campo de Texto para el nombre
        Label nameLabel = new Label("Nombre: ");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Ingrese su nombre");
        GridPane.setConstraints(nameInput, 1, 0);

        Button submitButton = new Button("Enviar");
        GridPane.setConstraints(submitButton, 1, 3);

        Button closeButton = new Button("Cerrar y Salir");
        GridPane.setConstraints(closeButton, 1, 4);

        grid.getChildren().addAll(nameLabel, nameInput, submitButton, closeButton);

        submitButton.setOnAction(event -> {
            String name = nameInput.getText();
            System.out.println("Nombre: " + name);
        });

        closeButton.setOnAction(event -> {
            primaryStage.close();
            Platform.exit();
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
