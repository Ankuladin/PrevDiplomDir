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

public class Table {
    private ObservableList<WorkDay> workData = FXCollections.observableArrayList();
    private Main mainApp;
    @FXML
    private TableView<WorkDay> tableData;
    @FXML
    private TableColumn<WorkDay, LocalDate> DataColumn ;
    @FXML
    private TableColumn<WorkDay, Double> FirstColumn1 ;
    @FXML
    private TableColumn<WorkDay, Double> SecondColumn1 ;
    @FXML
    private TableColumn<WorkDay, Double> ThirdColumn1 ;
    @FXML
    private TableColumn<WorkDay, Double> FourthColumn1 ;
    @FXML
    private TableColumn<WorkDay, Double> FirstColumn2 ;
    @FXML
    private TableColumn<WorkDay, Double> SecondColumn2 ;
    @FXML
    private TableColumn<WorkDay, Double> ThirdColumn2 ;
    @FXML
    private TableColumn<WorkDay, Double> FourthColumn2 ;
    @FXML
    private TableColumn<WorkDay, Double> FirstColumn3 ;
    @FXML
    private TableColumn<WorkDay, Double> SecondColumn3 ;
    @FXML
    private TableColumn<WorkDay, Double> ThirdColumn3 ;
    @FXML
    private TableColumn<WorkDay, Double> FourthColumn3 ;
    @FXML
    private TableColumn<WorkDay, Double> FirstEnSourseColumn ;
    @FXML
    private TableColumn<WorkDay, Double> SecondEnSourseColumn ;
    @FXML
    private TableColumn<WorkDay, Double> ThirdEnSourseColumn ;
    @FXML
    private TableColumn<WorkDay, Double> FourthEnSourseColumn ;
    @FXML
    private TableColumn<WorkDay, Double> BatteryColumnFirst ;
    @FXML
    private TableColumn<WorkDay, Double> BatteryColumnSecond ;
    @FXML
    private TableColumn<WorkDay, Double> BatteryColumnThird ;
    @FXML
    private TableColumn<WorkDay, Double> BatteryColumnFourth ;
    @FXML
    private void initialize(){
    }
    private void initial(){
        workData.setAll(Main.workDay);
        DataColumn.setCellValueFactory(new PropertyValueFactory<WorkDay, LocalDate>("currDate"));
        FirstColumn1.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("firstPart1"));
        SecondColumn1.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("secondPart1"));
        ThirdColumn1.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("thirdPart1"));
        FourthColumn1.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("fourthPart1"));
        FirstColumn2.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("firstPart2"));
        SecondColumn2.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("secondPart2"));
        ThirdColumn2.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("thirdPart2"));
        FourthColumn2.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("fourthPart2"));
        FirstColumn3.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("firstPart3"));
        SecondColumn3.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("secondPart3"));
        ThirdColumn3.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("thirdPart3"));
        FourthColumn3.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("fourthPart3"));
        FirstEnSourseColumn.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("firstEnSourse"));
        SecondEnSourseColumn.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("secondEnSourse"));
        ThirdEnSourseColumn.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("thirdEnSourse"));
        FourthEnSourseColumn.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("fourthEnSourse"));
        BatteryColumnFirst.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("currBatteryFirstPart"));
        BatteryColumnSecond.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("currBatterySecondPart"));
        BatteryColumnThird.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("currBatteryThirdPart"));
        BatteryColumnFourth.setCellValueFactory(new PropertyValueFactory<WorkDay, Double>("currBatteryFourthPart"));
        tableData.setItems(workData);
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        initial();
    }
}
