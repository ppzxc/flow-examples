DROP TABLE IF EXISTS member, member_role, role, member_authorization, company, business_number, scenario, sequence, business_message CASCADE;

CREATE TABLE IF NOT EXISTS member
(
    id               BIGSERIAL                NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified_at TIMESTAMP WITH TIME ZONE NOT NULL,
    email            VARCHAR(64)              NOT NULL,
    password         VARCHAR(128)             NOT NULL,
    name             VARCHAR(64)              NOT NULL,
    authorization_id BIGINT,
    company_id       BIGINT,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_role
(
    member_id BIGINT NOT NULL,
    role_id   BIGINT NOT NULL,
    CONSTRAINT pk_member_role PRIMARY KEY (member_id, role_id)
);

CREATE TABLE IF NOT EXISTS role
(
    id        BIGSERIAL    NOT NULL,
    type      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_authorization
(
    id                      BIGSERIAL NOT NULL,
    account_non_expired     BOOLEAN   NOT NULL,
    account_non_locked      BOOLEAN   NOT NULL,
    credentials_non_expired BOOLEAN   NOT NULL,
    enabled                 BOOLEAN   NOT NULL,
    CONSTRAINT pk_member_authorization PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS company
(
    id          BIGSERIAL    NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS business_number
(
    id          BIGSERIAL   NOT NULL,
    number      VARCHAR(32) NOT NULL,
    contry_code VARCHAR(8)  NOT NULL,
    company_id  BIGINT,
    CONSTRAINT pk_business_number PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS scenario
(
    id                 BIGSERIAL    NOT NULL,
    sequence_id        BIGINT,
    kt_id              VARCHAR(255) NOT NULL,
    type               VARCHAR(255) NOT NULL,
    customer_number    VARCHAR(255) NOT NULL,
    service_name       VARCHAR(255) NOT NULL,
    business_number_id BIGINT,
    CONSTRAINT pk_scenario PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sequence
(
    id               BIGSERIAL NOT NULL,
    message_sequence BIGINT    NOT NULL,
    CONSTRAINT pk_sequence PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS business_message
(
    id          BIGSERIAL NOT NULL,
    scenario_id BIGINT,
    CONSTRAINT pk_business_message PRIMARY KEY (id)
);

ALTER TABLE business_message
    ADD CONSTRAINT FK_BUSINESS_MESSAGE_ON_SCENARIO FOREIGN KEY (scenario_id) REFERENCES scenario (id);

ALTER TABLE scenario
    ADD CONSTRAINT FK_SCENARIO_ON_BUSINESS_NUMBER FOREIGN KEY (business_number_id) REFERENCES business_number (id);

ALTER TABLE scenario
    ADD CONSTRAINT FK_SCENARIO_ON_SEQUENCE FOREIGN KEY (sequence_id) REFERENCES sequence (id);

ALTER TABLE business_number
    ADD CONSTRAINT uc_business_number_number UNIQUE (number);

ALTER TABLE business_number
    ADD CONSTRAINT FK_BUSINESS_NUMBER_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE member
    ADD CONSTRAINT uc_member_email UNIQUE (email);

ALTER TABLE member
    ADD CONSTRAINT FK_MEMBER_ON_AUTHORIZATION FOREIGN KEY (authorization_id) REFERENCES member_authorization (id);

ALTER TABLE member
    ADD CONSTRAINT FK_MEMBER_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE member_role
    ADD CONSTRAINT fk_memrol_on_member FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE member_role
    ADD CONSTRAINT fk_memrol_on_role FOREIGN KEY (role_id) REFERENCES role (id);