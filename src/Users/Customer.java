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

        currentRide = r.accept_ride(this,currentLocation,toLocation);
        System.out.println("going from " + currentRide.fromLocation + " to " + currentRide.toLocation);
    }

    @Override
    public void delete_request() {
        return;
    }

    @Override
    public void view_request() {
        System.out.println("Rider: ");
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
