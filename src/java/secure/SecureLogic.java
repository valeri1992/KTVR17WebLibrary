/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.RoleFacade;
import session.UserRolesFacade;

/**
 *
 * @author pupil
 */
public class SecureLogic {

    private UserRolesFacade userRolesFacade;
private RoleFacade roleFacade;
    public SecureLogic() {
        Context context;
        try {

            context = new InitialContext();
            this.userRolesFacade = (UserRolesFacade) context.lookup("java:module/UserRolesFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(SecureLogic.class.getName()).log(Level.SEVERE, "Не удалось найти Bin", ex);
        }
    }

    public void addRoleToUser(UserRoles ur) {
        if (ur.getRole().getName().equals("ADMIN")) {
            userRolesFacade.create(ur);
            Role addNewRole = roleFacade.findRoleByName("USER");
            UserRoles addedNewRoles=new UserRoles(ur.getReader(),addNewRole);
            userRolesFacade.create(addedNewRoles);
        } else {
            (ur.getRole().equals("USER")){
        
            userRolesFacade.create(ur);

        }
        }
    }
}

