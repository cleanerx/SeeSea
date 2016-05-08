

-- depthsensor
CREATE TABLE depthsensor (
    vesselconfigid INTEGER NOT NULL,
    x numeric NULL,
    y numeric NULL,
    z numeric NULL,
    sensorid varchar (100) NULL,
    manufacturer varchar (100) NULL,
    model varchar (100) NULL,
    frequency numeric NULL,
    angleofbeam numeric  NULL,
    offsetkeel numeric NULL,
    offsettype varchar (12) NULL
);

CREATE TABLE user_tracks (
    track_id INTEGER NOT NULL,
    user_name varchar (40) NOT NULL,
    file_ref varchar (255) NULL,
    upload_state INTEGER NULL,
    filetype varchar (80) NULL,
    compression varchar (80) NULL,
    containertrack INTEGER NULL,
    vesselconfigid INTEGER NULL,
    license INTEGER NULL,
    gauge_name varchar (100) NULL,
    gauge numeric  NULL,
    height_ref varchar (100) NULL,
    comment varchar (1024) NULL,
    watertype varchar (20) NULL,
    uploaddate timestamp NULL,
);

CREATE TABLE user_profiles (
    user_name character varying(256) NOT NULL,
    password character varying(40),
    salt character varying(10),
    attempts smallint DEFAULT 0 NOT NULL,
    last_attempt timestamp without time zone,
    forename VARCHAR (100),
    surname VARCHAR (100),
    country VARCHAR (100),
    language VARCHAR (100),
    organisation VARCHAR (100),
    phone VARCHAR (100),
    acceptedemailcontact boolean DEFAULT false
);

INSERT INTO user_profiles(user_name)  VALUES ( 'test@test.de');

INSERT INTO user_tracks(track_id,file_ref,upload_state,vesselconfigid,license,uploaddate, user_name)  VALUES ('1', 'track1.dat', '8', '1', '1', NULL, 'test@test.de');
INSERT INTO user_tracks(track_id,file_ref,upload_state,vesselconfigid,license,uploaddate, user_name, containertrack)  VALUES ('2', 'track2.dat', '3', '1', '1', NULL, 'test@test.de', 1);
