import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
public class MessageForFieldController {
    int numbOperation = 0;
    @FXML
    TextField tf1;
    String current = "";
    Stage stage;
    @FXML
    private void clickSendAll(ActionEvent event) {
        if (Main.clientOutputStreams.size() >2) {
            try {
                numbOperation = 4;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("Шахти не підключені");
        }
    }
    @FXML
    private void clickSend1(ActionEvent event) {
        if (Main.clientOutputStreams.size() > 2) {
            try {
                numbOperation = 1;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("Шахта 1 не підключена");
        }

    }
    @FXML
    private void clickSend2(ActionEvent event) {
        if (Main.clientOutputStreams.size() > 3) {
            try {
                numbOperation = 2;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        } else {
            getEmptyConnectError("Шахта 2 не підключена");
        }
    }
    @FXML
    private void clickSend3(ActionEvent event) {
        if (Main.clientOutputStreams.size() > 4) {
            try {
                numbOperation = 3;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("Шахта 3 не підключена");
        }
    }
    @FXML
    private void clickSendMain(ActionEvent event){
        if (Main.clientOutputStreams.size() > 0) {
            try {
                numbOperation = 5;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("ТЕЦ-1 не підключена");
        }
    }
    @FXML
    private void clickSendGreen(ActionEvent event){
        if (Main.clientOutputStreams.size() > 1) {
            try {
                numbOperation = 6;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("НДЕ не підключена");
        }
    }
    @FXML
    private void clickPermAll(ActionEvent event) {
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setField1(true);
                Main.workDay.get(Main.workDay.size()-1).setField2(true);
                Main.workDay.get(Main.workDay.size()-1).setField3(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }
    @FXML
    private void clickPerm1(ActionEvent event) {
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setField1(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }
    @FXML
    private void clickPerm2(ActionEvent event) {
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setField2(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }
    @FXML
    private void clickPerm3(ActionEvent event) {
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setField3(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }
    @FXML
    private void clickPermMain(ActionEvent event){
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setMainEnergy(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }
    @FXML
    private void clickPermGreen(ActionEvent event){
        if (Main.workDay.size() > 0) {
            try {
                Main.workDay.get(Main.workDay.size()-1).setGreenEnergy(true);
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyArrayError();
        }
    }

    public void closeStage() {
        stage = (Stage) tf1.getScene().getWindow();
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

    public void getEmptyConnectError(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(a+" connected clients or less");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

    public void getEmptyArrayError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Your table is empty");
        VBox dialogPaneContent = new VBox();
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    Task<Integer> task = new Task<Integer>() {
        @Override
        protected Integer call() throws Exception {
            int iterations = 0;
            for (iterations = 0; iterations < 1; iterations++) {
                if (isCancelled()) {
                    break;
                }
                try {
                    if (Main.clientOutputStreams.size() != 0) {
                        ObjectOutputStream oos;
                        switch (numbOperation) {
                            case 1:
                                oos = (ObjectOutputStream) Main.clientOutputStreams.get(2);
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 2:
                                oos = (ObjectOutputStream) Main.clientOutputStreams.get(3);
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 3:
                                oos = (ObjectOutputStream) Main.clientOutputStreams.get(4);
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 4:
                                for (int i = 2; i < Main.clientOutputStreams.size(); i++) {
                                    oos = (ObjectOutputStream) Main.clientOutputStreams.get(i);
                                    oos.writeObject(Main.currentMessage);
                                    oos.flush();
                                }
                                break;
                            case 5:
                                oos = (ObjectOutputStream) Main.clientOutputStreams.get(0);
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 6:
                                oos = (ObjectOutputStream) Main.clientOutputStreams.get(1);
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                        }
                    }
                } catch (Exception e) {
                    showError(e);
                }
            }
            return iterations;
        }
        @Override
        protected void succeeded() {
            super.succeeded();
            stage.close();
            updateMessage("Done!");
        }
        @Override
        protected void cancelled() {
            super.cancelled();
            updateMessage("Cancelled!");
        }
        @Override
        protected void failed() {
            super.failed();
            updateMessage("Failed!");
        }
    };
}
