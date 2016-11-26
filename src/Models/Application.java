package Models;

/**
 * Created by Allen on 11/26/2016.
 */
public class Application {
    private String date;
    private String  name;
    private String status;
    public Application(String date, String name, String status){
        this.date = date;
        this.name = name;
        this.status = status;
    }
    public String getDate(){
        return date;
    }
    public String getName(){
        return name;
    }
    public String getStatus(){
        return status;
    }
}
