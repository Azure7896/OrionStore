package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String organisationName;
    @Column(length = 15)
    private String vatNumber;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(unique = true, nullable = false, length = 62)
    private String email;
    @Column(unique = true, length = 9)
    private String phone;
    @Column(length = 95)
    private String address;
    @Column(length = 6)
    private String postalCode;
    @Column(length = 35)
    private String city;
    @Column(nullable = false)
    private boolean isActive;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private List<Role> roles;

    public User(String firstName, String lastName, String password, String email, Date date, boolean enabled, List<Role> roles, String phone, String city, String vatNumber,
                String organisationName, String postalCode, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.date = date;
        this.roles = roles;
        this.phone = phone;
        this.city = city;
        this.vatNumber = vatNumber;
        this.organisationName = organisationName;
        this.postalCode = postalCode;
        this.address = address;
    }
}

