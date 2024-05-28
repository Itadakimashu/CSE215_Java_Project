package Users;

import java.io.Serializable;

import ExceptionError.ExceptionError;

public abstract class User implements Serializable{
    private String name;
    private String contactNumber;
    private String email;
    private char[] pass;

    User(){}

    User(String name, String contactNumber, String email){
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    User(String name, String contactNumber, String email,char[] pass){
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.pass = pass;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return this.name;
    }
    public String getContactNumber(){
        return this.contactNumber;
    }
    public String getEmail(){
        return this.email;
    }

    public void setPass(char[] pass){
        this.pass = pass;
    }

    public char[] getPass(){
        return pass;
    }

    public void edit_request(User user) throws ExceptionError{
        if(user.getName().isEmpty() && user.getContactNumber().isEmpty() && user.getEmail().isEmpty()) 
            throw new ExceptionError("All the edit fields can't be null.");
        if(this.getName() != user.getName() && !user.getName().isEmpty()){
            this.setName(user.getName());
        }

        if(this.getContactNumber() != user.getContactNumber() && !user.getContactNumber().isEmpty()){ 
            this.setContactNumber(user.getContactNumber());
        }
        
        if(this.getEmail() != user.getEmail() && !user.getEmail().isEmpty()){
            this.setEmail(user.getEmail());
        }
    }

    public abstract void cancel_request();
    public abstract String[][] view_request();
    public abstract void finish_ride();
    public abstract boolean ride_status_ongoing();
    public String toString(){
        return name;
    }
    
}