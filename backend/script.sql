create table user
(
    user_id    int auto_increment
        primary key,
    user_name  varchar(50)   not null,
    user_mail  varchar(50)   null,
    user_pwd   varchar(20)   null,
    user_state int default 0 not null,
    user_risk  int default 0 not null,
    constraint User_user_mail_uindex
        unique (user_mail)
);

create table catalog
(
    catalog_id int auto_increment
        primary key,
    user_id    int           null,
    amount     int default 0 not null,
    constraint Catalog_user_user_id_fk
        foreign key (user_id) references user (user_id)
);

create table doctor_visit_info
(
    info_id      int auto_increment
        primary key,
    catalog_id   int          null,
    doctor_name  varchar(20)  null,
    visit_date   varchar(20)  null,
    visit_detail varchar(100) null,
    constraint doctor_visit_info_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);

create table medicine_info
(
    catalog_id         int          null,
    info_id            int auto_increment
        primary key,
    info_timestamp     varchar(20)  null,
    medicine_name      varchar(50)  null,
    medicine_frequency varchar(20)  null,
    medicine_dosage    varchar(20)  null,
    medicine_details   varchar(100) null,
    constraint medicine_info_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);

create table related_news_info
(
    info_id        int auto_increment
        primary key,
    catalog_id     int          null,
    info_timestamp varchar(20)  null,
    news_date      varchar(20)  null,
    news_detail    varchar(100) null,
    constraint related_news_info_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);

create table symptom_info
(
    catalog_id     int          null,
    info_id        int auto_increment
        primary key,
    info_timestamp varchar(20)  null,
    start_time     varchar(20)  null,
    end_time       varchar(20)  null,
    symptom_type   int          null,
    symptom_detail varchar(100) null,
    constraint symptom_info_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);

create table takeouts_info
(
    info_id        int auto_increment
        primary key,
    catalog_id     int          null,
    info_timestamp varchar(20)  null,
    takeout_place  varchar(20)  null,
    takeout_date   varchar(20)  null,
    takeout_detail varchar(100) null,
    constraint takeouts_info_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);

create table trips_info
(
    trips_timestamp varchar(20)  not null,
    info_id         int auto_increment
        primary key,
    catalog_id      int          null,
    start_time      varchar(20)  null,
    end_time        varchar(20)  null,
    trips_detail    varchar(100) null,
    constraint TripsInfo_catalog_catalog_id_fk
        foreign key (catalog_id) references catalog (catalog_id)
);


