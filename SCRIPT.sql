/*
SQLyog Ultimate v12.09 (32 bit)
MySQL - 5.5.16 : Database - impacta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`impacta` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `impacta`;

/*Table structure for table `tb_assuntos` */

DROP TABLE IF EXISTS `tb_assuntos`;

CREATE TABLE `tb_assuntos` (
  `idassunto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_assunto` varchar(64) NOT NULL,
  PRIMARY KEY (`idassunto`),
  UNIQUE KEY `nome_assunto` (`nome_assunto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tb_assuntos` */

insert  into `tb_assuntos`(`idassunto`,`nome_assunto`) values (5,'Administração'),(2,'Filosofia'),(6,'Gastronomia'),(3,'Informática'),(4,'Publicidade'),(1,'Romance'),(7,'Teste');

/*Table structure for table `tb_autores` */

DROP TABLE IF EXISTS `tb_autores`;

CREATE TABLE `tb_autores` (
  `idautor` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nome_autor` varchar(64) NOT NULL,
  PRIMARY KEY (`idautor`),
  UNIQUE KEY `nome_autor` (`nome_autor`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `tb_autores` */

insert  into `tb_autores`(`idautor`,`nome_autor`) values (12,'Carlos'),(18,'Gustavo Almeida'),(9,'João Cardoso'),(1,'Leonardo Cardoso'),(19,'Teste');

/*Table structure for table `tb_devolucoes` */

DROP TABLE IF EXISTS `tb_devolucoes`;

CREATE TABLE `tb_devolucoes` (
  `iddevolucao` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idemprestimo` int(10) unsigned NOT NULL,
  `data_devolucao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`iddevolucao`,`idemprestimo`),
  KEY `tb_devolucoes_FKIndex1` (`idemprestimo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_devolucoes` */

/*Table structure for table `tb_editoras` */

DROP TABLE IF EXISTS `tb_editoras`;

CREATE TABLE `tb_editoras` (
  `ideditora` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_editora` varchar(64) NOT NULL,
  PRIMARY KEY (`ideditora`,`nome_editora`),
  UNIQUE KEY `nome_editora` (`nome_editora`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_editoras` */

insert  into `tb_editoras`(`ideditora`,`nome_editora`) values (10,'Alura'),(9,'Caelum LTDA.'),(1,'Casa do Código'),(2,'Levante'),(5,'Roberts Edits'),(11,'Teste');

/*Table structure for table `tb_emprestimos` */

DROP TABLE IF EXISTS `tb_emprestimos`;

CREATE TABLE `tb_emprestimos` (
  `idemprestimo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idpessoa` int(10) unsigned NOT NULL,
  `num_exemplar` int(10) unsigned NOT NULL,
  `data_emprestimo` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_prevista_retorno` date NOT NULL,
  `finalizado` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idemprestimo`),
  KEY `tb_emprestimos_FKIndex1` (`num_exemplar`),
  KEY `tb_emprestimos_FKIndex2` (`idpessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_emprestimos` */

insert  into `tb_emprestimos`(`idemprestimo`,`idpessoa`,`num_exemplar`,`data_emprestimo`,`data_prevista_retorno`,`finalizado`) values (1,1600559,1,'2017-06-02 13:10:38','2017-06-17',1),(2,1600562,548232035,'2017-06-02 12:17:51','2017-06-09',0),(3,1600561,4454,'2017-06-02 13:09:41','2017-06-09',1),(4,1600559,3,'2017-06-02 12:44:41','2017-06-17',1),(5,1600561,3,'2017-06-02 13:08:07','2017-06-09',0),(6,1600561,4454,'2017-06-02 15:49:21','2017-06-09',1),(7,1600559,1,'2017-06-02 13:12:23','2017-06-17',1),(8,1600559,1,'2017-06-02 15:16:07','2017-06-17',1),(9,1600559,1,'2017-06-02 15:46:15','2017-06-22',1),(10,1600562,1,'2017-06-02 15:48:55','2017-06-14',1),(11,1600559,1,'2017-06-02 15:49:18','2017-06-06',1),(12,1600562,4454,'2017-06-02 16:10:36','2017-06-09',1),(13,1600559,1,'2017-06-02 16:12:07','2017-06-17',1),(14,1600559,1,'2017-06-02 16:12:36','2017-06-04',1),(15,1600559,1,'2017-06-02 16:15:56','2017-06-17',1);

/*Table structure for table `tb_exemplares` */

DROP TABLE IF EXISTS `tb_exemplares`;

CREATE TABLE `tb_exemplares` (
  `num_exemplar` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `idobra` int(10) unsigned NOT NULL,
  `emprestado` tinyint(1) NOT NULL DEFAULT '0',
  `data_aquisicao` date NOT NULL,
  PRIMARY KEY (`num_exemplar`),
  KEY `tb_exemplar_FKIndex1` (`idobra`)
) ENGINE=InnoDB AUTO_INCREMENT=548232036 DEFAULT CHARSET=utf8;

/*Data for the table `tb_exemplares` */

insert  into `tb_exemplares`(`num_exemplar`,`idobra`,`emprestado`,`data_aquisicao`) values (1,1,0,'2017-05-04'),(3,2,1,'2017-05-03'),(4454,4,0,'2017-06-15'),(548232035,4,1,'2017-06-16');

/*Table structure for table `tb_obras` */

DROP TABLE IF EXISTS `tb_obras`;

CREATE TABLE `tb_obras` (
  `idobra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ideditora` int(10) unsigned NOT NULL,
  `titulo` varchar(64) NOT NULL,
  `ano_publicacao` year(4) NOT NULL,
  `idassunto` int(10) unsigned NOT NULL,
  `idautor` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idobra`),
  KEY `tb_obras_FKIndex1` (`ideditora`),
  KEY `tb_obras_FKIndex2` (`idassunto`),
  KEY `tb_obras_FKIndex3` (`idobra`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tb_obras` */

insert  into `tb_obras`(`idobra`,`ideditora`,`titulo`,`ano_publicacao`,`idassunto`,`idautor`) values (1,1,'Test-Driven Development',2016,3,1),(2,11,'Teste',2008,7,19),(4,5,'Clean Code',2010,3,18),(5,10,'Java Web',2017,5,9),(6,2,'Marketing Pessoal',1998,4,12);

/*Table structure for table `tb_pessoas` */

DROP TABLE IF EXISTS `tb_pessoas`;

CREATE TABLE `tb_pessoas` (
  `idpessoa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idtipo_pessoa` int(10) unsigned NOT NULL,
  `nome` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `inadmin` tinyint(1) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `data_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idpessoa`,`idtipo_pessoa`),
  KEY `tb_pessoas_FKIndex1` (`idtipo_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=1600563 DEFAULT CHARSET=utf8;

/*Data for the table `tb_pessoas` */

insert  into `tb_pessoas`(`idpessoa`,`idtipo_pessoa`,`nome`,`email`,`senha`,`telefone`,`inadmin`,`cpf`,`data_registro`) values (1600559,3,'Leonardo Cardoso','leocardoso@impacta.com.br','1234','11 95174-0678',1,'430.430.430-92','2017-05-18 16:45:26'),(1600561,1,'Fernando Carvalho','teste@teste.com','12121','11 37268-2262',0,'323.232.323-32','2017-05-23 15:44:34'),(1600562,1,'Madeira','madeira@impacta.edu.br','1234','11 93338-7788',0,'423.256.454-44','2017-05-31 13:19:42');

/*Table structure for table `tb_reservas` */

DROP TABLE IF EXISTS `tb_reservas`;

CREATE TABLE `tb_reservas` (
  `idreserva` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idpessoa` int(10) unsigned NOT NULL,
  `data_reserva` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `data_retirada` date NOT NULL,
  `num_exemplar` int(15) NOT NULL,
  PRIMARY KEY (`idreserva`),
  KEY `tb_reservas_FKIndex1` (`idpessoa`),
  KEY `tb_reservas_FKIndex2` (`num_exemplar`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_reservas` */

insert  into `tb_reservas`(`idreserva`,`idpessoa`,`data_reserva`,`data_retirada`,`num_exemplar`) values (1,1600559,'2017-06-02 16:37:17','2017-06-30',1);

/*Table structure for table `tb_tipo_pessoa` */

DROP TABLE IF EXISTS `tb_tipo_pessoa`;

CREATE TABLE `tb_tipo_pessoa` (
  `idtipo_pessoa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`idtipo_pessoa`),
  UNIQUE KEY `nome_tipo` (`nome_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_tipo_pessoa` */

insert  into `tb_tipo_pessoa`(`idtipo_pessoa`,`nome_tipo`) values (2,'Aluno'),(3,'Funcionário'),(1,'Professor');

/* Procedure structure for procedure `sp_assunto_insert` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_assunto_insert` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_assunto_insert`(pnome_assunto VARCHAR (64))
BEGIN
  INSERT INTO tb_assuntos (`nome_assunto`) 
  VALUES
	(pnome_assunto) ; 
			 
SELECT 
  * 
FROM
  `tb_assuntos` 
WHERE `idassunto` = LAST_INSERT_ID() ;
  
END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_autor_insert` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_autor_insert` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_autor_insert`(pnome_autor VARCHAR (64))
BEGIN
  INSERT INTO tb_autores (`nome_autor`) 
  VALUES
    (pnome_autor) ; 
             
SELECT 
  * 
FROM
  `tb_autores` 
WHERE `idautor` = LAST_INSERT_ID() ;
  
END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_editora_insert` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_editora_insert` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_editora_insert`(pnome_editora VARCHAR (64))
BEGIN
  INSERT INTO tb_editoras (`nome_editora`) 
  VALUES
	(pnome_editora) ; 
			 
SELECT 
  * 
FROM
  `tb_editoras` 
WHERE `ideditora` = LAST_INSERT_ID() ;
  
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
