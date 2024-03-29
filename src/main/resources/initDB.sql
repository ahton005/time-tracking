CREATE TABLE IF NOT EXISTS time_track
(
    id    BIGSERIAL PRIMARY KEY ,
    method_name  VARCHAR(200) NOT NULL ,
    exec_time BIGSERIAL NOT NULL
);