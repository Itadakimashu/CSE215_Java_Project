package Users;

public class Admin extends User{
    private int password;
    public Admin(int password){
        super("admin","991","admistrator@email.com");
        this.password = password;
    }

    @Override
    public void delete_request() {
        
    }

    @Override
    public String[][] view_request() {
        return null;
    }

    @Override
    public void edit_request(Object editedCustomer) {
        Admin ad = (Admin)editedCustomer;
        if(this.getName() != ad.getName() && ad.getName() != "") 
            this.setName(ad.getName());

        if(this.getContactNumber() != ad.getContactNumber() && ad.getContactNumber() != "") 
            this.setContactNumber(ad.getContactNumber());
        
        if(this.getEmail() != ad.getEmail() && ad.getEmail() != "")
            this.setEmail(ad.getEmail());
        
        System.out.println("Updated Admin infromation");
    }
 
    
}
