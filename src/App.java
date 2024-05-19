import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Users.*;

public class App implements Serializable{
    public static ArrayList<Rider> riders = new ArrayList<Rider>();
    public static String rider_file = "riders.bin";
    public static void main(String[] args){

        load_riders_from_bin();
        // createRider();
        System.out.println(riders);

        Customer c = new Customer("fazly","01823421","fazly@gmail.com");
        c.request_ride(riders, "Gaza", "West Bank");
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
}
