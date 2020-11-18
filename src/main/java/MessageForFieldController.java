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

        if (Main.newOutputStreams.length > 0) {

            try {
                numbOperation = 8;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("Вузли не підключені");
        }
    }
    @FXML
    private void clickSend1(ActionEvent event) {
        if (Main.newOutputStreams[1] != null) {
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
            getEmptyConnectError("Вузол 1 не підключений");
        }

    }

    @FXML
    private void clickSend2(ActionEvent event) {
        if (Main.newOutputStreams[2] != null) {
            try {
                numbOperation = 2;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("Вузол 2 не підключений");
        }

    }
    @FXML
    private void clickSend3(ActionEvent event) {
        if (Main.newOutputStreams[3] != null) {
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
            getEmptyConnectError("Вузол 3 не підключений");
        }

    }
    @FXML
    private void clickSend4(ActionEvent event) {
        if (Main.newOutputStreams[4] != null) {
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
            getEmptyConnectError("Вузол 4 не підключений");
        }

    }
    @FXML
    private void clickSend5(ActionEvent event) {
        if (Main.newOutputStreams[5] != null) {
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
            getEmptyConnectError("Вузол 5 не підключений");
        }

    }
    @FXML
    private void clickSend6(ActionEvent event) {
        if (Main.newOutputStreams[6] != null) {
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
            getEmptyConnectError("Шахта 6 не підключений");
        }

    }
    @FXML
    private void clickSend7(ActionEvent event) {
        if (Main.newOutputStreams[7] != null) {
            try {
                numbOperation = 7;
                stage = (Stage) tf1.getScene().getWindow();
                current = tf1.getText();
                Main.currentMessage = current;
                new Thread(task).start();
            } catch (Exception ex) {
                showError(ex);
            }
        }
        else {
            getEmptyConnectError("ТЕЦ не підключена");
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
                    if (Main.newOutputStreams.length > 0) {
                        ObjectOutputStream oos;
                        switch (numbOperation) {
                            case 1:
                                oos = Main.newOutputStreams[1];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 2:
                                oos = Main.newOutputStreams[2];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 3:
                                oos = Main.newOutputStreams[3];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 4:
                                oos = Main.newOutputStreams[4];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 5:
                                oos = Main.newOutputStreams[5];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 6:
                                oos = Main.newOutputStreams[6];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 7:
                                oos = Main.newOutputStreams[7];
                                oos.writeObject(Main.currentMessage);
                                oos.flush();
                                break;
                            case 8:
                                for (int i = 0; i<Main.newOutputStreams.length; i++){
                                    if(Main.newOutputStreams[i] != null){
                                        oos = Main.newOutputStreams[i];
                                        oos.writeObject(Main.currentMessage);
                                        oos.flush();
                                    }
                                }
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
