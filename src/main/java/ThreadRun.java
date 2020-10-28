import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
// класс поток для


// Main запускает данный поток , в котором создаются новые потоки при подключении к сокету
//run -> go
//в go обрабатывается присоединение сокета+ создается лист clientOutputStreams для обратной связи
//Также запускается новый поток в который передается сокет
//go->ClientHandler(Socket clientSocket) создает объекты для чтения
//run (из ClientHandler) обрабатывает прочитанный объект
public class ThreadRun implements Runnable {

    private Main mainApp;
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    public void run(){
        new ThreadRun().go();
    }
    public void go(){
        Main.clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = serverSock.accept();
                ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
                Main.clientOutputStreams.add(writer);
                Thread t = new Thread(new ThreadRun.ClientHandler(clientSocket));
                t.start();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        showAlertConnect();
                    }
                });
            }
        }
        catch(Exception ex){
        }
    }
    class ClientHandler implements  Runnable{
        BufferedReader reader;
        ObjectInputStream readerOb;
        Socket sock;
        public ClientHandler(Socket clientSocket){
            try{

                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                readerOb = new ObjectInputStream(clientSocket.getInputStream());
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            try{
                Object ob1;
                while((ob1 = readerOb.readObject())!= null) {

                    if (Main.permission != false) {
                        if (ob1 instanceof WorkDay) {
                            WorkDay workDayFromField = new WorkDay();
                            workDayFromField = (WorkDay) ob1;
                            if (Main.workDay.get(Main.workDay.size()-1).getMainEnergy() == true && workDayFromField.getMainEnergy() == true) {
                                Main.addDataFromField(workDayFromField, 1);
                                Main.workDay.get(Main.workDay.size()-1).setMainEnergy(false);
                            } else if (Main.workDay.get(Main.workDay.size()-1).getGreenEnergy() == true && workDayFromField.getGreenEnergy() == true) {
                                Main.addDataFromField(workDayFromField, 2);
                                Main.workDay.get(Main.workDay.size()-1).setGreenEnergy(false);
                            } else if (Main.workDay.get(Main.workDay.size()-1).getField1() == true && workDayFromField.getField1() == true) {
                                Main.addDataFromField(workDayFromField, 3);
                                Main.workDay.get(Main.workDay.size()-1).setField1(false);
                            } else if (Main.workDay.get(Main.workDay.size()-1).getField2() == true && workDayFromField.getField2() == true) {
                                Main.addDataFromField(workDayFromField, 4);
                                Main.workDay.get(Main.workDay.size()-1).setField2(false);
                            } else if (Main.workDay.get(Main.workDay.size()-1).getField3() == true && workDayFromField.getField3() == true) {
                                Main.addDataFromField(workDayFromField, 5);
                                Main.workDay.get(Main.workDay.size()-1).setField3(false);
                            }
                        }
                        else if (ob1 instanceof String) {
                            String k;
                            k = (String) ob1;
                            Main.showMessage(k);
                        }
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    private void showAlertConnect () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message to director");
        alert.setHeaderText("Message:");
        alert.setContentText("1 PC was connected");

        alert.showAndWait();
    }
}
