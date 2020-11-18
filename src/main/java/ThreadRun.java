import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                System.out.println("GERE");
                /*
                try {
                    System.out.println("GERE1");
                    boolean tag1 = true;
                    Object ob1;

                    while((ob1 = new ObjectInputStream(clientSocket.getInputStream()).readObject())!= null) {
                        String loggin = (String) ob1;
                        System.out.println(loggin);
                        System.out.println("GERE2.0");
                        ob1 = null;
                    }
                    System.out.println("GERE2");


                } catch (Exception ex){

                }
                */
                System.out.println("GERE3");




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
        ObjectOutputStream writer;
        Socket sock;
        public ClientHandler(Socket clientSocket){
            try{
                sock = clientSocket;
                writer = new ObjectOutputStream(sock.getOutputStream());
                readerOb = new ObjectInputStream(sock.getInputStream());
                Main.clientOutputStreams.add(writer);

                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            try{
                Object ob1;
                int id = -1;
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
                            String k = (String) ob1;
                            System.out.println(k);
                            int connMass = PasswordMass.massConn.length;
                            if (id == -1){
                                System.out.println("-1");
                                for (int i = 0; i<connMass; i++ ){
                                    if (k.equals(PasswordMass.massLog[i]+PasswordMass.massPass[i])){
                                        if(PasswordMass.massConn[i]==true){
                                            System.out.println("already exist");
                                        }else
                                        if(PasswordMass.massConn[i] == false){
                                            Main.newOutputStreams[i]=writer;
                                            Main.newInputStreams[i]= readerOb;
                                            System.out.println("get" + i);
                                            id = i;
                                            PasswordMass.massConn[i] = true;
                                        }
                                    }
                                }
                                if (id == -1){
                                    try {
                                        sock.close();

                                    } catch (Exception ex) {
                                    }
                                }
                            } else

                            if (k.length() > 3) {
                                Main.showMessage("Цех " + id +": " + k);
                                String comm = k.substring(0, 4);
                                System.out.println(comm);

                                if (comm.equals("quit")) {
                                    try {
                                        System.out.println("before");
                                        PasswordMass.massConn[id]= false;
                                            System.out.println( "after");
                                        sock.close();
                                    } catch (Exception ex) {

                                    }
                                }
                            }else
                            if (k.length() < 4) {
                                Main.showMessage("Цех " + id +": " + k);
                            }

                        }else if (ob1 instanceof Double){
                            DataOBJ currentOBJ = Main.DataMass.get(Main.DataMass.size()-1);
                            double k = (double) ob1;
                            System.out.print(k);
                            switch (id) {
                                case 1: currentOBJ.setField1(k);
                                    break;
                                case 2: currentOBJ.setField2(k);
                                    break;
                                case 3: currentOBJ.setField3(k);
                                    break;
                                case 4: currentOBJ.setField4(k);
                                    break;
                                case 5: currentOBJ.setField5(k);
                                    break;
                                case 6: currentOBJ.setField6(k);
                                    break;

                                case 7: currentOBJ.setPower(k);
                                    k=0;
                                    LocalDateTime now = LocalDateTime.now();
                                    LocalDateTime lct = now.minusMonths(3);
                                    LocalDate ld = LocalDate.of(lct.getYear(), lct.getMonth(), lct.getDayOfMonth());
                                    LocalTime lt = LocalTime.of(8,00,00);
                                    LocalDateTime start= LocalDateTime.of(ld, lt);
                                    if(lt.getHour()>19 && lt.getHour()<8 ) {
                                        if (k > 0) {
                                            if (Main.DataMass.get(Main.DataMass.size() - 2).getBattary() < 1120 - 5) {
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary() + 5);
                                            }
                                        } else if (k == 0) {
                                            double summ = currentOBJ.getField1() + currentOBJ.getField2() + currentOBJ.getField3() + currentOBJ.getField4() + currentOBJ.getField5() + currentOBJ.getField6();
                                            if (summ <= Main.DataMass.get(Main.DataMass.size() - 2).getBattary()) {
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary() - summ);
                                            }else {
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary());
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        showAlertRED();
                                                    }
                                                });
                                            }
                                        }
                                    } else if(lt.getHour()<=19 && lt.getHour()>= 8 ) {
                                        if (k > 0) {
                                            if (Main.DataMass.get(Main.DataMass.size() - 2).getBattary() < 400 - 5) {
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary() +5 );
                                            } else if (Main.DataMass.get(Main.DataMass.size() - 2).getBattary() > 400 + 5) {
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary() -5 );
                                            }
                                        }
                                        else if (k == 0) {
                                            System.out.println("221 stroka");
                                            double summ = currentOBJ.getField1() + currentOBJ.getField2() + currentOBJ.getField3() + currentOBJ.getField4() + currentOBJ.getField5() + currentOBJ.getField6();
                                            System.out.println(summ + "223 stroka");
                                            if (summ <= Main.DataMass.get(Main.DataMass.size() - 2).getBattary()) {
                                                System.out.println("225 stroka");
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary() - summ);
                                            }else if (summ > Main.DataMass.get(Main.DataMass.size() - 2).getBattary())  {
                                                System.out.println("228 stroka");
                                                currentOBJ.setBattary(Main.DataMass.get(Main.DataMass.size() - 2).getBattary());
                                                Platform.runLater(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        showAlertRED();
                                                    }
                                                });
                                            }
                                        }
                                    }








                                    break;

                                default:
                                    break;
                            }

                        }

                            //Main.DataMass[id]
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
    private void showAlertRED () {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message to director");
        alert.setHeaderText("Message:");
        alert.setContentText("ТЕЦ відключена, заряду батареї не вистачає");

        alert.showAndWait();
    }
}
