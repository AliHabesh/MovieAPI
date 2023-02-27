INSERT INTO franchise (name, description) VALUES ('Marvel Cinematic Universe', 'A series of interconnected superhero films');
INSERT INTO franchise (name, description) VALUES ('Star Wars', 'An epic space opera franchise');
INSERT INTO franchise (name, description) VALUES ('The Lord of the Rings', 'A high-fantasy book series and film franchise');
INSERT INTO movie (movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES ('Iron Man', 'Action, Adventure, Sci-Fi', 2008, 'Jon Favreau', 'https://google.com/iron-man-poster.jpg', 'https://www.youtube.com/watch?v=8hYlB38asDY', 1);
INSERT INTO movie (movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES ('The Avengers', 'Action, Adventure, Sci-Fi', 2012, 'Joss Whedon', 'https://google.com/the-avengers-poster.jpg', 'https://www.youtube.com/watch?v=eOrNdBpGMv8', 1);
INSERT INTO movie (movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES ('Star Wars: Episode IV - A New Hope', 'Action, Adventure, Fantasy', 1977, 'George Lucas', 'https://example.com/star-wars-poster.jpg', 'https://www.youtube.com/watch?v=1g3_CFmnU7k', 2);
INSERT INTO character (full_name, alias, gender, picture) VALUES ('Tony Stark', 'Iron Man', 'M', 'https://example.com/tony-stark.jpg');
INSERT INTO character (full_name, alias, gender, picture) VALUES ('Steve Rogers', 'Captain America', 'M', 'https://google.com/steve-rogers.jpg');
INSERT INTO character (full_name, alias, gender, picture) VALUES ('Princess Leia Organa', 'Leia', 'F', 'https://google.com/princess-leia.jpg');
INSERT INTO movie_character (movie_id, character_id) VALUES (1,1);
INSERT INTO movie_character (movie_id, character_id) VALUES (2,2);
INSERT INTO movie_character (movie_id, character_id) VALUES (3,3);

--if you want to run this, add "spring.sql.init.mode=always" to application.properties.
--Remove once you are done, or set it to none.