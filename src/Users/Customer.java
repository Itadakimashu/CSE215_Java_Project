package Users;

import java.util.ArrayList;

public class Customer extends User{

    private double wallet;
    private Ride currentRide;

    public Customer(){
        this.wallet = 0;
    }
    public Customer(String name, String contactNumber, String email){
        super(name,contactNumber,email);
        this.wallet = 0;
    }
    public Customer(String name, String contactNumber, String email, double initial_amount){
        super(name,contactNumber,email);
        this.wallet = initial_amount;
    }

    public void deposit(double amount){
        this.wallet += amount;
    }

    public void request_ride(ArrayList<Rider> riders,String currentLocation, String toLocation){
        Rider c = null;
        for(Rider x: riders){
            if (currentLocation.equals(x.getLocation())){
                c = x;
            }
        }

        if (c == null){
            System.out.println("No rider found");
            return;
        }

        currentRide = c.accept_ride(this,currentLocation,toLocation);
        System.out.println("going from " + currentRide.fromLocation + " to " + currentRide.toLocation);
    }

    public void finish_ride(){
        currentRide.update("finished");
        currentRide.rider.finish_ride(currentRide);
        wallet -= currentRide.fare;
        currentRide = null;
        System.out.println("Ride finished");

    }

    @Override
    public void delete_request() {
        currentRide.update("Cancelled");
        currentRide = null;
        System.out.println("The ride has been cancelled.");
    }

    @Override
    public String[][] view_request() {
        
        if(currentRide == null){
            System.out.println("Not currently on a ride.");
            return null;
        }
        String data[][] = {{"1",currentRide.rider.toString(), currentRide.fromLocation, currentRide.toLocation, String.valueOf(currentRide.fare), currentRide.progress}};
        System.out.println("from: " + currentRide.fromLocation);
        System.out.println("To: " + currentRide.toLocation);
        System.out.println("Rider: " + currentRide.rider);
        return data;

    }

    @Override
    public void edit_request(Object editedCustomer) {
        Customer c = (Customer)editedCustomer;
        if(this.getName() != c.getName() && !c.getName().isEmpty()){
            this.setName(c.getName());
        }

        if(this.getContactNumber() != c.getContactNumber() && !c.getContactNumber().isEmpty()){ 
            this.setContactNumber(c.getContactNumber());
        }
        
        if(this.getEmail() != c.getEmail() && !c.getEmail().isEmpty()){
            this.setEmail(c.getEmail());
        }
        
        System.out.println("Updated Customer information");
    }
    
}
