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

    public Ride accept_ride(Customer customer,String currentLocation, String destination){
        Ride ride = new Ride(customer,this,currentLocation,destination);
        return ride;
    }

    public void finish_ride(Ride ride){
        rides.add(ride);
        updataLocation(ride.toLocation);
    }


    @Override
    public void delete_request() {
        return;
    }

    @Override
    public String[][] view_request() {
        if(rides.size() == 0){
            return null;
        }
        String data[][] = new String[rides.size()][6];
        for(int i = 0; i < rides.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = rides.get(i).customer.toString();
            data[i][2] = rides.get(i).fromLocation;
            data[i][3] = rides.get(i).toLocation;
            data[i][4] = String.valueOf(rides.get(i).fare);
            data[i][5] = rides.get(i).progress;
            System.out.println(rides.get(i).progress);
            // System.out.println(data[i]);
        }
        return data;
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
