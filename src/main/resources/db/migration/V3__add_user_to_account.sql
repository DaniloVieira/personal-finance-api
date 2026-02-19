alter table pfi_account add column fk_user bigint not null;
alter table pfi_account add constraint pfi_account_fk_user_id foreign key (fk_user) references pfi_user(id);

alter table pfi_tag add column fk_user bigint not null;
alter table pfi_tag add constraint pfi_tag_fk_user_id foreign key (fk_user) references pfi_user(id);