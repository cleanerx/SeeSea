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

CREATE TABLE depthsoffset (
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


ALTER TABLE public.depthsoffset OWNER TO osm;

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
    vesselconfigid integer
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

--
-- Name: vesselconfiguration; Type: TABLE; Schema: public; Owner: osm; Tablespace: 
--

CREATE TABLE vesselconfiguration (
    id integer NOT NULL,
    name character varying,
    description character varying,
    user_name character varying,
    mmsi character varying(20),
    manufacturer character varying(100),
    model character varying,
    loa numeric(7,2),
    berth numeric(7,2),
    draft numeric(4,2),
    height numeric(4,2),
    displacement numeric(8,1),
    maximumspeed numeric(3,1)
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
-- Name: user_profiles_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY user_profiles
    ADD CONSTRAINT user_profiles_pkey PRIMARY KEY (user_name);


--
-- Name: user_tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY user_tracks
    ADD CONSTRAINT user_tracks_pkey PRIMARY KEY (track_id);


--
-- Name: vesselconfiguration_pkey; Type: CONSTRAINT; Schema: public; Owner: osm; Tablespace: 
--

ALTER TABLE ONLY vesselconfiguration
    ADD CONSTRAINT vesselconfiguration_pkey PRIMARY KEY (id);


--
-- Name: depthsoffset_vesselconfigid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY depthsoffset
    ADD CONSTRAINT depthsoffset_vesselconfigid_fkey FOREIGN KEY (vesselconfigid) REFERENCES vesselconfiguration(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: sbasoffset_vesselconfigid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY sbasoffset
    ADD CONSTRAINT sbasoffset_vesselconfigid_fkey FOREIGN KEY (vesselconfigid) REFERENCES vesselconfiguration(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vesselconfiguration_user_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: osm
--

ALTER TABLE ONLY vesselconfiguration
    ADD CONSTRAINT vesselconfiguration_user_name_fkey FOREIGN KEY (user_name) REFERENCES user_profiles(user_name) ON UPDATE CASCADE ON DELETE CASCADE;


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

