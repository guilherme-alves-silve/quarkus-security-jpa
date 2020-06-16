package br.com.guilhermealvessilve.security.jpa.data;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@UserDefinition
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_tbl")
@EqualsAndHashCode(callSuper = true)
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Username
    private String username;

    @Password
    private String password;

    @Roles
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static void add(
            final String username,
            final String password,
            final String roles
    ) {
        final var encryptedPassword = BcryptUtil.bcryptHash(password);
        final var user = new User(username, encryptedPassword, roles);
        user.persist();
    }

    public static User findByUsername(String name) {
        return User.find("username", name)
                .firstResult();
    }
}
