import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ThreadForAutoGetData implements Runnable{
    public void run(){
        new ThreadForAutoGetData().go();
    }
    public void go(){

        // на клиенте принять данній запрос, после чего обратно отправить данніе по затратам в флоате, и тут в тредране принять и обработать
        try {
            while (true) {
                GregorianCalendar gcalendar = new GregorianCalendar();
                int time = gcalendar.get(Calendar.HOUR_OF_DAY);
                int time2 = gcalendar.get(Calendar.MINUTE);
                int time3 ;

                time3 = gcalendar.get(Calendar.SECOND);
                //Внимательно пересмотреть тред слип
                Thread.sleep(1*1000);
                System.out.println(time3);
                if (time3 == 0){

                    System.out.println("testTiming"+ Main.clientOutputStreams.size());
                    Main.DataMass.add(new DataOBJ());
                    Main.DataMass.get(Main.DataMass.size()-1).setCurrDate(LocalDateTime.now());
                    System.out.println(Main.DataMass.get(0).getField1() + "field1");
                    System.out.println(Main.DataMass.get(0).getField3() + "field3");
                    System.out.println(Main.DataMass.get(0).getPower() + "TEC");
                    System.out.println(Main.DataMass.get(0).getField2() + "field2");
                    System.out.println(Main.DataMass.get(0).getField4() + "field4");
                    System.out.println(Main.DataMass.get(0).getPower() + "TEC");
                    System.out.println(Main.DataMass.get(0).getField5() + "field5");
                    System.out.println(Main.DataMass.get(0).getField6() + "field6");
                    System.out.println(Main.DataMass.get(0).getPower() + "TEC");

                    for (int i = 0; i < PasswordMass.massConn.length; i++){
                        if (PasswordMass.massConn[i]== true){
                            Main.newOutputStreams[i].writeObject("GetDataObj");
                            Main.newOutputStreams[i].flush();
                        }
                    }
                    System.out.println("testTimingstop");
                    //Thread.sleep(60*1000);


                }

            }


        }
        catch(Exception ex){
        }
    }
}
