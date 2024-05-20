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

    public static String state = "view";

    public static Gui gui = new Gui();
    public static void main(String[] args){

        load_riders_from_bin();
        load_customers_from_bin();
        
        Customer c = customers.get(0);
        Rider r = riders.get(0);

        if(state.equals("requestRide")){
            gui.request_rider(c,riders);
            // gui.view_show(c);
            // c.finish_ride();
            // gui.view_show(r);
            save_customers_to_bin();
            save_riders_to_bin(); 
        }

        if(state.equals("edit")){
            gui.edit_user(r);
            save_riders_to_bin();
            save_customers_to_bin();
        }

        if(state.equals("view")){
            gui.view_rides(r);
        }
        if(state.equals("view_profile")){
            gui.view_profile(c);
        }


        
    }

    public static void createRider(){
        Scanner sc = new Scanner(System.in);
        String name;
        String contactNumber;
        String email;
        String currentLocation;
        System.out.println("Enter Rider name: ");
        name = sc.nextLine();
        System.out.println("Contact Number: ");
        contactNumber = sc.nextLine();
        System.out.println("Enter Email: ");
        email = sc.nextLine();
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
