import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
public class BarChartGraphController {
    String string1;
    String string2;
    String string3;
    String string4;
    ArrayList<String> arrString = new ArrayList<String>();
    Main mainApp;
    @FXML
    StackedBarChart SBC;
    @FXML
    TextArea TA1;
    @FXML
    CategoryAxis CA;
    @FXML
    NumberAxis NA;
    LocalDate startDate;
    ArrayList<double[]> massOfMass;
    public void setMass(ArrayList<double[]> massOfMass) {
        this.massOfMass = massOfMass;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    public void clickReport(ActionEvent event) {
        try {
            mainApp.saveWeekReport(arrString);
        }
        catch (Exception ex){
            showError(ex);
        }
    }
    public void initializeSBC() {
        try {
            System.out.println(startDate);
            XYChart.Series<String, Double> dataSeries1 = new XYChart.Series<String, Double>();
            dataSeries1.setName("00:00-06:00");
            System.out.println(massOfMass.get(1)[0]);
            System.out.println(massOfMass.get(2)[0]);
            XYChart.Series<String, Double> dataSeries3 = new XYChart.Series<String, Double>();
            dataSeries3.setName("12:00-18:00");
            XYChart.Series<String, Double> dataSeries4 = new XYChart.Series<String, Double>();
            dataSeries4.setName("18:00-00:00");
            XYChart.Series<String, Double> dataSeries2 = new XYChart.Series<String, Double>();
            dataSeries2.setName("06:00-12:00");
            for (int i = 0; i < 7; i++) {
                dataSeries1.getData().add(new XYChart.Data<String, Double>("Буде використано " + startDate.plusDays(i).toString(), massOfMass.get(i)[0]));
                dataSeries1.getData().add(new XYChart.Data<String, Double>("ТЕЦ-1 " + startDate.plusDays(i).toString(), massOfMass.get(i)[4]));
                dataSeries1.getData().add(new XYChart.Data<String, Double>("НДЕ " + startDate.plusDays(i).toString(), massOfMass.get(i)[8]));
                dataSeries2.getData().add(new XYChart.Data<String, Double>("Буде використано " + startDate.plusDays(i).toString(), massOfMass.get(i)[1]));
                dataSeries2.getData().add(new XYChart.Data<String, Double>("ТЕЦ-1 " + startDate.plusDays(i).toString(), massOfMass.get(i)[5]));
                dataSeries2.getData().add(new XYChart.Data<String, Double>("НДЕ " + startDate.plusDays(i).toString(), massOfMass.get(i)[9]));
                dataSeries3.getData().add(new XYChart.Data<String, Double>("Буде використано " + startDate.plusDays(i).toString(), massOfMass.get(i)[2]));
                dataSeries3.getData().add(new XYChart.Data<String, Double>("ТЕЦ-1 " + startDate.plusDays(i).toString(), massOfMass.get(i)[6]));
                dataSeries3.getData().add(new XYChart.Data<String, Double>("НДЕ " + startDate.plusDays(i).toString(), massOfMass.get(i)[10]));
                dataSeries4.getData().add(new XYChart.Data<String, Double>("Буде використано " + startDate.plusDays(i).toString(), massOfMass.get(i)[3]));
                dataSeries4.getData().add(new XYChart.Data<String, Double>("ТЕЦ-1 " + startDate.plusDays(i).toString(), massOfMass.get(i)[7]));
                dataSeries4.getData().add(new XYChart.Data<String, Double>("НДЕ " + startDate.plusDays(i).toString(), massOfMass.get(i)[11]));
                double total1 = massOfMass.get(i)[4] + massOfMass.get(i)[8] - massOfMass.get(i)[0];
                double total2 = massOfMass.get(i)[5] + massOfMass.get(i)[9] - massOfMass.get(i)[1];
                double total3 = massOfMass.get(i)[6] + massOfMass.get(i)[10] - massOfMass.get(i)[2];
                double total4 = massOfMass.get(i)[7] + massOfMass.get(i)[11] - massOfMass.get(i)[3];
                string1 = "For time 00:00-06:00: Will used:" + massOfMass.get(i)[0] + " Energy source:" + massOfMass.get(i)[4] + " Green energy:" + massOfMass.get(i)[8] + " Total:" + total1 + "\n";
                string2 = "For time 06:00-12:00: Will used:" + massOfMass.get(i)[1] + " Energy source:" + massOfMass.get(i)[5] + " Green energy:" + massOfMass.get(i)[9] + " Total:" + total2 + "\n";
                string3 = "For time 12:00-18:00: Will used:" + massOfMass.get(i)[2] + " Energy source:" + massOfMass.get(i)[6] + " Green energy:" + massOfMass.get(i)[10] + " Total:" + total3 + "\n";
                string4 = "For time 18:00-00:00: Will used:" + massOfMass.get(i)[3] + " Energy source:" + massOfMass.get(i)[7] + " Green energy:" + massOfMass.get(i)[11] + " Total:" + total4 + "\n";
                TA1.appendText(startDate.plusDays(i).toString());
                TA1.appendText("\r\n");
                TA1.appendText(string1);
                TA1.appendText("\r\n");
                TA1.appendText(string2);
                TA1.appendText("\r\n");
                TA1.appendText(string3);
                TA1.appendText("\r\n");
                TA1.appendText(string4);
                TA1.appendText("\r\n");
                arrString.add(startDate.plusDays(i).toString());
                arrString.add("\r\n");
                arrString.add(string1);
                arrString.add("\r\n");
                arrString.add(string2);
                arrString.add("\r\n");
                arrString.add(string3);
                arrString.add("\r\n");
                arrString.add(string4);
                arrString.add("\r\n");
                if (total1 < 0 || total2 < 0 || total3 < 0 || total4 < 0) {
                    if (total1 < 0) {
                        TA1.appendText("lack of energy in 00:00-06:00" + "\r\n");
                    }
                    if (total2 < 0) {
                        TA1.appendText("lack of energy in 06:00-12:00" + "\r\n");
                    }
                    if (total3 < 0) {
                        TA1.appendText("lack of energy in 12:00-18:00" + "\r\n");
                    }
                    if (total4 < 0) {
                        TA1.appendText("lack of energy in 18:00-00:00" + "\r\n");
                    }
                } else {
                    TA1.appendText("Sufficient energy" + "\r\n");
                }
            }
            SBC.getData().add(dataSeries1);
            SBC.getData().add(dataSeries2);
            SBC.getData().add(dataSeries3);
            SBC.getData().add(dataSeries4);
        }
        catch (Exception ex){
            showError(ex);
        }
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
}
