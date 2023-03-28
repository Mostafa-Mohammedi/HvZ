package no.noroff.HvZ.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;

/**
 * The user represent a user in the Hvz Game
 */

@Entity
@Data
@Table(name = "users")
public class User {

    /**
     * A unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username from keycloak
     */
    private String username;

    /**
     * Bearer token from keycloak
     */

    private String idToken;
}
