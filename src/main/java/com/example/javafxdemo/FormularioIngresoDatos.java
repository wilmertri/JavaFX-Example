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
        //Aquí se define el título para el formulario
        primaryStage.setTitle("Formulario de Ingreso de Datos");

        //Aqui se define la grilla que se va a utilizar
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        //Define el ancho entre filas (Vertical)
        grid.setVgap(10);
        //Define el ancho entre columnas (Horizontal)
        grid.setHgap(10);

        //Etiqueta y Campo de Texto para el nombre
        Label nameLabel = new Label("Nombre: ");
        //Se asigna el componente (componente, columna, fila)
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        nameInput.setPromptText("Ingrese su nombre");
        GridPane.setConstraints(nameInput, 1, 0);

        //Etiqueta y Campo de Texto para el apellido
        Label lastnameLabel = new Label("Apellido");
        GridPane.setConstraints(lastnameLabel, 2,0);
        TextField lastnameInput = new TextField();
        lastnameInput.setPromptText("Ingrese su apellido");
        GridPane.setConstraints(lastnameInput, 3, 0);

        //Se define el botón para enviar la información de los campos de texto
        Button submitButton = new Button("Enviar");
        GridPane.setConstraints(submitButton, 1, 2);

        //Se define el botón para terminar la aplicación y cerrar el formulario
        Button closeButton = new Button("Cerrar y Salir");
        GridPane.setConstraints(closeButton, 1, 3);

        //Se agregan todos los componentes a la grilla
        grid.getChildren().addAll(nameLabel, nameInput, lastnameLabel, lastnameInput, submitButton, closeButton);

        //Se definen las acciones al evento clic en el botón Enviar
        submitButton.setOnAction(event -> {
            String name = nameInput.getText();
            System.out.println("Nombre: " + name);
            String lastname = lastnameInput.getText();
            System.out.println("Apellido: " + lastname);
        });

        //Se definen las acciones al evento clic en el botón Cerrar y Salir
        closeButton.setOnAction(event -> {
            primaryStage.close();
            Platform.exit();
        });

        //Se crea el escenario del formulario
        Scene scene = new Scene(grid, 540, 360);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
