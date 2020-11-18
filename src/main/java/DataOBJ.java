import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class DataOBJ {
    private LocalDateTime currDate;
    private GregorianCalendar calendar;
    private double field1 = 0;
    private double field2 = 0;
    private double field3 = 0;
    private double field4 = 0;
    private double field5 = 0;
    private double field6 = 0;
    private double power= 0;
    private double battary = 0;

    public DataOBJ(){

    }
    public DataOBJ(Double f1, Double f2, Double f3, Double f4, Double f5, Double f6, Double p, Double b, String ldt){
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
        this.field4 = f4;
        this.field5 = f5;
        this.field6 = f6;
        this.power = p;
        this.battary = b;
        System.out.println(ldt);
        System.out.println(Integer.parseInt(ldt.substring(0, 4)));
        System.out.println(Integer.parseInt(ldt.substring(5, 7)));
        System.out.println(Integer.parseInt(ldt.substring(8, 10)));
        System.out.println(Integer.parseInt(ldt.substring(11, 13)));
        System.out.println(Integer.parseInt(ldt.substring(14, 16)));
        System.out.println(Integer.parseInt(ldt.substring(17, 19)));

        LocalDate ld = LocalDate.of(Integer.parseInt(ldt.substring(0, 4)), Integer.parseInt(ldt.substring(5, 7)), Integer.parseInt(ldt.substring(8, 10)));
        LocalTime lt = LocalTime.of(Integer.parseInt(ldt.substring(11, 13)), Integer.parseInt(ldt.substring(14, 16)), Integer.parseInt(ldt.substring(17, 19)));
        this.currDate = LocalDateTime.of(ld, lt);
        /*
        System.out.println(Integer.parseInt(ldt.substring(0, 3)));
        System.out.println(Integer.parseInt(ldt.substring(5, 6)));
        System.out.println(Integer.parseInt(ldt.substring(8, 9)));
        System.out.println(Integer.parseInt(ldt.substring(11, 12)));
        System.out.println(Integer.parseInt(ldt.substring(14, 15)));
        System.out.println(Integer.parseInt(ldt.substring(17, 18)));
        LocalTime lt = LocalTime.of(Integer.parseInt(ldt.substring(11, 12)), Integer.parseInt(ldt.substring(14, 15)), Integer.parseInt(ldt.substring(17, 18)));
      //  this.currDate = LocalDateTime.of(ld, lt);
      */
    }
    public DataOBJ(Double f1, Double f2, Double f3, Double f4, Double f5, Double f6, LocalDateTime ldt){
        this.field1 = f1;
        this.field2 = f2;
        this.field3 = f3;
        this.field4 = f4;
        this.field5 = f5;
        this.field6 = f6;
        System.out.println(ldt);

        this.currDate = ldt;
        /*
        System.out.println(Integer.parseInt(ldt.substring(0, 3)));
        System.out.println(Integer.parseInt(ldt.substring(5, 6)));
        System.out.println(Integer.parseInt(ldt.substring(8, 9)));
        System.out.println(Integer.parseInt(ldt.substring(11, 12)));
        System.out.println(Integer.parseInt(ldt.substring(14, 15)));
        System.out.println(Integer.parseInt(ldt.substring(17, 18)));
        LocalTime lt = LocalTime.of(Integer.parseInt(ldt.substring(11, 12)), Integer.parseInt(ldt.substring(14, 15)), Integer.parseInt(ldt.substring(17, 18)));
      //  this.currDate = LocalDateTime.of(ld, lt);
      */
    }

    public GregorianCalendar getCurrDateCalendar(){ return  calendar;}
    public void setCurrDateCalendar(GregorianCalendar calendar) { this.calendar = calendar; }

    public LocalDateTime getCurrDate(){ return  currDate;}
    public void setCurrDate(LocalDateTime currDate) { this.currDate = currDate; }

    public double getField1(){ return  field1;}
    public void setField1(double field1) { this.field1 = field1; }
    public double getField2(){ return  field2;}
    public void setField2(double field2) { this.field2 = field2; }
    public double getField3(){ return  field3;}
    public void setField3(double field3) { this.field3 = field3; }
    public double getField4(){ return  field4;}
    public void setField4(double field4) { this.field4 = field4; }
    public double getField5(){ return  field5;}
    public void setField5(double field5) { this.field5 = field5; }
    public double getField6(){ return  field6;}
    public void setField6(double field6) { this.field6 = field6; }

    public double getPower(){ return  power;}
    public void setPower(double power) { this.power = power; }
    public double getBattary(){ return  battary;}
    public void setBattary(double battary) { this.battary = battary; }
}
