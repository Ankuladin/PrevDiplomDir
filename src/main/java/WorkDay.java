import java.io.Serializable;
import java.time.LocalDate;
public class WorkDay implements Serializable {
    private double firstPart1= 0;
    private double secondPart1= 0;
    private double thirdPart1 = 0;
    private double fourthPart1=0;
    private double firstPart2=0;
    private double secondPart2=0;
    private double thirdPart2=0;
    private double fourthPart2=0;
    private double firstPart3=0;
    private double secondPart3=0;
    private double thirdPart3=0;
    private double fourthPart3=0;
    private double currBatteryFirstPart=0;
    private double currBatterySecondPart=0;
    private double currBatteryThirdPart=0;
    private double currBatteryFourthPart=0;
    private double firstEnSourse=0;
    private double secondEnSourse=0;
    private double thirdEnSourse=0;
    private double fourthEnSourse=0;
    private String date;
    private LocalDate currDate;
    private boolean field1 = false;
    private boolean field2 = false;
    private boolean field3 = false;
    private boolean mainEnergy = false;
    private boolean greenEnergy = false;
    // Getters and Setters
    public boolean getField3() {
        return field3;
    }
    public void setField3(boolean field3) {
        this.field3 = field3;
    }
    public boolean getField2() {
        return field2;
    }
    public void setMainEnergy(boolean mainEnergy) {
        this.mainEnergy = mainEnergy;
    }
    public boolean getMainEnergy() {
        return mainEnergy;
    }
    public void setGreenEnergy(boolean greenEnergy) {
        this.greenEnergy = greenEnergy;
    }
    public boolean getGreenEnergy() {
        return greenEnergy;
    }
    public void setField2(boolean field2) {
        this.field2 = field2;
    }
    public boolean getField1() {
        return field1;
    }
    public void setField1(boolean field1) {
        this.field1 = field1;
    }
    public double getFirstPart1() {
        return firstPart1;
    }
    public void setFirstPart1(double firstPart1) {
        this.firstPart1 = firstPart1;
    }
    public double getFirstPart2() {
        return firstPart2;
    }
    public void setFirstPart2(double firstPart2) {
        this.firstPart2 = firstPart2;
    }
    public double getFirstPart3() {
        return firstPart3;
    }
    public void setFirstPart3(double firstPart3) {
        this.firstPart3 = firstPart3;
    }
    public double getSecondPart1() {
        return secondPart1;
    }
    public void setSecondPart1(double secondPart1) {
        this.secondPart1 = secondPart1;
    }
    public double getSecondPart2() {
        return secondPart2;
    }
    public void setSecondPart2(double secondPart2) {
        this.secondPart2 = secondPart2;
    }
    public double getSecondPart3() {
        return secondPart3;
    }
    public void setSecondPart3(double secondPart3) {
        this.secondPart3 = secondPart3;
    }
    public double getThirdPart1() {
        return thirdPart1;
    }
    public void setThirdPart1(double thirdPart1) {
        this.thirdPart1 = thirdPart1;
    }
    public double getThirdPart2() {
        return thirdPart2;
    }
    public void setThirdPart2(double thirdPart2) {
        this.thirdPart2 = thirdPart2;
    }
    public double getThirdPart3() {
        return thirdPart3;
    }
    public void setThirdPart3(double thirdPart3) {
        this.thirdPart3 = thirdPart3;
    }
    public double getFourthPart1() {
        return fourthPart1;
    }
    public void setFourthPart1(double fourthPart1) {
        this.fourthPart1 = fourthPart1;
    }
    public double getFourthPart2() {
        return fourthPart2;
    }
    public void setFourthPart2(double fourthPart2) {
        this.fourthPart2 = fourthPart2;
    }
    public double getFourthPart3() {
        return fourthPart3;
    }
    public void setFourthPart3(double fourthPart3) {
        this.fourthPart3 = fourthPart3;
    }
    public LocalDate getCurrDate(){ return  currDate;}
    public void setCurrDate(LocalDate currDate) { this.currDate = currDate; }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public double getCurrBatteryFirstPart() {
        return currBatteryFirstPart;
    }
    public void setCurrBatteryFirstPart(double currBatteryFirstPart) {
        this.currBatteryFirstPart = currBatteryFirstPart;
    }
    public double getCurrBatterySecondPart() {
        return currBatterySecondPart;
    }
    public void setCurrBatterySecondPart(double currBatterySecondPart) {
        this.currBatterySecondPart = currBatterySecondPart;
    }
    public double getCurrBatteryThirdPart() {
        return currBatteryThirdPart;
    }
    public void setCurrBatteryThirdPart(double currBatteryThirdPart) {
        this.currBatteryThirdPart = currBatteryThirdPart;
    }
    public double getCurrBatteryFourthPart() {
        return currBatteryFourthPart;
    }
    public void setCurrBatteryFourthPart(double currBatteryFourthPart) {
        this.currBatteryFourthPart = currBatteryFourthPart;
    }
    public double getFirstEnSourse() {
        return firstEnSourse;
    }
    public void setFirstEnSourse(double firstEnSourse) {
        this.firstEnSourse = firstEnSourse;
    }
    public double getSecondEnSourse() {
        return secondEnSourse;
    }
    public void setSecondEnSourse(double secondEnSourse) {
        this.secondEnSourse = secondEnSourse;
    }
    public double getThirdEnSourse() {
        return thirdEnSourse;
    }
    public void setThirdEnSourse(double thirdEnSourse) {
        this.thirdEnSourse = thirdEnSourse;
    }
    public double getFourthEnSourse() {
        return fourthEnSourse;
    }
    public void setFourthEnSourse(double fourthEnSourse) {
        this.fourthEnSourse = fourthEnSourse;
    }
    //Constructors
    //Default
    public WorkDay(){

    }
    //With data
    public WorkDay(double firstPart1, double secondPart1, double thirdPart1, double fourthPart1, double firstPart2, double secondPart2,
                   double thirdPart2, double fourthPart2, double firstPart3, double secondPart3, double thirdPart3, double fourthPart3,
                   double currBatteryFirst, double currBatterySecond, double currBatteryThird, double currBatteryFourth,  double firstEnSourse, double secondEnSourse, double thirdEnSourse, double fourthEnSourse,  String date){
        setFirstPart1(firstPart1);
        setSecondPart1(secondPart1);
        setThirdPart1(thirdPart1);
        setFourthPart1(fourthPart1);
        setFirstPart2(firstPart2);
        setSecondPart2(secondPart2);
        setThirdPart2(thirdPart2);
        setFourthPart2(fourthPart2);
        setFirstPart3(firstPart3);
        setSecondPart3(secondPart3);
        setThirdPart3(thirdPart3);
        setFourthPart3(fourthPart3);
        setFirstEnSourse(firstEnSourse);
        setSecondEnSourse(secondEnSourse);
        setThirdEnSourse(thirdEnSourse);
        setFourthEnSourse(fourthEnSourse);
        setCurrBatteryFirstPart(currBatteryFirst);
        setCurrBatterySecondPart(currBatterySecond);
        setCurrBatteryThirdPart(currBatteryThird);
        setCurrBatteryFourthPart(currBatteryFourth);
        setCurrDate(LocalDate.parse(date));

    }
}
