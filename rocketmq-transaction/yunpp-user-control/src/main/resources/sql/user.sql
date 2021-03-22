create table ypp_user
(
	id varchar(200) not null,
	username varchar(200) not null,
	password varchar(200) null,
	email varchar(200) null,
	sex int null,
	update_time    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create unique index ypp_user_id_uindex
	on ypp_user (id);

create unique index ypp_user_username_uindex
	on ypp_user (username);

