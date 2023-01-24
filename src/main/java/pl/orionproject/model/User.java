package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String organisationName;
    @Column (length = 15)
    private String vatNumber;
    @Column (nullable = false, length = 50)
    private String firstName;
    @Column (nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 255)
    private String password;
    @Column (unique = true, nullable = false, length = 62)
    private String email;
    @Column (unique = true, length = 9)
    private String phone;
    @Column (length = 95)
    private String address;
    @Column (length = 6)
    private String postalCode;
    @Column (length = 35)
    private String city;
    @Column (nullable = false)
    private boolean enabled;
    @Column (nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public User(String organisationName, String vatNumber, String firstName, String lastName, String password,
                String email, String phone, String address, String postalCode, String city, Date date) {
        this.organisationName = organisationName;
        this.vatNumber = vatNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.enabled = false;
        this.date = date;
    }

    public User(String firstName, String lastName, String password, String email, Date date) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.password = password;
        this.email = email;
        this.date = date;
    }
}

