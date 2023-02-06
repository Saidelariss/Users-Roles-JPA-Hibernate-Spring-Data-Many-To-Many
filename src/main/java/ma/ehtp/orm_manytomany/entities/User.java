package ma.ehtp.orm_manytomany.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="USERS")
public class User {
    @Id
    private String userId;
    @Column(name ="USER_Name", unique = true, length = 20)
    private String username;

    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)//a chaque fois que je demande de charger un user automatiquement il va charger aussi ces roles (ce qui est logique )
    private List<Role> roles = new ArrayList<>();
}
