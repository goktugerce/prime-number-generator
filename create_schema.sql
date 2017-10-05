begin;

drop SCHEMA IF EXISTS simscale;
create SCHEMA simscale;
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on simscale.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database

use simscale;

CREATE TABLE simscale.requests
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	timestamp DATETIME NOT NULL,
	start INT NOT NULL,
	end INT NOT NULL,
	time_elapsed INT NOT NULL,
	algorithm_type VARCHAR(100) NOT NULL,
	number_of_primes INT NOT NULL
);

commit;