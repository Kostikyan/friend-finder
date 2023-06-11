drop database if exists friend_finder;
create database friend_finder;



create table `friend_finder`.`user_country`
(
    `id`   int(11)                                                       NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 251
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `friend_finder`.`user_country`
VALUES (1, 'Afghanistan'),
       (2, 'Aland Islands'),
       (3, 'Albania'),
       (4, 'Algeria'),
       (5, 'American Samoa'),
       (6, 'Andorra'),
       (7, 'Angola'),
       (8, 'Anguilla'),
       (9, 'Antarctica'),
       (10, 'Antigua And Barbuda'),
       (11, 'Argentina'),
       (12, 'Armenia'),
       (13, 'Aruba'),
       (14, 'Australia'),
       (15, 'Austria'),
       (16, 'Azerbaijan'),
       (17, 'The Bahamas'),
       (18, 'Bahrain'),
       (19, 'Bangladesh'),
       (20, 'Barbados'),
       (21, 'Belarus'),
       (22, 'Belgium'),
       (23, 'Belize'),
       (24, 'Benin'),
       (25, 'Bermuda'),
       (26, 'Bhutan'),
       (27, 'Bolivia'),
       (28, 'Bosnia and Herzegovina'),
       (29, 'Botswana'),
       (30, 'Bouvet Island'),
       (31, 'Brazil'),
       (32, 'British Indian Ocean Territory'),
       (33, 'Brunei'),
       (34, 'Bulgaria'),
       (35, 'Burkina Faso'),
       (36, 'Burundi'),
       (37, 'Cambodia'),
       (38, 'Cameroon'),
       (39, 'Canada'),
       (40, 'Cape Verde'),
       (41, 'Cayman Islands'),
       (42, 'Central African Republic'),
       (43, 'Chad'),
       (44, 'Chile'),
       (45, 'China'),
       (46, 'Christmas Island'),
       (47, 'Cocos (Keeling) Islands'),
       (48, 'Colombia'),
       (49, 'Comoros'),
       (50, 'Congo'),
       (51, 'Democratic Republic of the Congo'),
       (52, 'Cook Islands'),
       (53, 'Costa Rica'),
       (54, 'Cote D\'Ivoire (Ivory Coast)'),
       (55, 'Croatia'),
       (56, 'Cuba'),
       (57, 'Cyprus'),
       (58, 'Czech Republic'),
       (59, 'Denmark'),
       (60, 'Djibouti'),
       (61, 'Dominica'),
       (62, 'Dominican Republic'),
       (63, 'East Timor'),
       (64, 'Ecuador'),
       (65, 'Egypt'),
       (66, 'El Salvador'),
       (67, 'Equatorial Guinea'),
       (68, 'Eritrea'),
       (69, 'Estonia'),
       (70, 'Ethiopia'),
       (71, 'Falkland Islands'),
       (72, 'Faroe Islands'),
       (73, 'Fiji Islands'),
       (74, 'Finland'),
       (75, 'France'),
       (76, 'French Guiana'),
       (77, 'French Polynesia'),
       (78, 'French Southern Territories'),
       (79, 'Gabon'),
       (80, 'Gambia The'),
       (81, 'Georgia'),
       (82, 'Germany'),
       (83, 'Ghana'),
       (84, 'Gibraltar'),
       (85, 'Greece'),
       (86, 'Greenland'),
       (87, 'Grenada'),
       (88, 'Guadeloupe'),
       (89, 'Guam'),
       (90, 'Guatemala'),
       (91, 'Guernsey and Alderney'),
       (92, 'Guinea'),
       (93, 'Guinea-Bissau'),
       (94, 'Guyana'),
       (95, 'Haiti'),
       (96, 'Heard Island and McDonald Islands'),
       (97, 'Honduras'),
       (98, 'Hong Kong S.A.R.'),
       (99, 'Hungary'),
       (100, 'Iceland'),
       (101, 'India'),
       (102, 'Indonesia'),
       (103, 'Iran'),
       (104, 'Iraq'),
       (105, 'Ireland'),
       (106, 'Israel'),
       (107, 'Italy'),
       (108, 'Jamaica'),
       (109, 'Japan'),
       (110, 'Jersey'),
       (111, 'Jordan'),
       (112, 'Kazakhstan'),
       (113, 'Kenya'),
       (114, 'Kiribati'),
       (115, 'North Korea'),
       (116, 'South Korea'),
       (117, 'Kuwait'),
       (118, 'Kyrgyzstan'),
       (119, 'Laos'),
       (120, 'Latvia'),
       (121, 'Lebanon'),
       (122, 'Lesotho'),
       (123, 'Liberia'),
       (124, 'Libya'),
       (125, 'Liechtenstein'),
       (126, 'Lithuania'),
       (127, 'Luxembourg'),
       (128, 'Macau S.A.R.'),
       (129, 'North Macedonia'),
       (130, 'Madagascar'),
       (131, 'Malawi'),
       (132, 'Malaysia'),
       (133, 'Maldives'),
       (134, 'Mali'),
       (135, 'Malta'),
       (136, 'Man (Isle of)'),
       (137, 'Marshall Islands'),
       (138, 'Martinique'),
       (139, 'Mauritania'),
       (140, 'Mauritius'),
       (141, 'Mayotte'),
       (142, 'Mexico'),
       (143, 'Micronesia'),
       (144, 'Moldova'),
       (145, 'Monaco'),
       (146, 'Mongolia'),
       (147, 'Montenegro'),
       (148, 'Montserrat'),
       (149, 'Morocco'),
       (150, 'Mozambique'),
       (151, 'Myanmar'),
       (152, 'Namibia'),
       (153, 'Nauru'),
       (154, 'Nepal'),
       (155, 'Bonaire,Sint Eustatius and Saba'),
       (156, 'Netherlands'),
       (157, 'New Caledonia'),
       (158, 'New Zealand'),
       (159, 'Nicaragua'),
       (160, 'Niger'),
       (161, 'Nigeria'),
       (162, 'Niue'),
       (163, 'Norfolk Island'),
       (164, 'Northern Mariana Islands'),
       (165, 'Norway'),
       (166, 'Oman'),
       (167, 'Pakistan'),
       (168, 'Palau'),
       (169, 'Palestinian Territory Occupied'),
       (170, 'Panama'),
       (171, 'Papua new Guinea'),
       (172, 'Paraguay'),
       (173, 'Peru'),
       (174, 'Philippines'),
       (175, 'Pitcairn Island'),
       (176, 'Poland'),
       (177, 'Portugal'),
       (178, 'Puerto Rico'),
       (179, 'Qatar'),
       (180, 'Reunion'),
       (181, 'Romania'),
       (182, 'Russia'),
       (183, 'Rwanda'),
       (184, 'Saint Helena'),
       (185, 'Saint Kitts And Nevis'),
       (186, 'Saint Lucia'),
       (187, 'Saint Pierre and Miquelon'),
       (188, 'Saint Vincent And The Grenadines'),
       (189, 'Saint-Barthelemy'),
       (190, 'Saint-Martin (French part)'),
       (191, 'Samoa'),
       (192, 'San Marino'),
       (193, 'Sao Tome and Principe'),
       (194, 'Saudi Arabia'),
       (195, 'Senegal'),
       (196, 'Serbia'),
       (197, 'Seychelles'),
       (198, 'Sierra Leone'),
       (199, 'Singapore'),
       (200, 'Slovakia'),
       (201, 'Slovenia'),
       (202, 'Solomon Islands'),
       (203, 'Somalia'),
       (204, 'South Africa'),
       (205, 'South Georgia'),
       (206, 'South Sudan'),
       (207, 'Spain'),
       (208, 'Sri Lanka'),
       (209, 'Sudan'),
       (210, 'Suriname'),
       (211, 'Svalbard And Jan Mayen Islands'),
       (212, 'Swaziland'),
       (213, 'Sweden'),
       (214, 'Switzerland'),
       (215, 'Syria'),
       (216, 'Taiwan'),
       (217, 'Tajikistan'),
       (218, 'Tanzania'),
       (219, 'Thailand'),
       (220, 'Togo'),
       (221, 'Tokelau'),
       (222, 'Tonga'),
       (223, 'Trinidad And Tobago'),
       (224, 'Tunisia'),
       (225, 'Turkey'),
       (226, 'Turkmenistan'),
       (227, 'Turks And Caicos Islands'),
       (228, 'Tuvalu'),
       (229, 'Uganda'),
       (230, 'Ukraine'),
       (231, 'United Arab Emirates'),
       (232, 'United Kingdom'),
       (233, 'United States'),
       (234, 'United States Minor Outlying Islands'),
       (235, 'Uruguay'),
       (236, 'Uzbekistan'),
       (237, 'Vanuatu'),
       (238, 'Vatican City State (Holy See)'),
       (239, 'Venezuela'),
       (240, 'Vietnam'),
       (241, 'Virgin Islands (British)'),
       (242, 'Virgin Islands (US)'),
       (243, 'Wallis And Futuna Islands'),
       (244, 'Western Sahara'),
       (245, 'Yemen'),
       (246, 'Zambia'),
       (247, 'Zimbabwe'),
       (248, 'Kosovo'),
       (249, 'Curaçao'),
       (250, 'Sint Maarten (Dutch part)');

create table `friend_finder`.`user`
(
    `id`                   int(11)                not null auto_increment primary key,
    `name`                 varchar(255)           not null, # First Name
    `surname`              varchar(255)           not null, # Last Name
    `email`                varchar(255)           not null,
    `password`             varchar(255)           not null,
    `date_of_birth`        date                   not null,
    `gender`               enum ('MALE','FEMALE') not null,
    `city`                 varchar(255)           not null,
    `country_id`           int(11)                not null,
    `profile_pic`          varchar(255),
    `profile_bg_pic`       varchar(255),
    `personal_information` varchar(255),
    constraint `user_country__fk` foreign key (`country_id`) references `friend_finder`.`user_country` (`id`)
);

create table `friend_finder`.`interests`
(
    `id`       int(11) not null auto_increment primary key,
    `interest` varchar(255),
    `user_id`  int,
    constraint `user_interests__fk` foreign key (`user_id`) references `friend_finder`.`user` (`id`)

);
create table `friend_finder`.`language`
(
    `id`       int(11) not null auto_increment primary key,
    `language` varchar(255),
    `user_id`  int,
    constraint `user_language__fk` foreign key (`user_id`) references `friend_finder`.`user` (`id`)

);

create table `friend_finder`.`education`
(
    `id`             int auto_increment primary key not null,
    `ed_name`        varchar(255),
    `ed_from_date`   int(11),
    `ed_to_date`     int(11),
    `ed_description` varchar(255),
    `user_id`        int(11)                        not null,
    constraint `education__fk` foreign key (`user_id`) references `friend_finder`.`user` (`id`)
);

create table `friend_finder`.`work_experiences`
(
    `id`             int(11) auto_increment primary key not null,
    `company`        varchar(255),
    `we_designation` varchar(255),
    `we_from_date`   int(11),
    `we_to_date`     int(11),
    `we_city`        varchar(255),
    `we_description` varchar(255),
    `user_id`        int(11)                            not null,
    constraint `work_experiences__fk` foreign key (`user_id`) references `friend_finder`.`user` (`id`)
);


create table `friend_finder`.`post`
(
    `id`              int(11) not null auto_increment primary key,
    `user_id`         int(11) not null,
    `description`     text(500),
    `img_name`        varchar(255),
    `music_file_name` varchar(255),
    `like_count`      int(11),
    `dislike_count`   int(11),
    `post_datetime`   datetime,

    constraint `user_post__fk` foreign key (`user_id`) references user (`id`)
);



create table `friend_finder`.`post_like`
(
    `id`          int(11) auto_increment primary key not null,
    `like_status` enum ('LIKE','DISLIKE'),
    `post_id`     int(11)                            not null,
    `user_id`     int(11)                            not null,
    constraint `postLike_postLike_fk` foreign key (`post_id`) references `friend_finder`.`post` (`id`),
    constraint `post_like_userLike_fk` foreign key (`user_id`) references `friend_finder`.`user` (`id`)

);


create table `friend_finder`.`commentary`
(
    `id`              int(11)   not null auto_increment primary key,
    `user_id`         int(11)   not null,
    `post_id`         int(11)   not null,
    `commentary_text` text(500) not null,
    `datetime` datetime not null,

    constraint `user_commentary__fk` foreign key (`user_id`) references user (`id`),
    constraint `commentary_post__fk` foreign key (`post_id`) references post (`id`)
);


create table `friend_finder`.`friend_request`
(
    `sender_id`   int(11) not null,
    `receiver_id` int(11) not null,
    `status`      enum ('PENDING', 'ACCEPTED', 'REJECTED'),

    constraint `friend_request_sender__fk` foreign key (`sender_id`) references user (`id`),
    constraint `friend_request_receiver__fk` foreign key (`receiver_id`) references user (`id`)
);
create table `friend_finder`.`user_friends`
(
    `user_id`   int(11) not null,
    `friend_id` int(11) not null,
    primary key (user_id, friend_id),

    constraint `user__fk` foreign key (`user_id`) references user (`id`),
    constraint `friends__fk` foreign key (`friend_id`) references user (`id`)
);


