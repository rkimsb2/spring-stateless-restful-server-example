insert into users (
	`email`,
	`username`, 
	`password`, 
	`authority`, 
	`nickname`
) values (
	'admin@krw.com',
	'admin',
	'$2a$10$Kp0fTC2Plcpgai1tkVBxv.c77MKce1cfvV6KfwH1Rb9FLz45IfIPe', -- 1234
	'ADMIN',
	'admin'
);