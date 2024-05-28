package Users;

import java.util.ArrayList;

public class Rider extends User{

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

    public Rider(String name, String contactNumber, String email, String currentLocation,char[] pass){
        super(name,contactNumber,email,pass);
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
        rides.add(ride);
        return ride;
    }

    public void finish_ride(){
        Ride ride = rides.get(rides.size()-1);
        if(ride.progress.equals("Ongoing")){
            rides.get(rides.size()-1).update("Finished");
            updataLocation(ride.toLocation);
        }
        
    }


    @Override
    public void cancel_request() {
        Ride ride = rides.get(rides.size()-1);
        if(ride.progress.equals("Ongoing")){
            ride.update("cancelled");
            ride.customer.cancel_request();
        }
        else{
            System.out.println("no ongoing rides");
        }
    }

    @Override
    public String[][] view_request() {
        if(rides.size() == 0){
            System.out.println("The Rider did not make any rides yet.");
            String data[][] = {};
            return data;
        }
        String data[][] = new String[rides.size()][6];
        for(int i = 0; i < rides.size(); i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = rides.get(i).customer.toString();
            data[i][2] = rides.get(i).fromLocation;
            data[i][3] = rides.get(i).toLocation;
            data[i][4] = String.valueOf(rides.get(i).fare);
            data[i][5] = rides.get(i).progress;
        }
        return data;
    }

    public Ride last_ride() {
        return rides.get(rides.size()-1);
    }

    @Override
    public boolean ride_status_ongoing() {
        if(rides.size() > 0 && last_ride().progress.equals("Ongoing")) return true;   
        return false;
    }



    
}
