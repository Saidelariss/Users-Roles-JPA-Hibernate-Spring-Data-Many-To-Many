package ma.ehtp.orm_manytomany.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.ehtp.orm_manytomany.entities.Role;
import ma.ehtp.orm_manytomany.entities.User;
import ma.ehtp.orm_manytomany.repositories.RoleRepository;
import ma.ehtp.orm_manytomany.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
//@AllArgsConstructor (constructeur avec parametres)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    //attention il faut pas utiliser un autre constructeur par défaut , parce que spring doit trouver un seul constructeur pour faire l'injection de dépendances

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());


        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);

    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(roleName);

        user.getRoles().add(role);
        role.getUsers().add(user);//relation est bidirectionnelle
        userRepository.save(user);//vous n'avez pas besoin de faire ça , dès que vous changez vos objets , ces changements vont être faites aussi dans la base de données


    }

    @Override
    public User autheticate(String userName, String password) {
        User user=userRepository.findByUsername(userName);
        if(user==null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad cre");
    }
}
