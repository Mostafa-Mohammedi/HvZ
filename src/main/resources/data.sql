INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Kids @ Noroff', 'Fun game to play with imaginary friends.', 12, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO game (title, description, max_players, player_count, status, date, game_type) VALUES ('Very Nice', 'The Zombies are not very nice lol.', 16, 0, 'registration', CURRENT_DATE, 'Hide and seek');
INSERT INTO player (is_human, is_patient_zero, bite_code) VALUES (true, true, 'something');
INSERT INTO squad (name, game_id) VALUES ('Noroff team', 1);
UPDATE player SET squad_id = 1, game_id = 1 WHERE id = 1;
UPDATE squad SET game_id = 1 WHERE id = 1;
INSERT INTO users (first_name, last_name) VALUES ('AK', '47');
Insert into mission (description, end_time, is_human_visible, is_zombie_visible, name, start_time) values('warzone', '2022', false, true, 'testing war zone', '2021' );
