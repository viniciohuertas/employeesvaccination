-- DB: postgres
-- SCHEMA: employeevaccine
-- TABLE: catalogue
-- DESC: insert table catalogue
INSERT INTO employeevaccine.catalogue (abbreviation, name, description, status) 
VALUES ('vaccines', 'Type vaccine', 'Types of vaccines list', true);

-- DB: postgres
-- SCHEMA: employeevaccine
-- TABLE: catalogue_detail
-- DESC: insert table catalogue_detail
INSERT INTO employeevaccine.catalogue_detail (abbreviation_detail, name_detail, description_detail, status_detail, catalogue_id_catalogue) 
VALUES ('sputnik', 'Sputnik', 'Sputnik vaccine', true, 1);
INSERT INTO employeevaccine.catalogue_detail (abbreviation_detail, name_detail, description_detail, status_detail, catalogue_id_catalogue) 
VALUES ('astrazeneca', 'AstraZeneca', 'AstraZeneca vaccine', true, 1);
INSERT INTO employeevaccine.catalogue_detail (abbreviation_detail, name_detail, description_detail, status_detail, catalogue_id_catalogue) 
VALUES ('pfizer', 'Pfizer', 'Pfizer vaccine', true, 1);
INSERT INTO employeevaccine.catalogue_detail (abbreviation_detail, name_detail, description_detail, status_detail, catalogue_id_catalogue) 
VALUES ('jhonson', 'Jhonson&Jhonson', 'Jhonson&Jhonson vaccine', true, 1);