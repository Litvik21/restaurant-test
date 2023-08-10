--liquibase formatted sql
--changeset <litvik>:<create-table-orders>
CREATE TABLE IF NOT EXISTS orders
(
    id bigint auto_increment,
    product varchar(255) not null,
    order_function varchar(255) not null,
    user_id BIGINT,
    CONSTRAINT orders_pk PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );
--rollback DROP TABLE videos;