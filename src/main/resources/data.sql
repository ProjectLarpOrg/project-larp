insert into sec_users (username, password, enabled) values
('admin', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true);

insert into sec_authorities (username, authority) values
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER');

insert into app_games (id, name, style) values
(1, 'Game A', 'FANTASY'),
(2, 'Game B', 'MODERN'),
(3, 'Game C', 'CYBERPUNK');
