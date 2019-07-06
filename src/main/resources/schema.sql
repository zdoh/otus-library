-- -----------------------------------------------------
-- Table `author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `book` ;
DROP TABLE IF EXISTS `author` ;
DROP TABLE IF EXISTS `genre` ;


CREATE TABLE author (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------

CREATE TABLE genre (
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(256) NOT NULL,
   PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `book`
-- -----------------------------------------------------

CREATE TABLE book (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `author_id` BIGINT NOT NULL,
  `genre_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
    CONSTRAINT `book_author_id`
      FOREIGN KEY (`author_id`)
      REFERENCES `author` (`id`)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT `book_genre_id`
      FOREIGN KEY (`genre_id`)
          REFERENCES `genre` (`id`)
          ON DELETE CASCADE
          ON UPDATE CASCADE

) DEFAULT CHARSET=utf8;
