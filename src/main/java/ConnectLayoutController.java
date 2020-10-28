import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.event.ActionEvent;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ConnectLayoutController {
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        //  test1layout = (AnchorPane) loader.load();
        //  mainApp.rootLayout.setCenter();
    }
    Stage stage;
    @FXML
    Button buttonConnect;
    @FXML
    TextField TF1;
    @FXML
    TextField TF2;
    @FXML
    TextField TF3;

    @FXML
    public void clickConnect(ActionEvent event){
        /*
        try{
            String currLogin = TF2.getText();
            String currPass = TF3.getText();
            if (currLogin.equals(pass.mainLogin) && currPass.equals(pass.mainPass)){
                mainApp.setUpNetworking(TF1.getText());
                mainApp.go();
                mainApp.oos.writeObject(Main.workDay);
                mainApp.oos.flush();


                closeStage();
            }else
            if (currLogin.equals(pass.field1Login) && currPass.equals(pass.field1Pass)){
                mainApp.setUpNetworking(TF1.getText());
                mainApp.go();

                closeStage();
            }else
            if (currLogin.equals(pass.field2Login) && currPass.equals(pass.field2Pass)){
                mainApp.setUpNetworking(TF1.getText());
                mainApp.go();

                closeStage();
            }else
            if (currLogin.equals(pass.field3Login) && currPass.equals(pass.field3Pass)){
                mainApp.setUpNetworking(TF1.getText());
                mainApp.go();

                closeStage();
            }else {
                getEmptyError();
            }

        }
        catch (Exception ex){
            showError(ex);
        }
        */
    }
    public void closeStage(){
        stage = (Stage) buttonConnect.getScene().getWindow();
        stage.close();
    }


    //Alert
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
    public void getEmptyError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect data");
        VBox dialogPaneContent = new VBox();
        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

}
