package com.tuempresa.serviciosweb.controller;

import com.tuempresa.serviciosweb.model.Cliente;
import com.tuempresa.serviciosweb.service.ClienteService;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class ClienteController {

    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, Long> colId;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colCorreo;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, String> colPais;
    @FXML
    private TableColumn<Cliente, String> colCiudad;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, BigDecimal> colIngresoAnual;
    @FXML
    private TableColumn<Cliente, Integer> colPuntajeCrediticio;
    @FXML
    private TableColumn<Cliente, Boolean> colDeudaImpaga;
    @FXML
    private TableColumn<Cliente, BigDecimal> colMontoDeuda;
    @FXML
    private TableColumn<Cliente, String> colEstado;
    @FXML
    private TableColumn<Cliente, String> colCreacion;
    @FXML
    private TableColumn<Cliente, String> colActualizacion;
    @FXML
    private Button btnConsultar;
    @FXML
    private Label lblEstado;

    private final ClienteService service = new ClienteService();

    @FXML
    private void initialize() {
        tblClientes.setEditable(false);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colIngresoAnual.setCellValueFactory(new PropertyValueFactory<>("ingresoAnual"));
        colPuntajeCrediticio.setCellValueFactory(new PropertyValueFactory<>("puntajeCrediticio"));
        colDeudaImpaga.setCellValueFactory(new PropertyValueFactory<>("tieneDeudaImpaga"));
        colMontoDeuda.setCellValueFactory(new PropertyValueFactory<>("montoDeudaImpaga"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        colActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));

        colDeudaImpaga.setCellFactory(columna -> new TableCell<Cliente, Boolean>() {
            @Override
            protected void updateItem(Boolean deuda, boolean vacia) {
                super.updateItem(deuda, vacia);

                if (vacia || deuda == null) {
                    setText(null);
                } else {
                    setText(deuda ? "Sí" : "No");
                }
            }
        });

        lblEstado.setText("Presioná el botón Consultar clientes");
        btnConsultar.setOnAction(evento -> consultarClientes());
    }

    @FXML
    private void consultarClientes() {
        Task<List<Cliente>> task = new Task<List<Cliente>>() {
            @Override
            protected List<Cliente> call() throws Exception {
                return service.ObtenerClientes();
            }
        };

        btnConsultar.setDisable(true);
        lblEstado.setText("Consultando API REST...");

        // En caso de éxito
        task.setOnSucceeded(evento -> {
            List<Cliente> clientes = task.getValue();
            tblClientes.setItems(FXCollections.observableArrayList(clientes));
            lblEstado.setText("Clientes encontrados: " + clientes.size());
            btnConsultar.setDisable(false);
        });

        // En caso de error
        task.setOnFailed(evento -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
            btnConsultar.setDisable(false);
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}