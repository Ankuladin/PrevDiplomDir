import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.io.StringWriter;
public class GetDBDataController {
    Main mainApp;
    int operNumb;
    public void setOperNumb(int operNumb){
        this.operNumb = operNumb;
    }
    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;
    }
    int indexNumb;
    @FXML
    private Button buttonApply;
    @FXML
    private TextField DBName;
    @FXML
    private TextField TableName;
    @FXML
    private TextField UserName;
    @FXML
    private TextField Password;
    private String strDBName;
    private String strTableName;
    private String strUserName;
    private String strPassword;
    private boolean work = true;
    boolean check = true;
    Stage stage;
    public int getIndexNumb() {
        return indexNumb;
    }
    public void setIndexNumb(int indexNumb) {
        this.indexNumb = indexNumb;
    }
    @FXML
    private void clickApply(ActionEvent event) {
        try {
            stage = (Stage) buttonApply.getScene().getWindow();
            strDBName = DBName.getText(); //http://qaru.site/questions/311587/using-threads-to-make-database-requests
            strTableName = TableName.getText();
            strUserName = UserName.getText();
            strPassword = Password.getText();
            mainApp.getConnectionDB(strDBName,strUserName, strPassword );
            if(operNumb== 0 || operNumb==1 || operNumb==2 || operNumb==3 &&  mainApp.workDay.size()!= 0) {
                new Thread(task).start();
            }
        } catch (Exception e){
            showError(e);
        }
    }
    Task<Integer> task = new Task<Integer>() {
        @Override protected Integer call() throws Exception {
            int iterations = 0;
            for (iterations = 0; iterations < 1; iterations++) {
                if (isCancelled()) {
                    break;
                }
                try{
                    if (operNumb == 0) /* Open*/{
                        mainApp.openDataDB(strTableName);
                    }
                    else if (operNumb == 1 )/* Save*/{
                        mainApp.saveDataDB(strTableName);
                    }
                    else if (operNumb == 2) /*insert*/ {
                        mainApp.insertDataDB(strTableName);
                    }
                    else if (operNumb == 3) /*insert*/ {
                        mainApp.updateDataDB(strTableName, indexNumb);
                    }
                }catch (Exception e){
                    work = false;
                }
            }
            return iterations;
        }
        @Override protected void succeeded() {
            if (work != true) {
                getEmptyError();
            }
                super.succeeded();
                stage.close();
                updateMessage("Done!");
            work = true;
        }
        @Override protected void cancelled() {
            super.cancelled();
            updateMessage("Cancelled!");
            work = true;
        }

        @Override protected void failed() {
            super.failed();
            updateMessage("Failed!");
            work = true;
        }
    };

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
    public void getEmptyError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Incorrect data");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

}
