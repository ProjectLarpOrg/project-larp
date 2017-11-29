INSERT INTO users (id,version, username, password, enabled) VALUES
  ('101',0, 'admin', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true),
  ('102',0, 'user1', '$2a$10$GD4VJSl.xy2eEIqouZ8XreRLkkfDvceB0yfWaTYRAK.9.ZFvZgdtS', true),
  ('103',0, 'user2', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true),
  ('104',0, 'user3', '$2a$10$ryPyPy4auHr8pwi6nfTjVenMNxHUxD5bE.H8Ydm8vVOH2FPNeO9ba', true)
;
INSERT INTO user_role (user_id, role) VALUES
  ('101', 'ADMIN'),
  ('101', 'PLAYER'),
  ('102', 'PLAYER'),
  ('103', 'PLAYER')
;
