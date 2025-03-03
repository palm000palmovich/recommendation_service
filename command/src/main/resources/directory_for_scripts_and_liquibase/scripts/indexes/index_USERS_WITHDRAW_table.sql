-- liquibase formatted sql

-- changeset formatted ityapkin:17

CREATE UNIQUE INDEX idx_id_withdraw ON USERS_WITHDRAWS (user_id);