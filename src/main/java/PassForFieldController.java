import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PassForFieldController {
    Stage stage;
    @FXML
    Button button;
    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void clickOk(ActionEvent event){
        try {
            Passwords pass = new Passwords();
            String currLogin = tf1.getText().toString();
            String currPass = tf2.getText().toString();
            //1 Field
            if (currLogin.equals(pass.field1Login) && currPass.equals(pass.field1Pass)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDataFieldLayout.fxml"));
                AnchorPane PSF = (AnchorPane) loader.load();
                AddDataFieldLayoutController addFieldController = loader.getController();
                addFieldController.setMainApp(mainApp);
                addFieldController.setNumbField(1);
                Scene PSFScene = new Scene(PSF, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Add data");
                newWindow.setScene(PSFScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.show();
                closeStage();
            }
            //2 Field
            else if (currLogin.equals(pass.field2Login) && currPass.equals(pass.field2Pass)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDataFieldLayout.fxml"));
                AnchorPane PSF = (AnchorPane) loader.load();
                AddDataFieldLayoutController addFieldController = loader.getController();
                addFieldController.setMainApp(mainApp);
                addFieldController.setNumbField(2);
                Scene PSFScene = new Scene(PSF, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Add data");
                newWindow.setScene(PSFScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.show();
                closeStage();
            }
            //3 Field
            else if (currLogin.equals(pass.field3Login) && currPass.equals(pass.field3Pass)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDataFieldLayout.fxml"));
                AnchorPane PSF = (AnchorPane) loader.load();
                AddDataFieldLayoutController addFieldController = loader.getController();
                addFieldController.setMainApp(mainApp);
                addFieldController.setNumbField(3);
                Scene PSFScene = new Scene(PSF, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Add data");
                newWindow.setScene(PSFScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.show();
                closeStage();
            }

            else {
                getIncorrectError();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void getIncorrectError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect login or password");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    public void closeStage(){
        stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
