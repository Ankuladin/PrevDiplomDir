import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Graph {
    static LocalDateTime localDateTime;
    double[] mass = new double[12];
    ArrayList<DataOBJ> massData;
    String string1;
    String string2;
    String string3;
    String string4;
    private Main mainApp;
    @FXML
    CategoryAxis xAxiss;
    int operNumb = 0;
    @FXML
    NumberAxis yAxiss;
    @FXML
    LineChart Graph;
    @FXML
    TextArea TA1;
    @FXML
    Label DateLabel;
    @FXML
    public void clickReport(ActionEvent event) {
        try {
            mainApp.saveReport(localDateTime.toString(), string1, string2, string3, string4);
        }
        catch ( Exception ex){
            showError(ex);
        }
    }
    @FXML
    private void initialize() {
        xAxiss.setLabel("time");
        yAxiss.setLabel("Sign");
    }
    public int getOperNumb() {
        return operNumb;
    }
    public void setOperNumb(int operNumb) {
        this.operNumb = operNumb;
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setMass(double[] mass) {
        this.mass = mass;
    }

    public void setData( ArrayList<DataOBJ> massData){
        this.massData = massData;
    }

    public void initializeGraph() {
        try {
            //DateLabel.setText(massData.get(0).getCurrDate().toString());
            xAxiss.setLabel("Час");
            yAxiss.setLabel("KV/min");
            XYChart.Series series1 = new XYChart.Series();
            XYChart.Series series2 = new XYChart.Series();
            XYChart.Series series3 = new XYChart.Series();
            XYChart.Series series4 = new XYChart.Series();
            XYChart.Series series5 = new XYChart.Series();
            XYChart.Series series6 = new XYChart.Series();
            series1.setName("Вузол 1");
            series2.setName("Вузол 2");
            series3.setName("Вузол 3");
            series4.setName("Вузол 4");
            series5.setName("Вузол 5");
            series6.setName("Вузол 6");
            LocalDateTime start = massData.get(0).getCurrDate();
            LocalDateTime end = massData.get(massData.size()-1).getCurrDate();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            for(int i = 0; i<24; i++){
                String formattedDateTime = massData.get(i).getCurrDate().format(formatter);
                series1.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField1()));
                series2.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField2()));
                series3.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField3()));
                series4.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField4()));
                series5.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField5()));
                series6.getData().add(new XYChart.Data(formattedDateTime, massData.get(i).getField6()));
            }
            Graph.getData().addAll(series1, series2, series3, series4, series5, series6);
            /*
            double total1 = mass[4] + mass[8] - mass[0];
            double total2 = mass[5] + mass[9] - mass[1];
            double total3 = mass[6] + mass[10] - mass[2];
            double total4 = mass[7] + mass[11] - mass[3];
            string1 = "For time 00:00-06:00: Will used:" + mass[0] + " Energy source:" + mass[4] + " Green energy:" + mass[8] + " Total:" + total1 + "\n";
            string2 = "For time 06:00-12:00: Will used:" + mass[1] + " Energy source:" + mass[5] + " Green energy:" + mass[9] + " Total:" + total2 + "\n";
            string3 = "For time 12:00-18:00: Will used:" + mass[2] + " Energy source:" + mass[6] + " Green energy:" + mass[10] + " Total:" + total3 + "\n";
            string4 = "For time 18:00-00:00: Will used:" + mass[3] + " Energy source:" + mass[7] + " Green energy:" + mass[11] + " Total:" + total4 + "\n";
            TA1.appendText(localDateTime.toString() + "\n");
            TA1.appendText(string1);
            TA1.appendText(string2);
            TA1.appendText(string3);
            TA1.appendText(string4);
            if (total1 < 0 || total2 < 0 || total3 < 0 || total4 < 0) {
                if (total1 < 0) {
                    TA1.appendText("lack of energy in 0-6h" + "\n");
                }
                if (total2 < 0) {
                    TA1.appendText("lack of energy in 6-12h" + "\n");
                }
                if (total3 < 0) {
                    TA1.appendText("lack of energy in 12-18h" + "\n");
                }
                if (total4 < 0) {
                    TA1.appendText("lack of energy in 18-00h" + "\n");
                }
            } else {
                TA1.appendText("Sufficient energy" + "\n");
            }
            */
        }catch (Exception ex){
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
