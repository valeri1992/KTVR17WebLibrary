/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import entity.Reader;
import java.util.List;
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

   public void addRoleToUser(UserRoles ur){//добавление ролей пользователей
        if(ur.getRole().getName().equals("ADMIN")){
            this.deleteRoleToUser(ur);
            userRolesFacade.create(ur);
            Role addNewRole = roleFacade.findRoleByName("USER");
            UserRoles addedNewRoles = new UserRoles(ur.getReader(),addNewRole);
            userRolesFacade.create(addedNewRoles);
        }else if(ur.getRole().getName().equals("USER")){
            if(!this.isRole(ur.getReader(), ur.getRole().getName()))
            userRolesFacade.create(ur);
        }
        }
   
   public void deleteRoleToUser(UserRoles ur){
            List<UserRoles> deleteUserRoles = userRolesFacade.findByReader(ur.getReader());
            int n = deleteUserRoles.size();
           for(int i=0;i<n;i++){
               userRolesFacade.remove(deleteUserRoles.get(i));
            }
    }
    public boolean isRole(Reader reader, String roleName){
        List<UserRoles> listUserRoles = userRolesFacade.findByReader(reader);
        Role role = roleFacade.findRoleByName(roleName);
        int n = listUserRoles.size();
        for(int i = 0; i < n; i++){
            if(listUserRoles.get(i).getRole().equals(role)){
                return true;
            }
        }
        return false;
    }
    /**
     * Проходим по списку userRoles, который содержит список ролей пользователя
     *  Проходим по списку ролей и при соответствии роли ADMIN возвращаем (return) роль
     *  Проходим по списку ролей и при соответствии роли USER возвращаем (return) роль
     * Если никто не вернул роль, то возвращает null
     * @param user
     * @return top role of user
     */
    public Role getRole(Reader user){
        List<UserRoles> userRoles = userRolesFacade.findByReader(user);
        List<Role>listRoles = roleFacade.findAll();
        int n = userRoles.size();
        int k = listRoles.size();
        for(int i=0;i<n;i++){
            for(int j=0;j<k;j++){
                if(listRoles.get(j).equals(userRoles.get(i).getRole()) && "ADMIN".equals(listRoles.get(j).getName())){
                    return listRoles.get(j);
                }
            }
            for(int j=0;j<k;j++){
                if(listRoles.get(j).equals(userRoles.get(i).getRole()) && "USER".equals(listRoles.get(j).getName())){
                    return listRoles.get(j);
                }
            }
        }
        return null;
    }
        
    
}

