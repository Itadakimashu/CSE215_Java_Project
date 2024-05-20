package Users;

import java.util.ArrayList;

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
    public void edit_request() {
        return;
    }

    public void view_profile(){
        return;
    }

    
    
}
