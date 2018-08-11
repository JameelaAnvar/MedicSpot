# MedicSpot
UI

Run MedicSpot/innvent-ui/x_coming_sssoon_pages/index.html to check the template for home page..


DB

Please run this script before table_creation_script :
CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; 

-- Table: public.med_store_map

-- DROP TABLE public.med_store_map;

CREATE TABLE public.med_store_map
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    medicine_id uuid NOT NULL,
    store_id uuid NOT NULL,
    store_score bigint DEFAULT 0,
    CONSTRAINT med_store_map_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.med_store_map
    OWNER to postgres;
	
-- Table: public.medicine

-- DROP TABLE public.medicine;

CREATE TABLE public.medicine
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text COLLATE pg_catalog."default" NOT NULL,
    composition text[] COLLATE pg_catalog."default" NOT NULL,
    price double precision NOT NULL DEFAULT 0,
    CONSTRAINT "Medicine_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.medicine
    OWNER to postgres;
	
-- Table: public.stores

-- DROP TABLE public.stores;

CREATE TABLE public.stores
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    storename text COLLATE pg_catalog."default" NOT NULL,
    location text COLLATE pg_catalog."default",
    CONSTRAINT stores_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.stores
    OWNER to postgres;
