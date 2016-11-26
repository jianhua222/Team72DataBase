package Models;

/**
 * Created by Allen on 11/26/2016.
 */
public class Result {
    private String name;
    private String type;
    public Result(String name, String type){
        this.name = name;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String toString(){
        return name+", " + type;
    }
}
