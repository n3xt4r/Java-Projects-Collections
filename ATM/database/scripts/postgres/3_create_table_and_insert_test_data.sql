--psql -U learnjava_my_atm -d atm
CREATE TABLE atm (
atm_id serial PRIMARY KEY,
customer_account_nr INTEGER UNIQUE NOT NULL,
pin_nr INTEGER NOT NULL,
created_on TIMESTAMP NOT NULL
);

INSERT INTO atm (customer_account_nr, pin_nr, created_on)
VALUES
('952141', '191904', '2023-01-01 12:00:00'),
('989947', '717976', '2023-01-02 14:30:00');