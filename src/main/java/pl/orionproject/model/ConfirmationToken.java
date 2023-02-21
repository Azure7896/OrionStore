package pl.orionproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String confirmationToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;

    public ConfirmationToken(User user) {
        this.createdDate = new Date();
        this.user = user;
        this.confirmationToken = UUID.randomUUID().toString();
    }
}
