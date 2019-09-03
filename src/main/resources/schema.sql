CREATE TABLE sources
(
    path        VARCHAR(400) PRIMARY KEY NOT NULL,
    type        VARCHAR(30)              NOT NULL,
    is_random   BOOLEAN                  NOT NULL,
    black_list  VARCHAR(400),
    black_url   VARCHAR(400)             NOT NULL,
    description VARCHAR(400)
);

CREATE TABLE redirect_log
(
    id               UUID PRIMARY KEY NOT NULL,
    redirect_time    TIMESTAMP        NOT NULL DEFAULT current_timestamp(),
    ip               VARCHAR(400)     NOT NULL,
    redirect_from    VARCHAR(400)     NOT NULL,
    url              VARCHAR(400)     NOT NULL,
    guid             VARCHAR(400),
    input_parameters VARCHAR(4000)
);

CREATE TABLE parameters
(
    domain    VARCHAR(400) NOT NULL,
    src_name  VARCHAR(50)  NOT NULL,
    dest_name VARCHAR(50)  NULL,
    PRIMARY KEY (domain, src_name)
);

CREATE TABLE destinations
(
    id          UUID PRIMARY KEY      NOT NULL,
    source_path VARCHAR(400)          NOT NULL REFERENCES sources (path),
    domain      VARCHAR(400)          NOT NULL,
    url         VARCHAR(400)          NOT NULL,
    by_default  BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE source_histories
(
    path VARCHAR(400),
    old  VARCHAR(4000),
    new  VARCHAR(4000)   NOT NULL,
    time TIMESTAMP(29,6) NOT NULL,
    id   INTEGER         NOT NULL
        CONSTRAINT source_histories_id_pk
            PRIMARY KEY
);
