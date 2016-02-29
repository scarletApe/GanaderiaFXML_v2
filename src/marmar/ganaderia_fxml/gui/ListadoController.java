/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marmar.ganaderia_fxml.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import marmar.ganaderia_fxml.entidades.Bovino;
import marmar.ganaderia_fxml.entidades.Raza;
import marmar.ganaderia_fxml.entidades.Vacunacion;
import marmar.ganaderia_fxml.entidades.Venta;
import marmar.ganaderia_fxml.hibernate.ConectorBovino;
import marmar.ganaderia_fxml.hibernate.ConectorRaza;
import marmar.ganaderia_fxml.hibernate.ConectorVacunacion;
import marmar.ganaderia_fxml.hibernate.ConectorVenta;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class ListadoController implements Initializable {

    @FXML
    private ComboBox<String> comboBovEdad;
    @FXML
    private ComboBox<String> comboBovSexo;
    @FXML
    private TextField tfBovAnos;
    @FXML
    private ComboBox<Raza> comboBovRaza;
    @FXML
    private ComboBox<String> comboBovEstado;
    @FXML
    private ComboBox<String> comboBovAretado;
    @FXML
    private TableView<Bovino> tablaBov;
    @FXML
    private Button bBovEliminar;
    @FXML
    private Button bBovModificar;
    @FXML
    private Button bBovActualizar;
    @FXML
    private Button bRazEliminar;
    @FXML
    private Button bRazModificar;
    @FXML
    private TableView<Raza> tablaRazas;
    @FXML
    private Button bRazaActualizar;
    @FXML
    private TableView<Venta> tablaVentas;
    @FXML
    private Button bVentaVerBovinos;
    @FXML
    private Button bVentasEliminar;
    @FXML
    private Button bVentasModificar;
    @FXML
    private Button bVentasActualizar;
    @FXML
    private Button bVacunaVerBovinos;
    @FXML
    private Button bVacunaEliminar;
    @FXML
    private Button bVacunaModificar;
    @FXML
    private Button bVacunaActualizar;
    @FXML
    private TableView<Vacunacion> tablaVacunas;

    private ConectorRaza conraza;
    private ConectorBovino conbovino;
    private ConectorVenta conventa;
    private ConectorVacunacion convacuna;

    private Raza selectedRaza;
    private Bovino selectedBovino;

    private RegistrarController registrarController;
    private ReportesController reportesController;
    private MainController mainController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conraza = new ConectorRaza();
        conbovino = new ConectorBovino();
        conventa = new ConectorVenta();
        convacuna = new ConectorVacunacion();

        fillBovineTab();
        fillRazaTab();
        fillVentaTab();
        fillVacunacionTab();
    }

    void initData(RegistrarController rc, ReportesController repc, MainController mc) {
        registrarController = rc;
        reportesController = repc;
        mainController = mc;
    }

    //**************************************************************************
    //******  Los metodos para bovinos tab
    //**************************************************************************
    @FXML
    private void handleButtonBovNuevo(ActionEvent e) {

    }

    @FXML
    private void handleButtonBovModificar(ActionEvent e) {

//        Bovino selectedItem = tablaBov.getSelectionModel().getSelectedItem();
//
//        if (selectedItem != null) {
//            tabPaneMain.getSelectionModel().select(tabRegistrar);
//            tabPaneRegistrar.getSelectionModel().select(tabRegBovComp);
//
//            bRB_Guardar.setText("Actualizar Bovino");
//            selectedBovino = selectedItem;
//
//            tfRB_ID.setText(selectedItem.getId_bovino() + "");
//            tfRB_Arete.setText(selectedItem.getNum_arete());
//            tfRB_Apodo.setText(selectedItem.getApodo());
//            tfRB_Color.setText(selectedItem.getPelaje());
//            taRB_Com.setText(selectedItem.getComentario());
//
//            java.sql.Date d = (java.sql.Date) selectedItem.getFecha_nacimiento();
//            if (d != null) {
//                dpRB_FNac.setValue(d.toLocalDate());
//            }
//
//            d = (java.sql.Date) selectedItem.getFecha_muerte();
//            if (d != null) {
//                dpRB_FMue.setValue(d.toLocalDate());
//            }
//
//            cbRB_Melona.setValue(selectedItem.getMelona());
//            cbRB_Sexo.setValue(selectedItem.getSexo());
//            cbRB_Raza.setValue(selectedItem.getRaza());
//        }
    }

    @FXML
    private void handleButtonBovEliminar(ActionEvent e) {
        Bovino selectedItem = tablaBov.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            conbovino.delete(selectedItem);
            actualizarBovineTab();
        }
    }

    @FXML
    private void handleButtonBovActualizar(ActionEvent e) {
        actualizarBovineTab();
    }

    @FXML
    private void handleButtonBovVerReporte(ActionEvent e) {
        Bovino selectedBov = tablaBov.getSelectionModel().getSelectedItem();
        if (selectedBov != null) {
//            mainController.setPane(mainController.reportesPane);
            mainController.selectListIndex(2);

            reportesController.loadReporteBovino(selectedBov);
        }
    }
    //**************************************************************************
    //******  Los metodos para raza tab
    //**************************************************************************

    @FXML
    private void handleButtonRazNuevo(ActionEvent e) {

    }

    @FXML
    private void handleButtonRazModificar(ActionEvent e) {
//        Raza selectedItem = tablaRazas.getSelectionModel().getSelectedItem();
//
//        if (selectedItem != null) {
//            tabPaneMain.getSelectionModel().select(tabRegistrar);
//            tabPaneRegistrar.getSelectionModel().select(tabRegRaza);
//
//            bRegRazaGuardar.setText("Actualizar Raza");
//
//            selectedRaza = selectedItem;
//            tfRegRazaNombre.setText(selectedItem.getNombre());
//            taRegTazaDescri.setText(selectedItem.getDescripcion());
//        }
    }

    @FXML
    private void handleButtonRazEliminar(ActionEvent e) {
        Raza selectedItem = tablaRazas.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            conraza.delete(selectedItem);
            actualizarRazaTab();
        }
    }

    @FXML
    private void handleButtonRazActualizar(ActionEvent e) {
        actualizarRazaTab();
    }

    //**************************************************************************
    //******  Los metodos para ventas tab
    //**************************************************************************
    @FXML
    private void handleButtonVentaVerBovinos(ActionEvent e) {
        Venta selectedVenta = tablaVentas.getSelectionModel().getSelectedItem();
        if (selectedVenta != null) {
//            mainController.setPane(mainController.reportesPane);
            mainController.selectListIndex(2);
            reportesController.loadReporteVenta(selectedVenta);
        }
    }

    @FXML
    private void handleButtonVentaModificar(ActionEvent e) {

    }

    @FXML
    private void handleButtonVentaEliminar(ActionEvent e) {
        Venta selectedItem = tablaVentas.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            conventa.delete(selectedItem);
            actualizarVentaTab();
        }
    }

    @FXML
    private void handleButtonVentaActualizar(ActionEvent e) {
        actualizarVentaTab();
    }

    //**************************************************************************
    //******  Los metodos para vacunas tab
    //**************************************************************************
    @FXML
    private void handleButtonVacunaVerBovinos(ActionEvent e) {
        Vacunacion selectedVac = tablaVacunas.getSelectionModel().getSelectedItem();
        if (selectedVac != null) {
//            mainController.setPane(mainController.reportesPane);
            mainController.selectListIndex(2);
            reportesController.loadReporteVacuna(selectedVac);
        }
    }

    @FXML
    private void handleButtonVacunaModificar(ActionEvent e) {

    }

    @FXML
    private void handleButtonVacunaEliminar(ActionEvent e) {
        Vacunacion selectedItem = tablaVacunas.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            convacuna.delete(selectedItem);
            actualizarVacunacionTab();
        }
    }

    @FXML
    private void handleButtonVacunaActualizar(ActionEvent e) {
        actualizarVacunacionTab();
    }

    private void fillBovineTab() {
        tfBovAnos.setText("0");

        //llenar el combobox de sexos
        ObservableList<String> sexos
                = FXCollections.observableArrayList(
                        "Ambos",
                        "Hembra",
                        "Macho"
                );
        comboBovSexo.setItems(sexos);
        comboBovSexo.setValue("Ambos");
        comboBovSexo.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    actualizarBovineTab();
                });

        //llenar el combobox de estados
        ObservableList<String> estados
                = FXCollections.observableArrayList(
                        "Todo Estado",
                        "Vivas",
                        "Muertas",
                        "Vendidas"
                );
        comboBovEstado.setItems(estados);
        comboBovEstado.setValue("Vivas");
        comboBovEstado.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    actualizarBovineTab();
                });

        //llenar el combobox de aretados
        ObservableList<String> aretados
                = FXCollections.observableArrayList(
                        "Todas",
                        "Con Arete",
                        "Sin Arete"
                );
        comboBovAretado.setItems(aretados);
        comboBovAretado.setValue("Todas");
        comboBovAretado.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    actualizarBovineTab();
                });

        //llenar el combobox de edades
        ObservableList<String> edades
                = FXCollections.observableArrayList(
                        "Toda Edad",
                        "Mayor o Igual Que",
                        "Menor o Igual Que"
                );
        comboBovEdad.setItems(edades);
        comboBovEdad.setValue("Toda Edad");
        comboBovEdad.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                    actualizarBovineTab();
                });

        //llenar el combobox de razas
//        ObservableList<Raza> razaData = FXCollections.observableArrayList();
//        ArrayList<Raza> alraza = conraza.getAll();
//        alraza.stream().forEach((alraza1) -> {
//            razaData.add(alraza1);
//        });
//        comboBovRaza.setItems(razaData);
        //llenar la tabla de bovinos
        ObservableList<Bovino> bovinoData = FXCollections.observableArrayList();
        ArrayList<Bovino> bovinos = conbovino.getVivosyNoVendidos();
        bovinos.stream().forEach((bovino) -> {
            bovinoData.add(bovino);
        });

        TableColumn colID = new TableColumn("Id");
        colID.setMinWidth(50);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("id_bovino"));

        TableColumn colArete = new TableColumn("Arete");
        colArete.setMinWidth(100);
        colArete.setCellValueFactory(
                new PropertyValueFactory<>("num_arete"));

        TableColumn colSexo = new TableColumn("Sexo");
        colSexo.setMinWidth(100);
        colSexo.setCellValueFactory(
                new PropertyValueFactory<>("sexo"));

        TableColumn colColor = new TableColumn("Color");
        colColor.setMinWidth(100);
        colColor.setCellValueFactory(
                new PropertyValueFactory<>("pelaje"));

        TableColumn colMelona = new TableColumn("Melona");
        colMelona.setMinWidth(50);
        colMelona.setCellValueFactory(
                new PropertyValueFactory<>("melona"));

        TableColumn colApodo = new TableColumn("Apodo");
        colApodo.setMinWidth(100);
        colApodo.setCellValueFactory(
                new PropertyValueFactory<>("apodo"));

        TableColumn colFNaci = new TableColumn("Fecha Naci.");
        colFNaci.setMinWidth(100);
        colFNaci.setCellValueFactory(
                new PropertyValueFactory<>("fecha_nacimiento"));

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        TableColumn colEdad = new TableColumn("Edad (Meses)");
        colEdad.setMinWidth(100);
        colEdad.setCellValueFactory(
                new PropertyValueFactory<>("edad"));
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        TableColumn colFMuer = new TableColumn("Fecha Muerte");
        colFMuer.setMinWidth(100);
        colFMuer.setCellValueFactory(
                new PropertyValueFactory<>("fecha_muerte"));

        TableColumn colRaza = new TableColumn("Raza");
        colRaza.setMinWidth(100);
        colRaza.setCellValueFactory(
                new PropertyValueFactory<>("raza"));

        TableColumn colVenta = new TableColumn("Venta");
        colVenta.setMinWidth(300);
        colVenta.setCellValueFactory(
                new PropertyValueFactory<>("venta"));

        TableColumn colComentario = new TableColumn("Comentario");
        colComentario.setMinWidth(300);
        colComentario.setCellValueFactory(
                new PropertyValueFactory<>("comentario"));

//        tablaBov.getItems().clear();
        tablaBov.getColumns().clear();

        tablaBov.setItems(bovinoData);
        tablaBov.getColumns().addAll(
                colID,
                colArete,
                colSexo,
                colColor,
                colRaza,
                colApodo,
                colMelona,
                colFNaci,
                colEdad,
                colFMuer,
                colVenta,
                colComentario);
    }

    private void actualizarBovineTab() {
        //llenar el combobox de razas
//        ArrayList<Raza> alraza = conraza.getAll();
//        comboBovRaza.getItems().clear();
//        comboBovRaza.getItems().addAll(alraza);

        //llenar la tabla de bovinos
//        ArrayList<Bovino> bovinos = conbovino.getAll();
//        tablaBov.getItems().clear();
//        tablaBov.getItems().addAll(bovinos);
        String sexo = comboBovSexo.getSelectionModel().getSelectedItem();
        String estado = comboBovEstado.getSelectionModel().getSelectedItem();
        String arete = comboBovAretado.getSelectionModel().getSelectedItem();
        String edad = comboBovEdad.getSelectionModel().getSelectedItem();
        int meses = 0;
        try {
            meses = Integer.parseInt(tfBovAnos.getText());
        } catch (Exception e) {
            System.out.println("es no es un entero!!! >:(");
        }

        ArrayList<Bovino> bovinos = conbovino.getBovinosFiltrados(sexo, estado, arete, selectedRaza, edad, meses);
        tablaBov.getItems().clear();
        tablaBov.getItems().addAll(bovinos);
    }

    private void fillVentaTab() {
        ObservableList<Venta> ventaData = FXCollections.observableArrayList();
        ArrayList<Venta> ventas = conventa.getAll();
        ventas.stream().forEach((venta) -> {
            ventaData.add(venta);
        });

        TableColumn colID = new TableColumn("Id");
        colID.setMinWidth(100);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("id_venta"));

        TableColumn colComprador = new TableColumn("Comprador");
        colComprador.setMinWidth(200);
        colComprador.setCellValueFactory(
                new PropertyValueFactory<>("comprador"));

        TableColumn colMonto = new TableColumn("Monto");
        colMonto.setMinWidth(100);
        colMonto.setCellValueFactory(
                new PropertyValueFactory<>("monto"));

        TableColumn colFechaVenta = new TableColumn("Fecha de Venta");
        colFechaVenta.setMinWidth(200);
        colFechaVenta.setCellValueFactory(
                new PropertyValueFactory<>("fecha_venta"));

        tablaVentas.setItems(ventaData);
        tablaVentas.getColumns().clear();
        tablaVentas.getColumns().addAll(colID,
                colComprador,
                colMonto,
                colFechaVenta);
    }

    private void actualizarVentaTab() {
        ArrayList<Venta> ventas = conventa.getAll();
        tablaVentas.getItems().clear();
        tablaVentas.getItems().addAll(ventas);
    }

    private void fillVacunacionTab() {
        ObservableList<Vacunacion> vacunaData = FXCollections.observableArrayList();
        ArrayList<Vacunacion> vacunaciones = convacuna.getAll();
        vacunaciones.stream().forEach((vacunacione) -> {
            vacunaData.add(vacunacione);
        });

        TableColumn colID = new TableColumn("Id");
        colID.setMinWidth(50);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("id_vacunacion"));

        TableColumn colMedico = new TableColumn("Veterinario");
        colMedico.setMinWidth(200);
        colMedico.setCellValueFactory(
                new PropertyValueFactory<>("medico"));

        TableColumn colTipo = new TableColumn("Vacuna");
        colTipo.setMinWidth(100);
        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));

        TableColumn colFormula = new TableColumn("Comentario");
        colFormula.setMinWidth(300);
        colFormula.setCellValueFactory(
                new PropertyValueFactory<>("formula"));

        TableColumn colFechaVacuna = new TableColumn("Fecha de Vacunación");
        colFechaVacuna.setMinWidth(200);
        colFechaVacuna.setCellValueFactory(
                new PropertyValueFactory<>("fecha_vacunacion"));

        tablaVacunas.setItems(vacunaData);
        tablaVacunas.getColumns().clear();
        tablaVacunas.getColumns().addAll(colID,
                colMedico,
                colTipo,
                colFormula,
                colFechaVacuna);
    }

    private void actualizarVacunacionTab() {
        ArrayList<Vacunacion> vacunaciones = convacuna.getAll();
        tablaVacunas.getItems().clear();
        tablaVacunas.getItems().addAll(vacunaciones);
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //     los metodos para llenar y actualizar los tabs
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void fillRazaTab() {
        ObservableList<Raza> razaData = FXCollections.observableArrayList();
        ArrayList<Raza> alraza = conraza.getAll();
        alraza.stream().forEach((alraza1) -> {
            razaData.add(alraza1);
        });

        TableColumn colID = new TableColumn("Id");
        colID.setMinWidth(100);
        colID.setCellValueFactory(
                new PropertyValueFactory<>("id_raza"));

        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setMinWidth(200);
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        TableColumn colDescripcion = new TableColumn("Descripcion");
        colDescripcion.setMinWidth(500);
        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion"));

        tablaRazas.setItems(razaData);
        tablaRazas.getColumns().clear();
        tablaRazas.getColumns().addAll(colID, colNombre, colDescripcion);

    }

    private void actualizarRazaTab() {
        ArrayList<Raza> alraza = conraza.getAll();
        tablaRazas.getItems().clear();
        tablaRazas.getItems().addAll(alraza);
    }
}
