INSERT INTO users(name, username, password, role)VALUES("Janko Stepanovic", "janko", "123", "ADMIN");
INSERT INTO users(name, username, password, role)VALUES("Sasa Popovic", "sasa", "123", "OBJAVLJIVAC");
INSERT INTO users(name, username, password, role)VALUES("Era Spasojevic", "era", "123", "KOMENTATOR");

INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('TRANSFORMERS 5: The Last Knight Trailer','Transformers: The Last Knight is a 2017 American science fiction action film based on the Transformers franchise. It is the fifth installment of the live-action Transformers film series and a sequel to 2014','2018-4-17',20,15,45.267136,19.833549,1);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('AVATAR','Avatar takes us to a spectacular world beyond imagination..','2018-4-2',40,10,45.267136,19.833549,2);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('TAXI 5','A police officer who is transfered in the police of Marseille gets assigned to take down a group of Italian robbers','2018-11-5',80,41,45.267136,19.833549,1);
INSERT INTO posts(title,description,date,likes,dislikes,latitude,longitude,user_id)VALUES ('STAR WARS','Star Wars is an American epic space opera media franchise, centered on a film series created by George Lucas.','2018-8-4',40,10,45.267136,19.833549,2);

INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Ok','One of my favorite movie trailers! ﻿','2018-5-3',4,0,2,1);
INSERT INTO comments(title,description,date,likes,dislikes,post_id,user_id)VALUES ('Ok','i dont understand why Optimus is attacking Bumblebe﻿','2018-4-1',4,0,1,1);

INSERT INTO tags(name)VALUES ('#thebest');
INSERT INTO tags(name)VALUES ('#trueoffalse');
INSERT INTO tags(name)VALUES ('#extraBad');

INSERT INTO post_tags(post_id,tag_id)VALUES (1,2);
INSERT INTO post_tags(post_id,tag_id)VALUES (2,1);
INSERT INTO post_tags(post_id,tag_id)VALUES (3,3);