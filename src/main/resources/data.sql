INSERT INTO game (title, description) VALUES ('Kids @ Noroff', 'Fun game to play with imaginary friends.');
INSERT INTO game (title, description) VALUES ('Very Nice', 'The Zombies are not very nice lol.');
INSERT INTO player (is_human, is_patient_zero, bite_code) VALUES (true, true, 'something');
INSERT INTO squad (name, game_id) VALUES ('Noroff team', 1);
UPDATE player SET squad_id = 1, game_id = 1 WHERE id = 1;
