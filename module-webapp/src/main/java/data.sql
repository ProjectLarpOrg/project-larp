DELETE FROM user WHERE id=1;
INSERT INTO user (id, username, password, enabled, account_Non_Expired, credentials_Non_Expired, account_Non_Locked) VALUES
  ('1','admin', '$2a$10$s32PUU1QHIVpmscX1O4.F.bAttAS1X2XvZ/iSYtHOY2QK8iMpgs9C', true, true, true, true)
;
