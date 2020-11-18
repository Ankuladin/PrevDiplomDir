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

public class PassForBossController {
    public int operNumb = 0;   //1 for addDay

    public int getOperNumb() {
        return operNumb;
    }

    public void setOperNumb(int operNumb) {
        this.operNumb = operNumb;
    }

    Stage stage;
    @FXML
    Button buttonApply;
    @FXML
    TextField tf1;
    @FXML
    PasswordField tf2;
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        //  test1layout = (AnchorPane) loader.load();
        //  mainApp.rootLayout.setCenter();
    }

    @FXML
    private void clickApply(ActionEvent event) {
        try {
            stage = (Stage) buttonApply.getScene().getWindow();
            System.out.println(getOperNumb());
            Passwords pass = new Passwords();
            String currLogin = tf1.getText().toString();
            String currPass = tf2.getText().toString();


            if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass) && getOperNumb() == 1) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDayLayout.fxml"));
                        AnchorPane ADC = (AnchorPane) loader.load();
                        AddDayLayoutController addDayController = loader.getController();
                        addDayController.setMainApp(mainApp);
                        Scene GDBDScene = new Scene(ADC, 350, 200);
                        Stage newWindow = new Stage();
                        newWindow.setHeight(300);
                        newWindow.setWidth(450);
                        newWindow.setTitle("AddDay");
                        newWindow.setScene(GDBDScene);
                        newWindow.setX(mainApp.primaryStage.getX() + 200);
                        newWindow.setY(mainApp.primaryStage.getY() + 100);
                        newWindow.setResizable(false);
                        newWindow.show();
                        closeStage();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            } else if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass) && getOperNumb() == 2) {
                if (mainApp.DataMass.size() != 0) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphChoiseLayout.fxml"));
                        AnchorPane GCLC = (AnchorPane) loader.load();
                        GraphChoiseController GraphController = loader.getController();
                        GraphController.setMainApp(mainApp);
                        Scene GDBDScene = new Scene(GCLC, 350, 200);
                        Stage newWindow = new Stage();
                        newWindow.setHeight(300);
                        newWindow.setWidth(450);
                        newWindow.setTitle("Choise Graph");
                        newWindow.setScene(GDBDScene);
                        newWindow.setX(mainApp.primaryStage.getX() + 200);
                        newWindow.setY(mainApp.primaryStage.getY() + 100);
                        newWindow.setResizable(false);
                        newWindow.show();
                        closeStage();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    getEmptyError();
                }
            } else if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass) && getOperNumb() == 3) {
                if (mainApp.workDay.size() != 0) {
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeLayout.fxml"));
                        AnchorPane CLC = (AnchorPane) loader.load();
                        ChangeLayoutController ChangeController = loader.getController();
                        ChangeController.setMainApp(mainApp);
                        Scene GDBDScene = new Scene(CLC, 350, 200);
                        Stage newWindow = new Stage();
                        newWindow.setHeight(400);
                        newWindow.setWidth(700);
                        newWindow.setTitle("Change Day");
                        newWindow.setScene(GDBDScene);
                        newWindow.setX(mainApp.primaryStage.getX() + 200);
                        newWindow.setY(mainApp.primaryStage.getY() + 100);
                        newWindow.setResizable(false);
                        newWindow.show();
                        closeStage();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    getEmptyError();
                }
            } else if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass) && getOperNumb() == 4) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageForFieldLayout.fxml"));
                AnchorPane CLC = (AnchorPane) loader.load();
                MessageForFieldController MessController = loader.getController();
                Scene GDBDScene = new Scene(CLC, 500, 200);
                Stage newWindow = new Stage();
                newWindow.setHeight(500);
                newWindow.setWidth(850);
                newWindow.setTitle("Change Day");
                newWindow.setScene(GDBDScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
                closeStage();
            } else if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass) && getOperNumb() == 5) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentLinesLayout.fxml"));
                AnchorPane GL = (AnchorPane) loader.load();
                CurrentLinesController CLC = loader.getController();
                CLC.setMainApp(mainApp);
                CLC.setWorkDay(Main.workDay.get(Main.workDay.size()-1));
                CLC.initializeLine();
                mainApp.rootLayout.setCenter(GL);
                closeStage();
            }
            else {
                getIncorrectErrorData();
            }
        } catch (Exception ex) {
            showError(ex);
        }
    }
    public void getEmptyError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Your list is empty");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    public void closeStage() {
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
    public void getIncorrectErrorData() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect data");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
}


