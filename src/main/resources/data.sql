INSERT INTO game (title, description) VALUES ('Kids @ Noroff', 'Fun game to play with imaginary friends.');
INSERT INTO game (title, description) VALUES ('Very Nice', 'The Zombies are not very nice lol.');
INSERT INTO users (first_name, last_name) VALUES ('Morten', 'Mortensen');
INSERT INTO users (first_name, last_name) VALUES ('Eirik', 'Eiriksen');
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id) VALUES (true, true, 'something', 1, 1);
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id) VALUES (true, true, 'something', 2, 2);

INSERT INTO squad (name, game_id) VALUES ('Noroff team', 1);
UPDATE player SET squad_id = 1, game_id = 1 WHERE id = 1;
Insert into mission (description, end_time, is_human_visible, is_zombie_visible, name, start_time) values('warzone', '2022', false, true, 'testing war zone', '2021' );
insert into chat (chat_time, is_human_global, is_zombie_global, message) values('2022', false, true, 'dette er en chat');
