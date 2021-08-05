--Table: public.restaurants

--DROP TABLE public.restaurants;

CREATE TABLE IF NOT EXISTS public.restaurants
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    building integer NOT NULL,
    street character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Restaurants_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.restaurants
    OWNER to postgres;


-- Table: public.buildings

-- DROP TABLE public.buildings;

CREATE TABLE IF NOT EXISTS public.buildings
(
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "number" integer NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public.buildings
    OWNER to postgres;

-- Table: public.dishes

-- DROP TABLE public.dishes;

CREATE TABLE IF NOT EXISTS public.dishes
(
    id integer NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    price integer,
    restaurant integer NOT NULL,
    CONSTRAINT "Dishes_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.dishes
    OWNER to postgres;

-- Table: public.orders

-- DROP TABLE public.orders;

CREATE TABLE IF NOT EXISTS public.orders
(
    dish integer,
    restaurant integer,
    street character varying COLLATE pg_catalog."default",
    building_number integer,
    id integer NOT NULL DEFAULT nextval('orders_id_seq'::regclass),
    CONSTRAINT orders_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;

--
INSERT INTO restaurants (id, name, building, street) 
VALUES
(1, 'Perezmen', 5, 'Kalesnikava'), 
(2, 'Freedomster', 3, 'Kalinouski'), 
(3, 'Meating', 1, 'Skaryna');

INSERT INTO dishes (id, restaurant, name, price) 
VALUES 
(1, 1, 'pizza', 4), 
(2, 1, 'burger', 1), 
(3, 1, 'cola', 1), 
(4, 2, 'domster', 3),
(5, 2, 'shawarma', 5),
(6, 3, 'chicken barbeque', 5),
(7, 3, 'meatballs', 3),
(8, 3, 'pork kebab', 7);

INSERT INTO buildings (number, street) 
VALUES
(1, 'Kalesnikava'),
(2, 'Kalesnikava'),
(3, 'Kalesnikava'),
(4, 'Kalesnikava'),
(5, 'Kalesnikava'),
(6, 'Kalesnikava'),
(1, 'Kalinouski'),
(2, 'Kalinouski'),
(3, 'Kalinouski'),
(4, 'Kalinouski'),
(1, 'Skaryna'),
(2, 'Skaryna'),
(3, 'Skaryna');

INSERT INTO orders (restaurant, dish, street, building_number) VALUES 
(1, 3, 'Skaryna', 2),
(2, 1, 'Kalinouski', 1);
