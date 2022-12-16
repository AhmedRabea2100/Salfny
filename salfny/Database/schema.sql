-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Salfny
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Salfny
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Salfny` DEFAULT CHARACTER SET utf8 ;
USE `Salfny` ;

-- -----------------------------------------------------
-- Table `Salfny`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `address` VARCHAR(100) NULL,
  `email` VARCHAR(45) NOT NULL,
  `profile_pic` VARCHAR(200) NULL,
  `member_since` DATE NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `rating` DECIMAL(2,1) NULL,
  `no_of_done_deal` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`payment_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`payment_option` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `option` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `option_UNIQUE` (`option` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(2000) NULL,
  `price` INT NOT NULL,
  `payment_option` INT NULL,
  `views` INT NULL DEFAULT 0,
  `date` DATE NOT NULL,
  `category_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `payment_option_fk_idx` (`payment_option` ASC) VISIBLE,
  INDEX `fk_Post_Category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_Post_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `payment_option_fk`
    FOREIGN KEY (`payment_option`)
    REFERENCES `Salfny`.`payment_option` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `category_id_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `Salfny`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Preferences`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Preferences` (
  `user_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `category_id`),
  INDEX `fk_User_has_Category_Category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_User_has_Category_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Category_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Category_Category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `Salfny`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Photo` (
  `id` INT NOT NULL,
  `url` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`, `url`),
  CONSTRAINT `photo_fk`
    FOREIGN KEY (`id`)
    REFERENCES `Salfny`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Comment` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `comment` VARCHAR(100) NULL,
  PRIMARY KEY (`user_id`, `post_id`),
  INDEX `fk_User_has_Post_Post1_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_User_has_Post_User1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Post_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Post_Post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `Salfny`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Save`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Save` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `post_id`),
  INDEX `fk_User_has_Post_Post2_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_User_has_Post_User2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Post_User2`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Post_Post2`
    FOREIGN KEY (`post_id`)
    REFERENCES `Salfny`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`View`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`View` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `post_id`),
  INDEX `fk_User_has_Post_Post3_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_User_has_Post_User3_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Post_User3`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Post_Post3`
    FOREIGN KEY (`post_id`)
    REFERENCES `Salfny`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Salfny`.`Rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Salfny`.`Rate` (
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `rating` INT NULL,
  PRIMARY KEY (`user_id`, `post_id`),
  INDEX `fk_User_has_Post_Post4_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_User_has_Post_User4_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_has_Post_User4`
    FOREIGN KEY (`user_id`)
    REFERENCES `Salfny`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Post_Post4`
    FOREIGN KEY (`post_id`)
    REFERENCES `Salfny`.`Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
