package Users;
import ExceptionError.*;

import java.util.ArrayList;


public class Customer extends User{

    private Ride currentRide;

    public Customer(){

    }
    public Customer(String name, String contactNumber, String email){
        super(name,contactNumber,email);

    }


    public void request_ride(ArrayList<Rider> riders,String currentLocation, String toLocation) throws ExceptionError{
        
        if(currentLocation.equals(toLocation)) throw new ExceptionError("Current location and destination can't be same");
        
        Rider u = null;
        for(Rider x: riders){
            if (!x.ride_status_ongoing() && currentLocation.equals(x.getLocation())){
                u = x;
            }
        }

        if (u == null){
            throw new ExceptionError("Currently there are no riders avaialble in " + currentLocation);
        }

        currentRide = u.accept_ride(this,currentLocation,toLocation);
        System.out.println("going from " + currentRide.fromLocation + " to " + currentRide.toLocation);
    }

    public void finish_ride(){
        currentRide.update("Finished");
        currentRide.rider.updataLocation(currentRide.toLocation);

        currentRide = null;
        System.out.println("Ride finished");

    }

    @Override
    public void cancel_request() {
        currentRide.update("Cancelled");
        currentRide = null;
        System.out.println("The ride has been cancelled.");
    }

    @Override
    public String[][] view_request() {
        
        if(currentRide == null){
            return null;
        }
        String data[][] = {{"1",currentRide.rider.toString(), currentRide.fromLocation, currentRide.toLocation, String.valueOf(currentRide.fare), currentRide.progress}};
        System.out.println("from: " + currentRide.fromLocation);
        System.out.println("To: " + currentRide.toLocation);
        System.out.println("Rider: " + currentRide.rider);
        return data;

    }

    @Override
    public boolean ride_status_ongoing() {
       if(currentRide == null) return false;
       return true;
    }
    
}
