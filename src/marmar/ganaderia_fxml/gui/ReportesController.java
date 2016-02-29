/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marmar.ganaderia_fxml.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import marmar.ganaderia_fxml.entidades.Bovino;
import marmar.ganaderia_fxml.entidades.Vacunacion;
import marmar.ganaderia_fxml.entidades.Venta;
import marmar.ganaderia_fxml.managers.ReportManager;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class ReportesController implements Initializable {

    @FXML
    private ComboBox<String> cbReporte;
    @FXML
    private WebView viewReporte;

    private ReportManager reporter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        reporter = new ReportManager();

        fillReporteTab();

        //para que muestre el pie chart desde un principio
        switchReport('1');
    }

    private void fillReporteTab() {
        //llenar el combobox de grafos
        ObservableList<String> cd = FXCollections.observableArrayList();
        cd.add("1. Reporte General");
        cd.add("2. Reporte de Población de Bovinos Vivos");
        cd.add("3. Reporte de Ventas del Año");
        cd.add("4. Reporte de Vacunaciones del Año");
        cd.add("5. Reporte de Muertes del Año");

        cbReporte.setItems(cd);
        cbReporte.setValue(cd.get(0));

        cbReporte.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
            char c = nv.charAt(0);
            switchReport(c);
        });
    }

    private void switchReport(char c) {
        switch (c) {
            case '1':
            default:
                final WebEngine webEngine = viewReporte.getEngine();
                webEngine.loadContent(reporter.getReporteInicial());
                break;

        }
    }

    public void loadReporteBovino(Bovino b) {
        final WebEngine webEngine = viewReporte.getEngine();
        webEngine.loadContent(reporter.getReporteBovino(b));
    }

    //loadReporteVenta
    public void loadReporteVenta(Venta v) {
        final WebEngine webEngine = viewReporte.getEngine();
        webEngine.loadContent(reporter.getReportVenta(v));
    }

    //loadReporteVacuna
    public void loadReporteVacuna(Vacunacion v) {
        final WebEngine webEngine = viewReporte.getEngine();
        webEngine.loadContent(reporter.getReportVacunacion(v));
    }
}
