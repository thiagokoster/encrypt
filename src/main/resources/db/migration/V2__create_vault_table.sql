CREATE TABLE IF NOT EXISTS vault (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    user_id UUID NOT NULL,

    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);