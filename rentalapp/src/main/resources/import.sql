INSERT INTO member (member_no,first_name,last_name) VALUES (001,'Nathaniel','Steers');
INSERT INTO member (member_no,first_name,last_name) VALUES (11001,'Jack','Brown');
INSERT INTO member (member_no,first_name,last_name) VALUES (11009,'James','White');
INSERT INTO member (member_no,first_name,last_name) VALUES (11014,'Barbara','Jones');
INSERT INTO member (member_no,first_name,last_name) VALUES (11022,'Fred','Johnson');
INSERT INTO member (member_no,first_name,last_name) VALUES (11023,'Dan','Greggs');
INSERT INTO member (member_no,first_name,last_name) VALUES (11029,'David','Jones');
INSERT INTO member (member_no,first_name,last_name) VALUES (11033,'Mark','James');
INSERT INTO member (member_no,first_name,last_name) VALUES (11036,'Tod','Rutherfold');
INSERT INTO member (member_no,first_name,last_name) VALUES (11037,'George','Thatcher');
INSERT INTO member (member_no,first_name,last_name) VALUES (11038,'Nick','Parsons');
INSERT INTO member (member_no,first_name,last_name) VALUES (11048,'George','Blair');
INSERT INTO member (member_no,first_name,last_name) VALUES (11049,'Kate','May');
INSERT INTO member (member_no,first_name,last_name) VALUES (11057,'Sam','Foulds');
INSERT INTO member (member_no,first_name,last_name) VALUES (11058,'Quincy','Edwards');
INSERT INTO member (member_no,first_name,last_name) VALUES (11067,'James','Webb');
INSERT INTO member (member_no,first_name,last_name) VALUES (11068,'Martha','Berry');
INSERT INTO member (member_no,first_name,last_name) VALUES (11069,'Mary','Davids');
INSERT INTO member (member_no,first_name,last_name) VALUES (11079,'Dave','Edwards');
INSERT INTO member (member_no,first_name,last_name) VALUES (11092,'Lisa','Smith');
INSERT INTO member (member_no,first_name,last_name) VALUES (11132,'Josh','Smith');
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1001,'The Man Who Knew Infinity','Film',3);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1002,'The Legend of Tarzan','Film',4);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1003,'Nerve','Film',2);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1010,'I Am Bolt','Film',6);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1012,'The Nice Guys','Film',7);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1015,'The Godfather','Film',4);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1027,'The Bureau Season 1','TV',5);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1028,'The Sellout','Book',8);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1032,'Blackadder','TV',8);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1037,'Game of Thrones Season 6','TV',9);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1042,'Guinness World Records 2017','Book',10);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1078,'The Night Manager','TV',7);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1109,'The Mistletoe Murder and Other Stories','Book',7);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1214,'Elementary','TV',8);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1217,'Million Reasons','Music',8);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1305,'Classic House','Music',6);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1328,'Piano Man','Music',9);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1329,'Bon Jovi Greatest Hits','Music',10);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1338,'Fifa Mobile Football','Game',10);
INSERT INTO item (item_no,item_name,item_type,number_available) VALUES (1342,'Dawn of Titans','Game',10);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000001,1001,11009,'2016-12-01', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000002,1003,11001,'2016-12-02', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000003,1010,11009,'2016-12-02', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000004,1012,11036,'2016-12-03', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000005,1010,11023,'2016-12-03', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000006,1027,11023,'2016-12-03', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000007,1032,11049,'2016-12-03', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000008,1109,11079,'2016-12-05', 0);
INSERT INTO transaction (transaction_no, item_number, member_number, transaction_date, expired) VALUES (000009,1214,11092,'2016-12-05', 0);
