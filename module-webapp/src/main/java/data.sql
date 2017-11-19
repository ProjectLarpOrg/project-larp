DELETE FROM user WHERE id=1;
INSERT INTO user (id, username, password, enabled, account_Non_Expired, credentials_Non_Expired, account_Non_Locked) VALUES
  ('1','admin', 'admin', true, true, true, true)
;
