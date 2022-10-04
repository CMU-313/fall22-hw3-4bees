create cached table T_SCORE ( SCORE_ID_C varchar(36) not null, SCORE_IDDOC_C varchar(36) not null, SCORE_IDUSER_C varchar(36) not null, SCORE_SKILLS_C int, SCORE_EXPERIENCE_C int, SCORE_TRANSCRIPTGPA_C int, SCORE_MATCH_C int, SCORE_CREATEDATE_D datetime not null, SCORE_DELETEDATE_D datetime, primary key (SCORE_ID_C) );

alter table T_SCORE add constraint FK_SCORE_IDDOC_C foreign key (SCORE_IDDOC_C) references T_DOCUMENT (DOC_ID_C) on delete restrict on update restrict;
alter table T_SCORE add constraint FK_SCORE_IDUSER_C foreign key (SCORE_IDUSER_C) references T_USER (USE_ID_C) on delete restrict on update restrict;

update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';
