
INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Kids @ Noroff', 'Fun game to play with imaginary friends.', 12, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Very Nice', 'The Zombies are not very nice lol.', 16, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO chat (game_ref) VALUES (1);
UPDATE chat SET game_id = 1 WHERE id = 1;
INSERT INTO chat (game_ref) VALUES (2);
UPDATE chat SET game_id = 2 WHERE id = 2;
INSERT INTO player (is_human, is_patient_zero, bite_code,lat, lng) VALUES (true, true, 'something',59.911491,10.757933);
INSERT INTO squad (name, game_id, game_ref) VALUES ('Noroff team', 1, 0);
INSERT INTO squad (name, game_id, game_ref) VALUES ('Team nice', 2, 0);
UPDATE player SET squad_id = 1, game_id = 1 WHERE id = 1;
UPDATE squad SET game_id = 1 WHERE id = 1;
INSERT INTO users (username) VALUES ('Love');
INSERT INTO users (username) VALUES ('Hans');
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id,lat, lng) VALUES (true, true, 'something', 1, 1,59.92,10.77);
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id,lat, lng) VALUES (true, true, 'something', 1, 1,59.92,10.77);
INSERT INTO player (is_human, is_patient_zero, bite_code, user_id, game_id,lat, lng) VALUES (true, true, 'something', 2, 2,0,0);
Insert into mission (description, end_time, is_human_visible, is_zombie_visible, name, start_time) values('warzone', '2022', false, true, 'testing war zone', '2021' );
UPDATE player SET squad_id = 1 WHERE id = 2

