package sample.Main;

/**
 * Created by Allen on 11/6/2016.
 */
public class User {
    private String user_name;
    private String password;
    private String GT_email;
    private String year;
    private String Major_name;
    private String UserType;
    public User(String user_name, String GT_email,String password){
        this.user_name = user_name;
        this.GT_email = GT_email;
        this.password = password;
        UserType = "Student";
    }
    public void setMajor_name(String major_name){
        this.Major_name = major_name;
    }
    public void setYear(String year){
        this.year = year;
    }
    public String getUser_name(){
        return user_name;
    }
    public String getPassword(){
        return password;
    }
    public String getGT_email(){
        return GT_email;
    }
    public String getYear(){
        return year;
    }
    public String getMajor_name(){
        return Major_name;
    }
    public String getUserType(){
        return UserType;
    }

}
