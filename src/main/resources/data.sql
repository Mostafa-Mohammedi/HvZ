
INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Kids @ Noroff', 'Fun game to play with imaginary friends.', 12, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Very Nice', 'The Zombies are not very nice lol.', 16, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO player (is_human, is_patient_zero, bite_code,lat, lng) VALUES (true, true, 'something',59.911491,10.757933);
INSERT INTO squad (name, game_id) VALUES ('Noroff team', 1);
INSERT INTO squad (name, game_id) VALUES ('Team nice', 2);
INSERT INTO kill (lat, lng, story, time_of_death) VALUES (2.2, 2.2, 'livet suger', 'igår');
UPDATE player SET squad_id = 1, game_id = 1 WHERE id = 1;
UPDATE squad SET game_id = 1 WHERE id = 1;
UPDATE kill SET game_id = 1 WHERE id = 1;

INSERT INTO users (username) VALUES ('Love');
INSERT INTO users (username) VALUES ('Hans');
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id,lat, lng) VALUES (true, true, 'something', 1, 1,59.92,10.77);
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id,lat, lng) VALUES (true, true, 'something', 2, 2,0,0);

Insert into mission (description, end_time, is_human_visible, is_zombie_visible, name, start_time) values('warzone', '2022', false, true, 'testing war zone', '2021' );
insert into chat (chat_time, is_human_global, is_zombie_global, message, game_id) values('2022', false, true, 'dette er en chat', 1);
insert into chat (chat_time, is_human_global, is_zombie_global, message, game_id) values('2022', false, true, 'dette er en chat2', 1);
insert into chat (chat_time, is_human_global, is_zombie_global, message, game_id) values('2022', false, true, 'dette er en chat3', 1);


INSERT INTO kill (lat, lng, story, time_of_death) VALUES (1.222, 2.2222, 'livet suger', '1234');

UPDATE kill SET player_id = 2 WHERE id = 1;
UPDATE player SET squad_id = 1 WHERE id = 2

