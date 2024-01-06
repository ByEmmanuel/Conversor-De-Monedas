package conversor.conversordemonedas;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.beans.binding.StringExpression;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

    private VBox vbox;
    private Label documentacion;
    private ComboBox<String> Boton1;
    private ComboBox<String> Boton2;
    String selectedOption1;
    String selectedOption2;
    TextField subject;
    private String cantidad;

    public Controller() {
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));

        setupUI();
    }

    private void setupUI() {

        Label labelBetweenDropdowns = new Label("CONVERTIR A ");
        labelBetweenDropdowns.setAlignment(Pos.CENTER);

        Label notification = new Label("ingrese valor:");

        this.subject = new TextField("");

        this.subject.setEditable(true); // Deshabilitar la edición
        this.subject.setPromptText("Ingrese Valor a calcular");
        
        this.cantidad = this.subject.getText();
        TextArea textArea = new TextArea("");
        textArea.setEditable(false); // Deshabilitar la edición

        documentacion = new Label("\u00A9 Derechos Reservados.         Jesus Emmanuel G. Challenge Alura Grupo: 5. ");
        documentacion.setPadding(new Insets(10));

        Button button = new Button("Enviar");
        button.setId("button");
        button.setDisable(true); // Inicialmente, el botón está deshabilitado
        subject.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isNumeric(newValue)) {
                // Si el contenido del TextArea es un número, habilitar el botón
                button.setDisable(false);
            } else {
                // Si el contenido del TextArea no es un número, deshabilitar el botón
                button.setDisable(true);

            }
        });

        button.setOnAction(event -> {
            this.cantidad = this.subject.getText();
            
        
            this.selectedOption1 = Boton1.getValue();
            this.selectedOption2 = Boton2.getValue();
            //Exchange_Rate_API apicall = new Exchange_Rate_API();
            ApiCalls apicall = new ApiCalls();
            

            System.out.println("Primera opcion: " + selectedOption1);
            System.out.println("Segunda opcion: " + selectedOption2);
            System.out.println("Cantidad de dinero en la clase botones= "+ cantidad);
        
            String tasaDeConversion = apicall.connection(selectedOption1,selectedOption2, cantidad);
            textArea.setText(tasaDeConversion);
            System.out.println(tasaDeConversion);
            System.out.println("Primera opción: " + selectedOption1);
            System.out.println("Tasa de cambio: " );
            System.out.println("Valor a cambiar: " + cantidad);
            
            });
        

        Button clearButton = new Button("Limpiar Campos");
        clearButton.setId("clear-button"); // Asignar el ID "clear-button"
        clearButton.setOnAction(event -> {
            textArea.setText("");
            subject.setText("");
            clearConsole();
        });

        Button botonapi = new Button("Llamar a la api");
        botonapi.setId("Api-button"); // Asignar el ID "clear-button"
        botonapi.setOnAction(event -> {
            String ResultadoApi;
           

        });

        this.Boton1 = new ComboBox<>();
        this.Boton1.setId("lion");
        this.Boton1.getItems().addAll("MXN", "USD", "EUR", "CAD", "AUD", "EGP" );
        this.Boton1.setPromptText("Seleccionar moneda");
        this.Boton1.setOnAction(event -> {
            System.out.println("");
        });

        this.Boton2 = new ComboBox<>();
        this.Boton2.setId("lion");
        this.Boton2.getItems().addAll("MXN", "USD", "EUR", "CAD", "AUD", "EGP", "AFN");
        this.Boton2.setPromptText("Seleccionar moneda");
        this.Boton2.setOnAction(event -> {
            System.out.println("");
        });

        HBox comboBoxesContainer = new HBox(Boton1, Boton2);
        comboBoxesContainer.setAlignment(Pos.CENTER);
        comboBoxesContainer.setSpacing(0);

        vbox.getChildren().addAll(notification, subject, Boton1, labelBetweenDropdowns, Boton2, textArea,
                button, clearButton, botonapi);
    }

    private boolean isNumeric(String str) {
        try {
            // Intentar convertir el texto a un número
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            // Si ocurre una excepción, significa que el texto no es un número
            return false;
        }
    }

    public VBox getRoot() {
        return this.vbox;
    }

    public Label getDocumentacion() {
        return this.documentacion;
    }

    private void clearConsole() {
        try {
            // Verificar el sistema operativo para usar el comando adecuado para limpiar la
            // consola
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // En Windows, utiliza el comando "cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // En sistemas Unix/Linux, utiliza el comando "clear"
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de error
            e.printStackTrace();
        }
    }

    public String getSelectedOption() {
        return Boton1.getValue();
    }

    public String getSelectedOption2() {
        return Boton2.getValue();
    }

    public void setSelectedOption1(String selectedOption1) {
        this.selectedOption1 = selectedOption1;
    }

    
    
    public String getCantidad() {
        if (subject != null) {
                return subject.getText();
        } else {
                return "a"; // Devuelve una cadena vacía si el campo de texto es nulo
        }
    }
        
}

