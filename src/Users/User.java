package Users;

import java.io.Serializable;

abstract class User implements Serializable{
    private String name;
    private String contactNumber;
    private String email;

    User(){}

    User(String name, String contactNumber, String email){
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
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

    public abstract void delete_request();
    public abstract String[][] view_request();
    public abstract void edit_request();
    public abstract void view_profile();

    public String toString(){
        return name;
    }
    
}