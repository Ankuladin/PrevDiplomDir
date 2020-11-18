import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        if (mainApp.DataMass.size() != 0) {
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
            System.out.println("lol");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GetDBData.fxml"));
            AnchorPane GDBD = (AnchorPane) loader.load();
            GetDBDataController GDBDC = loader.getController();
            GDBDC.setMainApp(mainApp);
            GDBDC.setOperNumb(0);
            System.out.println("lol1");
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
            Scene PSFScene = new Scene(PSB, 450, 175);
            Stage newWindow = new Stage();
            newWindow.setTitle("Pass");
            newWindow.setHeight(200);
            newWindow.setWidth(450);
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
                Scene PSFScene = new Scene(PSB, 450, 175);
                Stage newWindow = new Stage();
                newWindow.setHeight(200);
                newWindow.setWidth(450);
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
        if (mainApp.DataMass.size() != 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PassForBoss.fxml"));
                AnchorPane PSB = (AnchorPane) loader.load();
                PassForBossController passController = loader.getController();
                passController.setMainApp(mainApp);
                passController.setOperNumb(2);
                Scene PSFScene = new Scene(PSB, 450, 175);
                Stage newWindow = new Stage();
                newWindow.setTitle("Pass");
                newWindow.setScene(PSFScene);
                newWindow.setHeight(200);
                newWindow.setWidth(450);
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
    @FXML
    private void startAutoGetDataFromClient(ActionEvent event){

            ThreadForAutoGetData l1 = new ThreadForAutoGetData();
            Thread t1 = new Thread(l1);
            t1.start();


    }
    @FXML
    private void startGenerateData(ActionEvent event){
        /*GregorianCalendar startDate = new GregorianCalendar();
        startDate.add(Calendar.MONTH, -3);
        int starttime = startDate.get(Calendar.HOUR_OF_DAY);
        int starttime2 = startDate.get(Calendar.MINUTE);
        int starttime3 = startDate.get(Calendar.SECOND);

        GregorianCalendar gcalendar = new GregorianCalendar();
       // gcalendar.add(Calendar.HOUR_OF_DAY, 15);
        while(startDate.before(gcalendar)){
            System.out.println(startDate.get(Calendar.DAY_OF_YEAR)+":"+startDate.get(Calendar.HOUR_OF_DAY)+ ":" +startDate.get(Calendar.MINUTE)+ ":"+ startDate.get(Calendar.SECOND));
            DataOBJ dObj = new DataOBJ();
            dObj.setfield1((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setfield2((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setfield3((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setfield4((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setfield5((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setfield6((float) (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setbattary(175);
            dObj.setpower(175);
            dObj.setCurrDateCalendar(startDate);
            Main.DataMass.add(dObj);
            startDate.add(Calendar.MINUTE, 1);
        }
        System.out.println(Main.DataMass.size());
        System.out.println(Main.DataMass.get(10).getfield2());
        System.out.println(Main.DataMass.get(10).getCurrDateCalendar());
        System.out.println(Main.DataMass.get(13).getCurrDateCalendar());
        */


        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime lct = now.minusMonths(3);
        System.out.println(lct);
        LocalDate ld = LocalDate.of(lct.getYear(), lct.getMonth(), lct.getDayOfMonth());
        LocalTime lt = LocalTime.of(8,00,00);
        LocalDateTime start= LocalDateTime.of(ld, lt);
        System.out.println(start);
        if (Main.DataMass.size()==0){
            System.out.println("datamass == 0");
            DataOBJ dObj = new DataOBJ();
            dObj.setField1( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setField2( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setField3( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setField4( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setField5( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setField6( (2.3 + Math.random() * (3.1 - 2.3)));
            dObj.setBattary(1120);
            dObj.setPower(175);
            dObj.setCurrDate(start);
            Main.DataMass.add(dObj);
            start =start.plusMinutes(1);

        }


        // gcalendar.add(Calendar.HOUR_OF_DAY, 15);
        while(start.isBefore(now)){
            int hour = Main.DataMass.get(Main.DataMass.size() - 1).getCurrDate().getHour();
            if (hour >= 8 && hour < 20 ) {
                DataOBJ dObj = new DataOBJ();
                dObj.setField1( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField2( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField3( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField4( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField5( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField6( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setBattary(Main.DataMass.get(Main.DataMass.size() - 1).getBattary() -1);
                dObj.setPower(175);
                dObj.setCurrDate(start);
                Main.DataMass.add(dObj);
                start = start.plusMinutes(1);
                System.out.println(start);
                System.out.println(dObj.getBattary());
            }
            else {
                DataOBJ dObj = new DataOBJ();
                dObj.setField1( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField2( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField3( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField4( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField5( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setField6( (2.3 + Math.random() * (3.1 - 2.3)));
                dObj.setBattary(Main.DataMass.get(Main.DataMass.size() - 1).getBattary() + 1);
                dObj.setPower(175);
                dObj.setCurrDate(start);
                Main.DataMass.add(dObj);
                start = start.plusMinutes(1);
                System.out.println(start);
                System.out.println(dObj.getBattary());
            }
        }
        System.out.println(Main.DataMass.size());
        System.out.println(Main.DataMass.get(10).getField2());
        System.out.println(Main.DataMass.get(10).getBattary());
        System.out.println(Main.DataMass.get(10).getCurrDate());
        System.out.println(Main.DataMass.get(13).getCurrDate());





        //for (int i = 1; i<1441 ; i++) {

      //  }

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
