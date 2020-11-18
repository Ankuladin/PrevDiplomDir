import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
public class GraphChoiseController {
    @FXML
    TextField TfforRand1;
    @FXML
    TextField TfforRand2;
    @FXML
    TextField TfforRand3;
    Main mainApp;
    Stage stage;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    //прогноз затрат на следующий день
    @FXML
    private void clickTomorrow(ActionEvent event)  {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphLayout.fxml"));
            AnchorPane GL = (AnchorPane) loader.load();
            Graph GC = loader.getController();
            GC.setMainApp(mainApp);
            GC.setOperNumb(1);
            ArrayList<DataOBJ> mass;
            mass= mainApp.Prediction();
            GC.setData(mass);
            GC.initializeGraph();
            mainApp.rootLayout.setCenter(GL);
            closeStage();
        } catch (Exception e) {
            showError(e);
            e.printStackTrace();
        }
    }
    @FXML
    private void clickRandomDay(ActionEvent event) {
        try {
            String year = TfforRand1.getText();
            String month = TfforRand2.getText();
            String day = TfforRand3.getText();
            LocalDate ldNeed = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            LocalDate ldCurr = Main.workDay.get(Main.workDay.size() - 1).getCurrDate();
            ldCurr = ldCurr.plusYears(1);
            if (ldCurr.isAfter(ldNeed)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphLayout.fxml"));
                AnchorPane GL = (AnchorPane) loader.load();
                Graph GC = loader.getController();
                GC.setMainApp(mainApp);
                GC.setOperNumb(2);
                double[] mass = new double[12];
                mass = mainApp.meddiumSlipeMethodByRandomDay(year, month, day);
                GC.setMass(mass);
                GC.initializeGraph();
                mainApp.rootLayout.setCenter(GL);
                closeStage();
            }
            else {
                getIncorrectErrorDate();
            }
            } catch(Exception e){
                showError(e);
                e.printStackTrace();
            }
    }
    @FXML
    private void clickCurrentDay(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphLayout.fxml"));
            AnchorPane GL = (AnchorPane) loader.load();
            Graph GC = loader.getController();
            GC.setMainApp(mainApp);
            GC.setOperNumb(3);
            double [] mass = new double[12];
            mass= mainApp.GraphByCurrentDay();
            GC.setMass(mass);
            GC.initializeGraph();
            mainApp.rootLayout.setCenter(GL);
            closeStage();
        } catch (Exception e) {
            showError(e);
            e.printStackTrace();

        }
    }

    public void closeStage(){
        stage = (Stage) TfforRand3.getScene().getWindow();
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

        // Set content for Dialog Pane
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

    public void getIncorrectErrorDate() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect date");
        VBox dialogPaneContent = new VBox();
        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

}
