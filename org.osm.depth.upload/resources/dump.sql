--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: depthsoffset; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE depthsensor (
    vesselconfigid integer NOT NULL,
    x numeric(5,2),
    y numeric(5,2),
    z numeric(5,2),
    sensorid character varying,
    manufacturer character varying(100),
    model character varying(100),
    frequency numeric(5,0),
    angleofbeam numeric(3,0),
    offsetkeel numeric(5,2)
);


ALTER TABLE public.depthsensor OWNER TO osm;

--
-- Name: depthsoffset_vesselconfigid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY depthsensor
    ADD CONSTRAINT depthsoffset_vesselconfigid_fkey FOREIGN KEY (vesselconfigid) REFERENCES vesselconfiguration(id) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Name: sbasoffset; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE sbasoffset (
    vesselconfigid integer,
    x numeric(5,2) NOT NULL,
    y numeric(5,2) NOT NULL,
    z numeric(5,2) NOT NULL,
    sensorid character varying,
    manufacturer character varying(100),
    model character varying(100)
);


ALTER TABLE public.sbasoffset OWNER TO osm;

--
-- Name: track2file; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE track2file (
    trackid bigint,
    "order" integer,
    trackfileid bigint
);


ALTER TABLE public.track2file OWNER TO osm;

--
-- Name: trackdata; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE trackdata (
    track_id integer,
    sentence character varying(16),
    sensorid character varying(10),
    updaterate integer
);


ALTER TABLE public.trackdata OWNER TO osm;

--
-- Name: user_profiles; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE user_profiles (
    user_name character varying(40) NOT NULL,
    password character varying(40),
    salt character varying(10),
    attempts smallint DEFAULT 0 NOT NULL,
    last_attempt timestamp without time zone
);


ALTER TABLE public.user_profiles OWNER TO osm;

--
-- Name: user_tracks; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE user_tracks (
    track_id integer NOT NULL,
    user_name character varying(40) NOT NULL,
    file_ref character varying(255),
    upload_state smallint DEFAULT 0,
    filetype character varying(80),
    compression character varying(80),
    starttime timestamp without time zone,
    endtime timestamp without time zone,
    containertrack integer,
    vesselconfigid integer,
    license integer
      CONSTRAINT license_fkey FOREIGN KEY (license)
      REFERENCES license (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


ALTER TABLE public.user_tracks OWNER TO osm;

--
-- Name: user_tracks_track_id_seq; Type: SEQUENCE; Schema: public; Owner: osm
--

CREATE SEQUENCE user_tracks_track_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_tracks_track_id_seq OWNER TO osm;

--
-- Name: userroles; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE userroles (
    user_name character varying(250),
    role character varying(15)
);


ALTER TABLE public.userroles OWNER TO osm;

CREATE TABLE vesselconfiguration (
    id integer NOT NULL,
    name character varying,
    description character varying,
    user_name character varying,
    mmsi character varying(20),
    manufacturer character varying(100),
    model character varying,
    loa numeric(7,2),
    breadth numeric(7,2),
    draft numeric(4,2),
    height numeric(4,2),
    displacement numeric(8,1),
    maximumspeed numeric(3,1),
    type integer
);



ALTER TABLE public.vesselconfiguration OWNER TO osm;

--
-- Name: vesselconfiguration_id_seq; Type: SEQUENCE; Schema: public; Owner: osm
--

CREATE SEQUENCE vesselconfiguration_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vesselconfiguration_id_seq OWNER TO osm;

--
-- Name: vesselconfiguration_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: osm
--

ALTER SEQUENCE vesselconfiguration_id_seq OWNED BY vesselconfiguration.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: osm
--

ALTER TABLE ONLY vesselconfiguration ALTER COLUMN id SET DEFAULT nextval('vesselconfiguration_id_seq'::regclass);


--
-- Name: vesselconfiguration_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY vesselconfiguration
    ADD CONSTRAINT vesselconfiguration_pkey PRIMARY KEY (id);


--
-- Name: vesselconfiguration_user_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY vesselconfiguration
    ADD CONSTRAINT vesselconfiguration_user_name_fkey FOREIGN KEY (user_name) REFERENCES user_profiles(user_name) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE depthsensor (
    vesselconfigid integer NOT NULL,
    x numeric(5,2),
    y numeric(5,2),
    z numeric(5,2),
    sensorid character varying,
    manufacturer character varying(100),
    model character varying(100),
    frequency numeric(5,0),
    angleofbeam numeric(3,0)
);


ALTER TABLE public.depthsensor OWNER TO osm;

--
-- Name: depthsoffset_vesselconfigid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY depthsensor
    ADD CONSTRAINT depthsoffset_vesselconfigid_fkey FOREIGN KEY (vesselconfigid) REFERENCES vesselconfiguration(id) ON UPDATE CASCADE ON DELETE CASCADE;



CREATE TABLE sbassensor (
    vesselconfigid integer NOT NULL,
    x numeric(5,2) ,
    y numeric(5,2) ,
    z numeric(5,2) ,
    sensorid character varying,
    manufacturer character varying(100),
    model character varying(100)
);


ALTER TABLE public.sbassensor OWNER TO osm;

--
-- Name: sbasoffset_vesselconfigid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY sbassensor
    ADD CONSTRAINT sbasoffset_vesselconfigid_fkey FOREIGN KEY (vesselconfigid) REFERENCES vesselconfiguration(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY user_profiles
    ADD CONSTRAINT user_profiles_pkey PRIMARY KEY (user_name);


--
-- Name: user_tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY user_tracks
    ADD CONSTRAINT user_tracks_pkey PRIMARY KEY (track_id);



CREATE TABLE gauge
(
  id integer NOT NULL,
  name character varying(255) NOT NULL,
  gaugetype character varying(10) DEFAULT 'UNKNOWN'::character varying,
  lat numeric(11,3),
  lon numeric(11,3),
  CONSTRAINT gauge_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE gauge
  OWNER TO osm;

-- Sequence: gauge_id_seq

-- DROP SEQUENCE gauge_id_seq;

CREATE SEQUENCE gauge_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 51
  CACHE 1;
ALTER TABLE gauge_id_seq
  OWNER TO osm;

-- Table: license

-- DROP TABLE license;

CREATE TABLE license
(
  name character varying(255) NOT NULL,
  shortname character varying(16),
  text text,
  public boolean,
  id serial NOT NULL,
  user_name character varying(255),
  CONSTRAINT license_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE license
  OWNER TO osm;

-- Sequence: license_id_seq

-- DROP SEQUENCE license_id_seq;

CREATE SEQUENCE license_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 20
  CACHE 1;
ALTER TABLE license_id_seq
  OWNER TO osm;

-- Table: gaugemeasurement

-- DROP TABLE gaugemeasurement;

CREATE TABLE gaugemeasurement
(
  gaugeid integer NOT NULL,
  value numeric(4,2) NOT NULL,
  "time" timestamp without time zone NOT NULL,
  CONSTRAINT gaugemeasurement_fkey FOREIGN KEY (gaugeid)
      REFERENCES gauge (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT gaugemeasurement_unique UNIQUE (gaugeid, "time")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE gaugemeasurement
  OWNER TO osm;

-- Index: fki_gaugemeasurement_fkey

-- DROP INDEX fki_gaugemeasurement_fkey;

CREATE INDEX fki_gaugemeasurement_fkey
  ON gaugemeasurement
  USING btree
  (gaugeid);



--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

