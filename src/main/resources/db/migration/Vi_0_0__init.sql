DROP DATABASE IF EXISTS covid;
CREATE DATABASE covid;
create sequence hibernate_sequence;


create table IF NOT EXISTS revinfo
(
    rev      integer not null
        constraint revinfo_pkey
            primary key,
    revtstmp bigint
);

create table IF NOT EXISTS table_role
(
    role_id      bigserial not null
        constraint table_role_pkey
            primary key,
    created_by   varchar(255),
    created_date timestamp not null,
    modified_by  varchar(255),
    updated_date timestamp,
    version      bigint,
    role         varchar(255)
);

create table IF NOT EXISTS table_user
(
    user_id      bigserial not null
        constraint table_user_pkey
            primary key,
    created_by   varchar(255),
    created_date timestamp not null,
    modified_by  varchar(255),
    updated_date timestamp,
    version      bigint,
    active       boolean,
    email        varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    username     varchar(255)
        constraint uk_username
            unique
);

create table IF NOT EXISTS user_role
(
    user_id bigint not null
        constraint fk_user_role_to_user
            references table_user,
    role_id bigint not null
        constraint fk_user_role_to_role
            references table_role
);


create table IF NOT EXISTS table_user_aud
(
    user_id      bigint  not null,
    rev          integer not null
        constraint fk_revinfotouseraud
            references revinfo,
    revtype      smallint,
    created_by   varchar(255),
    created_date timestamp,
    modified_by  varchar(255),
    updated_date timestamp,
    active       boolean,
    email        varchar(255),
    fullname     varchar(255),
    password     varchar(255),
    username     varchar(255),
    constraint table_user_aud_pkey
        primary key (user_id, rev)
);

create table IF NOT EXISTS user_role
(
    user_id bigint not null
        constraint fk_userrole_to_user
            references table_user,
    role_id bigint not null
        constraint fk_userrole_to_role
            references table_role
);




INSERT INTO table_role (created_by, created_date, modified_by, updated_date, version, role)
VALUES ('Admin Isztrátor', now(), null, null, 1, 'Admin'),
       ('Admin Isztrátor', now(), null, null, 1, 'User'),
       ('Admin Isztrátor', now(), null, null, 1, 'User'),
       ('Admin Isztrátor', now(), null, null, 1, 'User');

INSERT INTO table_user (created_by, created_date, modified_by, updated_date, version, active, email, fullname, password,
                         username)
VALUES ('Adin Istrator', now(), null, null, 1, true, 'admin@admin.hu', 'Adin Istrator',
        '$2a$10$zrY19Kq0RUdQDA1Qju7UEeturZktURFGdOPSNrgD.0GsX/QWP87vO', 'Admin'),
       ('Adin Istrator', now(), null, null, 1, true, 'test1@test1.hu', 'Teszt Elek',
        '$2a$10$zrY19Kq0RUdQDA1Qju7UEeturZktURFGdOPSNrgD.0GsX/QWP87vO', 'Teszt1'),
       ('Adin Istrator', now(), null, null, 1, true, 'test2@test2.hu', 'Teszt Elnék',
        '$2a$10$zrY19Kq0RUdQDA1Qju7UEeturZktURFGdOPSNrgD.0GsX/QWP87vO', 'Teszt2'),
       ('Adin Istrator', now(), null, null, 1, true, 'test3@test3.hu', 'Teszt Eltem',
        '$2a$10$zrY19Kq0RUdQDA1Qju7UEeturZktURFGdOPSNrgD.0GsX/QWP87vO', 'Teszt3');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role (user_id, role_id)
VALUES (3, 3);
INSERT INTO user_role (user_id, role_id)
VALUES (4, 4);



create table IF NOT EXISTS table_country
(
    country_id       bigserial    not null
        constraint table_country_pkey
            primary key,
    created_by       varchar(255),
    created_date     timestamp not null,
    modified_by      varchar(255),
    updated_date     timestamp,
    version          bigint,
    iso_code     varchar(5)
        constraint uk_isocode
            unique,
    name    varchar(150)
        constraint uk_name
            unique,
    region varchar(255),
    population     bigint
);

INSERT INTO table_country(created_by, created_date, name,  region, iso_code, population) VALUES
        ('Adin Istrator', now(), 'Afghanistan','Ázsia','AFG',39000000),
        ('Adin Istrator', now(), 'Andorra','AD','Europa',77000),
        ('Adin Istrator', now(), 'United Arab Emirates)','Ázsia','ARE',9900000),
        ('Adin Istrator', now(), 'Antigua and Barbuda','Amerika','ATG',97000),
        ('Adin Istrator', now(), 'Anguilla','Amerika','AIA',15000),
        ('Adin Istrator', now(), 'Albania','Európa','ALB',2800000),
        ('Adin Istrator', now(), 'Armenia','Ázsia','ARM',2900000),
        ('Adin Istrator', now(), 'Angola','Afrika','AGO',32800000),
        ('Adin Istrator', now(), 'Antarctica','Amerika','ATA',2000),
        ('Adin Istrator', now(), 'Argentina','Amerika','ARG',45400000),
        ('Adin Istrator', now(), 'American Samoa','Ausztrália','ASM',55000),
        ('Adin Istrator', now(), 'Austria','Európa','AUT',8900000),
        ('Adin Istrator', now(), 'Australia','Ausztrália','AUS',25000000),
        ('Adin Istrator', now(), 'Aruba','Amerika','ABW',106000),
        ('Adin Istrator', now(), 'Åland Islands','Európa','ALA', 29000),
        ('Adin Istrator', now(), 'Azerbaijan','Ázsia','AZE',10000000),
        ('Adin Istrator', now(), 'Bosnia and Herzegovina','Európa','BIH',3300000),
        ('Adin Istrator', now(), 'Barbados','Amerika','BRB',287000),
        ('Adin Istrator', now(), 'Bangladesh','Ázsia','BGD',164000000),
        ('Adin Istrator', now(), 'Belgium','Európa','BEL',11000000),
        ('Adin Istrator', now(), 'Burkina Faso','Afrika','BFA',20900000),
        ('Adin Istrator', now(), 'Bulgaria','Európa','BGR',6900000),
        ('Adin Istrator', now(), 'Bahrain','Ázsia','BHR',1700000),
        ('Adin Istrator', now(), 'Burundi','Afrika','BDI',11800000),
        ('Adin Istrator', now(), 'Benin','Afrika','BEN',12120000),
        ('Adin Istrator', now(), 'Saint Barthélemy','Amerika','BLM',9000),
        ('Adin Istrator', now(), 'Bermuda','Amerika','BMU',63903),
        ('Adin Istrator', now(), 'Brunei Darussalam','Ázsia','BRN',437000),
        ('Adin Istrator', now(), 'Bolivia Plurinational','Amerika','BOL',116000000),
        ('Adin Istrator', now(), 'Bonaire','Amerika','BES',20000),
        ('Adin Istrator', now(), 'Brazil','Amerika','BRA',212600000),
        ('Adin Istrator', now(), 'Commonwealth of The Bahamas','Amerika','BHS',395000),
        ('Adin Istrator', now(), 'Bhutan','Ázsia','BTN',771000),
        ('Adin Istrator', now(), 'Bouvet Island','Ausztrália','BVT',1),
        ('Adin Istrator', now(), 'Botswana','Afrika','BWA',2352000),
        ('Adin Istrator', now(), 'Belarus','Európa','BLR',9300000),
        ('Adin Istrator', now(), 'Belize','Amerika','BLZ',397000),
        ('Adin Istrator', now(), 'Canada','Amerika','CAN',38000000),
        ('Adin Istrator', now(), 'Cocos Keeling','Ausztrália','CCK',596),
        ('Adin Istrator', now(), 'Congo the Democratic Republic','Afrika','COD',89560000),
        ('Adin Istrator', now(), 'Central African Republic','Afrika','CAF',4800000),
        ('Adin Istrator', now(), 'Congo','Afrika','COG',5500000),
        ('Adin Istrator', now(), 'Switzerland','Európa','CHE',8600000),
        ('Adin Istrator', now(), 'Côte d Ivoire','Afrika','CIV',26380000),
        ('Adin Istrator', now(), 'Cook Islands','Amerika','COK',17000),
        ('Adin Istrator', now(), 'Chile','Amerika','CHL',19120000),
        ('Adin Istrator', now(), 'Cameroon','Afrika','CMR',26500000),
        ('Adin Istrator', now(), 'China','Ázsia','CHN',1402000000),
        ('Adin Istrator', now(), 'Colombia','Amerika','COL',50880000),
        ('Adin Istrator', now(), 'Costa Rica','Amerika','CRI',5094000),
        ('Adin Istrator', now(), 'Cuba','Amerika','CUB',11000000),
        ('Adin Istrator', now(), 'Cabo Verde','Afrika','CPV',555000),
        ('Adin Istrator', now(), 'Curaçao','Amerika','CUW',155000),
        ('Adin Istrator', now(), 'Christmas Island','Ausztrália','CXR',1400),
        ('Adin Istrator', now(), 'Cyprus','Ázsia','CYP',1200000),
        ('Adin Istrator', now(), 'Czechia','Európa','CZE',10000000),
        ('Adin Istrator', now(), 'Germany','Európa','DEU',83240000),
        ('Adin Istrator', now(), 'Djibouti','Afrika','DJI',988000),
        ('Adin Istrator', now(), 'Denmark','Európa','DNK',5800000),
        ('Adin Istrator', now(), 'Dominica','Amerika','DMA',71991),
        ('Adin Istrator', now(), 'Dominican Republic','Amerika','DOM',10850000),
        ('Adin Istrator', now(), 'Algeria','Afrika','DZA',43000000),
        ('Adin Istrator', now(), 'Ecuador','Amerika','ECU',17000000),
        ('Adin Istrator', now(), 'Estonia','Európa','EST',1331000),
        ('Adin Istrator', now(), 'Egypt','Afrika','EGY',102000000),
        ('Adin Istrator', now(), 'Western Sahara','Afrika','ESH',732),
        ('Adin Istrator', now(), 'Eritrea','Afrika','ERI',3000000),
        ('Adin Istrator', now(), 'Spain','ES','ESP',724),
        ('Adin Istrator', now(), 'Ethiopia','Afrika','ETH',115000000),
        ('Adin Istrator', now(), 'Finland','Európa','FIN',5531000),
        ('Adin Istrator', now(), 'Fiji','Ausztrália','FJI',896000),
        ('Adin Istrator', now(), 'Falkland Islands','Amerika','FLK',3800),
        ('Adin Istrator', now(), 'Federated States of Micronesia','Ázsia','FSM',115000),
        ('Adin Istrator', now(), 'Faroe Islands','Európa','FRO',48000),
        ('Adin Istrator', now(), 'France','Európa','FRA',67000000),
        ('Adin Istrator', now(), 'Gabon','Afrika','GAB',2226000),
        ('Adin Istrator', now(), 'Great Britain','Európa','GBR',67220000),
        ('Adin Istrator', now(), 'Grenada','Amerika','GRD',112000),
        ('Adin Istrator', now(), 'Georgia','Amerika','GEO',10620000),
        ('Adin Istrator', now(), 'French Guiana','Amerika','GUF',294000),
        ('Adin Istrator', now(), 'Guernsey','Európa','GGY',200000),
        ('Adin Istrator', now(), 'Ghana','Afrika','GHA',31000000),
        ('Adin Istrator', now(), 'Gibraltar','Európa','GIB',33000),
        ('Adin Istrator', now(), 'Greenland','Amerika','GRL',56000),
        ('Adin Istrator', now(), 'Gambia','Afrika','GMB',2417000),
        ('Adin Istrator', now(), 'Guinea','Afrika','GIN',13130000),
        ('Adin Istrator', now(), 'Guadeloupe','Amerika','GLP',395000),
        ('Adin Istrator', now(), 'Equatorial Guinea','Afrika','GNQ',1400000),
        ('Adin Istrator', now(), 'Greece','Európa','GRC',10720000),
        ('Adin Istrator', now(), 'South Georgia and the South Sandwich Islands','Amerika','SGS',30),
        ('Adin Istrator', now(), 'Guatemala','Amerika','GTM',16860000),
        ('Adin Istrator', now(), 'Guam','Ázsia','GUM',168000),
        ('Adin Istrator', now(), 'Guinea-Bissau','Afrika','GNB',1600000),
        ('Adin Istrator', now(), 'Guyana','Amerika','GUY',786000),
        ('Adin Istrator', now(), 'Hong Kong','Ázsia','HKG',7482000),
        ('Adin Istrator', now(), 'Heard Island and McDonald Islands','Afrika','HMD',0),
        ('Adin Istrator', now(), 'Honduras','Amerika','HND',9905000),
        ('Adin Istrator', now(), 'Croatia','Európa','HRV',4047000),
        ('Adin Istrator', now(), 'Haiti','Amerika','HTI',11400000),
        ('Adin Istrator', now(), 'Hungary','Európa','HUN',9750000),
        ('Adin Istrator', now(), 'Indonesia','Ázsia','IDN',273500000),
        ('Adin Istrator', now(), 'Ireland','Európa','IRL',6572000),
        ('Adin Istrator', now(), 'Israel','Ázsia','ISR',9217000),
        ('Adin Istrator', now(), 'Isle of Man','Európa','IMN',85000),
        ('Adin Istrator', now(), 'India','Ázsia','IND',1380000000),
        ('Adin Istrator', now(), 'British Indian Ocean Territory','Ázsia','IOT',86),
        ('Adin Istrator', now(), 'Iraq','Ázsia','IRQ',40000000),
        ('Adin Istrator', now(), 'Iran','Ázsia','IRN',83990000),
        ('Adin Istrator', now(), 'Iceland','Európa','ISL',366000),
        ('Adin Istrator', now(), 'Italy','Európa','ITA',59550000),
        ('Adin Istrator', now(), 'Jersey','Európa','JEY',97000),
        ('Adin Istrator', now(), 'Jamaica','Amerika','JAM',2961000),
        ('Adin Istrator', now(), 'Jordan','Ázsia','JOR',10200000),
        ('Adin Istrator', now(), 'Japan','Ázsia','JPN',125800000),
        ('Adin Istrator', now(), 'Kenya','Afrika','KEN',53770000),
        ('Adin Istrator', now(), 'Kyrgyzstan','Ázsia','KGZ',6592000),
        ('Adin Istrator', now(), 'Cambodia','Ázsia','KHM',16720000),
        ('Adin Istrator', now(), 'Kiribati','Ausztrália','KIR',119000),
        ('Adin Istrator', now(), 'Comoros','Afrika','COM',869595),
        ('Adin Istrator', now(), 'Saint Kitts and Nevis','Amerika','KNA',53192),
        ('Adin Istrator', now(), 'North Korea','Ázsia','PRK',25549604),
        ('Adin Istrator', now(), 'South Korea','Ázsia','KOR',51780000),
        ('Adin Istrator', now(), 'Kuwait','Ázsia','KWT',4271000),
        ('Adin Istrator', now(), 'Cayman Islands','Amerika','CYM',65720),
        ('Adin Istrator', now(), 'Kazakhstan','Ázsia','KAZ',18750000),
        ('Adin Istrator', now(), 'Laos','Ázsia','LAO',7276000),
        ('Adin Istrator', now(), 'Lebanon','Ázsia','LBN',6825000),
        ('Adin Istrator', now(), 'Saint Lucia','Amerika','LCA',183692),
        ('Adin Istrator', now(), 'Liechtenstein','Európa','LIE',38137),
        ('Adin Istrator', now(), 'Sri Lanka','Ázsia','LKA',21920000),
        ('Adin Istrator', now(), 'Liberia','Afrika','LBR',5058),
        ('Adin Istrator', now(), 'Lesotho','Afrika','LSO',2142000),
        ('Adin Istrator', now(), 'Lithuania','Európa','LTU',2795000),
        ('Adin Istrator', now(), 'Luxembourg','Európa','LUX',632275),
        ('Adin Istrator', now(), 'Latvia','Európa','LVA',1902000),
        ('Adin Istrator', now(), 'Libya','Afrika','LBY',6871000),
        ('Adin Istrator', now(), 'Morocco','Afrika','MAR',36910000),
        ('Adin Istrator', now(), 'Monaco','Európa','MCO',39244),
        ('Adin Istrator', now(), 'Moldova','Európa','MDA',2597000),
        ('Adin Istrator', now(), 'Montenegro','Európa','MNE',621000),
        ('Adin Istrator', now(), 'Saint Martin','Amerika','MAF',38000),
        ('Adin Istrator', now(), 'Madagascar','Afrika','MDG',27690000),
        ('Adin Istrator', now(), 'Marshall Islands','Ausztrália','MHL',59194),
        ('Adin Istrator', now(), 'North Macedonia','Európa','MKD',2083000),
        ('Adin Istrator', now(), 'Mali','Afrika','MLI',20250000),
        ('Adin Istrator', now(), 'Myanmar','Ázsia','MMR',54410000),
        ('Adin Istrator', now(), 'Mongolia','Ázsia','MNG',3278000),
        ('Adin Istrator', now(), 'Macao','Ázsia','MAC',649342),
        ('Adin Istrator', now(), 'Northern Mariana Islands','Ázsia','MNP',57557),
        ('Adin Istrator', now(), 'Martinique','Amerika','MTQ',376000),
        ('Adin Istrator', now(), 'Mauritania','Afrika','MRT',4650000),
        ('Adin Istrator', now(), 'Montserrat','Európa','MSR',500),
        ('Adin Istrator', now(), 'Malta','Európa','MLT',525000),
        ('Adin Istrator', now(), 'Mauritius','Afrika','MUS',1266000),
        ('Adin Istrator', now(), 'Maldives','Ázsia','MDV',540542),
        ('Adin Istrator', now(), 'Malawi','Afrika','MWI',19130000),
        ('Adin Istrator', now(), 'Mexico','Amerika','MEX',128900000),
        ('Adin Istrator', now(), 'Malaysia','Ázsia','MYS',32370000),
        ('Adin Istrator', now(), 'Mozambique','Afrika','MOZ',31260000),
        ('Adin Istrator', now(), 'Namibia','Afrika','NAM',2541000),
        ('Adin Istrator', now(), 'New Caledonia','Ausztrália','NCL',271000),
        ('Adin Istrator', now(), 'Niger','Afrika','NER',24112753),
        ('Adin Istrator', now(), 'Norfolk Island','Ausztrália','NFK',2169),
        ('Adin Istrator', now(), 'Nigeria','Afrika','NGA',206100000),
        ('Adin Istrator', now(), 'Nicaragua','Amerika','NIC',6625000),
        ('Adin Istrator', now(), 'Netherlands','Európa','NLD',17440000),
        ('Adin Istrator', now(), 'Norway','Európa','NOR',5379000),
        ('Adin Istrator', now(), 'Nepal','Ázsia','NPL',29140000),
        ('Adin Istrator', now(), 'Nauru','Ázsia','NRU',10800),
        ('Adin Istrator', now(), 'Niue','Ausztrália','NIU',1620),
        ('Adin Istrator', now(), 'New Zealand','Ausztrália','NZL',5112000),
        ('Adin Istrator', now(), 'Oman','Ázsia','OMN',5107000),
        ('Adin Istrator', now(), 'Panama','Amerika','PAN',4315000),
        ('Adin Istrator', now(), 'Peru','Amerika','PER',32970000),
        ('Adin Istrator', now(), 'French Polynesia','Amerika','PYF',280000),
        ('Adin Istrator', now(), 'Papua New Guinea','Ausztrália','PNG',8947000),
        ('Adin Istrator', now(), 'Philippines','Ázsia','PHL',109600000),
        ('Adin Istrator', now(), 'Pakistan','Ázsia','PAK',220900000),
        ('Adin Istrator', now(), 'Poland','Európa','POL',37950000),
        ('Adin Istrator', now(), 'Saint Pierre and Miquelon','Amerika','SPM',5888),
        ('Adin Istrator', now(), 'Pitcairn','Amerika','PCN',67),
        ('Adin Istrator', now(), 'Puerto Rico','Amerika','PRI',3194000),
        ('Adin Istrator', now(), 'Palestine','Ázsia','PSE',4803),
        ('Adin Istrator', now(), 'Portugal','Európa','PRT',10310000),
        ('Adin Istrator', now(), 'Palau','Ázsia','PLW',18092),
        ('Adin Istrator', now(), 'Paraguay','Amerika','PRY',6007133000),
        ('Adin Istrator', now(), 'Qatar','Ázsia','QAT',2881000),
        ('Adin Istrator', now(), 'Réunion','Afrika','REU',859959),
        ('Adin Istrator', now(), 'Romania','Európa','ROU',19290000),
        ('Adin Istrator', now(), 'Serbia','Európa','SRB',6908000),
        ('Adin Istrator', now(), 'Russia','Ázsia','RUS',144100000),
        ('Adin Istrator', now(), 'Rwanda','Afrika','RWA',12950000),
        ('Adin Istrator', now(), 'Saudi Arabia','Ázsia','SAU',34810000),
        ('Adin Istrator', now(), 'Solomon Islands','Ausztrália','SLB',686878),
        ('Adin Istrator', now(), 'Seychelles','Afrika','SYC',98462),
        ('Adin Istrator', now(), 'Sudan','Afrika','SDN',43850000),
        ('Adin Istrator', now(), 'Sweden','Európa','SWE',10350000),
        ('Adin Istrator', now(), 'Singapore','Ázsia','SGP',5686000),
        ('Adin Istrator', now(), 'Saint Helena','Afrika','SHN',4255),
        ('Adin Istrator', now(), 'Slovenia','Európa','SVN',2100000),
        ('Adin Istrator', now(), 'Svalbard and Jan Mayen','Európa','SJM',3000),
        ('Adin Istrator', now(), 'Slovakia','Európa','SVK',5459000),
        ('Adin Istrator', now(), 'Sierra Leone','Afrika','SLE',7977000),
        ('Adin Istrator', now(), 'San Marino','Európa','SMR',33938),
        ('Adin Istrator', now(), 'Senegal','Afrika','SEN',16740000),
        ('Adin Istrator', now(), 'Somalia','Afrika','SOM',15890000),
        ('Adin Istrator', now(), 'Suriname','Amerika','SUR',586634),
        ('Adin Istrator', now(), 'South Sudan','Afrika','SSD',11190000),
        ('Adin Istrator', now(), 'Sao Tome and Principe','Afrika','STP',219161),
        ('Adin Istrator', now(), 'El Salvador','Amerika','SLV',6486000),
        ('Adin Istrator', now(), 'Sint Maarten','Amerika','SXM',41486),
        ('Adin Istrator', now(), 'Syria','Ázsia','SYR',17500000),
        ('Adin Istrator', now(), 'Eswatini','Afrika','SWZ',1160000),
        ('Adin Istrator', now(), 'Turks and Caicos Islands','Amerika','TCA',38718),
        ('Adin Istrator', now(), 'Chad','Afrika','TCD',16430000),
        ('Adin Istrator', now(), 'French Southern Territories','Afrika','ATF',0),
        ('Adin Istrator', now(), 'Togo','Afrika','TGO',8279000),
        ('Adin Istrator', now(), 'Thailand','Ázsia','THA',69800000),
        ('Adin Istrator', now(), 'Tajikistan','Ázsia','TJK',9538000),
        ('Adin Istrator', now(), 'Tokelau','Ausztrália','TKL',1411),
        ('Adin Istrator', now(), 'Timor-Leste','Ázsia','TLS',1318000),
        ('Adin Istrator', now(), 'Turkmenistan','Ázsia','TKM',6031000),
        ('Adin Istrator', now(), 'Tunisia','Afrika','TUN',11820000),
        ('Adin Istrator', now(), 'Tonga','Ausztrália','TON',105697),
        ('Adin Istrator', now(), 'Turkey','Ázsia','TUR',84340000),
        ('Adin Istrator', now(), 'Trinidad and Tobago','Amerika','TTO',1399000),
        ('Adin Istrator', now(), 'Tuvalu','Ausztrália','TUV',11792),
        ('Adin Istrator', now(), 'Taiwan','Ázsia','TWN',23570000),
        ('Adin Istrator', now(), 'Tanzania','Afrika','TZA',59730000),
        ('Adin Istrator', now(), 'Ukraine','Európa','UKR',44130000),
        ('Adin Istrator', now(), 'Uganda','Afrika','UGA',45740000),
        ('Adin Istrator', now(), 'United States Minor Outlying Islands the)','Amerika','UMI',300),
        ('Adin Istrator', now(), 'United States of America','Amerika','USA',329500000),
        ('Adin Istrator', now(), 'Uruguay','Amerika','URY',3474000),
        ('Adin Istrator', now(), 'Uzbekistan','Ázsia','UZB',34230000),
        ('Adin Istrator', now(), 'Holy See','Európa','VAT',825),
        ('Adin Istrator', now(), 'Saint Vincent and the Grenadines','Amerika','VCT',110947),
        ('Adin Istrator', now(), 'Venezuela','Amerika','VEN',28440000),
        ('Adin Istrator', now(), 'Virgin Islands British)','Amerika','VGB',30237),
        ('Adin Istrator', now(), 'Virgin Islands U.S.','Amerika','VIR',106290),
        ('Adin Istrator', now(), 'Vietnam','Ázsia','VNM',97340000),
        ('Adin Istrator', now(), 'Vanuatu','Ausztrália','VUT',307150),
        ('Adin Istrator', now(), 'Wallis and Futuna','Ausztrália','WLF',15289),
        ('Adin Istrator', now(), 'Samoa','Ausztrália','WSM',198410),
        ('Adin Istrator', now(), 'Yemen','Ázsia','YEM',29830000),
        ('Adin Istrator', now(), 'Mayotte','Afrika','MYT',270372),
        ('Adin Istrator', now(), 'South Africa','Afrika','ZAF',59310000),
        ('Adin Istrator', now(), 'Zambia','Afrika','ZMB',18380000),
        ('Adin Istrator', now(), 'Zimbabwe','Afrika','ZWE',14860000);


create table IF NOT EXISTS table_daily_stat
(
    id       bigserial    not null
        constraint table_daily_stat_pkey
            primary key,
    created_by       varchar(255),
    created_date     timestamp not null,
    modified_by      varchar(255),
    updated_date     timestamp,
    testing          bigint,
    new_infected          bigint,
    deaths          bigint,
    healing          bigint,
    country_id     bigint
        constraint fk_daily_stat_to_country
            references table_country
);

create table IF NOT EXISTS table_daily_stat_aud
(
    id       bigint  not null,
    rev              integer not null
    constraint fk_daily_stat_to_revinfo
        references revinfo,
    revtype         smallint,
    created_by       varchar(255),
    created_date     timestamp not null,
    modified_by      varchar(255),
    updated_date     timestamp,
    testing          bigint,
    new_infected          bigint,
    deaths          bigint,
    healing          bigint,
    country_id     bigint,
        constraint table_daily_stat_aud_pkey
            primary key (id, rev)
);

