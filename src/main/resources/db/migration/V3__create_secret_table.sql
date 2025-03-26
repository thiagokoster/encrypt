CREATE TABLE IF NOT EXISTS secret (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(2048),
    username VARCHAR(255),
    encrypted_password TEXT NOT NULL,
    vault_id UUID NOT NULL,

    CONSTRAINT fk_vault FOREIGN KEY (vault_id)
        REFERENCES vault(id)
        ON DELETE CASCADE
);