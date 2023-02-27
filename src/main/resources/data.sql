INSERT INTO franchise (franchise_id,name, description) VALUES (1,'Marvel Cinematic Universe', 'A series of interconnected superhero films');
INSERT INTO franchise (franchise_id,name, description) VALUES (2, 'Star Wars', 'An epic space opera franchise');
INSERT INTO franchise (franchise_id,name, description) VALUES (3, 'The Lord of the Rings', 'A high-fantasy book series and film franchise');
INSERT INTO movie (movie_id, movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES (1,'Iron Man', 'Action, Adventure, Sci-Fi', 2008, 'Jon Favreau', 'https://google.com/iron-man-poster.jpg', 'https://www.youtube.com/watch?v=8hYlB38asDY', 1);
INSERT INTO movie (movie_id, movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES (2, 'The Avengers', 'Action, Adventure, Sci-Fi', 2012, 'Joss Whedon', 'https://google.com/the-avengers-poster.jpg', 'https://www.youtube.com/watch?v=eOrNdBpGMv8', 1);
INSERT INTO movie (movie_id, movie_title, genre, movie_release_year, director, picture, trailer, franchise_id) VALUES (3, 'Star Wars: Episode IV - A New Hope', 'Action, Adventure, Fantasy', 1977, 'George Lucas', 'https://example.com/star-wars-poster.jpg', 'https://www.youtube.com/watch?v=1g3_CFmnU7k', 2);
INSERT INTO character (character_id,full_name, alias, gender, picture, movie_id) VALUES (1,'Tony Stark', 'Iron Man', 'M', 'https://example.com/tony-stark.jpg', 1);
INSERT INTO character (character_id,full_name, alias, gender, picture, movie_id) VALUES (2,'Steve Rogers', 'Captain America', 'M', 'https://google.com/steve-rogers.jpg', 2);
INSERT INTO character (character_id,full_name, alias, gender, picture, movie_id) VALUES (3,'Princess Leia Organa', 'Leia', 'F', 'https://google.com/princess-leia.jpg', 3);


--if you want to run this, add "spring.sql.init.mode=always" to application.properties.
--Remove once you are done, or set it to none.