package com.tuempresa.serviciosweb.controller;

import com.tuempresa.serviciosweb.model.Usuario;
import com.tuempresa.serviciosweb.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UsuarioController {

    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario,String> colId;
    @FXML
    private TableColumn<Usuario,String> colNombre;
    @FXML
    private TableColumn<Usuario,String> colEmail;
    @FXML
    private TableColumn<Usuario,String> colUsername;
    @FXML
    private TableColumn<Usuario,String> colTelefono;
    @FXML
    private TableColumn<Usuario,String> colCiudad;
    @FXML
    private TableColumn<Usuario,String> colEmpresa;
    @FXML
    private Button btnConsultar;
    @FXML
    private Label lblEstado;

    private final UsuarioService service = new UsuarioService();

    @FXML
    private void initialize() {

        tblUsuarios.setEditable(false);

        colId.setCellValueFactory(
                new PropertyValueFactory<>("Id")
        );
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("Nombre")
        );
        colUsername.setCellValueFactory(
                new PropertyValueFactory<>("Username")
        );
        colEmail.setCellValueFactory(
                new PropertyValueFactory<>("Email")
        );
        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("Telefono")
        );
        colCiudad.setCellValueFactory(
                new PropertyValueFactory<>("Ciudad")
        );
        colEmpresa.setCellValueFactory(
                new PropertyValueFactory<>("Empresa")
        );

        lblEstado.setText("Presiona el boton Consultar");
    }

    @FXML
    private void consultarUsuarios(){
        Task<List<Usuario>> task = new Task<List<Usuario>>() {
            @Override
            protected List<Usuario> call() throws Exception {
                return service.ObtenerUsuarios();
            }
        };

        btnConsultar.disableProperty().bind(
                task.runningProperty()
        );
        lblEstado.setText("Consultando API REST......");

        //En caso que el resultado haya sido exitoso
        task.setOnSucceeded(e -> {
            List<Usuario> usuarios = task.getValue();
            tblUsuarios.setItems(FXCollections.observableList(usuarios));
            lblEstado.setText("Usuario Encontrado.");
        });

        //En caso de error
        task.setOnFailed(e -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
        });
        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();

    }
}
