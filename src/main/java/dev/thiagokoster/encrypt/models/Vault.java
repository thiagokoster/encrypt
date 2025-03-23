package dev.thiagokoster.encrypt.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Vault {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vault() {}
    public Vault(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public User getUserId() { return user; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setUserId(User user) { this.user = user; }
}
