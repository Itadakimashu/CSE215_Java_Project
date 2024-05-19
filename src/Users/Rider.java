package Users;

import java.util.ArrayList;

public class Rider extends User{

    private String vehical;
    private String vehicalModel;
    private int numberPlate;
    private String currentLocation;

    private ArrayList<Ride> rides;

    public Rider(){
        rides = new ArrayList<Ride>();
    }

    public Rider(String name,String contactNumber, String email){
        super(name,contactNumber,email);
        rides = new ArrayList<Ride>();
    }

    public Rider(String name,String contactNumber, String email, String currentLocation){
        super(name,contactNumber,email);
        this.currentLocation = currentLocation;
        rides = new ArrayList<Ride>();
    }

    public Rider(String name, String contactNumber, String email,String vehical, String vehicalModel, int numberPlate, String currentLocation){
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
        if (this.currentLocation == "")
            return "Location not set";
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
