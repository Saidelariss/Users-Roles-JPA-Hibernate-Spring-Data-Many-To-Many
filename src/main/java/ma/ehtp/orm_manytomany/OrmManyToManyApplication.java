package ma.ehtp.orm_manytomany;

import ma.ehtp.orm_manytomany.entities.Role;
import ma.ehtp.orm_manytomany.entities.User;
import ma.ehtp.orm_manytomany.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class OrmManyToManyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrmManyToManyApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return  args-> {
            User user= new User();
            user.setUsername("said");
            user.setPassword("1234");
            userService.addNewUser(user);

            User user1= new User();
            user1.setUsername("admin");
            user1.setPassword("Pass@rd");
            userService.addNewUser(user1);

            Stream.of("STUDENT","USER","ADMIN")
                    .forEach(r->{
                        Role role = new Role();
                        role.setRoleName(r);
                        userService.addNewRole(role);
                    });
            userService.addRoleToUser("said","STUDENT");
            userService.addRoleToUser("said","USER");
            userService.addRoleToUser("admin","ADMIN");
            userService.addRoleToUser("admin","USER");

            try {
                User u=userService.autheticate("said","1234");
                System.out.println(u.getUserId());
                System.out.println(u.getUsername());
                System.out.println("Roles==>");
                u.getRoles().forEach(r->{
                    System.out.println("role====>"+r);
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }



        };
    }
}
