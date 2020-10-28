import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class AddDayLayoutController {
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private Button buttonApply;
    @FXML
    private TextField FirstText ;
    @FXML
    private TextField SecondText;
    @FXML
    private TextField ThirdText;
    @FXML
    private TextField FourthText;
    @FXML
    private TextField BatteryTextFirst;
    @FXML
    private TextField BatteryTextSecond;
    @FXML
    private TextField BatteryTextThird;
    @FXML
    private TextField BatteryTextFourth;
    private double firstes = 0;
    private double secondes = 0;
    private double thirdes = 0;
    private double fourthes = 0;
    private double currBatteryFirst=0;
    private double currBatterySecond=0;
    private double currBatteryThird=0;
    private double currBatteryFourth=0;
    Stage stage;
    @FXML
    public void clickApply(ActionEvent event){
        try {
            checkEmptyField();
            WorkDay day = new WorkDay();
            day.setFirstEnSourse(firstes);
            day.setSecondEnSourse(secondes);
            day.setThirdEnSourse(thirdes);
            day.setFourthEnSourse(fourthes);
            day.setCurrBatteryFirstPart(currBatteryFirst);
            day.setCurrBatterySecondPart(currBatterySecond);
            day.setCurrBatteryThirdPart(currBatteryThird);
            day.setCurrBatteryFourthPart(currBatteryFourth);
            day.setField1(true);
            day.setField2(true);
            day.setField3(true);
            day.setMainEnergy(true);
            day.setGreenEnergy(true);
            mainApp.addWorkDay(day);
            saveChangestoDB();
            closeStage();
        }catch (Exception ex){
            showError(ex);
        }
    }
    public void checkEmptyField(){
        if (FirstText.getText().toString().trim().isEmpty() != true){
            firstes = Double.parseDouble(FirstText.getText());
        }
        if (SecondText.getText().toString().trim().isEmpty() != true){
            secondes = Double.parseDouble(SecondText.getText());
        }
        if (ThirdText.getText().toString().trim().isEmpty() != true){
            thirdes = Double.parseDouble(ThirdText.getText());
        }
        if (FourthText.getText().toString().trim().isEmpty() != true){
            fourthes = Double.parseDouble(FourthText.getText());
        }
        if (BatteryTextFirst.getText().toString().trim().isEmpty() != true){
            currBatteryFirst = Double.parseDouble(BatteryTextFirst.getText());
        }
        if (BatteryTextSecond.getText().toString().trim().isEmpty() != true){
            currBatterySecond = Double.parseDouble(BatteryTextSecond.getText());
        }
        if (BatteryTextThird.getText().toString().trim().isEmpty() != true){
            currBatteryThird = Double.parseDouble(BatteryTextThird.getText());
        }
        if (BatteryTextFourth.getText().toString().trim().isEmpty() != true){
            currBatteryFourth = Double.parseDouble(BatteryTextFourth.getText());
        }
    }
    public void closeStage(){
        stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
    }
    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(e.getMessage());
        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        String stackTrace = this.getStackTrace(e);
        TextArea textArea = new TextArea();
        textArea.setText(stackTrace);
        dialogPaneContent.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
    private void saveChangestoDB() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Синхронізація");
            alert.setHeaderText("Синхронізувати з БД?");
            alert.setContentText("");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GetDBData.fxml"));
                AnchorPane GDBD = (AnchorPane) loader.load();
                GetDBDataController GDBDC = loader.getController();
                GDBDC.setMainApp(mainApp);
                GDBDC.setOperNumb(2);
                Scene GDBDScene = new Scene(GDBD, 530, 300);
                Stage newWindow = new Stage();
                newWindow.setTitle("Save DB");
                newWindow.setScene(GDBDScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
            } else if (option.get() == ButtonType.CANCEL) {
            } else {
            }
        }catch (Exception ex){
            showError(ex);
        }
    }

}
