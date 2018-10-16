use mybatis_test;

CREATE TABLE country (
  id int NOT NULL AUTO_INCREMENT,
  countryname VARCHAR(255) null,
  countrycode VARCHAR(255) null,
  PRIMARY KEY (id)
);

insert country(countryname, countrycode) VALUES ('中国','CN'),('美国','US'),('俄罗斯','RU'),('英国','GB'),('法国','FR');