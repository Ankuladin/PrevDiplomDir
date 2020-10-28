import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Optional;
public class ChangeLayoutController {
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private Button buttonApply;
    @FXML
    private TextField FirstText1;
    @FXML
    private TextField SecondText1;
    @FXML
    private TextField ThirdText1;
    @FXML
    private TextField FourthText1;
    @FXML
    private TextField FirstText2;
    @FXML
    private TextField SecondText2;
    @FXML
    private TextField ThirdText2;
    @FXML
    private TextField FourthText2;
    @FXML
    private TextField FirstText3;
    @FXML
    private TextField SecondText3;
    @FXML
    private TextField ThirdText3;
    @FXML
    private TextField FourthText3;
    @FXML
    private TextField FirstEnText;
    @FXML
    private TextField SecondEnText;
    @FXML
    private TextField ThirdEnText;
    @FXML
    private TextField FourthEnText;
    @FXML
    private TextField Year;
    @FXML
    private TextField Month;
    @FXML
    private TextField BatteryTextFirst;
    @FXML
    private TextField BatteryTextSecond;
    @FXML
    private TextField BatteryTextThird;
    @FXML
    private TextField BatteryTextFourth;
    @FXML
    private TextField Day;
    private double firstp1 = 0;
    private double secondp1 = 0;
    private double thirdp1 = 0;
    private double fourthp1 = 0;
    private double firstp2 = 0;
    private double secondp2 = 0;
    private double thirdp2 = 0;
    private double fourthp2 = 0;
    private double firstp3 = 0;
    private double secondp3 = 0;
    private double thirdp3 = 0;
    private double fourthp3 = 0;
    private double firstes = 0;
    private double secondes = 0;
    private double thirdes = 0;
    private double fourthes = 0;
    private double currBatteryFirst=0;
    private double currBatterySecond=0;
    private double currBatteryThird=0;
    private double currBatteryFourth=0;
    public void checkEmptyField() throws Exception{
        if (FirstText1.getText().toString().trim().isEmpty() != true){
            firstp1 = Double.parseDouble(FirstText1.getText());
        }
        if (SecondText1.getText().toString().trim().isEmpty() != true){
            secondp1 = Double.parseDouble(SecondText1.getText());
        }
        if (ThirdText1.getText().toString().trim().isEmpty() != true){
            thirdp1 = Double.parseDouble(ThirdText1.getText());
        }
        if (FourthText1.getText().toString().trim().isEmpty() != true){
            fourthp1 = Double.parseDouble(FourthText1.getText());
        }
        if (FirstText2.getText().toString().trim().isEmpty() != true){
            firstp2 = Double.parseDouble(FirstText2.getText());
        }
        if (SecondText2.getText().toString().trim().isEmpty() != true){
            secondp2 = Double.parseDouble(SecondText2.getText());
        }
        if (ThirdText2.getText().toString().trim().isEmpty() != true){
            thirdp2 = Double.parseDouble(ThirdText2.getText());
        }
        if (FourthText2.getText().toString().trim().isEmpty() != true){
            fourthp2 = Double.parseDouble(FourthText2.getText());
        }
        if (FirstText3.getText().toString().trim().isEmpty() != true){
            firstp3 = Double.parseDouble(FirstText3.getText());
        }
        if (SecondText3.getText().toString().trim().isEmpty() != true){
            secondp3 = Double.parseDouble(SecondText3.getText());
        }
        if (ThirdText3.getText().toString().trim().isEmpty() != true){
            thirdp3 = Double.parseDouble(ThirdText3.getText());
        }
        if (FourthText3.getText().toString().trim().isEmpty() != true){
            fourthp3 = Double.parseDouble(FourthText3.getText());
        }
        if (FirstEnText.getText().toString().trim().isEmpty() != true){
            firstes = Double.parseDouble(FirstEnText.getText());
        }
        if (SecondEnText.getText().toString().trim().isEmpty() != true){
            secondes = Double.parseDouble(SecondEnText.getText());
        }
        if (ThirdEnText.getText().toString().trim().isEmpty() != true){
            thirdes = Double.parseDouble(ThirdEnText.getText());
        }
        if (FourthEnText.getText().toString().trim().isEmpty() != true){
            fourthes = Double.parseDouble(FourthEnText.getText());
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
    Stage stage;
    @FXML
    public void clickApply(ActionEvent event){
        try {
            WorkDay day = new WorkDay();
            LocalDate ld = LocalDate.of(Integer.parseInt(Year.getText()),Integer.parseInt(Month.getText()),Integer.parseInt(Day.getText()));
            checkEmptyField();
            day.setFirstPart1(firstp1);
            day.setSecondPart1(secondp1);
            day.setThirdPart1(thirdp1);
            day.setFourthPart1(fourthp1);
            day.setFirstPart2(firstp2);
            day.setSecondPart2(secondp2);
            day.setThirdPart2(thirdp2);
            day.setFourthPart2(fourthp2);
            day.setFirstPart3(firstp3);
            day.setSecondPart3(secondp3);
            day.setThirdPart3(thirdp3);
            day.setFourthPart3(fourthp3);
            day.setFirstEnSourse(firstes);
            day.setSecondEnSourse(secondes);
            day.setThirdEnSourse(thirdes);
            day.setFourthEnSourse(fourthes);
            day.setCurrBatteryFirstPart(currBatteryFirst);
            day.setCurrBatterySecondPart(currBatterySecond);
            day.setCurrBatteryThirdPart(currBatteryThird);
            day.setCurrBatteryFourthPart(currBatteryFourth);
            for(int r = 0; r < mainApp.workDay.size() ; r++) {
                if (mainApp.workDay.get(r).getCurrDate().toString().equals(ld.toString())) {
                    mainApp.changeWorkDay(r, day);
                    saveChangesToDB(r);
                    System.out.println(r);
                }
            }
            closeStage();


        }catch (Exception ex){
            showError(ex);
        }
    }
    public void closeStage(){
        stage = (Stage) Day.getScene().getWindow();
        stage.close();
    }
    private void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(e.getMessage());
        VBox dialogPaneContent = new VBox();
        javafx.scene.control.Label label = new javafx.scene.control.Label("Stack Trace:");
        String stackTrace = this.getStackTrace(e);
        javafx.scene.control.TextArea textArea = new TextArea();
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
    private void saveChangesToDB(int r) {
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
                GDBDC.setOperNumb(3);
                GDBDC.setIndexNumb(r);
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
