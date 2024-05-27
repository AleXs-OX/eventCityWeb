-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para publicaciones
CREATE DATABASE IF NOT EXISTS `publicaciones` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `publicaciones`;

-- Volcando estructura para tabla publicaciones.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `idUsuario` int NOT NULL,
  `rol` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `fk_admin_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.admin: ~2 rows (aproximadamente)
INSERT INTO `admin` (`idUsuario`, `rol`) VALUES
	(1, 'SuperAdmin'),
	(2, 'Admin');

-- Volcando estructura para tabla publicaciones.categorias
CREATE TABLE IF NOT EXISTS `categorias` (
  `idCategoria` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.categorias: ~2 rows (aproximadamente)
INSERT INTO `categorias` (`idCategoria`, `nombre`) VALUES
	(1, 'Categoría 1'),
	(2, 'Categoría 2');

-- Volcando estructura para tabla publicaciones.eventos
CREATE TABLE IF NOT EXISTS `eventos` (
  `idEvento` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `fechaAlta` date DEFAULT NULL,
  `fechaEvento` date DEFAULT NULL,
  `horaEvento` time DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `precio` float NOT NULL DEFAULT '0',
  `idPublicador` int DEFAULT NULL,
  `idCategoria` int DEFAULT NULL,
  `idLocalizacion` int DEFAULT NULL,
  `capacidadActual` int DEFAULT '1',
  PRIMARY KEY (`idEvento`),
  KEY `fk_eventos_publicadores` (`idPublicador`),
  KEY `fk_eventos_categorias` (`idCategoria`),
  KEY `fk_eventos_localizaciones` (`idLocalizacion`),
  CONSTRAINT `fk_eventos_categorias` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `fk_eventos_localizaciones` FOREIGN KEY (`idLocalizacion`) REFERENCES `localizaciones` (`idLocalizacion`),
  CONSTRAINT `fk_eventos_publicadores` FOREIGN KEY (`idPublicador`) REFERENCES `publicadores` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.eventos: ~2 rows (aproximadamente)
INSERT INTO `eventos` (`idEvento`, `titulo`, `descripcion`, `fechaAlta`, `fechaEvento`, `horaEvento`, `activo`, `precio`, `idPublicador`, `idCategoria`, `idLocalizacion`, `capacidadActual`) VALUES
	(1, 'Evento 1', 'Descripción del evento 1', '2023-01-01', '2023-06-01', NULL, 1, 50, 1, 1, 1, 50),
	(2, 'Evento 2', 'Descripción del evento 2', '2023-01-02', '2023-06-02', NULL, 0, 75, 2, 2, 2, 75);

-- Volcando estructura para tabla publicaciones.localizaciones
CREATE TABLE IF NOT EXISTS `localizaciones` (
  `idLocalizacion` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `capacidadTotal` int DEFAULT NULL,
  `idLugar` int DEFAULT NULL,
  PRIMARY KEY (`idLocalizacion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.localizaciones: ~2 rows (aproximadamente)
INSERT INTO `localizaciones` (`idLocalizacion`, `direccion`, `ciudad`, `pais`, `capacidadTotal`, `idLugar`) VALUES
	(1, 'Calle 1', 'Ciudad 1', 'País 1', 100, 1),
	(2, 'Calle 2', 'Ciudad 2', 'País 2', 200, 2);

-- Volcando estructura para tabla publicaciones.lugares
CREATE TABLE IF NOT EXISTS `lugares` (
  `idLugar` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idLugar`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.lugares: ~2 rows (aproximadamente)
INSERT INTO `lugares` (`idLugar`, `tipo`) VALUES
	(1, 'Tipo 1'),
	(2, 'Tipo 2');

-- Volcando estructura para tabla publicaciones.personas
CREATE TABLE IF NOT EXISTS `personas` (
  `IDPERSONA` int NOT NULL AUTO_INCREMENT,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPERSONA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla publicaciones.personas: ~0 rows (aproximadamente)

-- Volcando estructura para tabla publicaciones.publicadores
CREATE TABLE IF NOT EXISTS `publicadores` (
  `idUsuario` int NOT NULL,
  `fechaAlta` date DEFAULT NULL,
  `numEventos` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `fk_publicadores_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.publicadores: ~2 rows (aproximadamente)
INSERT INTO `publicadores` (`idUsuario`, `fechaAlta`, `numEventos`, `descripcion`) VALUES
	(1, '2023-01-01', 5, 'Descripción del publicador 1'),
	(2, '2023-01-02', 3, 'Descripción del publicador 2');

-- Volcando estructura para tabla publicaciones.puntuaciones
CREATE TABLE IF NOT EXISTS `puntuaciones` (
  `idSubscriptor` int NOT NULL,
  `idUsuario` int NOT NULL,
  `puntuacion` int DEFAULT NULL,
  `idEvento` int NOT NULL,
  PRIMARY KEY (`idSubscriptor`,`idUsuario`,`idEvento`),
  KEY `fk_puntuaciones_usuarios` (`idUsuario`),
  KEY `fk_puntuaciones_eventos` (`idEvento`),
  CONSTRAINT `fk_puntuaciones_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_puntuaciones_suscriptores` FOREIGN KEY (`idSubscriptor`) REFERENCES `suscriptores` (`idSubscriptor`),
  CONSTRAINT `fk_puntuaciones_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.puntuaciones: ~2 rows (aproximadamente)
INSERT INTO `puntuaciones` (`idSubscriptor`, `idUsuario`, `puntuacion`, `idEvento`) VALUES
	(1, 1, 5, 1),
	(2, 2, 4, 2);

-- Volcando estructura para tabla publicaciones.resenas
CREATE TABLE IF NOT EXISTS `resenas` (
  `comentario` varchar(255) DEFAULT NULL,
  `idSuscriptor` int NOT NULL,
  `idEvento` int NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idSuscriptor`,`idEvento`),
  KEY `fk_resenas_eventos` (`idEvento`),
  CONSTRAINT `fk_resenas_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_resenas_suscriptores` FOREIGN KEY (`idSuscriptor`) REFERENCES `suscriptores` (`idSubscriptor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.resenas: ~2 rows (aproximadamente)
INSERT INTO `resenas` (`comentario`, `idSuscriptor`, `idEvento`, `fecha`) VALUES
	('Comentario del evento 1 por suscriptor 1', 1, 1, '2023-05-01'),
	('Comentario del evento 2 por suscriptor 2', 2, 2, '2023-05-02');

-- Volcando estructura para tabla publicaciones.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `IDROL` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipousuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Volcando datos para la tabla publicaciones.roles: ~0 rows (aproximadamente)

-- Volcando estructura para tabla publicaciones.suscripciones
CREATE TABLE IF NOT EXISTS `suscripciones` (
  `idSuscriptor` int NOT NULL,
  `idEvento` int NOT NULL,
  `fechaSus` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idSuscriptor`,`idEvento`),
  KEY `fk_suscripciones_eventos` (`idEvento`),
  CONSTRAINT `fk_suscripciones_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_suscripciones_suscriptores` FOREIGN KEY (`idSuscriptor`) REFERENCES `suscriptores` (`idSubscriptor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.suscripciones: ~2 rows (aproximadamente)
INSERT INTO `suscripciones` (`idSuscriptor`, `idEvento`, `fechaSus`, `estado`) VALUES
	(1, 1, '2023-05-01', 1),
	(2, 2, '2023-05-02', 0);

-- Volcando estructura para tabla publicaciones.suscriptores
CREATE TABLE IF NOT EXISTS `suscriptores` (
  `idSubscriptor` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int DEFAULT NULL,
  `numSuscripciones` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idSubscriptor`),
  KEY `fk_suscriptores_usuarios` (`idUsuario`),
  CONSTRAINT `fk_suscriptores_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.suscriptores: ~2 rows (aproximadamente)
INSERT INTO `suscriptores` (`idSubscriptor`, `idUsuario`, `numSuscripciones`, `direccion`, `ciudad`, `pais`) VALUES
	(1, 1, '5', 'Dirección 1', 'Ciudad 1', 'País 1'),
	(2, 2, '3', 'Dirección 2', 'Ciudad 2', 'País 2');

-- Volcando estructura para tabla publicaciones.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(100) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.usuarios: ~2 rows (aproximadamente)
INSERT INTO `usuarios` (`idUsuario`, `nombreusuario`, `contrasena`, `nombre`, `apellidos`, `telefono`, `email`) VALUES
	(1, 'usuario1', 'pass1', 'Nombre1', 'Apellido1', 123456789, 'usuario1@example.com'),
	(2, 'usuario2', 'pass2', 'Nombre2', 'Apellido2', 987654321, 'usuario2@example.com'),
	(3, 'usuario1', 'pass1', 'Nombre1', 'Apellido1', 123456789, 'usuario1@example.com'),
	(4, 'usuario2', 'pass2', 'Nombre2', 'Apellido2', 987654321, 'usuario2@example.com'),
	(5, 'usuario1', 'pass1', 'Nombre1', 'Apellido1', 123456789, 'usuario1@example.com'),
	(6, 'usuario2', 'pass2', 'Nombre2', 'Apellido2', 987654321, 'usuario2@example.com'),
	(7, 'usuario1', 'pass1', 'Nombre1', 'Apellido1', 123456789, 'usuario1@example.com'),
	(8, 'usuario2', 'pass2', 'Nombre2', 'Apellido2', 987654321, 'usuario2@example.com'),
	(9, 'usuario3', 'pass3', 'Nombre3', 'Apellido3', 192837465, 'usuario3@example.com'),
	(10, 'usuario4', 'pass4', 'Nombre4', 'Apellido4', 564738291, 'usuario4@example.com'),
	(11, 'usuario5', 'pass5', 'Nombre5', 'Apellido5', 182736459, 'usuario5@example.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
