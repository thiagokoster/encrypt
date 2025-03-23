package dev.thiagokoster.encrypt.services;

import com.password4j.Password;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    public String hashPassword(String password) {
        return Password.hash(password).addRandomSalt().withScrypt().getResult();
    }

    public boolean verify(String password, String hashed_password) {
        return Password.check(password, hashed_password).withScrypt();
    }
}
