import java.io.*;
import java.util.ArrayList;

import Users.*;
import GUI.*;


public class App implements Serializable{
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static String rider_file = "riders.bin";

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static String customer_file = "customers.bin";

    public static String state = "homepage";
 
    public static Gui gui = new Gui();

    public static void main(String[] args){

        load_riders_from_bin();
        load_customers_from_bin();
        
        // Customer c = new Customer();
        // Rider r = new Rider();

        User user = null;

        while (!state.equals("exit")) {
            switch (state) {
                case "homepage":
                    state = gui.homepage();
                    break;
        
                case "create_customer":
                    Customer customer = gui.create_customer();
                    if (customer == null) {
                        System.out.println("failed to create customer");
                    } else {
                        customers.add(customer);
                        save_customers_to_bin();
                    }
                    state = "homepage";
                    break;
        
                case "create_rider":
                    Rider rider = gui.create_rider();
                    if (rider == null) {
                        System.out.println("failed to create rider");
                    } else {
                        riders.add(rider);
                        save_riders_to_bin();
                    }
                    state = "homepage";
                    break;
        
                case "login_customer":
                    user = null;
                    user = gui.login(customers);
                    state = (user != null) ? "main_menu" : "homepage";
                    break;
        
                case "login_rider":
                    user = null;
                    user = gui.login(riders);
                    state = (user != null) ? "main_menu" : "homepage";
                    break;
        
                case "delete_customer":
                    state = gui.delete_user(customers);
                    save_customers_to_bin();
                    break;
        
                case "delete_rider":
                    state = gui.delete_user(riders);
                    save_riders_to_bin();
                    break;
        
                case "main_menu":
                    state = gui.main_menu(user);
                    break;
        
                case "request_ride":
                    gui.request_rider((Customer) user, riders);
                    state = "main_menu";
                    break;
        
                case "finish_ride":
                    user.finish_ride();
                    save_customers_to_bin();
                    save_riders_to_bin();
                    state = "main_menu";
                    break;
        
                case "cancel_request":
                    user.cancel_request();
                    save_customers_to_bin();
                    save_riders_to_bin();
                    state = "main_menu";
                    break;
        
                case "edit_profile":
                    gui.edit_user(user);
                    save_riders_to_bin();
                    save_customers_to_bin();
                    state = "main_menu";
                    break;
        
                case "view_ride":
                    state = gui.view_rides(user);
                    break;
        
                case "view_profile":
                    gui.view_profile(user);
                    state = "main_menu";
                    break;
        
                default:
                    System.out.println(state);
                    state = "exit";
                    break;
            }
        }
        
    } //end main


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
