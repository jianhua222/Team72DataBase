package Models;

/**
 * Created by Ranchy on 12/4/2016.
 */
public class PopularProject {
    private String name;
    private Integer number;
    public PopularProject(String name , Integer number){
        this.name = name;
        this.number = number;

    }
    public String getName(){
        return name;
    }
    public  Integer getNumber(){
        return number;
    }
}
