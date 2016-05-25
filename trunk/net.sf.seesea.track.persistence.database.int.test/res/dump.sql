


-- depthsensor
CREATE TABLE depthsensor (
    vesselconfigid integer  NOT NULL,
    x numeric (5,2) NULL,
    y numeric (5,2) NULL,
    z numeric (5,2) NULL,
    sensorid varchar (100)   NULL,
    manufacturer varchar (100) NULL,
    model varchar (100) NULL,
    frequency numeric (5,0) NULL,
    angleofbeam numeric (3,0) NULL,
    offsetkeel numeric (5,2) NULL,
    offsettype varchar (12) NULL
);


-- gauge
CREATE TABLE gauge (
    id integer  NOT NULL,
    name varchar (255) NOT NULL,
    gaugetype varchar (10) NULL,
    lat numeric (11,3) NULL,
    lon numeric (11,3) NULL,
    provider varchar (100) NULL,
    water varchar (100) NULL,
    remoteid varchar (100) NULL,
    waterlevel numeric (6,2) NULL
);
ALTER TABLE gauge ADD CONSTRAINT gauge_pkey PRIMARY KEY (id);


-- gaugemeasurement
CREATE TABLE gaugemeasurement (
    gaugeid integer  NOT NULL,
    value numeric (4,2) NOT NULL,
    time timestamp  NOT NULL
);


-- license
CREATE TABLE license (
    name varchar (255) NOT NULL,
    shortname varchar (16) NULL,
    "public" integer  NULL,
    id integer  NOT NULL,
    user_name varchar (255) NULL
);
ALTER TABLE license ADD CONSTRAINT license_pkey PRIMARY KEY (id);


-- sbassensor
CREATE TABLE sbassensor (
    vesselconfigid integer  NOT NULL,
    x numeric (5,2) NULL,
    y numeric (5,2) NULL,
    z numeric (5,2) NULL,
    sensorid varchar (100) NULL,
    manufacturer varchar (100) NULL,
    model varchar (100) NULL
);


-- user_profiles
CREATE TABLE user_profiles (
    user_name varchar (256) NOT NULL,
    password varchar (40) NULL,
    salt varchar (10) NULL,
    attempts smallint  NOT NULL,
    last_attempt timestamp NULL,
    forename varchar (100) NULL,
    surname varchar (100) NULL,
    country varchar (100) NULL,
    language varchar (100) NULL,
    organisation varchar (100) NULL,
    phone varchar (100) NULL,
    acceptedemailcontact integer  NULL
);
ALTER TABLE user_profiles ADD CONSTRAINT user_profiles_pkey PRIMARY KEY (user_name);


-- user_tracks
CREATE TABLE user_tracks (
    track_id bigint  NOT NULL,
    user_name varchar (40) NOT NULL,
    file_ref varchar (255) NULL,
    upload_state smallint  NULL,
    filetype varchar (80) NULL,
    compression varchar (80) NULL,
    containertrack integer  NULL,
    vesselconfigid integer  NULL,
    license integer  NULL,
    gauge_name varchar (100) NULL,
    gauge numeric (6,2) NULL,
    height_ref varchar (100) NULL,
    comment varchar (100)  NULL,
    watertype varchar (20) NULL,
    uploaddate timestamp NULL,
);
ALTER TABLE user_tracks ADD CONSTRAINT user_tracks_pkey PRIMARY KEY (track_id);


-- userroles
CREATE TABLE userroles (
    user_name varchar (250) NULL,
    role varchar (15) NULL
);


-- vesselconfiguration
CREATE TABLE vesselconfiguration (
    id integer  NOT NULL,
    name varchar (100)  NULL,
    description varchar (100)  NULL,
    user_name varchar (100)  NULL,
    mmsi varchar (20) NULL,
    manufacturer varchar (100) NULL,
    model varchar(100)  NULL,
    loa numeric (7,2) NULL,
    breadth numeric (7,2) NULL,
    draft numeric (4,2) NULL,
    height numeric (4,2) NULL,
    displacement numeric (8,1) NULL,
    maximumspeed numeric (3,1) NULL,
    "type" integer  NULL
);
ALTER TABLE vesselconfiguration ADD CONSTRAINT vesselconfiguration_pkey PRIMARY KEY (id);

INSERT INTO user_profiles(user_name, attempts)  VALUES ( 'test@test.de', 1);

INSERT INTO user_tracks(track_id,file_ref,upload_state,vesselconfigid,license,uploaddate, user_name)  VALUES ('1', 'track1.dat', '1', '1', '1', NULL, 'test@test.de');

INSERT INTO vesselconfiguration(id,loa, breadth, user_name)  VALUES ('1', '5.00', '1.95', 'test@test.de');

INSERT INTO depthsensor(vesselconfigid,x, y, z, sensorid)  VALUES ('1', '-0.8', '0.1','-0.1', 'DBT');

INSERT INTO sbassensor(vesselconfigid,x, y, sensorid)  VALUES ('1', '-0.8', '0.1', 'DBT');
