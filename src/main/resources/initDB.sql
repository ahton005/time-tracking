CREATE TABLE IF NOT EXISTS time_track
(
    id    BIGSERIAL PRIMARY KEY ,
    method_name  VARCHAR(200) NOT NULL ,
    exec_time NUMERIC(20, 3) NOT NULL,
    status VARCHAR(30) NOT NULL,
    group_method VARCHAR(30) NOT NULL
);