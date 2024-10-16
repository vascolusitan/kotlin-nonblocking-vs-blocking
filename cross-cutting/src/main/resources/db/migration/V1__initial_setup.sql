CREATE TABLE IF NOT EXISTS person (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

INSERT INTO person (name, age) VALUES
   ('Vasco', 27),
   ('Rita', 26),
   ('Jojo', 35),
   ('Mafalda', 28);
