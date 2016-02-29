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
import javafx.scene.layout.BorderPane;
import marmar.ganaderia_fxml.managers.ChartManager;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class GrafosController implements Initializable {

    @FXML
    private ComboBox<String> cbGrafos;
    @FXML
    private BorderPane paneGrafo;

    private ChartManager charter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        charter = new ChartManager();

        fillGrafoTab();

        //para que muestre el pie chart desde un principio
        switchChart('1');
    }

    //fill chart tab
    private void fillGrafoTab() {
        //llenar el combobox de grafos
        ObservableList<String> cd = FXCollections.observableArrayList();
        cd.add("1. Población de Bovinos en Grafo Circular");
        cd.add("2. Población de Bovinos en Grafo de Barras");
        cd.add("3. Población de Razas en Grafo de Barras");
        cd.add("4. Nacimeintos por Año en Grafo Lineal");
        cd.add("5. Muertes por Año en Grafo Lineal");

        cbGrafos.setItems(cd);
        cbGrafos.setValue(cd.get(0));

        cbGrafos.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            char c = newValue.charAt(0);
            switchChart(c);
        });
    }

    private void switchChart(char c) {
        switch (c) {
            case '1':
                //1. Grafo Circular de Población de Bovinos
                paneGrafo.setCenter(charter.fillChartPiePopBovinos());
                break;
            case '2':
                //2. Grafo de Barras de Población de Bovinos
                paneGrafo.setCenter(charter.fillChartBarPopBovinos());
                break;
            case '3':
                //3. Población de Razas en Grafo de Barras
                paneGrafo.setCenter(charter.fillChartRazas());
                break;
            case '4':
                //4. Nacimeintos por Año en Grafo Lineal
                paneGrafo.setCenter(charter.fillChartNacimientos());
                break;
            default:
            case '5':
                //5. Muertes por Año en Grafo Lineal
                paneGrafo.setCenter(charter.fillChartMuertes());
                break;
        }
    }

}
