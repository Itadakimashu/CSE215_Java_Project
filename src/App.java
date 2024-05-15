import java.util.ArrayList;

abstract class User {
    private String name;
    private String contactNumber;
    private String email;

    User(){}

    User(String name, String contactNumber, String email){
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public abstract void delete_request();
    public abstract void view_request();
    public abstract void edit_request();
    public abstract void view_profile();

    public String toString(){
        return name;
    }
    
}


class Admin extends User{

    Admin(String name, String contactNumber, String email){
        super(name,contactNumber,email);
    }

    @Override
    public void delete_request() {
        return;
    }

    @Override
    public void view_request() {
        return;
    }

    @Override
    public void edit_request() {
        return;
    }

    public void view_profile(){
        return;
    }

    
    
}

class Rider extends User{

    private String vehical;
    private String vehicalModel;
    private int numberPlate;
    private String currentLocation;

    Rider(){}

    Rider(String name,String contactNumber, String email){
        super(name,contactNumber,email);
    }
    Rider(String name, String contactNumber, String email,String vehical, String vehicalModel, int numberPlate, String currentLocation){
        super(name,contactNumber,email);
        this.vehical = vehical;
        this.vehicalModel = vehicalModel;
        this.numberPlate = numberPlate;
        this.currentLocation = currentLocation;
    }

    public void updata_location(String location){
        this.currentLocation = location;
    }


    @Override
    public void delete_request() {
        return;
    }

    @Override
    public void view_request() {
        return;
    }

    @Override
    public void edit_request() {
        return;
    }

    @Override
    public void view_profile(){
        return;
    }
    
}





public class App {
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static void main(String[] args){
        User user = new Rider("Fazly","01823333484","fazlyfardin@gmail.com");
        riders.add((Rider) user);
    
        for(Rider r: riders){
            System.out.println(r);
        }
    }
}
