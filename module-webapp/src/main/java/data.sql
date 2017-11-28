INSERT INTO user (id, username, password, enabled, account_Non_Expired, credentials_Non_Expired, account_Non_Locked) VALUES
  ('101','admin', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true, true, true, true),
  ('102','user1', '$2a$10$GD4VJSl.xy2eEIqouZ8XreRLkkfDvceB0yfWaTYRAK.9.ZFvZgdtS', true, true, true, true),
  ('103','user2', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true, true, true, true),
  ('104','user3', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true, true, true, true)
;
INSERT INTO user_role (user_id, role) VALUES
  ('1', 'ADMIN'),
  ('1', 'PLAYER'),
  ('2', 'PLAYER'),
  ('3', 'PLAYER')
;
