package HES.Scanners;

import java.util.Random;

public class Scanner {
    public String name;
    public Scanner(String name){
        this.name = name;
    }
    public String getStatus(){
        Random rand = new Random();
        int value = rand.nextInt(20);   // 1 to 20
        if (value==1)return name+" scanner has environmental issues affecting calibration";
        else return (name+" scanner OK");
    }
}
