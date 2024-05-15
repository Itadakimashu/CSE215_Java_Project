import java.io.*;
import java.util.ArrayList;

abstract class User implements Serializable{
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

class Customer extends User{



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
    public void view_profile() {
        return;
    }
    
}





public class App implements Serializable{
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static String rider_file = "riders.bin";
    public static void main(String[] args){
        
        load_riders_from_bin();
        for(Rider r: riders){
            System.out.println(r);
        }
    }

    public static void save_riders_to_bin(){
        try {
			FileOutputStream fos = new FileOutputStream(rider_file);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(riders);
            System.out.println(riders + "\nhave been saved to the file");
			os.close();
			
		}
		catch(IOException e){
			System.out.println("An error occured");
			e.printStackTrace();
		}
    }

    @SuppressWarnings("unchecked")
    public static void load_riders_from_bin(){
        try{
			FileInputStream fis = new FileInputStream(rider_file);
			ObjectInputStream is = new ObjectInputStream(fis);

			riders = (ArrayList<Rider>) is.readObject();
            System.out.println("The array of objects "+ riders + " has been loaded to riders");
            is.close();
			
		}
		catch(IOException | ClassNotFoundException IoCnfe){
			System.out.println("An error occured");
			IoCnfe.printStackTrace();
		}
    }
}
