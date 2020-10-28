import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ThreadForAuto implements Runnable {
    Label l1;
    public ThreadForAuto(Label l1){
        this.l1 = l1;
    }
    RootLayoutController RLC;
    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

    }
    // Запуск потоку
    public void run() {
        try {
            while (true) {
                WorkDay currentWorkDay = Main.workDay.get(Main.workDay.size() - 1);
                GregorianCalendar gcalendar = new GregorianCalendar();
                int time = gcalendar.get(Calendar.HOUR_OF_DAY);
                int time2 = gcalendar.get(Calendar.MINUTE);
                if (time > 0 && time < 6) {
                    if (changeControlls(currentWorkDay.getCurrBatteryFirstPart(), currentWorkDay.getFirstEnSourse(),
                            currentWorkDay.getFirstPart1(), currentWorkDay.getFirstPart2(), currentWorkDay.getFirstPart3()) == 1) {
                        Controll("Енергії достатньо 00:00-06:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    } else if (changeControlls(currentWorkDay.getCurrBatteryFirstPart(), currentWorkDay.getFirstEnSourse(),
                            currentWorkDay.getFirstPart1(), currentWorkDay.getFirstPart2(), currentWorkDay.getFirstPart3()) == -1) {
                        Controll("Енергії не достатньо 00:00-06:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    }
                } else if (time > 5 && time < 12) {
                    if (changeControlls(currentWorkDay.getCurrBatterySecondPart(), currentWorkDay.getSecondEnSourse(),
                            currentWorkDay.getSecondPart1(), currentWorkDay.getSecondPart2(), currentWorkDay.getSecondPart3()) == 1) {
                        Controll("Енергії достатньо 06:00-12:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    } else if (changeControlls(currentWorkDay.getCurrBatterySecondPart(), currentWorkDay.getSecondEnSourse(),
                            currentWorkDay.getSecondPart1(), currentWorkDay.getSecondPart2(), currentWorkDay.getSecondPart3()) == -1) {
                        Controll("Енергії не достатньо 06:00-12:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    }
                } else if (time > 11 && time < 18) {
                    if (changeControlls(currentWorkDay.getCurrBatteryThirdPart(), currentWorkDay.getThirdEnSourse(),
                            currentWorkDay.getThirdPart1(), currentWorkDay.getThirdPart2(), currentWorkDay.getThirdPart3()) == 1) {
                        Controll("Енергії достатньо 12:00-18:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    } else if (changeControlls(currentWorkDay.getCurrBatteryThirdPart(), currentWorkDay.getThirdEnSourse(),
                            currentWorkDay.getThirdPart1(), currentWorkDay.getThirdPart2(), currentWorkDay.getThirdPart3()) == -1) {
                        Controll("Енергії не достатньо 12:00-18:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    }
                } else if (time > 17 && time < 0) {
                    if (changeControlls(currentWorkDay.getCurrBatteryFourthPart(), currentWorkDay.getFourthEnSourse(),
                            currentWorkDay.getFourthPart1(), currentWorkDay.getFourthPart2(), currentWorkDay.getFourthPart3()) == 1) {
                        Controll("Енергії достатньо 18:00-00:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    } else if (changeControlls(currentWorkDay.getCurrBatteryFourthPart(), currentWorkDay.getFourthEnSourse(),
                            currentWorkDay.getFourthPart1(), currentWorkDay.getFourthPart2(), currentWorkDay.getFourthPart3()) == -1) {
                        Controll("Енергії не достатньо 18:00-00:00 "+ currentWorkDay.getCurrDate()+ " Остання перевірка: " + Integer.toString(time) + ":" + Integer.toString(time2));
                    }
                }
                try {
                    Thread.sleep(600000); //Поток засыпает на 10 минут
                }catch (Exception ex){

                }
            }
        } catch (Exception ex) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    showError(ex);
                }
            });
        }
    }
    //Перевірка
    public int changeControlls(double l1, double l2, double l3, double l4, double l5) {
        int ans = 0;
        if (l1 + l2 - l3 - l4 - l5 >= 0) {
            ans = 1;
        } else if (l1 + l2 - l3 - l4 - l5 < 0) {
            ans = -1;
        }
        return ans;
    }
    //Генерація повідомлення
    private void Controll(String s){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    l1.setText(s);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("AutoMessage");
                    alert.setHeaderText(s);
                    VBox dialogPaneContent = new VBox();
                    // Set content for Dialog Pane
                    alert.getDialogPane().setContent(dialogPaneContent);

                    alert.showAndWait();
                }catch (Exception ex){
                    showError(ex);
                }
            }
        });
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
}
