create database trueherbDB;

use trueherbDB;



alter table users modify column is_email_verified varchar(1);

alter table users modify column is_first_time_login varchar(1) default 0;

alter table users modify column uid int(11) not null auto_increment;

alter table warehouse modify column wid int(11) not null auto_increment;


--SUPER ADMIN PASSWORD: trueherb@123
-- SUPER ADMIN USERNAME:trueherb EMAIL ID:admin@trueherb.com

INSERT INTO users (`email_id`, `password`,`token`,`username`, `utype`,  `is_first_time_login`,`is_email_verified`,`created_date`, `modified_date`) VALUES 
('admin@trueherb.com', 'dHJ1ZWhlcmJAMTIz','21232f297a57a5a743894a0e4a801fc3','trueherb', 1,0,1,now(), now());


CREATE INDEX sales_orderno ON order_info (sales_orderno);

CREATE INDEX createdBy ON order_info (created_by);







	mysql -uroot -porderMark@123 LappOrderMarkingDB;






ALTER TABLE order_info CHARACTER SET latin1 COLLATE latin1_swedish_ci;
ALTER TABLE order_line_item CHARACTER SET latin1 COLLATE latin1_swedish_ci;