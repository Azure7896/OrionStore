package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long customerId;
    private String organisationName;
    private int vatNumber;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column (unique = true, nullable = false)
    private String email;
    private int phone;
    private String address;
    private String postalCode;
    private String city;
    @Column (nullable = false)
    private boolean enabled;
    @Column (nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public User(String organisationName, int vatNumber, String firstName, String lastName, String password,
                String email, int phone, String address, String postalCode, String city, Date date) {
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

    public User(String email, String password, Date date) {
        this.password = password;
        this.email = email;
        this.date = date;
    }
}
