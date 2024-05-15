import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void setName(String name){
        this.name = name;
    }
    
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email){
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

    private ArrayList<Ride> rides;

    Rider(){
        rides = new ArrayList<Ride>();
    }

    Rider(String name,String contactNumber, String email){
        super(name,contactNumber,email);
        rides = new ArrayList<Ride>();
    }
    Rider(String name, String contactNumber, String email,String vehical, String vehicalModel, int numberPlate, String currentLocation){
        super(name,contactNumber,email);
        this.vehical = vehical;
        this.vehicalModel = vehicalModel;
        this.numberPlate = numberPlate;
        this.currentLocation = currentLocation;

        rides = new ArrayList<Ride>();
    }

    public void updataLocation(String location){
        this.currentLocation = location;
    }

    public String getLocation(){
        return this.currentLocation;
    }

    public Ride accept_ride(String currentLocation, String destination){
        Ride ride = new Ride(currentLocation,destination);
        rides.add(ride);
        return ride;
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

    private double wallet;
    private Ride currentRide;

    Customer(){
        this.wallet = 0;
    }
    Customer(String name, String contactNumber, String email){
        super(name,contactNumber,email);
        this.wallet = 0;
    }
    Customer(String name, String contactNumber, String email, double initial_amount){
        super(name,contactNumber,email);
        this.wallet = initial_amount;
    }

    public void deposit(double amount){
        this.wallet += amount;
    }

    public void request_ride(ArrayList<Rider> riders,String currentLocation, String destination){
        Rider r = null;
        for(Rider x: riders){
            if (currentLocation.equals(x.getLocation())){
                r = x;
            }
        }

        if (r == null){
            System.out.println("No rider found");
            return;
        }

        currentRide = r.accept_ride(currentLocation,destination);
        System.out.println("going from " + currentRide.fromLocation + " to " + currentRide.destination);
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
    public void view_profile() {
        return;
    }
    
}


class Ride{
    String fromLocation;
    String destination;
    double fare;
    public Ride(String fromLocation, String destination) {
        this.fromLocation = fromLocation;
        this.destination = destination;
    }
    
}




public class App implements Serializable{
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static String rider_file = "riders.bin";
    public static void main(String[] args){
        load_riders_from_bin();

        Customer c = new Customer("fazly","01823421","fazly@gmail.com");
        c.request_ride(riders, "Asdad", "West Bank");
    }

    public static void createRider(){
        Scanner sc = new Scanner(System.in);
        String name;
        String contactNumber;
        String email;
        String vehical;
        String vehicalModel;
        int numberPlate;
        String currentLocation;
        System.out.println("Enter Rider name: ");
        name = sc.nextLine();
        System.out.println("Contact Number: ");
        contactNumber = sc.nextLine();
        System.out.println("Enter Email: ");
        email = sc.nextLine();
        System.out.println("Enter vechial: ");
        vehical = sc.nextLine();
        System.out.println("Enter vechial model: ");
        vehicalModel = sc.nextLine();
        System.out.println("Enter number plate: ");
        numberPlate = sc.nextInt();
        System.out.println("Enter current location: ");
        sc.nextLine();
        currentLocation = sc.nextLine();
        Rider r = new Rider(name, contactNumber, email, vehical, vehicalModel, numberPlate, currentLocation);
        riders.add(r);
        save_riders_to_bin();
        sc.close();
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
