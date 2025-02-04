-- 7.2   - В ранее подключенном MySQL создать базу данных с названием "Human Friends".
CREATE SCHEMA HumanFriends ;

--    - Создать таблицы, соответствующие иерархии из вашей диаграммы классов.
CREATE TABLE TypeGroup(
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL
);

CREATE TABLE AnimalType(
    ID SERIAL PRIMARY KEY,
    TypeGroupID BIGINT UNSIGNED,
    Name VARCHAR(30) NOT NULL,
    FOREIGN KEY (TypeGroupID) REFERENCES TypeGroup(ID)
);

CREATE TABLE Dog (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

CREATE TABLE Cat (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

CREATE TABLE Hamster (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

CREATE TABLE Horse (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

CREATE TABLE Camel (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

CREATE TABLE Donkey (
    ID SERIAL PRIMARY KEY,
    Name VARCHAR(30) NOT NULL,
    BirthDate DATE,
    Commands VARCHAR(255)
);

--    - Заполнить таблицы данными о животных, их командах и датами рождения.
INSERT INTO TypeGroup (Name) VALUES
	  ('Pet'), ('PackAnimal');
INSERT INTO AnimalType (TypeGroupID, Name) VALUES
      (1, 'Dog')
    , (1, 'Cat')
    , (1, 'Hamster')
    , (2, 'Horse')
    , (2, 'Camel')
    , (2, 'Donkey');

INSERT INTO Dog (Name, BirthDate, Commands) VALUES
    ('Rex', '2020-03-12', 'Sit, Lie Down'),
    ('Max', '2021-06-25', 'Fetch, Search'),
    ('Bella', '2019-09-05', 'Shake Hands, Bark'),
    ('Luna', '2022-01-18', 'Fetch, Bark'),
    ('Baron', '2023-11-07', 'Bark, Sit');
    
INSERT INTO Cat (Name, BirthDate, Commands) VALUES
    ('Whiskers', '2019-03-12', 'Play, Scratch, Purr'),
    ('Mittens', '2018-08-25', 'Purr, Play'),
    ('Oliver', '2020-01-05', 'Jump'),
    ('Luna', '2017-06-18', 'Scratch, Purr'),
    ('Simba', '2016-11-30', 'Play, Sleep');    

INSERT INTO Hamster (Name, BirthDate, Commands) VALUES
    ('Fluffy', '2020-04-01', 'Run, Jump'),
    ('Nibbles', '2019-09-15', 'Hide, Explore'),
    ('Cheeks', '2021-02-05', 'Spin'),
    ('Whiskers', '2018-07-18', 'Jump, Hide'),
    ('Squeaky', '2017-12-30', 'Run, Spin');

INSERT INTO Horse (Name, BirthDate, Commands) VALUES
    ('Spirit', '2022-03-12', 'Run, Jump'),
    ('Midnight', '2024-09-25', 'Gallop, Trot'),
    ('Starlight', '2018-09-05', 'Walk, Canter'),
    ('Thunder', '2021-01-18', 'Turn, Stop'),
    ('Daisy', '2022-11-07', 'Back, Stand');
    
INSERT INTO Camel (Name, BirthDate, Commands) VALUES
    ('Sahara', '2018-05-10', 'Walk, Rest'),
    ('Desert Rose', '2019-02-15', 'Walk, Stop'),
    ('Dune Rider', '2020-07-20', 'Walk, Run, Stop'),
    ('Sandy', '2017-11-30', 'Walk, Run, Rest'),
    ('Amber', '2016-09-25', 'Walk, Rest');
    
INSERT INTO Donkey (Name, BirthDate, Commands) VALUES
    ('Dusty', '2020-04-01', 'Walk, Turn'),
    ('Buck', '2021-06-25', 'Walk, Turn, Stop'),
    ('Misty', '2019-09-05', 'Walk, Run, Stand'),
    ('Rocky', '2022-01-18', 'Walk, Gallop, Back'),
    ('Rosie', '2023-11-07', 'Gallop, Rest');

--    - Удалить записи о верблюдах и объединить таблицы лошадей и ослов.
TRUNCATE TABLE Camel;

SELECT Name, 'Horse' AS Type, BirthDate, Commands FROM Horse
UNION
SELECT Name, 'Donkey' AS Type, BirthDate, Commands FROM Donkey;

--    - Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить их возраст с точностью до месяца.
WITH Animal AS
(
	SELECT Name, 'Dog' AS Type, BirthDate, Commands FROM Dog
	UNION
	SELECT Name, 'Cat' AS Type, BirthDate, Commands FROM Cat
	UNION
	SELECT Name, 'Hamster' AS Type, BirthDate, Commands FROM Hamster
    UNION
	SELECT Name, 'Horse' AS Type, BirthDate, Commands FROM Horse
    UNION
	SELECT Name, 'Camel' AS Type, BirthDate, Commands FROM Camel
    UNION
	SELECT Name, 'Donkey' AS Type, BirthDate, Commands FROM Donkey
)
SELECT 
	  Name
    , BirthDate
    , CONCAT(
		  TIMESTAMPDIFF(YEAR, BirthDate, CURDATE())
        , ' YEARS, '
        , TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) % 12
        , ' MONTHS'
        ) AS Age 
	, Commands
FROM Animal
WHERE TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) BETWEEN 1 AND 3;

--    - Объединить все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам.
WITH TypeInfo AS (
	SELECT 
		  g.Name AS TypeGroup
		, a.Name AS Type
	FROM AnimalType AS a 
	LEFT JOIN TypeGroup AS g 
		ON a.TypeGroupID = g.ID
)
	SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Dog 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Dog'
	UNION
    SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Cat 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Cat'
	UNION
    SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Hamster 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Hamster'
	UNION
    SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Horse 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Horse'
	UNION
    SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Camel 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Camel'
	UNION
    SELECT i.TypeGroup, i.Type, Name, BirthDate, Commands 
    FROM Donkey 
    LEFT JOIN TypeInfo AS i
		ON i.Type = 'Donkey'
	ORDER BY BirthDate;