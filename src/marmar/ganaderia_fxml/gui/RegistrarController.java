/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marmar.ganaderia_fxml.gui;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import marmar.ganaderia_fxml.entidades.Bovino;
import marmar.ganaderia_fxml.entidades.Raza;
import marmar.ganaderia_fxml.entidades.Vacunacion;
import marmar.ganaderia_fxml.entidades.Venta;
import marmar.ganaderia_fxml.hibernate.ConectorBovino;
import marmar.ganaderia_fxml.hibernate.ConectorRaza;
import marmar.ganaderia_fxml.hibernate.ConectorVacunacion;
import marmar.ganaderia_fxml.hibernate.ConectorVenta;
import org.hibernate.collection.internal.PersistentSet;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class RegistrarController implements Initializable {

    @FXML
    private TabPane tabPaneRegistrar;
    @FXML
    private Tab tabRegBovComp;
    @FXML
    private TextField tfRB_Apodo;
    @FXML
    private TextField tfRB_Color;
    @FXML
    private DatePicker dpRB_FMue;
    @FXML
    private TextField tfRB_Arete;
    @FXML
    private ComboBox<String> cbRB_Melona;
    @FXML
    private TextArea taRB_Com;
    @FXML
    private DatePicker dpRB_FNac;
    @FXML
    private TextField tfRB_ID;
    @FXML
    private ComboBox<String> cbRB_Sexo;
    @FXML
    private ComboBox<Raza> cbRB_Raza;
    @FXML
    private Button bRB_Guardar;
    @FXML
    private Tab tabRegRaza;
    @FXML
    private TextArea taRegTazaDescri;
    @FXML
    private TextField tfRegRazaNombre;
    @FXML
    private Button bRegRazaLimpiar;
    @FXML
    private Button bRegRazaGuardar;
    @FXML
    private TextField tfRegVenComprador;
    @FXML
    private TextField tfRegVenMonto;
    @FXML
    private DatePicker dpRegVenFecha;
    @FXML
    private ListView<Bovino> listRegVenRight;
    @FXML
    private ListView<Bovino> listRegVenDisp;
    @FXML
    private Tab tabRegVacuna;
    @FXML
    private TextField tfRegVacunaVeterinario;
    @FXML
    private DatePicker dpRegVacunaFecha;
    @FXML
    private TextField tfRegVacunaComentario;
    @FXML
    private TextField tfRegVacuna;
    @FXML
    private ListView<Bovino> listRegVacunaRight;
    @FXML
    private ListView<Bovino> listRegVacunaDisp;
    @FXML
    private Button bRegVacunaGuardar;
    @FXML
    private TextField tfRegNacColor;
    @FXML
    private DatePicker dpRegNacFN;
    @FXML
    private ComboBox<String> cbRegNacSexo;
    @FXML
    private ComboBox<Raza> cbRegNacRaza;
    @FXML
    private TextArea taRegNacComentario;
    @FXML
    private Button bRegNacLimpiar;
    @FXML
    private Button bRegNacAnadir;
    @FXML
    private Button bRegNacActualizar;
    @FXML
    private ListView<Bovino> listRegNac;
    @FXML
    private Button bRegNacLimpiar2;
    @FXML
    private Button bRegNacGuardar;
    @FXML
    private ListView<Bovino> listRegMtRight;
    @FXML
    private ListView<Bovino> listRegMtDisp;
    @FXML
    private Button bRegMtAnadir;
    @FXML
    private Button bRegMtRemover;
    @FXML
    private Button bRegMtLimpiar;
    @FXML
    private Button bRegMtGuardar;
    @FXML
    private DatePicker dpRegMtFecha;

    private ConectorRaza conraza;
    private ConectorBovino conbovino;
    private ConectorVenta conventa;
    private ConectorVacunacion convacuna;
    
    private Raza selectedRaza;
    private Bovino selectedBovino;
    
    private ListadoController listadoController;

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

        fillRegBovinoTab();
        fillRegVenTab();
        fillRegVacunaTab();
        fillRegNacTab();
        fillRegMtTab();
    }
    
    void initData(ListadoController lc) {
        listadoController = lc;
    }

    //**************************************************************************
    //******  Los metodos para registrar nacimeintos tab
    //**************************************************************************
    @FXML
    private void handleButtonRegNacActualizar(ActionEvent e) {
        //llenar el combobox de razas
        ObservableList<Raza> razaData = FXCollections.observableArrayList();
        ArrayList<Raza> alraza = conraza.getAll();
        alraza.stream().forEach((alraza1) -> {
            razaData.add(alraza1);
        });
        cbRegNacRaza.setItems(razaData);
    }

    @FXML
    private void handleButtonRegNacLimpiar(ActionEvent e) {
        tfRegNacColor.clear();
//        dpRegNacFN

    }

    @FXML
    private void handleButtonRegNacAnadir(ActionEvent e) {
        String color = tfRegNacColor.getText();
        String sexo = cbRegNacSexo.getSelectionModel().getSelectedItem();
        Raza raza = cbRegNacRaza.getSelectionModel().getSelectedItem();
        String da = dpRegNacFN.getEditor().getText();
//        String comentario = ta

        System.out.println("date string=" + da);

        if (color != null
                && sexo != null
                && raza != null
                && da != null
                && !da.isEmpty()
                && !color.isEmpty()) {
            Date fn = new Date(da);
            Bovino b = new Bovino(sexo, color, fn, raza);
            listRegNac.getItems().add(b);

            handleButtonRegNacLimpiar(e);
        }
    }

    @FXML
    private void handleButtonRegNacLimpiar2(ActionEvent e) {
        listRegNac.getItems().clear();
    }

    @FXML
    private void handleButtonRegNacGuardar(ActionEvent e) {
        ObservableList<Bovino> items = listRegNac.getItems();
        items.stream().forEach((item) -> {
            conbovino.saveNew(item);
        });
        handleButtonRegNacLimpiar2(e);
//        actualizarBovineTab();
    }

    //**************************************************************************
    //******  Los metodos para registrar razas tab
    //**************************************************************************
    @FXML
    private void handleButtonRegRazaLimpiar(ActionEvent e) {
        tfRegRazaNombre.clear();
        taRegTazaDescri.clear();
        bRegRazaGuardar.setText("Guardar");
    }

    @FXML
    private void handleButtonRegRazaGuardar(ActionEvent e) {
        String nombre = tfRegRazaNombre.getText();
        String descri = taRegTazaDescri.getText();

        if (nombre != null & descri != null
                & !nombre.isEmpty() && !descri.isEmpty()) {

            if (bRegRazaGuardar.getText().equalsIgnoreCase("Actualizar Raza")) {
                selectedRaza.setNombre(nombre);
                selectedRaza.setDescripcion(descri);
                conraza.update(selectedRaza);
                handleButtonRegRazaLimpiar(e);
                bRegRazaGuardar.setText("Guardar");

            } else {
                Raza r = new Raza(nombre, descri);
                conraza.saveNew(r);
                handleButtonRegRazaLimpiar(e);
            }
        }
//        actualizarRazaTab();
    }

    //**************************************************************************
    //******  Los metodos para registrar muertes tab
    //**************************************************************************
    @FXML
    private void handleButtonRegMTAnadir(ActionEvent e) {
        Bovino selectedItem = listRegMtDisp.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegMtDisp.getItems().remove(selectedItem);
            listRegMtRight.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegMTRemover(ActionEvent e) {
        Bovino selectedItem = listRegMtRight.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegMtRight.getItems().remove(selectedItem);
            listRegMtDisp.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegMTLimpiar(ActionEvent e) {
        fillRegMtTab();
        dpRegMtFecha.getEditor().setText("");
    }

    @FXML
    private void handleButtonRegMTGuardar(ActionEvent e) {
        String f = dpRegMtFecha.getEditor().getText();
        if (f != null && !f.isEmpty()) {
            Date fecha = new Date(f);
            Bovino b;
            ObservableList<Bovino> items = listRegMtRight.getItems();
            for (Bovino item : items) {
                b = item;
                b.setFecha_muerte(fecha);
                conbovino.update(b);
            }

            fillRegMtTab();
        }
//        actualizarBovineTab();
    }

    //**************************************************************************
    //******  Los metodos para registrar ventas tab
    //**************************************************************************
    @FXML
    private void handleButtonRegVenAnadir(ActionEvent e) {
        Bovino selectedItem = listRegVenDisp.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegVenDisp.getItems().remove(selectedItem);
            listRegVenRight.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegVenRemover(ActionEvent e) {
        Bovino selectedItem = listRegVenRight.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegVenRight.getItems().remove(selectedItem);
            listRegVenDisp.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegVenLimpiar(ActionEvent e) {
        fillRegVenTab();
    }

    @FXML
    private void handleButtonRegVenGuardar(ActionEvent e) {
        //TODO
        String f = dpRegVenFecha.getEditor().getText();
        String monto = tfRegVenMonto.getText();
        String comprador = tfRegVenComprador.getText();

        if (f != null && !f.isEmpty()
                && monto != null && !monto.isEmpty()
                && comprador != null && !comprador.isEmpty()) {

            Date fecha = new Date(f);
            Venta v = new Venta(comprador, fecha, new BigDecimal(monto));
            conventa.saveNew(v);
            Bovino b;

            ObservableList<Bovino> items = listRegVenRight.getItems();
            for (Bovino item : items) {
                b = item;
                b.setVenta(v);
                conbovino.update(b);
            }

            fillRegVenTab();
        }
//        actualizarVentaTab();
    }

    //**************************************************************************
    //******  Los metodos para registrar vacunacion tab
    //**************************************************************************
    @FXML
    private void handleButtonRegVacunaAnadir(ActionEvent e) {
        Bovino selectedItem = listRegVacunaDisp.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegVacunaDisp.getItems().remove(selectedItem);
            listRegVacunaRight.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegVacunaRemover(ActionEvent e) {
        Bovino selectedItem = listRegVacunaRight.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            listRegVacunaRight.getItems().remove(selectedItem);
            listRegVacunaDisp.getItems().add(selectedItem);
        }
    }

    @FXML
    private void handleButtonRegVacunaLimpiar(ActionEvent e) {
        fillRegVacunaTab();
    }

    @FXML
    private void handleButtonRegVacunaGuardar(ActionEvent e) {
        //TODO
        String f = dpRegVacunaFecha.getEditor().getText();
        String vacuna = tfRegVacuna.getText();
        String veterinario = tfRegVacunaVeterinario.getText();
        String comentario = tfRegVacunaComentario.getText();

        if (f != null && !f.isEmpty()
                && vacuna != null && !vacuna.isEmpty()
                && veterinario != null && !veterinario.isEmpty()
                && comentario != null && !comentario.isEmpty()) {

            Date fecha = new Date(f);

            Vacunacion vac = new Vacunacion(vacuna, veterinario, comentario, fecha);
//            convacuna.saveNew(vac);
            Bovino b;
            PersistentSet hs;

            ObservableList<Bovino> items = listRegVacunaRight.getItems();
            for (Bovino item : items) {
                b = item;
                hs = (PersistentSet) b.getVacunas();
                hs.add(vac);
                conbovino.update(b);
            }

            fillRegVacunaTab();
            System.out.println("Finished with the vacunas");
        }
//        actualizarVacunacionTab();
    }

    //**************************************************************************
    //******  Los metodos para registrar bovino tab
    //**************************************************************************
    @FXML
    private void handleButtonRegBovinoLimpiar(ActionEvent e) {
        fillRegBovinoTab();
        bRB_Guardar.setText("Guardar");
    }

    @FXML
    private void handleButtonRegBovinoGuardar(ActionEvent e) {
        //TODO
        String arete = tfRB_Arete.getText();
        String apodo = tfRB_Apodo.getText();
        String color = tfRB_Color.getText();
        String sexo = cbRB_Sexo.getSelectionModel().getSelectedItem();
        Raza raza = cbRB_Raza.getSelectionModel().getSelectedItem();
        String melona = cbRB_Melona.getSelectionModel().getSelectedItem();
        String fn = dpRB_FNac.getEditor().getText();
        String fm = dpRB_FMue.getEditor().getText();
        String comentario = taRB_Com.getText();

        if (fn.isEmpty()) {
            return;
        }

        Date nacimeinto = new Date(fn);

        if (bRB_Guardar.getText().equalsIgnoreCase("Actualizar Bovino")) {
            Bovino b = selectedBovino;
            b.setSexo(sexo);
            b.setPelaje(color);
            b.setFecha_nacimiento(nacimeinto);
            b.setNum_arete(arete);
            b.setApodo(apodo);
            b.setMelona(melona);
            b.setRaza(raza);
            b.setComentario(comentario);

            if (!fm.isEmpty()) {
                Date muerte = new Date(fm);
                b.setFecha_muerte(muerte);
            } else {
                b.setFecha_muerte(null);
            }

            conbovino.update(b);
            handleButtonRegBovinoLimpiar(e);
        } else {

            Bovino b = new Bovino(sexo, color, nacimeinto, raza);
            b.setNum_arete(arete);
            b.setApodo(apodo);
            b.setMelona(melona);
            b.setComentario(comentario);

            if (!fm.isEmpty()) {
                Date muerte = new Date(fm);
                b.setFecha_muerte(muerte);
            } else {
                b.setFecha_muerte(null);
            }

            conbovino.saveNew(b);
            handleButtonRegBovinoLimpiar(e);
        }
//        actualizarBovineTab();
    }

    //fillRegBovinoTab
    private void fillRegBovinoTab() {
        tfRB_ID.setText("");
        tfRB_Arete.setText("");
        tfRB_Apodo.setText("");
        tfRB_Color.setText("");
        dpRB_FNac.getEditor().setText("");
        dpRB_FMue.getEditor().setText("");
        taRB_Com.setText("");

        //llenar el combobox de melonas
        ObservableList<String> mel
                = FXCollections.observableArrayList(
                        "Si",
                        "No"
                );
        cbRB_Melona.setItems(mel);
        cbRB_Melona.setValue("No");

        //llenar el combobox de sexos
        ObservableList<String> sexos
                = FXCollections.observableArrayList(
                        "Hembra",
                        "Macho"
                );
        cbRB_Sexo.setItems(sexos);
        cbRB_Sexo.setValue("Hembra");

        //llenar el combobox de razas
        ObservableList<Raza> razaData = FXCollections.observableArrayList();
        ArrayList<Raza> alraza = conraza.getAll();
        alraza.stream().forEach((alraza1) -> {
            razaData.add(alraza1);
        });
        cbRB_Raza.setItems(razaData);
        cbRB_Raza.setValue(alraza.get(0));
    }

    //fillRegVenTab();
    private void fillRegVenTab() {
        tfRegVenComprador.clear();
        tfRegVenMonto.clear();
        dpRegVenFecha.getEditor().clear();

        //llenar la lista de los bovinos disponibles
        ObservableList<Bovino> bovData = FXCollections.observableArrayList();
        ArrayList<Bovino> bovinos = conbovino.getVivosyNoVendidos();
        bovinos.stream().forEach((bovino) -> {
            bovData.add(bovino);
        });
        listRegVenDisp.setItems(bovData);

        //limpiar la otra lista
        ObservableList<Bovino> bovData2 = FXCollections.observableArrayList();
        listRegVenRight.setItems(bovData2);
    }

    //fillRegVacunaTab
    private void fillRegVacunaTab() {
        tfRegVacuna.clear();
        tfRegVacunaVeterinario.clear();
        tfRegVacunaComentario.clear();
        dpRegVacunaFecha.getEditor().clear();

        //llenar la lista de los bovinos disponibles
        ObservableList<Bovino> bovData = FXCollections.observableArrayList();
        ArrayList<Bovino> bovinos = conbovino.getVivosyNoVendidos();
        bovinos.stream().forEach((bovino) -> {
            bovData.add(bovino);
        });
        listRegVacunaDisp.setItems(bovData);

        //limpiar la otra lista
        ObservableList<Bovino> bovData2 = FXCollections.observableArrayList();
        listRegVacunaRight.setItems(bovData2);
    }

    private void fillRegNacTab() {
        //llenar el combobox de sexos
        ObservableList<String> sexos
                = FXCollections.observableArrayList(
                        "Hembra",
                        "Macho"
                );
        cbRegNacSexo.setItems(sexos);
        cbRegNacSexo.setValue("Hembra");

        //llenar el combobox de razas
        ObservableList<Raza> razaData = FXCollections.observableArrayList();
        ArrayList<Raza> alraza = conraza.getAll();
        alraza.stream().forEach((alraza1) -> {
            razaData.add(alraza1);
        });
        cbRegNacRaza.setItems(razaData);
        cbRegNacRaza.setValue(alraza.get(0));

        ObservableList<Bovino> nacimientosData = FXCollections.observableArrayList();
        listRegNac.setItems(nacimientosData);
    }

    private void fillRegMtTab() {
        //llenar la lista de los bovinos disponibles
        ObservableList<Bovino> bovData = FXCollections.observableArrayList();
        ArrayList<Bovino> bovinos = conbovino.getVivosyNoVendidos();
        bovinos.stream().forEach((bovino) -> {
            bovData.add(bovino);
        });
        listRegMtDisp.setItems(bovData);

        //limpiar la otra lista
        ObservableList<Bovino> bovData2 = FXCollections.observableArrayList();
        listRegMtRight.setItems(bovData2);
    }
}
