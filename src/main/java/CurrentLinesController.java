import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class CurrentLinesController {
    Main mainApp;
    Stage stage;
    WorkDay currentWorkDay;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    Circle C1;
    @FXML
    Circle C2;
    @FXML
    Circle C3;
    @FXML
    Circle C4;
    @FXML
    Circle C5;
    @FXML
    Label L1;
    @FXML
    Label L2;
    @FXML
    Label L3;
    @FXML
    Label L4;
    @FXML
    Label L5;
    @FXML
    Label LSummEn;
    @FXML
    Label LSummNeed;
    public void setWorkDay(WorkDay currentWorkDay) {
        this.currentWorkDay = currentWorkDay;
    }
    public void initializeLine(){
        GregorianCalendar gcalendar = new GregorianCalendar();
        int time = gcalendar.get(Calendar.HOUR_OF_DAY);
        if (time> 0 && time < 6){
            System.out.println("0-6");
            changeControlls(currentWorkDay.getCurrBatteryFirstPart(), currentWorkDay.getFirstEnSourse(),
                    currentWorkDay.getFirstPart1(), currentWorkDay.getFirstPart2(), currentWorkDay.getFirstPart3());
        }else if (time> 5 && time < 12){
            System.out.println("5-12");
            changeControlls(currentWorkDay.getCurrBatterySecondPart(), currentWorkDay.getSecondEnSourse(),
                    currentWorkDay.getSecondPart1(), currentWorkDay.getSecondPart2(), currentWorkDay.getSecondPart3());
        }
        else if (time> 11 && time < 18){
            System.out.println("11-18");
            changeControlls(currentWorkDay.getCurrBatteryThirdPart(), currentWorkDay.getThirdEnSourse(),
                    currentWorkDay.getThirdPart1(), currentWorkDay.getThirdPart2(), currentWorkDay.getThirdPart3());
        }
        else if (time> 17 && time < 0){
            System.out.println("17-0");
            changeControlls(currentWorkDay.getCurrBatteryFourthPart(), currentWorkDay.getFourthEnSourse(),
                    currentWorkDay.getFourthPart1(), currentWorkDay.getFourthPart2(), currentWorkDay.getFourthPart3());
        }
    }
    public void changeControlls(double l1, double l2, double l3, double l4, double l5){
        L1.setText(new DecimalFormat("#0.00").format(l1));
        L2.setText(new DecimalFormat("#0.00").format(l2));
        L3.setText(new DecimalFormat("#0.00").format(l3));
        L4.setText(new DecimalFormat("#0.00").format(l4));
        L5.setText(new DecimalFormat("#0.00").format(l5));
        LSummEn.setText(new DecimalFormat("#0.00").format(l1+l2));
        LSummNeed.setText(new DecimalFormat("#0.00").format(l3+l4+l5));
        if (l1 == 0){
            C1.setFill(Color.RED);
        }
        if (l2 == 0){
            C2.setFill(Color.RED);
        }
        if (l3 == 0){
            C3.setFill(Color.RED);
        }
        if (l4 == 0){
            C4.setFill(Color.RED);
        }
        if (l5 == 0){
            C5.setFill(Color.RED);
        }
    }
}
