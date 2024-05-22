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
        Rider u = null;
        for(Rider x: riders){
            if (!x.ride_status_ongoing() && currentLocation.equals(x.getLocation())){
                u = x;
            }
        }

        if (u == null){
            System.out.println("No rider found");
            return;
        }

        currentRide = u.accept_ride(this,currentLocation,toLocation);
        System.out.println("going from " + currentRide.fromLocation + " to " + currentRide.toLocation);
    }

    public void finish_ride(){
        currentRide.update("Finished");
        currentRide.rider.updataLocation(currentRide.toLocation);
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
            String data[][] = {{"","", "", "","", ""}};
            return data;
        }
        String data[][] = {{"1",currentRide.rider.toString(), currentRide.fromLocation, currentRide.toLocation, String.valueOf(currentRide.fare), currentRide.progress}};
        System.out.println("from: " + currentRide.fromLocation);
        System.out.println("To: " + currentRide.toLocation);
        System.out.println("Rider: " + currentRide.rider);
        return data;

    }

    @Override
    public void edit_request(Object editedCustomer) {
        Customer u = (Customer)editedCustomer;
        if(this.getName() != u.getName() && !u.getName().isEmpty()){
            this.setName(u.getName());
        }

        if(this.getContactNumber() != u.getContactNumber() && !u.getContactNumber().isEmpty()){ 
            this.setContactNumber(u.getContactNumber());
        }
        
        if(this.getEmail() != u.getEmail() && !u.getEmail().isEmpty()){
            this.setEmail(u.getEmail());
        }
        
        System.out.println("Updated Customer information");
    }

    @Override
    public boolean ride_status_ongoing() {
       if(currentRide == null) return false;
       return true;
    }
    
}
