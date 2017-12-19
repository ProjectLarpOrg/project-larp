INSERT INTO users (id, username, email, password, enabled) VALUES
  (1,'admin', 'admin@mail.com', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true),
  (2,'user', 'user@mail.com', '$2a$10$UXXNVXWfqc0Hl7Xdtpch5ujraUirpwpE14XsaqudUbC/pAza70xqW', true),
;
INSERT INTO authorities(id, user_id, authority) VALUES
  (101, 1, 'USER'), (102, 1, 'ADMIN'),
  (201, 2, 'USER')
;
