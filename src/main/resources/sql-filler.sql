// material;

INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('360', '200', '25', 'understærnbrædder til for og bag ende', '174.43', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('540', '200', '25', 'understærnbrædder til siderne', '261.63', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('360', '125', '25', 'overstærnbrædder til forenden', '108.90', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('540', '125', '25', 'oversternbrædder til siderne', '163.35', '1');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('420', '73', '38', 'til z på bagside af dør', '120.13', '2');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('270', '95', '45', 'løsholter til skur gavle', '5628', '3');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('240', '95', '45', 'løsholter til skur sider', '56.28', '3');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('480', '195', '45', 'Remme i sider, sadles ned i stolper', '205.44', '4');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('600', '195', '45', 'Spær, monteres på rem', '257.70', '4');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('300', '97', '97', 'Stolper nedgraves 90 cm. i jord', '134.85', '5');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('210', '100', '19', 'til beklædning af skur 1 på 2', '20.16', '5');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('540', '100', '19', 'vandbrædt på stern i sider', '51.84', '5');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('360', '100', '19', 'vandbrædt på stern i forende', '34.56', '5');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('600', 'tagplader monteres på spær', '633.00', '6');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('360', 'tagplader monteres på spær', '266', '6');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('30', 'skruer til tagplader', '441', '7');
INSERT INTO `carport`.`material` (`length`, `width`, `width`, `description`, `price`, `type`) VALUES ('1000', '20', 'til vindkryds og spær', '339', '8');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('190', 'til montering af spær på rem', '76.95', '9');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('190', 'til montering af spær på rem', '76.95', '10');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('60', '60', '4.5', 'Til montering af stern&vandbrædt', '307.95', '11');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('50', '50', '4.0', 'Til montering af universalbeslag + hulbånd', '263.95', '12');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('120', '120', '10', 'Til montering af rem på stolper', '464', '13');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('40', '11', '40', 'Til montering af rem på stolper', '149.50', '14');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('70', '70', '4.5', 'til montering af yderste beklædning', '149.95', '11');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('50', '50', '4.5', 'til montering af inderste beklædning', '160.95', '11');
INSERT INTO `carport`.`material` (`length`, `width`, `height`, `description`, `price`, `type`) VALUES ('75', '75', '50', 'Til lås på dør i skur', '183.95', '15');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('390', 'til skurdø', '156.95', '16');
INSERT INTO `carport`.`material` (`length`, `description`, `price`, `type`) VALUES ('35', 'Til montering af løsholter i skur', '13.95', '17');





// packaging;


INSERT INTO `carport`.`packaging` (`type`) VALUES ('stk');
INSERT INTO `carport`.`packaging` (`type`) VALUES ('pakke');
INSERT INTO `carport`.`packaging` (`type`) VALUES ('rulle');
INSERT INTO `carport`.`packaging` (`type`) VALUES ('sæt');



insert into carport.type (type) values
('trykimp. brædt'),
('lægte ubh'),
('regular ub.'),
('spærtræ ubh.'),
('trykimp. stolpe'),
('Plastmo Ecolite blåtonet'),
('Plastmo Trapez Bundskrue'),
('hulbånd'),
('universalbeslag højre'),
('universalbeslag venstre'),
('skruer'),
('beslagskruer'),
('bræddebolt'),
('firkantskiver'),
('stalddørsgreb'),
('t-hængsel'),
('vinkelbeslag');




insert into carport.membership (id, type) values
(1, 'basic'),
(2, 'member'),
(3, 'worker');

insert into carport.role (id, type) values
(1, 'user'),
(2, 'admin');


insert into carport.zip(zip, city) VALUES
(2800, 'lyngby'),
(3200, 'helsinge');


insert into carport.user (first_name, last_name, email, password, address, phone_number, role_id, membership_id, zip) VALUES
('admin', 'admin', 'admin', '1234', 'admin', 1234, 2, 3, 3200 ),
('user', 'user', 'user', '1234', '1', 1234, 1, 1, 3200),
('memberuser', 'user', 'user', '1234', 'lærkevej 41', 1234, 1, 2, 2800 ),
('workeruser', 'user', '1234', '1234', 'æøåvej', 1234, 1, 3, 3200);