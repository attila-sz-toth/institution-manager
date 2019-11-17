create index idx_users_username
  on institution_manager.users (username);

create index idx_personal_details
  on institution_manager.personal_details (first_name, last_name, birth_date, mothers_name);