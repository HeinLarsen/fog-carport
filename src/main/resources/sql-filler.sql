alter table carport.order_item_fitting AUTO_INCREMENT = 0;
alter table carport.order_item_wood AUTO_INCREMENT = 0;
alter table carport.order_item_roof_tile AUTO_INCREMENT = 0;
alter table carport.membership AUTO_INCREMENT = 0;
alter table carport.category AUTO_INCREMENT = 0;
alter table carport.order_item_screw AUTO_INCREMENT = 0;
alter table carport.order AUTO_INCREMENT = 0;
alter table carport.fitting AUTO_INCREMENT = 0;
alter table carport.user AUTO_INCREMENT = 0;
alter table carport.wood AUTO_INCREMENT = 0;
alter table carport.roof_tile AUTO_INCREMENT = 0;
alter table carport.screw AUTO_INCREMENT = 0;
alter table carport.role AUTO_INCREMENT = 0;
alter table carport.unit AUTO_INCREMENT = 0;


-- unit;

INSERT INTO `carport`.`unit` (`unit`)
VALUES ('stk');
INSERT INTO `carport`.`unit` (`unit`)
VALUES ('pakke');
INSERT INTO `carport`.`unit` (`unit`)
VALUES ('rulle');
INSERT INTO `carport`.`unit` (`unit`)
VALUES ('sæt');


-- category;

insert into carport.category(category)
values ('brædt'),
       ('lægte'),
       ('reglar'),
       ('spærtræ'),
       ('stolpe');

-- membership;

insert into carport.membership (id, membership)
values (1, 'basic'),
       (2, 'member'),
       (3, 'worker');

-- role;

insert into carport.role (id, role)
values (1, 'user'),
       (2, 'admin');

-- zip;

insert into carport.zip(zip, city)
VALUES (2800, 'lyngby'),
       (3200, 'helsinge');

-- user;

insert into carport.user (first_name, last_name, email, password, address, phone_number, role, membership, zip)
VALUES ('admin', 'admin', 'admin', '1234', 'admin', 1234, 2, 3, 3200),
       ('user', 'user', 'user', '1234', '1', 1234, 1, 1, 3200),
       ('memberuser', 'user', 'user', '1234', 'lærkevej 41', 1234, 1, 2, 2800),
       ('workeruser', 'user', '1234', '1234', 'æøåvej', 1234, 1, 3, 3200);

       -- category;

       insert into carport_test.category(category)
       values ('brædt'),
              ('lægte'),
              ('reglar'),
              ('spærtræ'),
              ('stolpe');



-- material;

INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 20.0, 2.5, 1, 1, 1, 174.43);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 20.0, 2.5, 1, 1, 1, 262.03);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 12.5, 2.5, 1, 1, 1, 108.90);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 12.5, 2.5, 1, 1, 1, 163.35);

INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (420, 7.3, 3.8, 0, 2, 1, 120.13);

INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (270, 9.5, 4.5, 0, 3, 1, 5628);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (240, 9.5, 4.5, 0, 3, 1, 56.28);

INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (480, 19.5, 4.5, 0, 4, 1, 205.44);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (600, 19.5, 4.5, 0, 4, 1, 257.70);

INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (300, 9.7, 9.7, 1, 5, 1, 134.85);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (210, 10.0, 1.9, 1, 1, 1, 20.16);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 10.0, 1.9, 1, 5, 1, 51.84);
INSERT INTO `carport`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 10.0, 1.9, 1, 5, 1, 34.56);

INSERT INTO `carport`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`)
VALUES ('Plastmo Ecolite blåtonet', 600, 109, 1, 633.00);
INSERT INTO `carport`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`)
VALUES ('Plastmo Ecolite blåtonet', 360, 109, 1, 266);

INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('plastmo bundskruer 200 stk', 0, 30, 2, 441);
INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('skruer 200 stk.', 4.5, 60, 2, 307.95); -- skruer
INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('Skruer 400 stk.', 4.5, 70, 2, 149.95); -- skruer
INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('beslagskruer 250 stk.', 4.5, 50, 2, 160.95); -- skruer
INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('Skruer 300 stk.', 4.0, 50, 2, 263.95); -- beslagskruer
INSERT INTO `carport`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('bræddebolt', 10, 120, 2, 464); -- bræddebolt

INSERT INTO `carport`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)
VALUES ('firkantskiver', 40, 40, 11, 1, 149.50); -- firkantskiver
INSERT INTO `carport`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)
VALUES ('hulbånd', 20, 10000, 1, 3, 339); -- hulbånd
INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('universal', 190, 1, 76.95); -- universal
INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('universal', 190, 1, 76.95); -- universal
INSERT INTO `carport`.`fitting` (`name`, `width`, `height`, `unit`, `price`)
VALUES ('stalddørsgreb', 75, 50, 4, 183.95); -- stalddørsgreb
INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('t hængsel', 390, 2, 156.95); -- t hængsel
INSERT INTO `carport`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('vinkelbeslag', 35, 1, 13.95); -- vinkelbeslag



-- unit;

INSERT INTO `carport_test`.`unit` (`unit`)
VALUES ('stk');
INSERT INTO `carport_test`.`unit` (`unit`)
VALUES ('pakke');
INSERT INTO `carport_test`.`unit` (`unit`)
VALUES ('rulle');
INSERT INTO `carport_test`.`unit` (`unit`)
VALUES ('sæt');



-- membership;

insert into carport_test.membership (id, membership)
values (1, 'basic'),
       (2, 'member'),
       (3, 'worker');

-- role;

insert into carport_test.role (id, role)
values (1, 'user'),
       (2, 'admin');

-- zip;

insert into carport_test.zip(zip, city)
VALUES (2800, 'lyngby'),
       (3200, 'helsinge');

-- user;

insert into carport_test.user (first_name, last_name, email, password, address, phone_number, role, membership, zip)
VALUES ('admin', 'admin', 'admin', '1234', 'admin', 1234, 2, 3, 3200),
       ('user', 'user', 'user', '1234', '1', 1234, 1, 1, 3200),
       ('memberuser', 'user', 'user', '1234', 'lærkevej 41', 1234, 1, 2, 2800),
       ('workeruser', 'user', '1234', '1234', 'æøåvej', 1234, 1, 3, 3200);


-- material;

INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 20.0, 2.5, 1, 1, 1, 174.43);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 20.0, 2.5, 1, 1, 1, 262.03);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 12.5, 2.5, 1, 1, 1, 108.90);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 12.5, 2.5, 1, 1, 1, 163.35);

INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (420, 7.3, 3.8, 0, 2, 1, 120.13);

INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (270, 9.5, 4.5, 0, 3, 1, 5628);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (240, 9.5, 4.5, 0, 3, 1, 56.28);

INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (480, 19.5, 4.5, 0, 4, 1, 205.44);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (600, 19.5, 4.5, 0, 4, 1, 257.70);

INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (300, 9.7, 9.7, 1, 5, 1, 134.85);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (210, 10.0, 1.9, 1, 5, 1, 20.16);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (540, 10.0, 1.9, 1, 5, 1, 51.84);
INSERT INTO `carport_test`.`wood` (`length`, `width`, `height`, `is_pressure_treated`, `category`, `unit`, `price`)
VALUES (360, 10.0, 1.9, 1, 5, 1, 34.56);

INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`)
VALUES ('Plastmo Ecolite blåtonet', 600, 109, 1, 633.00);
INSERT INTO `carport_test`.`roof_tile` (`name`, `length`, `width`, `unit`, `price`)
VALUES ('Plastmo Ecolite blåtonet', 360, 109, 1, 266);

INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('plastmo bundskruer 200 stk', 0, 30, 2, 441);
INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('skruer 200 stk.', 4.5, 60, 2, 307.95); -- skruer
INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('Skruer 400 stk.', 4.5, 70, 2, 149.95); -- skruer
INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('beslagskruer 250 stk.', 4.5, 50, 2, 160.95); -- skruer
INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('Skruer 300 stk.', 4.0, 50, 2, 263.95); -- beslagskruer
INSERT INTO `carport_test`.`screw` (`name`, `diameter`, `length`, `unit`, `price`)
VALUES ('bræddebolt', 10, 120, 2, 464); -- bræddebolt

INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)
VALUES ('firkantskiver', 40, 40, 11, 1, 149.50); -- firkantskiver
INSERT INTO `carport_test`.`fitting` (`name`, `width`, `length`, `height`, `unit`, `price`)
VALUES ('hulbånd', 20, 10000, 1, 3, 339); -- hulbånd
INSERT INTO `carport_test`.`fitting` (`name`,`height`, `unit`, `price`)
VALUES ('universal',190, 1, 76.95); -- universal
INSERT INTO `carport_test`.`fitting` (`name`,`height`, `unit`, `price`)
VALUES ('universal', 190, 1, 76.95); -- universal
INSERT INTO `carport_test`.`fitting` (`name`, `width`, `height`, `unit`, `price`)
VALUES ('stalddørsgreb', 75, 50, 4, 183.95); -- stalddørsgreb
INSERT INTO `carport_test`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('t hængsel', 390, 2, 156.95); -- t hængsel
INSERT INTO `carport_test`.`fitting` (`name`, `height`, `unit`, `price`)
VALUES ('vinkelbeslag', 35, 1, 13.95); -- vinkelbeslag











