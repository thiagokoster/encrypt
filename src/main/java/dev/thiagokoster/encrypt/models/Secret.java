package dev.thiagokoster.encrypt.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Secret {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String url;

    private String username;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @ManyToOne
    @JoinColumn(name = "vault_id", nullable = false)
    private Vault vault;

    public Secret(String name, String url, String username, String encryptedPassword, Vault vault) {
        this.name = name;
        this.url = url;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.vault = vault;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Vault getVault() {
        return vault;
    }
}