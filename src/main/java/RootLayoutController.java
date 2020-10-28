import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class RootLayoutController {
    @FXML
    public Label L1;
    @FXML
    public TextArea TA1;
    @FXML
    private void initialize() {
    }
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void clickTable(ActionEvent event) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TableLayout.fxml"));
            AnchorPane test1 = (AnchorPane) loader.load();
            mainApp.rootLayout.setCenter(test1);
            Table tableF = loader.getController();
            tableF.setMainApp(this.mainApp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void setStageSizeMini() {
        mainApp.primaryStage.setWidth(1024);
        mainApp.primaryStage.setHeight(768);
    }
    @FXML
    public void setStageSizeMeddium() {
        mainApp.primaryStage.setWidth(1600);
        mainApp.primaryStage.setHeight(900);
    }
    @FXML
    public void setStageSizeMax() {
        mainApp.primaryStage.setWidth(1920);
        mainApp.primaryStage.setHeight(1080);
    }
    @FXML
    private void clickAddDay(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
            AnchorPane PSB = (AnchorPane) loader.load();
            PassForBossController passController = loader.getController();
            passController.setMainApp(mainApp);
            passController.setOperNumb(1);
            Scene PSFScene = new Scene(PSB, 300, 100);
            Stage newWindow = new Stage();
            newWindow.setHeight(200);
            newWindow.setWidth(350);
            newWindow.setResizable(false); // Запрет изменять размер окна
            newWindow.setTitle("Pass");
            newWindow.setScene(PSFScene);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.show();
        } catch (Exception ex) {
        }
    }
    @FXML
    private void clickMenuSaveData(ActionEvent event) {
        mainApp.saveMenuData();
    }
    @FXML
    private void clickMenuOpenData(ActionEvent event) {
        mainApp.openMenuData();
    }
    @FXML
    private void clickMenuSaveDB(ActionEvent event) {
        if (mainApp.workDay.size() != 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GetDBData.fxml"));
                AnchorPane GDBD = (AnchorPane) loader.load();
                GetDBDataController GDBDC = loader.getController();
                GDBDC.setMainApp(mainApp);
                GDBDC.setOperNumb(1);
                Scene GDBDScene = new Scene(GDBD, 530, 300);
                Stage newWindow = new Stage();
                newWindow.setTitle("Save DB");
                newWindow.setScene(GDBDScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Root");
            }
        } else {
            getEmptyError();
        }
    }
    @FXML
    private void clickMenuOpenDB(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GetDBData.fxml"));
            AnchorPane GDBD = (AnchorPane) loader.load();
            GetDBDataController GDBDC = loader.getController();
            GDBDC.setMainApp(mainApp);
            GDBDC.setOperNumb(0);
            Scene GDBDScene = new Scene(GDBD, 530, 300);
            Stage newWindow = new Stage();
            newWindow.setTitle("Load from DB");
            newWindow.setScene(GDBDScene);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.setResizable(false);
            newWindow.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lol");
        }

    }
    @FXML
    private void clickQuery(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
            AnchorPane PSB = (AnchorPane) loader.load();
            PassForBossController passController = loader.getController();
            passController.setMainApp(mainApp);
            passController.setOperNumb(4);
            Scene PSFScene = new Scene(PSB, 230, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Pass");
            newWindow.setHeight(200);
            newWindow.setWidth(350);
            newWindow.setScene(PSFScene);
            newWindow.setResizable(false);
            newWindow.setX(mainApp.primaryStage.getX() + 200);
            newWindow.setY(mainApp.primaryStage.getY() + 100);
            newWindow.show();
        } catch (Exception ex) {
        }
    }
    @FXML
    private void clickChange(ActionEvent event) {
        if (mainApp.workDay.size() != 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
                AnchorPane PSB = (AnchorPane) loader.load();
                PassForBossController passController = loader.getController();
                passController.setMainApp(mainApp);
                passController.setOperNumb(3);
                Scene PSFScene = new Scene(PSB, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setHeight(200);
                newWindow.setWidth(350);
                newWindow.setTitle("Pass");
                newWindow.setScene(PSFScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            getEmptyError();
        }
    }
    @FXML
    private void clickCheckLines(ActionEvent event) {
        if (mainApp.workDay.size() != 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
                AnchorPane PSB = (AnchorPane) loader.load();
                PassForBossController passController = loader.getController();
                passController.setMainApp(mainApp);
                passController.setOperNumb(5);
                Scene PSFScene = new Scene(PSB, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setHeight(200);
                newWindow.setWidth(350);
                newWindow.setTitle("Pass");
                newWindow.setScene(PSFScene);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
            } catch (Exception e) {
            }
        } else {
            getEmptyError();
        }
    }
    @FXML
    private void clickPrediction(ActionEvent event) {
        if (mainApp.workDay.size() != 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
                AnchorPane PSB = (AnchorPane) loader.load();
                PassForBossController passController = loader.getController();
                passController.setMainApp(mainApp);
                passController.setOperNumb(2);
                Scene PSFScene = new Scene(PSB, 230, 100);
                Stage newWindow = new Stage();
                newWindow.setTitle("Pass");
                newWindow.setScene(PSFScene);
                newWindow.setHeight(200);
                newWindow.setWidth(350);
                newWindow.setX(mainApp.primaryStage.getX() + 200);
                newWindow.setY(mainApp.primaryStage.getY() + 100);
                newWindow.setResizable(false);
                newWindow.show();
            } catch (Exception ex) {
            }
        } else {
            getEmptyError();
        }
    }
    @FXML
    private void startAuto(ActionEvent event){
        if (Main.workDay.size()>0) {
            ThreadForAuto l1 = new ThreadForAuto(L1);
            Thread t1 = new Thread(l1);
            t1.start();
        }
        else {
            getEmptyError();
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
    public void showMessage(String a){
        TA1.appendText(a);
        TA1.appendText("\r\n");
    }
}
