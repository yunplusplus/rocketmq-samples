
create table ypp_coupon
(
	id varchar(200) not null,
	username varchar(200) not null,
	coupon_name varchar(200) null,
	coupon_id varchar(200) null,
	denomination int null,
	update_time    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_time timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

create unique index ypp_coupon_id_uindex
	on ypp_coupon (id);

create unique index ypp_coupon_username_coupon_id_uindex
	on ypp_coupon (username,coupon_id);