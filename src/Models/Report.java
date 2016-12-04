package Models;

/**
 * Created by Ranchy on 12/4/2016.
 */
public class Report {
    private String name;
    private Integer number;
    private double rate;
    private String major;
    public Report(String name, Integer number, double rate, String major){
        this.name = name;
        this.number = number;
        this.rate = rate;
        this.major = major;
    }
    public String getName(){
        return name;
    }
    public Integer getNumber(){
        return number;
    }
    public double getRate(){
        return rate;
    }
    public String getMajor(){
        return major;
    }
}
