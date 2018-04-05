CREATE TABLE IF NOT EXISTS `users` (
	`userSeq` INT auto_increment,
    `uuid` UUID NOT NULL default UUID(), 
    `email` VARCHAR(254) NOT NULL, 
    `username` VARCHAR(25) NOT NULL, 
    `password` VARCHAR(1024) NOT NULL, 
    `authority` VARCHAR(30) NOT NULL,
    `nickname` VARCHAR(25) NOT NULL, 
    `created` TIMESTAMP NOT NULL DEFAULT now(), 
    `enabled` BOOLEAN NOT NULL DEFAULT TRUE,
    `accountNonExpired` BOOLEAN NOT NULL DEFAULT TRUE,
    `accountNonLocked` BOOLEAN NOT NULL DEFAULT TRUE,
    `credentialsNonExpired` BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (`userSeq`), 
    UNIQUE INDEX `uuid` (`uuid`),
    UNIQUE INDEX `email` (`email`) 
);