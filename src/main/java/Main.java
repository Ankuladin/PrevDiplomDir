import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main extends Application {
    static ArrayList<WorkDay> workDay = new ArrayList<WorkDay>();
    static ArrayList<DataOBJ> DataMass = new ArrayList<DataOBJ>();
    static ArrayList clientOutputStreams;
    static boolean permission = true;
    FileChooser fileChooser;
    Connection connection;
    static String currentMessage = "";
    static RootLayoutController rootController;
    //public static Socket[] sockMass = new Socket[4];
    public static ObjectOutputStream[] newOutputStreams = new ObjectOutputStream[8];
    public static ObjectInputStream[] newInputStreams = new ObjectInputStream[8];
    public Main() {
    }
    public Stage primaryStage;
    public BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Головний додаток");
        initRootLayout();
        ThreadRun l = new ThreadRun();
        l.setMainApp(this);
        Thread t = new Thread(l);
        t.start();
    }
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            rootController = loader.getController();
            rootController.setMainApp(this);
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Повідомлення про відсутність записів для збереження
    public void getEmptyError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Your list is empty");
        VBox dialogPaneContent = new VBox();
        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);

        alert.showAndWait();
    }

    // Фільтри розширень
    private void configuringFileChooser(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().addAll(//
                new FileChooser.ExtensionFilter("All Files", "*.*"), //
                new FileChooser.ExtensionFilter("DAT", "*.dat"));
    }
    //Підключення до БД
    public void getConnectionDB(String currDBName, String currDBUsername, String currDBPass) throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/" + currDBName +
                "?serverTimezone=Europe/Moscow&useSSL=false", currDBUsername, currDBPass);
    }
    // Запис до БД
    public void saveDataDB(String tableName) throws SQLException, IOException {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
            String sqlCommand = "CREATE TABLE " + tableName + " (Id INT PRIMARY KEY AUTO_INCREMENT, Date DATETIME, Field_1 DOUBLE, Field_2 DOUBLE, Field_3 DOUBLE, Field_4 DOUBLE, Field_5 DOUBLE, Field_6 DOUBLE, POWER DOUBLE, BATTERY DOUBLE)";
            statement.executeUpdate(sqlCommand);
            if (DataMass.size() != 0) {
                for (int i = 0; i < DataMass.size(); i++) {
                    //statement.executeUpdate("INSERT " + tableName + "(Date, FirstPart , SecondPart , ThirdPart, FoursPart) VALUES " +"(" + workDay.get(i).getDate() + " , " + workDay.get(i).getFirstPart() + " , " + workDay.get(i).getSecondPart() +" , " + workDay.get(i).getThirdPart() + " , " + workDay.get(i).getFourthPart() + " ) ");
                    String sql = "INSERT INTO products (Date, Field_1 , Field_2 , Field_3 , Field_4 , Field_5 , Field_6 , POWER , BATTERY) Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, DataMass.get(i).getCurrDate().toString());
                    preparedStatement.setDouble(2, DataMass.get(i).getField1());
                    preparedStatement.setDouble(3, DataMass.get(i).getField2());
                    preparedStatement.setDouble(4, DataMass.get(i).getField3());
                    preparedStatement.setDouble(5, DataMass.get(i).getField4());
                    preparedStatement.setDouble(6, DataMass.get(i).getField5());
                    preparedStatement.setDouble(7, DataMass.get(i).getField6());
                    preparedStatement.setDouble(8, DataMass.get(i).getPower());
                    preparedStatement.setDouble(9, DataMass.get(i).getBattary());
                    preparedStatement.executeUpdate();
                }
            } else {
                getEmptyError();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    //Оновлює запис у БД
    public void updateDataDB(String tableName, int numb) throws SQLException, IOException {
        try {
            Statement statement = connection.createStatement();
            if (DataMass.size() != 0) {
                int numb1 = numb+1;
                //statement.executeUpdate("INSERT " + tableName + "(Date, FirstPart , SecondPart , ThirdPart, FoursPart) VALUES " +"(" + workDay.get(i).getDate() + " , " + workDay.get(i).getFirstPart() + " , " + workDay.get(i).getSecondPart() +" , " + workDay.get(i).getThirdPart() + " , " + workDay.get(i).getFourthPart() + " ) ");
                String sql = "UPDATE " + tableName + " SET Date = ?, Field_1 = ?, Field_2 = ? , Field_3 = ?, Field_4 = ?, Field_5 = ?, Field_6 = ?, POWER = ?, BATTERY = ? " + " WHERE ID = " + numb1;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, DataMass.get(numb).getCurrDate().toString());
                preparedStatement.setDouble(2, DataMass.get(numb).getField1());
                preparedStatement.setDouble(3, DataMass.get(numb).getField2());
                preparedStatement.setDouble(4, DataMass.get(numb).getField3());
                preparedStatement.setDouble(5, DataMass.get(numb).getField4());
                preparedStatement.setDouble(6, DataMass.get(numb).getField5());
                preparedStatement.setDouble(7, DataMass.get(numb).getField6());
                preparedStatement.setDouble(8, DataMass.get(numb).getPower());
                preparedStatement.setDouble(9, DataMass.get(numb).getBattary());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Додає новий запис до БД
    public void insertDataDB(String tableName) throws SQLException, IOException {
        try {
            Statement statement = connection.createStatement();
            if (DataMass.size() != 0) {
                    String sql = "INSERT INTO " + tableName + " (Date, Field_1 , Field_2 , Field_3 , Field_4 , Field_5 , Field_6 , POWER , BATTERY  ) Values (?, ?, ?, ?, ?, ?, ?, ?, ? )";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, DataMass.get(DataMass.size()-1).getCurrDate().toString());
                    preparedStatement.setDouble(2, DataMass.get(DataMass.size()-1).getField1());
                    preparedStatement.setDouble(3, DataMass.get(DataMass.size()-1).getField2());
                    preparedStatement.setDouble(4, DataMass.get(DataMass.size()-1).getField3());
                    preparedStatement.setDouble(5, DataMass.get(DataMass.size()-1).getField4());
                    preparedStatement.setDouble(6, DataMass.get(DataMass.size()-1).getField5());
                    preparedStatement.setDouble(7, DataMass.get(DataMass.size()-1).getField6());
                    preparedStatement.setDouble(8, DataMass.get(DataMass.size()-1).getPower());
                    preparedStatement.setDouble(9, DataMass.get(DataMass.size()-1).getBattary());
                    preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Завантажує дані з БД
    public void openDataDB(String tableName) throws SQLException, IOException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        DataMass.clear();
        System.out.println("openDB");
        while (resultSet.next()) {
            System.out.println("openDBinWhile");
            DataMass.add(new DataOBJ(resultSet.getDouble(3), resultSet.getDouble(4),
                    resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getDouble(7), resultSet.getDouble(8),
                    resultSet.getDouble(9), resultSet.getDouble(10),  resultSet.getString(2)));
        }
        System.out.println(DataMass.size());
    }
    //Метод для графічного відображення вибору файлу
    public void openMenuData() {
        try {
            fileChooser = new FileChooser();
            configuringFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                DataMass = (ArrayList<DataOBJ>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //Зберігає дані у обраний файл
    public void saveMenuData() {

        try {
            fileChooser = new FileChooser();
            configuringFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(DataMass);
            oos.close();
        } catch (Exception e) {
            showError(e);
        }
    }
    //Генерує та зберігає звіт
    public void saveReport(String ld1, String s1, String s2, String s3, String s4) {
        try {
            fileChooser = new FileChooser();
            configuringFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            PrintWriter pw = new PrintWriter(file);
            BufferedWriter bw = new BufferedWriter(pw);
            bw.write(ld1);
            bw.write("\r\n");
            bw.write(s1);
            bw.write("\r\n");
            bw.write(s2);
            bw.write("\r\n");
            bw.write(s3);
            bw.write("\r\n");
            bw.write(s4);
            bw.flush();
            pw.close();
        } catch (Exception e) {
            showError(e);
        }
    }
    //Зберігає тижневий звіт
    public void saveWeekReport(ArrayList<String> arrString) {
        try {
            fileChooser = new FileChooser();
            configuringFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            PrintWriter pw = new PrintWriter(file);
            BufferedWriter bw = new BufferedWriter(pw);
            for (int i = 0; i < arrString.size(); i++) {
                bw.write(arrString.get(i));
                bw.write("\r\n");
            }
            bw.flush();
            pw.close();
        } catch (Exception e) {
            showError(e);
        }
    }
    static void showMessage(String a){
        rootController.showMessage(a);
    }
    //Метод для прогнозу на наступний день
    public double[] meddiumSlipeMethodByNextDay() throws Exception {
        WorkDay dayfirstYearAgo = null;
        WorkDay daysecondYearAgo = null;
        WorkDay daythirdYearAgo = null;
        WorkDay dayfirstDayAgo;
        WorkDay daysecondDayAgo;
        WorkDay daythirdDayAgo;
        LocalDate ld = workDay.get(workDay.size() - 1).getCurrDate();
        ld = ld.plusDays(1);
      //  Graph.localDate=ld;
        double[] k = new double[12];
        if (!(ld.toString().substring(5, 7).equals("02") && ld.toString().substring(8, 10).equals("29"))) {
            for (int r = 0; r < workDay.size(); r++) {
                WorkDay currday = workDay.get(r);
                if (currday.getCurrDate().toString().substring(5, 7).equals(ld.toString().substring(5, 7)) &&
                        currday.getCurrDate().toString().substring(8, 10).equals(ld.toString().substring(8, 10)) &&
                        Double.parseDouble(currday.getCurrDate().toString().substring(0, 4)) == Double.parseDouble(ld.toString().substring(0, 4)) - 1) {
                    dayfirstYearAgo = workDay.get(r);
                }
                if (currday.getCurrDate().toString().substring(5, 7).equals(ld.toString().substring(5, 7)) &&
                        currday.getCurrDate().toString().substring(8, 10).equals(ld.toString().substring(8, 10)) &&
                        Double.parseDouble(currday.getCurrDate().toString().substring(0, 4)) == Double.parseDouble(ld.toString().substring(0, 4)) - 2) {
                    daysecondYearAgo = workDay.get(r);
                }
                if (currday.getCurrDate().toString().substring(5, 7).equals(ld.toString().substring(5, 7)) &&
                        currday.getCurrDate().toString().substring(8, 10).equals(ld.toString().substring(8, 10)) &&
                        Double.parseDouble(currday.getCurrDate().toString().substring(0, 4)) == Double.parseDouble(ld.toString().substring(0, 4)) - 3) {
                    daythirdYearAgo = workDay.get(r);
                }
            }
            dayfirstDayAgo = workDay.get(workDay.size() - 2);
            daysecondDayAgo = workDay.get(workDay.size() - 3);
            daythirdDayAgo = workDay.get(workDay.size() - 4);
            k[0] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getFirstPart1() + dayfirstYearAgo.getFirstPart2() + dayfirstYearAgo.getFirstPart3(),
                    daysecondYearAgo.getFirstPart1() + daysecondYearAgo.getFirstPart2() + daysecondYearAgo.getFirstPart3(),
                    daythirdYearAgo.getFirstPart1() + daythirdYearAgo.getFirstPart2() + daythirdYearAgo.getFirstPart3(),
                    dayfirstDayAgo.getFirstPart1() + dayfirstDayAgo.getFirstPart2() + dayfirstDayAgo.getFirstPart3(),
                    daysecondDayAgo.getFirstPart1() + daysecondDayAgo.getFirstPart2() + daysecondDayAgo.getFirstPart3(),
                    daythirdDayAgo.getFirstPart1() + daythirdDayAgo.getFirstPart2() + daythirdDayAgo.getFirstPart3());
            k[1] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getSecondPart1() + dayfirstYearAgo.getSecondPart2() + dayfirstYearAgo.getSecondPart3(),
                    daysecondYearAgo.getSecondPart1() + daysecondYearAgo.getSecondPart2() + daysecondYearAgo.getSecondPart3(),
                    daythirdYearAgo.getSecondPart1() + daythirdYearAgo.getSecondPart2() + daythirdYearAgo.getSecondPart3(),
                    dayfirstDayAgo.getSecondPart1() + dayfirstDayAgo.getSecondPart2() + dayfirstDayAgo.getSecondPart3(),
                    daysecondDayAgo.getSecondPart1() + daysecondDayAgo.getSecondPart2() + daysecondDayAgo.getSecondPart3(),
                    daythirdDayAgo.getSecondPart1() + daythirdDayAgo.getSecondPart2() + daythirdDayAgo.getSecondPart3());
            k[2] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getThirdPart1() + dayfirstYearAgo.getThirdPart2() + dayfirstYearAgo.getThirdPart3(),
                    daysecondYearAgo.getThirdPart1() + daysecondYearAgo.getThirdPart2() + daysecondYearAgo.getThirdPart3(),
                    daythirdYearAgo.getThirdPart1() + daythirdYearAgo.getThirdPart2() + daythirdYearAgo.getThirdPart3(),
                    dayfirstDayAgo.getThirdPart1() + dayfirstDayAgo.getThirdPart2() + dayfirstDayAgo.getThirdPart3(),
                    daysecondDayAgo.getThirdPart1() + daysecondDayAgo.getThirdPart2() + daysecondDayAgo.getThirdPart3(),
                    daythirdDayAgo.getThirdPart1() + daythirdDayAgo.getThirdPart2() + daythirdDayAgo.getThirdPart3());
            k[3] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getFourthPart1() + dayfirstYearAgo.getFourthPart2() + dayfirstYearAgo.getFourthPart3(),
                    daysecondYearAgo.getFourthPart1() + daysecondYearAgo.getFourthPart2() + daysecondYearAgo.getFourthPart3(),
                    daythirdYearAgo.getFourthPart1() + daythirdYearAgo.getFourthPart2() + daythirdYearAgo.getFourthPart3(),
                    dayfirstDayAgo.getFourthPart1() + dayfirstDayAgo.getFourthPart2() + dayfirstDayAgo.getFourthPart3(),
                    daysecondDayAgo.getFourthPart1() + daysecondDayAgo.getFourthPart2() + daysecondDayAgo.getFourthPart3(),
                    daythirdDayAgo.getFourthPart1() + daythirdDayAgo.getFourthPart2() + daythirdDayAgo.getFourthPart3());
            k[4] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getFirstEnSourse(),
                    daysecondYearAgo.getFirstEnSourse(),
                    daythirdYearAgo.getFirstEnSourse(),
                    dayfirstDayAgo.getFirstEnSourse(),
                    daysecondDayAgo.getFirstEnSourse(),
                    daythirdDayAgo.getFirstEnSourse());
            k[5] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getSecondEnSourse(),
                    daysecondYearAgo.getSecondEnSourse(),
                    daythirdYearAgo.getSecondEnSourse(),
                    dayfirstDayAgo.getSecondEnSourse(),
                    daysecondDayAgo.getSecondEnSourse(),
                    daythirdDayAgo.getSecondEnSourse());
            k[6] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getThirdEnSourse(),
                    daysecondYearAgo.getThirdEnSourse(),
                    daythirdYearAgo.getThirdEnSourse(),
                    dayfirstDayAgo.getThirdEnSourse(),
                    daysecondDayAgo.getThirdEnSourse(),
                    daythirdDayAgo.getThirdEnSourse());
            k[7] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getFourthEnSourse(),
                    daysecondYearAgo.getFourthEnSourse(),
                    daythirdYearAgo.getFourthEnSourse(),
                    dayfirstDayAgo.getFourthEnSourse(),
                    daysecondDayAgo.getFourthEnSourse(),
                    daythirdDayAgo.getFourthEnSourse());
            k[8] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getCurrBatteryFirstPart(),
                    daysecondYearAgo.getCurrBatteryFirstPart(),
                    daythirdYearAgo.getCurrBatteryFirstPart(),
                    dayfirstDayAgo.getCurrBatteryFirstPart(),
                    daysecondDayAgo.getCurrBatteryFirstPart(),
                    daythirdDayAgo.getCurrBatteryFirstPart());
            k[9] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getCurrBatterySecondPart(),
                    daysecondYearAgo.getCurrBatterySecondPart(),
                    daythirdYearAgo.getCurrBatterySecondPart(),
                    dayfirstDayAgo.getCurrBatterySecondPart(),
                    daysecondDayAgo.getCurrBatterySecondPart(),
                    daythirdDayAgo.getCurrBatterySecondPart());
            k[10] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getCurrBatteryThirdPart(),
                    daysecondYearAgo.getCurrBatteryThirdPart(),
                    daythirdYearAgo.getCurrBatteryThirdPart(),
                    dayfirstDayAgo.getCurrBatteryThirdPart(),
                    daysecondDayAgo.getCurrBatteryThirdPart(),
                    daythirdDayAgo.getCurrBatteryThirdPart());
            k[11] = meddiumSlipeMethodSolveByNextDay(dayfirstYearAgo.getCurrBatteryFourthPart(),
                    daysecondYearAgo.getCurrBatteryFourthPart(),
                    daythirdYearAgo.getCurrBatteryFourthPart(),
                    dayfirstDayAgo.getCurrBatteryFourthPart(),
                    daysecondDayAgo.getCurrBatteryFourthPart(),
                    daythirdDayAgo.getCurrBatteryFourthPart());
        } else {
            getFebrError();
        }
        return k;
    }
    //Метод для прогнозу на наступний день
    public double meddiumSlipeMethodSolveByNextDay(double firstYearAgo, double secondYearAgo, double thirdYearAgo,
                                                   double firstDayAgo, double secondDayAgo, double thirdDayAgo) throws Exception {  // first = -1 year , Добавить взятие из workDay!!!!!!!
        double needSlipeYear = (firstYearAgo + secondYearAgo + thirdYearAgo) / 3;
        double ansByYear = needSlipeYear + ((1 / 3) * (firstYearAgo - secondYearAgo));
        double needSlipeDay = (firstDayAgo + secondDayAgo + thirdDayAgo) / 3;
        double ansByDay = needSlipeDay + ((1 / 3) * (firstDayAgo - secondDayAgo));
        double ans = 0;
        ans = (0.6 * ansByDay) + (0.4 * ansByYear);
        return ans;
    }
    //Метод для прогнозу на обраний день
    public double meddiumSlipeMethodSolveByRandomDay(double first, double second, double third) throws Exception {
        double needSlipeYear = (first + second + third) / 3;
        double ans = needSlipeYear + ((1 / 3) * (first - second));
        return ans;
    }
    //Метод для прогнозу на обраний день
    public double[] meddiumSlipeMethodByRandomDay(String year, String month, String day) throws Exception {   // Добавить взятие из workDay по нужной дате!!!!!!!
        LocalDate ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
      //  Graph.localDate=ld;
        int oneYearAgo = Integer.parseInt(year) - 1;
        int twoYearAgo = Integer.parseInt(year) - 2;
        int threeYearAgo = Integer.parseInt(year) - 3;
        LocalDate ld11 = ld.minusDays(1);
        ld11 = ld11.minusYears(1);
        LocalDate ld12 = ld.minusDays(1);
        ld12 = ld12.minusYears(2);
        LocalDate ld13 = ld.minusDays(1);
        ld13 = ld13.minusYears(3);
        LocalDate ld21 = ld.minusDays(2);
        ld21 = ld21.minusYears(1);
        LocalDate ld22 = ld.minusDays(2);
        ld22 = ld22.minusYears(2);
        LocalDate ld23 = ld.minusDays(2);
        ld23 = ld23.minusYears(3);
        LocalDate ld31 = ld.minusDays(3);
        ld31 = ld31.minusYears(1);
        LocalDate ld32 = ld.minusDays(3);
        ld32 = ld32.minusYears(2);
        LocalDate ld33 = ld.minusDays(3);
        ld33 = ld33.minusYears(3);
        int index11 = 0;
        int index12 = 0;
        int index13 = 0;
        int index21 = 0;
        int index22 = 0;
        int index23 = 0;
        int index31 = 0;
        int index32 = 0;
        int index33 = 0;
        for (int r = 0; r < workDay.size(); r++) {
            WorkDay currday = workDay.get(r);
            if (currday.getCurrDate().toString().equals(ld11.toString())) {
                index11 = r;
            }
            if (currday.getCurrDate().toString().equals(ld21.toString())) {
                index12 = r;
            }
            if (currday.getCurrDate().toString().equals(ld31.toString())) {
                index13 = r;
            }
            if (currday.getCurrDate().toString().equals(ld21.toString())) {
                index21 = r;
            }
            if (currday.getCurrDate().toString().equals(ld22.toString())) {
                index22 = r;
            }
            if (currday.getCurrDate().toString().equals(ld23.toString())) {
                index23 = r;
            }
            if (currday.getCurrDate().toString().equals(ld31.toString())) {
                index31 = r;
            }
            if (currday.getCurrDate().toString().equals(ld32.toString())) {
                index32 = r;
            }
            if (currday.getCurrDate().toString().equals(ld33.toString())) {
                index33 = r;
            }
        }
        double[] k = new double[12];
        k[0] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getFirstPart1() + workDay.get(index11).getFirstPart2() + workDay.get(index11).getFirstPart3(),
                workDay.get(index12).getFirstPart1() + workDay.get(index12).getFirstPart2() + workDay.get(index12).getFirstPart3(),
                workDay.get(index13).getFirstPart1() + workDay.get(index13).getFirstPart2() + workDay.get(index13).getFirstPart3())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getFirstPart1() + workDay.get(index21).getFirstPart2() + workDay.get(index21).getFirstPart3(),
                workDay.get(index22).getFirstPart1() + workDay.get(index22).getFirstPart2() + workDay.get(index22).getFirstPart3(),
                workDay.get(index23).getFirstPart1() + workDay.get(index23).getFirstPart2() + workDay.get(index23).getFirstPart3())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getFirstPart1() + workDay.get(index31).getFirstPart2() + workDay.get(index31).getFirstPart3(),
                workDay.get(index32).getFirstPart1() + workDay.get(index32).getFirstPart2() + workDay.get(index32).getFirstPart3(),
                workDay.get(index33).getFirstPart1() + workDay.get(index33).getFirstPart2() + workDay.get(index33).getFirstPart3());
        k[1] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getSecondPart1() + workDay.get(index11).getSecondPart2() + workDay.get(index11).getSecondPart3(),
                workDay.get(index12).getSecondPart1() + workDay.get(index12).getSecondPart2() + workDay.get(index12).getSecondPart3(),
                workDay.get(index13).getSecondPart1() + workDay.get(index13).getSecondPart2() + workDay.get(index13).getSecondPart3())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getSecondPart1() + workDay.get(index21).getSecondPart2() + workDay.get(index21).getSecondPart3(),
                workDay.get(index22).getSecondPart1() + workDay.get(index22).getSecondPart2() + workDay.get(index22).getSecondPart3(),
                workDay.get(index23).getSecondPart1() + workDay.get(index23).getSecondPart2() + workDay.get(index23).getSecondPart3())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getSecondPart1() + workDay.get(index31).getSecondPart2() + workDay.get(index31).getSecondPart3(),
                workDay.get(index32).getSecondPart1() + workDay.get(index32).getSecondPart2() + workDay.get(index32).getSecondPart3(),
                workDay.get(index33).getSecondPart1() + workDay.get(index33).getSecondPart2() + workDay.get(index33).getSecondPart3());
        k[2] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getThirdPart1() + workDay.get(index11).getThirdPart2() + workDay.get(index11).getThirdPart3(),
                workDay.get(index12).getThirdPart1() + workDay.get(index12).getThirdPart2() + workDay.get(index12).getThirdPart3(),
                workDay.get(index13).getThirdPart1() + workDay.get(index13).getThirdPart2() + workDay.get(index13).getThirdPart3())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getThirdPart1() + workDay.get(index21).getThirdPart2() + workDay.get(index21).getThirdPart3(),
                workDay.get(index22).getThirdPart1() + workDay.get(index22).getThirdPart2() + workDay.get(index22).getThirdPart3(),
                workDay.get(index23).getThirdPart1() + workDay.get(index23).getThirdPart2() + workDay.get(index23).getThirdPart3())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getThirdPart1() + workDay.get(index31).getThirdPart2() + workDay.get(index31).getThirdPart3(),
                workDay.get(index32).getThirdPart1() + workDay.get(index32).getThirdPart2() + workDay.get(index32).getThirdPart3(),
                workDay.get(index33).getThirdPart1() + workDay.get(index33).getThirdPart2() + workDay.get(index33).getThirdPart3());
        k[3] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getFourthPart1() + workDay.get(index11).getFourthPart2() + workDay.get(index11).getFourthPart3(),
                workDay.get(index12).getFourthPart1() + workDay.get(index12).getFourthPart2() + workDay.get(index12).getFourthPart3(),
                workDay.get(index13).getFourthPart1() + workDay.get(index13).getFourthPart2() + workDay.get(index13).getFourthPart3())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getFourthPart1() + workDay.get(index21).getFourthPart2() + workDay.get(index21).getFourthPart3(),
                workDay.get(index22).getFourthPart1() + workDay.get(index22).getFourthPart2() + workDay.get(index22).getFourthPart3(),
                workDay.get(index23).getFourthPart1() + workDay.get(index23).getFourthPart2() + workDay.get(index23).getFourthPart3())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getFourthPart1() + workDay.get(index31).getFourthPart2() + workDay.get(index31).getFourthPart3(),
                workDay.get(index32).getFourthPart1() + workDay.get(index32).getFourthPart2() + workDay.get(index32).getFourthPart3(),
                workDay.get(index33).getFourthPart1() + workDay.get(index33).getFourthPart2() + workDay.get(index33).getFourthPart3());
        k[4] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getFirstEnSourse(), workDay.get(index12).getFirstEnSourse(), workDay.get(index13).getFirstEnSourse())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getFirstEnSourse(), workDay.get(index22).getFirstEnSourse(), workDay.get(index23).getFirstEnSourse())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getFirstEnSourse(), workDay.get(index32).getFirstEnSourse(), workDay.get(index33).getFirstEnSourse());
        k[5] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getSecondEnSourse(), workDay.get(index12).getSecondEnSourse(), workDay.get(index13).getSecondEnSourse())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getSecondEnSourse(), workDay.get(index22).getSecondEnSourse(), workDay.get(index23).getSecondEnSourse())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getSecondEnSourse(), workDay.get(index32).getSecondEnSourse(), workDay.get(index33).getSecondEnSourse());
        k[6] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getThirdEnSourse(), workDay.get(index12).getThirdEnSourse(), workDay.get(index13).getThirdEnSourse())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getThirdEnSourse(), workDay.get(index22).getThirdEnSourse(), workDay.get(index23).getThirdEnSourse())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getThirdEnSourse(), workDay.get(index32).getThirdEnSourse(), workDay.get(index33).getThirdEnSourse());
        k[7] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getFourthEnSourse(), workDay.get(index12).getFourthEnSourse(), workDay.get(index13).getFourthEnSourse())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getFourthEnSourse(), workDay.get(index22).getFourthEnSourse(), workDay.get(index23).getFourthEnSourse())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getFourthEnSourse(), workDay.get(index32).getFourthEnSourse(), workDay.get(index33).getFourthEnSourse());
        k[8] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getCurrBatteryFirstPart(), workDay.get(index12).getCurrBatteryFirstPart(), workDay.get(index13).getCurrBatteryFirstPart())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getCurrBatteryFirstPart(), workDay.get(index22).getCurrBatteryFirstPart(), workDay.get(index23).getCurrBatteryFirstPart())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getCurrBatteryFirstPart(), workDay.get(index32).getCurrBatteryFirstPart(), workDay.get(index33).getCurrBatteryFirstPart());
        k[9] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getCurrBatterySecondPart(), workDay.get(index12).getCurrBatterySecondPart(), workDay.get(index13).getCurrBatterySecondPart())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getCurrBatterySecondPart(), workDay.get(index22).getCurrBatterySecondPart(), workDay.get(index23).getCurrBatterySecondPart())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getCurrBatterySecondPart(), workDay.get(index32).getCurrBatterySecondPart(), workDay.get(index33).getCurrBatterySecondPart());
        k[10] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getCurrBatteryThirdPart(), workDay.get(index12).getCurrBatteryThirdPart(), workDay.get(index13).getCurrBatteryThirdPart())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getCurrBatteryThirdPart(), workDay.get(index22).getCurrBatteryThirdPart(), workDay.get(index23).getCurrBatteryThirdPart())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getCurrBatteryThirdPart(), workDay.get(index32).getCurrBatteryThirdPart(), workDay.get(index33).getCurrBatteryThirdPart());
        k[11] = 0.5 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index11).getCurrBatteryFourthPart(), workDay.get(index12).getCurrBatteryFourthPart(), workDay.get(index13).getCurrBatteryFourthPart())
                + 0.3 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index21).getCurrBatteryFourthPart(), workDay.get(index22).getCurrBatteryFourthPart(), workDay.get(index23).getCurrBatteryFourthPart())
                + 0.2 * meddiumSlipeMethodSolveByRandomDay(workDay.get(index31).getCurrBatteryFourthPart(), workDay.get(index32).getCurrBatteryFourthPart(), workDay.get(index33).getCurrBatteryFourthPart());
        return k;
    }
    //Генерація масиву для прогнозу
    public double[] GraphByCurrentDay() throws Exception {
        WorkDay needDay = workDay.get(workDay.size() - 1);
       // Graph.localDate=needDay.getCurrDate();
        double[] k = new double[12];
        k[0] = needDay.getFirstPart1() + needDay.getFirstPart2() + needDay.getFirstPart3();
        k[1] = needDay.getSecondPart1() + needDay.getSecondPart2() + needDay.getSecondPart3();
        k[2] = needDay.getThirdPart1() + needDay.getThirdPart2() + needDay.getThirdPart3();
        k[3] = needDay.getFourthPart1() + needDay.getFourthPart2() + needDay.getFourthPart3();
        k[4] = needDay.getFirstEnSourse();
        k[5] = needDay.getSecondEnSourse();
        k[6] = needDay.getThirdEnSourse();
        k[7] = needDay.getFourthEnSourse();
        k[8] = needDay.getCurrBatteryFirstPart();
        k[9] = needDay.getCurrBatterySecondPart();
        k[10] = needDay.getCurrBatteryThirdPart();
        k[11] = needDay.getCurrBatteryFourthPart();
        return k;
    }
    //Повідомленя про недопустиму дату
    public void getFebrError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("For 29.02 use RandomDay Prediction");
        VBox dialogPaneContent = new VBox();
        // Set content for Dialog Pane
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }
    //Завантаження даних з цехів та ТЕЦ-1 , НДЕ
    public synchronized static void addDataFromField(WorkDay workday, int a) {
        WorkDay day = workDay.get(Main.workDay.size() - 1);
        if (a == 1) {
            day.setFirstEnSourse(workday.getFirstEnSourse());
            day.setSecondEnSourse(workday.getSecondEnSourse());
            day.setThirdEnSourse(workday.getThirdEnSourse());
            day.setFourthEnSourse(workday.getFourthEnSourse());
        }
        else if (a == 2) {
            day.setCurrBatteryFirstPart(workday.getCurrBatteryFirstPart());
            day.setCurrBatterySecondPart(workday.getCurrBatterySecondPart());
            day.setCurrBatteryThirdPart(workday.getCurrBatteryThirdPart());
            day.setCurrBatteryFourthPart(workday.getCurrBatteryFourthPart());
        }
        else if (a == 3) {
            day.setFirstPart1(workday.getFirstPart1());
            day.setSecondPart1(workday.getSecondPart1());
            day.setThirdPart1(workday.getThirdPart1());
            day.setFourthPart1(workday.getFourthPart1());
        }
        else if (a == 4) {
            day.setFirstPart2(workday.getFirstPart2());
            day.setSecondPart2(workday.getSecondPart2());
            day.setThirdPart2(workday.getThirdPart2());
            day.setFourthPart2(workday.getFourthPart2());
        }
        else if (a == 5) {
            day.setFirstPart3(workday.getFirstPart3());
            day.setSecondPart3(workday.getSecondPart3());
            day.setThirdPart3(workday.getThirdPart3());
            day.setFourthPart3(workday.getFourthPart3());
        }
    }
    //Зміна показів
    public void changeWorkDay(int id, WorkDay wDay) {
        LocalDate date = workDay.get(id).getCurrDate();

        wDay.setCurrDate(date);
        workDay.set(id, wDay);

    }
    //Додання нового запису
    public void addWorkDay(WorkDay wDay) {
        LocalDate date = workDay.get(workDay.size() - 1).getCurrDate();
        date = date.plusDays(1);
        wDay.setCurrDate(date);
        workDay.add(wDay);
    }

    public ArrayList<DataOBJ> Prediction(){
        double[] newInfo = new double[8];
        //prognoz na den
        ArrayList<DataOBJ> wd = new ArrayList<DataOBJ>();
        LocalDateTime now = LocalDateTime.now();
        LocalDate ld1 = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()+1);
        LocalTime lt1 = LocalTime.of(00,00,00);
        LocalDateTime needDate= LocalDateTime.of(ld1, lt1);
        LocalDate ld2 = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()-8);
        LocalTime lt2 = LocalTime.of(00,00,00);
        LocalDateTime startDate= LocalDateTime.of(ld2, lt2);
        LocalDate ld3 = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth()-1);
        LocalTime lt3 = LocalTime.of(00,00,00);
        LocalDateTime endDate= LocalDateTime.of(ld3, lt3);
        System.out.println(startDate + "rofl");
        LocalDateTime startDate1 = startDate.plusDays(1);
        int count = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (int i =Main.DataMass.size()-1; i > 0 ; i--){
            count++;
            if(Main.DataMass.get(startIndex).getCurrDate().isEqual(startDate)){
                startIndex=i;
                System.out.println(startIndex);
            }
        }
        for (int i = 0; i<1440 ; i++) {
            double field1 = (Main.DataMass.get(startIndex+i).getField1() + Main.DataMass.get(startIndex +i +(1* 1440)).getField1()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField1() + Main.DataMass.get(startIndex +i +(3* 1440)).getField1() + Main.DataMass.get(startIndex +i +(4* 1440)).getField1() + Main.DataMass.get(startIndex +i +(5* 1440)).getField1() + Main.DataMass.get(startIndex +i +(6* 1440)).getField1()) / 7;
            double field2 = (Main.DataMass.get(startIndex+i).getField2() + Main.DataMass.get(startIndex +i +(1* 1440)).getField2()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField2() + Main.DataMass.get(startIndex +i +(3* 1440)).getField2() + Main.DataMass.get(startIndex +i +(4* 1440)).getField2() + Main.DataMass.get(startIndex +i +(5* 1440)).getField2() + Main.DataMass.get(startIndex +i +(6* 1440)).getField2()) / 7;
            double field3 = (Main.DataMass.get(startIndex+i).getField3() + Main.DataMass.get(startIndex +i +(1* 1440)).getField3()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField3() + Main.DataMass.get(startIndex +i +(3* 1440)).getField3() + Main.DataMass.get(startIndex +i +(4* 1440)).getField3() + Main.DataMass.get(startIndex +i +(5* 1440)).getField3() + Main.DataMass.get(startIndex +i +(6* 1440)).getField3()) / 7;
            double field4 = (Main.DataMass.get(startIndex+i).getField4() + Main.DataMass.get(startIndex +i +(1* 1440)).getField4()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField4() + Main.DataMass.get(startIndex +i +(3* 1440)).getField4() + Main.DataMass.get(startIndex +i +(4* 1440)).getField4() + Main.DataMass.get(startIndex +i +(5* 1440)).getField4() + Main.DataMass.get(startIndex +i +(6* 1440)).getField4()) / 7;
            double field5 = (Main.DataMass.get(startIndex+i).getField5() + Main.DataMass.get(startIndex +i +(1* 1440)).getField5()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField5() + Main.DataMass.get(startIndex +i +(3* 1440)).getField5() + Main.DataMass.get(startIndex +i +(4* 1440)).getField5() + Main.DataMass.get(startIndex +i +(5* 1440)).getField5() + Main.DataMass.get(startIndex +i +(6* 1440)).getField5()) / 7;
            double field6 = (Main.DataMass.get(startIndex+i).getField6() + Main.DataMass.get(startIndex +i +(1* 1440)).getField6()  + Main.DataMass.get(startIndex +i +(2* 1440)).getField6() + Main.DataMass.get(startIndex +i +(3* 1440)).getField6() + Main.DataMass.get(startIndex +i +(4* 1440)).getField6() + Main.DataMass.get(startIndex +i +(5* 1440)).getField6() + Main.DataMass.get(startIndex +i +(6* 1440)).getField6()) / 7;
            wd.add(new DataOBJ(field1, field2, field3, field4, field5, field6, needDate));
            startDate = startDate.plusMinutes(1);
            needDate = needDate.plusMinutes(1);
        }
        System.out.println("lenght "+ wd.size());
        System.out.println("lenght "+ wd.get(wd.size()-1).getCurrDate());




        ArrayList<DataOBJ> wd1 = new ArrayList<DataOBJ>();
        LocalDateTime std = wd.get(0).getCurrDate();
        LocalDateTime etd = wd.get(wd.size()-1).getCurrDate();

        while(std.isBefore(etd)){
            DataOBJ dto = new DataOBJ();
            dto.setCurrDate(std);
            wd1.add(dto);
            std = std.plusHours(1);
        }

        System.out.println(wd1.size());
        for(int i = 0; i<24 ; i++){
           for (int j=0; j<1440;j++){
               if(wd.get(j).getCurrDate().getHour() == wd1.get(i).getCurrDate().getHour()){
                   wd1.get(i).setField1(wd1.get(i).getField1()+ wd.get(j).getField1());
                   wd1.get(i).setField2(wd1.get(i).getField2()+ wd.get(j).getField2());
                   wd1.get(i).setField3(wd1.get(i).getField3()+ wd.get(j).getField3());
                   wd1.get(i).setField4(wd1.get(i).getField4()+ wd.get(j).getField4());
                   wd1.get(i).setField5(wd1.get(i).getField5()+ wd.get(j).getField5());
                   wd1.get(i).setField6(wd1.get(i).getField6()+ wd.get(j).getField6());
               }

           }
            wd1.get(i).setField1(wd1.get(i).getField1()/60);
            wd1.get(i).setField2(wd1.get(i).getField2()/60);
            wd1.get(i).setField3(wd1.get(i).getField3()/60);
            wd1.get(i).setField4(wd1.get(i).getField4()/60);
            wd1.get(i).setField5(wd1.get(i).getField5()/60);
            wd1.get(i).setField6(wd1.get(i).getField6()/60);




            // double[] doubleMass = new double[7];
            //wd.get(wd.get(i).getCurrDate().getHour()).setField1();
            //if (wd.get(i).getCurrDate().getHour()

        }












        return wd1;


    }

    //Головний метод
    public static void main(String[] args) {
        launch(args);
    }
    //Відображення виключення
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
    //Генерація коду виключення
    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String s = sw.toString();
        return s;
    }
}
