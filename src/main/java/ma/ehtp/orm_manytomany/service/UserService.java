package ma.ehtp.orm_manytomany.service;

import ma.ehtp.orm_manytomany.entities.Role;
import ma.ehtp.orm_manytomany.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username,String roleName);//ça suppose que roleName et userName sont uniques
    User autheticate(String userName, String password);


}
