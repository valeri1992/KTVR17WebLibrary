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
            this.deleteRoleToUser(ur.getReader());
            userRolesFacade.create(ur);
            Role addNewRole = roleFacade.findRoleByName("USER");
            UserRoles addedNewRoles = new UserRoles(ur.getReader(),addNewRole);
            userRolesFacade.create(addedNewRoles);
        }else if(ur.getRole().getName().equals("USER")){
           
            userRolesFacade.create(ur);
        }
        }
   
   public void deleteRoleToUser(Reader user){
            List<UserRoles> deleteUserRoles = userRolesFacade.findByUser(user);
            int n = deleteUserRoles.size();
           for(int i=0;i<n;i++){
               userRolesFacade.remove(deleteUserRoles.get(i));
            }
    }
//    public String isRole(String roleName, HttpServletReguest request){
//      SecureLogic sl=new SecureLogic();
//      Reader regUser=(Reader)
//        int n = listUserRoles.size();
//       for(int i = 0; i < n; i++){
//           if(listUserRoles.get(i).getRole().equals(role)){
//                return true;
//            }
//       }
//        return false;
//    }
    /**
     * Проходим по списку userRoles, который содержит список ролей пользователя
     *  Проходим по списку ролей и при соответствии роли ADMIN возвращаем (return) роль
     *  Проходим по списку ролей и при соответствии роли USER возвращаем (return) роль
     * Если никто не вернул роль, то возвращает null
     * @param 
     * @return top role of user
     */
   public String getRole(Reader regUser){
        List<UserRoles> listUserRoles = userRolesFacade.findByUser(regUser);
//        List<Role>listRoles = roleFacade.findAll();
//        int n = userRoles.size();
        int n = listUserRoles.size();
        
        for(int i=0;i<n;i++){
            if( "ADMIN".equals(listUserRoles.get(i).getRole().getName())){
                return listUserRoles.get(i).getRole().getName();
            }
        }
       for(int i=0;i<n;i++){
            if("USER".equals(listUserRoles.get(i).getRole().getName())){
                return listUserRoles.get(i).getRole().getName();
            }
        }
//            public void deleteRoleToUser(Reader regUser){
//        List<UserRoles> listUserRoles = userRolesFacade.findByUser(user);
//        int n = listUserRoles.size();
//        for(int i=0; i<n; i++){
//            userRolesFacade.remove(listUserRoles.get(i));
//        }
        
   return null;
   }
}
    
        
    


