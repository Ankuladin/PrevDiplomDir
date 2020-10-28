import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//Класс не используется на серверере
public class AddDataFieldLayoutController {
    int numbField = 0;
    Stage stage;
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
        //  test1layout = (AnchorPane) loader.load();
        //  mainApp.rootLayout.setCenter();
    }
    @FXML
    private TextField Tf11first;
    private double sTf11first=0;
    @FXML
    private TextField Tf12first;
    private double sTf12first=0;
    @FXML
    private TextField Tf13first;
    private double sTf13first=0;
    @FXML
    private TextField Tf14first;
    private double sTf14first=0;
    @FXML
    private TextField Tf21first;
    private double sTf21first=0;
    @FXML
    private TextField Tf22first;
    private double sTf22first=0;
    @FXML
    private TextField Tf23first;
    private double sTf23first=0;
    @FXML
    private TextField Tf24first;
    private double sTf24first=0;
    @FXML
    private TextField Tf31first;
    private double sTf31first=0;
    @FXML
    private TextField Tf32first;
    private double sTf32first=0;
    @FXML
    private TextField Tf33first;
    private double sTf33first=0;
    @FXML
    private TextField Tf34first;
    private double sTf34first=0;
    @FXML
    private TextField Tf41first;
    private double sTf41first=0;
    @FXML
    private TextField Tf42first;
    private double sTf42first=0;
    @FXML
    private TextField Tf43first;
    private double sTf43first=0;
    @FXML
    private TextField Tf44first;
    private double sTf44first=0;
    @FXML
    private TextField Tf51first;
    private double sTf51first=0;
    @FXML
    private TextField Tf52first;
    private double sTf52first=0;
    @FXML
    private TextField Tf53first;
    private double sTf53first=0;
    @FXML
    private TextField Tf54first;
    private double sTf54first=0;
    @FXML
    private TextField Tf61first;
    private double sTf61first=0;
    @FXML
    private TextField Tf62first;
    private double sTf62first=0;
    @FXML
    private TextField Tf63first;
    private double sTf63first=0;
    @FXML
    private TextField Tf64first;
    private double sTf64first=0;
    @FXML
    private TextField Tf11second;
    private double sTf11second=0;
    @FXML
    private TextField Tf21second;
    private double sTf21second=0;
    @FXML
    private TextField Tf31second;
    private double sTf31second=0;
    @FXML
    private TextField Tf41second;
    private double sTf41second=0;
    @FXML
    private TextField Tf51second;
    private double sTf51second=0;
    @FXML
    private TextField Tf61second;
    private double sTf61second=0;

    @FXML
    private Button buttonApply;


    @FXML
    private void clickApply(ActionEvent event){
        try {
            /*

            // Отправляем запрос на сервер , получаем в ответ аррей лист
            // Размещенние по очереди с 1 по 3й
            if (numbField == 1) {
                mainApp.oos.writeObject("field1Connect");
                mainApp.writer.flush();
            }
            else if (numbField == 2) {
                mainApp.oos.writeObject("field2Connect");
                mainApp.writer.flush();
            }
            else if (numbField == 3) {
                mainApp.oos.writeObject("field3Connect");
                mainApp.writer.flush();
            }



            checkEmptyField();
            double sumfirst = 0;
            double sumsecond = 0;
            double sumthird = 0;
            double sumfourth = 0;
            sumfirst = sTf11first * sTf11second + sTf21first * sTf21second + sTf31first * sTf31second + sTf41first * sTf41second + sTf51first * sTf51second + sTf61first * sTf61second;
            sumsecond = sTf12first * sTf11second + sTf22first * sTf21second + sTf32first * sTf31second + sTf42first * sTf41second + sTf52first * sTf51second + sTf62first * sTf61second;
            sumthird = sTf13first * sTf11second + sTf23first * sTf21second + sTf33first * sTf31second + sTf43first * sTf41second + sTf53first * sTf51second + sTf63first * sTf61second;
            sumfourth = sTf14first * sTf11second + sTf24first * sTf21second + sTf34first * sTf31second + sTf44first * sTf41second + sTf54first * sTf51second + sTf64first * sTf61second;

            WorkDay currentDay = mainApp.workDay.get(mainApp.workDay.size() - 1);
            // Размещенние по очереди с 1 по 3й
            if (numbField == 1 && currentDay.getField1() == true) {


                currentDay.setFirstPart1(sumfirst);
                currentDay.setSecondPart1(sumsecond);
                currentDay.setThirdPart1(sumthird);
                currentDay.setFourthPart1(sumfourth);
                currentDay.setField1(false);


                // Отправляем Готовый аррей лист на сервер
                mainApp.oos.writeObject(currentDay);
                mainApp.writer.flush();


                closeStage();

            }else
            if (numbField == 2 && currentDay.getField2() == true && currentDay.getField1() == false) {

                currentDay.setFirstPart2(sumfirst);
                currentDay.setSecondPart2(sumsecond);
                currentDay.setThirdPart2(sumthird);
                currentDay.setFourthPart2(sumfourth);
                currentDay.setField2(false);
                mainApp.oos.writeObject(currentDay);
                mainApp.writer.flush();

                closeStage();
            }else
            if (numbField == 3 && currentDay.getField3() == true && currentDay.getField1() == false &&  currentDay.getField2() == false) {

                currentDay.setFirstPart3(sumfirst);
                currentDay.setSecondPart3(sumsecond);
                currentDay.setThirdPart3(sumthird);
                currentDay.setFourthPart3(sumfourth);
                currentDay.setField3(false);
                mainApp.oos.writeObject(currentDay);
                mainApp.writer.flush();

                closeStage();
            }
            else{
                getEmptyError();
            }

*/
        }catch (Exception ex){
            System.out.println("11232131313");  //изменить на норм обработку ошибки
        }
    }

    public void setNumbField(int numbField) {
        this.numbField = numbField;
    }
    public int getNumbField() {
        return numbField;
    }
    public void checkEmptyField(){
        if (Tf11first.getText().toString().trim().isEmpty() != true){
            sTf11first = Double.parseDouble(Tf11first.getText());
        }
        if (Tf12first.getText().toString().trim().isEmpty() != true){
            sTf12first = Double.parseDouble(Tf12first.getText());
        }
        if (Tf13first.getText().toString().trim().isEmpty() != true){
            sTf13first = Double.parseDouble(Tf13first.getText());
        }
        if (Tf14first.getText().toString().trim().isEmpty() != true){
            sTf14first = Double.parseDouble(Tf14first.getText());
        }
        if (Tf21first.getText().toString().trim().isEmpty() != true){
            sTf21first = Double.parseDouble(Tf21first.getText());
        }
        if (Tf22first.getText().toString().trim().isEmpty() != true){
            sTf22first = Double.parseDouble(Tf22first.getText());
        }
        if (Tf23first.getText().toString().trim().isEmpty() != true){
            sTf23first = Double.parseDouble(Tf23first.getText());
        }
        if (Tf24first.getText().toString().trim().isEmpty() != true){
            sTf24first = Double.parseDouble(Tf24first.getText());
        }
        if (Tf31first.getText().toString().trim().isEmpty() != true){
            sTf31first = Double.parseDouble(Tf31first.getText());
        }
        if (Tf32first.getText().toString().trim().isEmpty() != true){
            sTf32first = Double.parseDouble(Tf32first.getText());
        }
        if (Tf33first.getText().toString().trim().isEmpty() != true){
            sTf33first = Double.parseDouble(Tf33first.getText());
        }
        if (Tf34first.getText().toString().trim().isEmpty() != true){
            sTf34first = Double.parseDouble(Tf34first.getText());
        }
        if (Tf41first.getText().toString().trim().isEmpty() != true){
            sTf41first = Double.parseDouble(Tf41first.getText());
        }
        if (Tf42first.getText().toString().trim().isEmpty() != true){
            sTf42first = Double.parseDouble(Tf42first.getText());
        }
        if (Tf43first.getText().toString().trim().isEmpty() != true){
            sTf43first = Double.parseDouble(Tf43first.getText());
        }
        if (Tf44first.getText().toString().trim().isEmpty() != true){
            sTf44first = Double.parseDouble(Tf44first.getText());
        }
        if (Tf51first.getText().toString().trim().isEmpty() != true){
            sTf51first = Double.parseDouble(Tf51first.getText());
        }
        if (Tf52first.getText().toString().trim().isEmpty() != true){
            sTf52first = Double.parseDouble(Tf52first.getText());
        }
        if (Tf53first.getText().toString().trim().isEmpty() != true){
            sTf53first = Double.parseDouble(Tf53first.getText());
        }
        if (Tf54first.getText().toString().trim().isEmpty() != true){
            sTf54first = Double.parseDouble(Tf54first.getText());
        }
        if (Tf61first.getText().toString().trim().isEmpty() != true){
            sTf61first = Double.parseDouble(Tf61first.getText());
        }
        if (Tf62first.getText().toString().trim().isEmpty() != true){
            sTf62first = Double.parseDouble(Tf62first.getText());
        }
        if (Tf63first.getText().toString().trim().isEmpty() != true){
            sTf63first = Double.parseDouble(Tf63first.getText());
        }
        if (Tf64first.getText().toString().trim().isEmpty() != true){
            sTf64first = Double.parseDouble(Tf64first.getText());
        }
        if (Tf11second.getText().toString().trim().isEmpty() != true){
            sTf11second = Double.parseDouble(Tf11second.getText());
        }
        if (Tf21second.getText().toString().trim().isEmpty() != true){
            sTf21second = Double.parseDouble(Tf21second.getText());
        }
        if (Tf31second.getText().toString().trim().isEmpty() != true){
            sTf31second = Double.parseDouble(Tf31second.getText());
        }
        if (Tf41second.getText().toString().trim().isEmpty() != true){
            sTf41second = Double.parseDouble(Tf41second.getText());
        }
        if (Tf51second.getText().toString().trim().isEmpty() != true){
            sTf51second = Double.parseDouble(Tf51second.getText());
        }
        if (Tf61second.getText().toString().trim().isEmpty() != true){
            sTf61second = Double.parseDouble(Tf61second.getText());
        }
    }
    public void getEmptyError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("This day already was changed");
        VBox dialogPaneContent = new VBox();
        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    public void closeStage(){
        stage = (Stage) buttonApply.getScene().getWindow();
        stage.close();
    }
}
