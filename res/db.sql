CREATE TABLE plante (
	id 			INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0, INCREMENT BY 1) PRIMARY KEY,
	nom_Usuel		VARCHAR(25),
	nom_Latin 		VARCHAR(25),
	type_Plante		INTEGER,
	hauteur			INTEGER,
	luminosite		INTEGER,
	url_Image_En_Fleur	VARCHAR(50),
	couleur_en_fleur	INTEGER,
	couleur_non_fleuris	INTEGER,
	type_Sol		INTEGER,
	date_Plantation		DATE,
	date_DebutFloraison	DATE,
	date_FinFloraison	DATE,
	vivace			BOOLEAN,
	description		VARCHAR(50)
)

CREATE TABLE jardin (
	id 			INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0, INCREMENT BY 1) PRIMARY KEY,
	nom			VARCHAR(25),
	width			INTEGER,
	height			INTEGER
)

CREATE TABLE zone (
	id 			INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0, INCREMENT BY 1) PRIMARY KEY,
	id_Jardin		INTEGER UNIQUE FOREIGN KEY REFERENCES jardin(id),
	x			INTEGER ARRAY,
	y			INTEGER ARRAY,
	luminosite		INTEGER
)

CREATE TABLE zonePlantable (
	id 			INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 0, INCREMENT BY 1) PRIMARY KEY,
	id_Plante		INTEGER UNIQUE FOREIGN KEY REFERENCES plante(id),
	id_Zone			INTEGER UNIQUE FOREIGN KEY REFERENCES zone(id),
	x			INTEGER ARRAY,
	y			INTEGER ARRAY,
	type_Sol		INTEGER,
	luminosite		INTEGER
)












