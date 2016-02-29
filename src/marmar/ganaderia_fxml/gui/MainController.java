/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marmar.ganaderia_fxml.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author juanmartinez
 */
public class MainController implements Initializable {

//    private Usuario usuario;
    private AnchorPane listadoPane;
    private AnchorPane registrarPane;
    private AnchorPane reportesPane;
    private AnchorPane grafosPane;

    private ListadoController listadoController;
    private RegistrarController registrarController;
    private ReportesController reportesController;
    private GrafosController grafosController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> opciones
                = FXCollections.observableArrayList(
                        "Listado",
                        "Registrar",
                        "Reportes",
                        "Grafos"
                );
        listMenu.setItems(opciones);

        //load the first pane
        Platform.runLater(() -> {
            listMenu.getSelectionModel().select(0);
            loadAllPanes();
            paneData.getChildren().setAll(listadoPane);
            listMenu.requestFocus();

            listMenu.getSelectionModel().selectedItemProperty()
                    .addListener((ObservableValue<? extends String> o, String ov, String nv) -> {
                        handleListMenu();
                    });
        });

    }

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane paneData;

    @FXML
    private Button btnResize;

    @FXML
    private ListView<String> listMenu;

    @FXML
    private Label lbTitle;

    private void handleListMenu() {
        switch (listMenu.getSelectionModel().getSelectedIndex()) {
            case 0:
//                loadAnchorPane(paneData, "Listado.fxml");
                setPane(listadoPane);
                break;
            case 1:
//                loadAnchorPane(paneData, "Registrar.fxml");
                setPane(registrarPane);
                break;
            case 2:
//                loadAnchorPane(paneData, "Reportes.fxml");
                setPane(reportesPane);
                break;
            case 3:
//                loadAnchorPane(paneData, "Grafos.fxml");
                setPane(grafosPane);
                break;
        }
    }

    @FXML
    void handleLogout(ActionEvent event) {

    }

    private void loadAllPanes() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/marmar/ganaderia_fxml/gui/Listado.fxml"));
            listadoPane = (AnchorPane) loader.load();
            listadoController = loader.<ListadoController>getController();

            loader = new FXMLLoader(getClass().getResource("/marmar/ganaderia_fxml/gui/Registrar.fxml"));
            registrarPane = (AnchorPane) loader.load();
            registrarController = loader.<RegistrarController>getController();

            loader = new FXMLLoader(getClass().getResource("/marmar/ganaderia_fxml/gui/Reportes.fxml"));
            reportesPane = (AnchorPane) loader.load();
            reportesController = loader.<ReportesController>getController();

            loader = new FXMLLoader(getClass().getResource("/marmar/ganaderia_fxml/gui/Grafos.fxml"));
            grafosPane = (AnchorPane) loader.load();
            grafosController = loader.<GrafosController>getController();

            listadoController.initData(registrarController, reportesController,this);
            registrarController.initData(listadoController);

        } catch (IOException e) {
        }
    }
    
    public void setPane(AnchorPane p){
        paneData.getChildren().setAll(p);
    }
    
    public void selectListIndex(int i){
        listMenu.getSelectionModel().select(i);
    }
}
