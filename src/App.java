import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Users.*;
import GUI.*;

public class App implements Serializable{
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static String rider_file = "riders.bin";

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static String customer_file = "customers.bin";

    public static Gui gui = new Gui();
    public static void main(String[] args){

        // createRider();
        load_riders_from_bin();
        load_customers_from_bin();
        Customer c = customers.get(0);
        Rider r = riders.get(0);

        // c.request_ride(riders, "Gaza", "West Bank");
        // c.finish_ride();

        

        // c.request_ride(riders, "Utopia", "West Bank");
        // String data[][] = c.view_request();
        // c.finish_ride();

        // r.updataLocation("West Bank");

        // System.out.println(r.getLocation());

        // save_riders_to_bin();
        
        String data[][] = r.view_request();
        gui.view_show(data,"Customer");
        
    }

    public static void createRider(){
        Scanner sc = new Scanner(System.in);
        String name;
        String contactNumber;
        String email;
        // String vehical;
        // String vehicalModel;
        // int numberPlate;
        String currentLocation;
        System.out.println("Enter Rider name: ");
        name = sc.nextLine();
        System.out.println("Contact Number: ");
        contactNumber = sc.nextLine();
        System.out.println("Enter Email: ");
        email = sc.nextLine();
        // System.out.println("Enter vechial: ");
        // vehical = sc.nextLine();
        // System.out.println("Enter vechial model: ");
        // vehicalModel = sc.nextLine();
        // System.out.println("Enter number plate: ");
        // numberPlate = sc.nextInt();
        System.out.println("Enter current location: ");
        currentLocation = sc.nextLine();
        Rider r = new Rider(name, contactNumber, email, currentLocation);
        riders.add(r);
        save_riders_to_bin();
        sc.close();
    }


    public static void save_riders_to_bin(){
        try {
			FileOutputStream fos = new FileOutputStream(rider_file,false);
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


    public static void save_customers_to_bin(){
        try {
            FileOutputStream fos = new FileOutputStream(customer_file,false);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(customers);
            System.out.println(customers + "\nhave been saved to the file");

            os.close();
        }
        catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void load_customers_from_bin(){
        try{
            FileInputStream fis = new FileInputStream(customer_file);
            ObjectInputStream is = new ObjectInputStream(fis);
            customers = (ArrayList<Customer>) is.readObject();
            System.out.println("The array of objects "+ customers + " has been loaded to customers");
            is.close();
        }
        catch(IOException | ClassNotFoundException IoCnfe){
            System.out.println("An error occured");
            IoCnfe.printStackTrace();
        }
        
    }
}
