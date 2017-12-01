INSERT INTO users (id, username, email, password, enabled) VALUES
  ('101','admin', 'admin@mail.com', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true),
  ('102','user1', 'user1@mail.com', '$2a$10$GD4VJSl.xy2eEIqouZ8XreRLkkfDvceB0yfWaTYRAK.9.ZFvZgdtS', true),
  ('103','user2', 'user2@mail.com', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true),
  ('104','user3', 'user3@mail.com', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true)
;
INSERT INTO authorities (id, user_id, authority) VALUES
  (10101, '101', 'ADMIN'),
  (10102, '101', 'PLAYER'),
  (10201, '102', 'PLAYER'),
  (10301, '103', 'PLAYER')
;

INSERT INTO profils (id, user_id, language, theme) VALUES
  (10101, '101', 'fr', 'Light')
;
