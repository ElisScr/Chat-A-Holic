
CREATE SCHEMA chat;

CREATE TABLE departament
(
  departament_ID integer not NULL UNIQUE PRIMARY KEY ,
  departament_name VARCHAR (50) 
);
--CREATE SEQUENCE next_departament_ID_seq START 1 INCREMENT 1  departament_ID := nextval('next_departament_ID_seq');


CREATE TABLE employees
(

 employee_Name VARCHAR (50) NOT NULL ,
 employee_ID VARCHAR (10)NOT NULL UNIQUE PRIMARY KEY ,
 employee_departament_ID INTEGER NOT NULL,
 password VARCHAR (20)NOT NULL ,

 FOREIGN KEY(employee_departament_ID) REFERENCES departament(departament_ID)

);

INSERT INTO departament VALUES (1,'Executive'),
                               (2,'IT'),
							   (3,'Marketing'),
						       (4,'Production');
							   
INSERT INTO employees VALUES   ('Jeff Looney','admin',2,'admin'),
                               ('Bianca Baranga','BiaBar',1,'password'),
                               ('Bianca Neve','BiaNev',4,'password'),
							   ('Elisabeta Scriba','EliScr',2,'password'),
						       ('Mos Craciun','MosCra',4,'password'),
							   ('Vitalie Costin','VitCos',2,'password'),
							   ('Samim Barakazi','SamBar',3,'password'),
							   ('John Dog','JoDog',3,'password'),
						       ('Jusin Timberlake','JusTim',1,'password'),
							   ('Neo Litic','NeoLit',4,'password'),
							   ('Bob Marley','BobMar',4,'password');	
							   
CREATE VIEW departament_executive AS
    SELECT *
    FROM employees
    WHERE employee_departament_ID = 1;
	
	
	CREATE VIEW departament_it AS
    SELECT *
    FROM employees
    WHERE employee_departament_ID = 2;
	
	CREATE VIEW departament_marketing AS
    SELECT *
    FROM employees
    WHERE employee_departament_ID = 3;
	
	CREATE VIEW departament_production AS
    SELECT *
    FROM employees
    WHERE employee_departament_ID = 4;