package Users;


public class Admin extends User{

    public Admin(String name, String contactNumber, String email){
        super(name,contactNumber,email);
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

    public void view_profile(){
        return;
    }

    
    
}
