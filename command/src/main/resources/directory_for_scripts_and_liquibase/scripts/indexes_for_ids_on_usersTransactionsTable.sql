-- liquibase formatted sql

-- changeset formatted ityapkin:6

CREATE INDEX user_id_index ON users_transactions(user_id)