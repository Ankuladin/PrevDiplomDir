import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Table {
    private ObservableList<DataOBJ> workData = FXCollections.observableArrayList();
    public Main mainApp;
    @FXML
    private TableView<DataOBJ> tableData;
    @FXML
    private TableColumn<DataOBJ, LocalDateTime> DataColumn ;
    @FXML
    private TableColumn<DataOBJ, Double> Field1 ;
    @FXML
    private TableColumn<DataOBJ, Double> Field2 ;
    @FXML
    private TableColumn<DataOBJ, Double> Field3 ;
    @FXML
    private TableColumn<DataOBJ, Double> Field4 ;
    @FXML
    private TableColumn<DataOBJ, Double> Field5 ;
    @FXML
    private TableColumn<DataOBJ, Double> Field6 ;
    @FXML
    private TableColumn<DataOBJ, Double> Power ;
    @FXML
    private TableColumn<DataOBJ, Double> Battery ;

    @FXML
    private void initialize(){
    }
    private void initial(){
        workData.setAll(Main.DataMass);
        DataColumn.setCellValueFactory(new PropertyValueFactory<DataOBJ, LocalDateTime>("currDate"));
        Field1.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field1"));
        Field2.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field2"));
        Field3.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field3"));
        Field4.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field4"));
        Field5.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field5"));
        Field6.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("field6"));
        Power.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("power"));
        Battery.setCellValueFactory(new PropertyValueFactory<DataOBJ, Double>("battary"));
        tableData.setItems(workData);
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        initial();
    }
}
