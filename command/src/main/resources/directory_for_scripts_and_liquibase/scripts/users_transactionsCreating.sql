-- liquibase formatted sql

-- changeset formatted ityapkin:5

CREATE TABLE users_transactions (
    user_id UUID PRIMARY KEY,
    debit_amount DECIMAL(10, 2) DEFAULT 0,
    invest_amount DECIMAL(10, 2) DEFAULT 0,
    credit_amount DECIMAL(10, 2) DEFAULT 0,
    saving_amount DECIMAL(10, 2) DEFAULT 0
);
