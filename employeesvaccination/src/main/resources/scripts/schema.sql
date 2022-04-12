create database dbemployeevaccine
	with owner postgres;

grant connect, create on database dbemployeevaccine to employeevaccineuser;


create schema employeevaccine;

alter schema employeevaccine owner to postgres;

create table if not exists employeevaccine.catalogue
(
	id_catalogue serial not null
		constraint catalogue_pk
			primary key,
	abbreviation varchar(20),
	name varchar(250),
	description varchar(250),
	status boolean
);

alter table employeevaccine.catalogue owner to postgres;

create unique index if not exists catalogue_abbreviation_uindex
	on employeevaccine.catalogue (abbreviation);

create unique index if not exists catalogue_id_catalogue_uindex
	on employeevaccine.catalogue (id_catalogue);

create table if not exists employeevaccine.catalogue_detail
(
	id_catalogue_detail serial not null
		constraint catalogue_detail_pk
			primary key,
	abbreviation_detail varchar(20),
	name_detail varchar(250),
	description_detail varchar(250),
	status_detail boolean not null,
	catalogue_id_catalogue integer
		constraint cat_detail_cat_id_catalogue_fk
			references employeevaccine.catalogue
);

alter table employeevaccine.catalogue_detail owner to postgres;

create unique index if not exists catalogue_detail_abbreviation_detail_uindex
	on employeevaccine.catalogue_detail (abbreviation_detail);

create unique index if not exists catalogue_detail_id_catalogue_detail_uindex
	on employeevaccine.catalogue_detail (id_catalogue_detail);

create table if not exists employeevaccine.employee
(
	id_employee serial not null
		constraint employee_pk
			primary key,
	identification varchar(10) not null,
	firstname varchar(250) not null,
	lastname varchar(250) not null,
	email varchar(250) not null,
	birthdate date,
	address varchar(250),
	cellphone varchar(20),
	vaccination_status boolean,
	status_employee boolean
);

alter table employeevaccine.employee owner to postgres;

create unique index if not exists employee_id_employee_uindex
	on employeevaccine.employee (id_employee);

create unique index if not exists employee_identification_uindex
	on employeevaccine.employee (identification);

create table if not exists employeevaccine.vaccine
(
	id_vaccine serial not null
		constraint vaccine_pk
			primary key,
	type_vaccine varchar(20),
	vaccination_date date,
	number_doses integer,
	employee_id_employee integer
		constraint vaccine_employee_id_employee_fk
			references employeevaccine.employee
);

alter table employeevaccine.vaccine owner to postgres;

create unique index if not exists vaccine_id_vaccine_uindex
	on employeevaccine.vaccine (id_vaccine);

create table if not exists employeevaccine.users
(
	id bigserial not null
		constraint users_pkey
			primary key,
	password varchar(60),
	username varchar(30)
		constraint uk_r43af9ap4edm43mmtq01oddj6
			unique,
	enabled boolean
);

alter table employeevaccine.users owner to postgres;

create table if not exists employeevaccine.authorities
(
	id bigserial not null
		constraint authorities_pkey
			primary key,
	authority varchar(255),
	users_id bigint,
	constraint ukrimuuii4fm3j9qt8uupyiy8nd
		unique (users_id, authority)
);

alter table employeevaccine.authorities owner to postgres;

