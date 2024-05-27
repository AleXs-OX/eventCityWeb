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
  (1, 'Conciertos'),
  (2, 'Talleres y Clases'),
  (3, 'Competiciones y Torneos'),
  (4, 'Misceláneo');

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
  (1, 'Concierto de Jazz', 'Una noche de jazz con artistas locales.', '2024-05-26', '2024-05-28', '20:00:00', 1, 30, 1, 1, 1, 80),
  (2, 'Clase de Yoga', 'Clase de yoga para principiantes.', '2024-05-26', '2024-05-28', '10:00:00', 1, 15, 2, 2, 2, 20),
  (3, 'Torneo de Ajedrez', 'Competencia de ajedrez para todos los niveles.', '2024-05-27', '2024-05-29', '16:00:00', 1, 10, 1, 3, 3, 30),
  (4, 'Feria de Arte', 'Exposición de arte local y ventas.', '2024-05-27', '2024-05-28', '14:00:00', 1, 5, 2, 4, 4, 50),
  (5, 'Concierto de Rock', 'Bandas de rock en vivo.', '2024-05-26', '2024-05-27', '21:00:00', 1, 40, 1, 1, 1, 100),
  (6, 'Taller de Pintura', 'Aprende técnicas básicas de pintura.', '2024-05-26', '2024-05-28', '11:00:00', 1, 20, 2, 2, 2, 25),
  (7, 'Competencia de Baile', 'Competencia de baile para aficionados.', '2024-05-27', '2024-05-30', '18:00:00', 1, 25, 1, 3, 3, 40),
  (8, 'Mercado de Pulgas', 'Venta de productos de segunda mano.', '2024-05-27', '2024-05-29', '09:00:00', 1, 0, 2, 4, 4, 70),
  (9, 'Concierto Acústico', 'Música acústica en vivo.', '2024-05-26', '2024-05-28', '19:00:00', 1, 20, 1, 1, 1, 60),
  (10, 'Clase de Cocina', 'Aprende a cocinar platos locales.', '2024-05-26', '2024-05-27', '15:00:00', 1, 35, 2, 2, 2, 15),
  (11, 'Torneo de Videojuegos', 'Competencia de videojuegos en grupo.', '2024-05-27', '2024-05-30', '17:00:00', 1, 10, 2, 3, 3, 50),
  (12, 'Feria Gastronómica', 'Degustación de platos típicos.', '2024-05-27', '2024-05-28', '12:00:00', 1, 10, 1, 4, 4, 100),
  (13, 'Concierto de Pop', 'Concierto con las mejores bandas de pop.', '2024-05-26', '2024-05-28', '20:00:00', 1, 50, 2, 1, 1, 120),
  (14, 'Taller de Fotografía', 'Curso básico de fotografía digital.', '2024-05-26', '2024-05-28', '13:00:00', 1, 25, 1, 2, 2, 20),
  (15, 'Torneo de Póker', 'Competencia de póker con premios.', '2024-05-27', '2024-05-29', '19:00:00', 1, 15, 2, 3, 3, 40),
  (16, 'Subasta de Arte', 'Subasta de obras de arte locales.', '2024-05-27', '2024-05-28', '16:00:00', 1, 5, 1, 4, 4, 30),
  (17, 'Concierto de Blues', 'Noche de blues con músicos reconocidos.', '2024-05-26', '2024-05-28', '21:00:00', 1, 35, 2, 1, 1, 90),
  (18, 'Clase de Cerámica', 'Aprende a crear piezas de cerámica.', '2024-05-26', '2024-05-28', '10:00:00', 1, 20, 1, 2, 2, 15),
  (19, 'Competencia de Fotografía', 'Concurso de fotografía con temas locales.', '2024-05-27', '2024-05-29', '14:00:00', 1, 10, 2, 3, 3, 30),
  (20, 'Festival de Cine', 'Proyección de cortometrajes locales.', '2024-05-27', '2024-05-28', '18:00:00', 1, 10, 1, 4, 4, 80),
  (21, 'Concierto de Música Clásica', 'Recital de música clásica.', '2024-05-26', '2024-05-28', '20:00:00', 1, 30, 2, 1, 1, 70),
  (22, 'Taller de Escritura Creativa', 'Curso de escritura para principiantes.', '2024-05-26', '2024-05-27', '11:00:00', 1, 15, 1, 2, 2, 10),
  (23, 'Competencia de Canto', 'Concurso de canto para aficionados.', '2024-05-27', '2024-05-30', '19:00:00', 1, 20, 2, 3, 3, 50),
  (24, 'Exposición Fotográfica', 'Muestra de fotografías artísticas.', '2024-05-27', '2024-05-29', '17:00:00', 1, 5, 1, 4, 4, 60),
  (25, 'Concierto de Hip-Hop', 'Presentación de artistas de hip-hop.', '2024-05-26', '2024-05-27', '21:00:00', 1, 45, 2, 1, 1, 110),
  (26, 'Clase de Danza', 'Clase de danza moderna para principiantes.', '2024-05-26', '2024-05-28', '12:00:00', 1, 20, 1, 2, 2, 25),
  (27, 'Torneo de Tenis de Mesa', 'Competencia de ping pong.', '2024-05-27', '2024-05-30', '15:00:00', 1, 10, 2, 3, 3, 35),
  (28, 'Mercadillo Artesanal', 'Venta de productos artesanales.', '2024-05-27', '2024-05-29', '09:00:00', 1, 0, 1, 4, 4, 50),
  (29, 'Concierto de Música Electrónica', 'DJ en vivo con música electrónica.', '2024-05-26', '2024-05-28', '22:00:00', 1, 40, 2, 1, 1, 130),
  (30, 'Taller de Teatro', 'Introducción al teatro y la actuación.', '2024-05-26', '2024-05-28', '14:00:00', 1, 25, 1, 2, 2, 20);

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
INSERT INTO `puntuaciones` (`idSuscriptor`, `idUsuario`, `puntuacion`, `idEvento`) VALUES
  (1, 3, 5, 1),
  (1, 3, 4, 5),
  (1, 3, 4, 9),
  (1, 3, 5, 13),
  (1, 3, 5, 17),
  (1, 3, 5, 21),
  (1, 3, 5, 25),
  (1, 3, 5, 29),
  (2, 4, 4, 2),
  (2, 4, 5, 6),
  (2, 4, 5, 10),
  (2, 4, 4, 14),
  (2, 4, 4, 18),
  (2, 4, 4, 22),
  (2, 4, 4, 26),
  (2, 4, 4, 30),
  (3, 5, 3, 3),
  (3, 5, 4, 7),
  (3, 5, 2, 11),
  (3, 5, 3, 15),
  (3, 5, 5, 19),
  (3, 5, 4, 23),
  (3, 5, 3, 27),
  (4, 6, 5, 4),
  (4, 6, 3, 8),
  (4, 6, 4, 12),
  (4, 6, 4, 16),
  (4, 6, 4, 20),
  (4, 6, 3, 24),
  (4, 6, 4, 28);

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
  ('Una noche increíble con excelente música.', 1, 1, '2024-05-28'),
  ('Buen ambiente y buena música, pero muy ruidoso.', 1, 5, '2024-05-27'),
  ('Música hermosa en un ambiente acogedor.', 1, 9, '2024-05-28'),
  ('Una noche increíble con bandas talentosas.', 1, 13, '2024-05-28'),
  ('Blues en vivo, una experiencia única.', 1, 17, '2024-05-28'),
  ('Música clásica en un entorno perfecto.', 1, 21, '2024-05-28'),
  ('Hip-hop en su mejor versión en vivo.', 1, 25, '2024-05-27'),
  ('La mejor música electrónica en vivo.', 1, 29, '2024-05-28'),
  ('Clase muy relajante y bien organizada.', 2, 2, '2024-05-28'),
  ('Me encantó aprender a pintar, muy instructivo.', 2, 6, '2024-05-28'),
  ('Excelente clase de cocina, aprendí mucho.', 2, 10, '2024-05-27'),
  ('Buen taller, aprendí nuevas técnicas.', 2, 14, '2024-05-28'),
  ('Divertido y educativo taller de cerámica.', 2, 18, '2024-05-28'),
  ('Inspirador taller de escritura.', 2, 22, '2024-05-27'),
  ('Clase de danza muy divertida.', 2, 26, '2024-05-28'),
  ('Taller de teatro muy interactivo y educativo.', 2, 30, '2024-05-28'),
  ('Interesante, pero esperaba más competencia.', 3, 3, '2024-05-29'),
  ('Competencia divertida y bien organizada.', 3, 7, '2024-05-30'),
  ('La organización fue pobre, esperaba más.', 3, 11, '2024-05-30'),
  ('Competencia emocionante, pero muy larga.', 3, 15, '2024-05-29'),
  ('Gran concurso, me encantaron las fotos.', 3, 19, '2024-05-29'),
  ('Gran ambiente para cantar y competir.', 3, 23, '2024-05-30'),
  ('Competencia intensa, muy emocionante.', 3, 27, '2024-05-30'),
  ('Gran evento con mucha variedad de arte.', 4, 4, '2024-05-28'),
  ('Un buen lugar para encontrar cosas únicas.', 4, 8, '2024-05-29'),
  ('Deliciosa comida y buena compañía.', 4, 12, '2024-05-28'),
  ('Interesante variedad de arte en subasta.', 4, 16, '2024-05-28'),
  ('Buenas proyecciones, me encantó el cine.', 4, 20, '2024-05-28'),
  ('Exposición interesante, pero algo pequeña.', 4, 24, '2024-05-29'),
  ('Buenos productos artesanales disponibles.', 4, 28, '2024-05-29');


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
  (1, 3, '5', 'Calle Mayor, 10', 'Madrid', 'España'),
  (2, 4, '3', 'Carrer de Provença, 20', 'Barcelona', 'España'),
  (3, 5, '7', 'Avenida de la Constitución, 15', 'Sevilla', 'España'),
  (4, 6, '4', 'Calle Real, 5', 'Valencia', 'España');

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
  (1, 'lauragarcia', 'password1', 'Laura', 'García Pérez', 612345678, 'lauragarcia@example.com'),
  (2, 'miguelhernandez', 'password1', 'Miguel', 'Hernández Martínez', 655432198, 'miguelhernandez@example.com'),
  (3, 'anaisabel', 'password1', 'Ana', 'Isabel Rodríguez', 678901234, 'anaisabel@example.com'),
  (4, 'davidlopez', 'password1', 'David', 'López Sánchez', 612345678, 'davidlopez@example.com'),
  (5, 'sarasanz', 'password2', 'Sara', 'Sanz Gómez', 654321987, 'sarasanz@example.com'),
  (6, 'pablogonzalez', 'password2', 'Pablo', 'González Martín', 689076543, 'pablogonzalez@example.com'),
  (7, 'elenarodriguez', 'password2', 'Elena', 'Rodríguez Pérez', 623456789, 'elenarodriguez@example.com'),
  (8, 'sergioalvarez', 'password2', 'Sergio', 'Álvarez García', 645678901, 'sergioalvarez@example.com'),
  (9, 'carlotafernandez', 'password3', 'Carlota', 'Fernández López', 679012345, 'carlotafernandez@example.com'),
  (10, 'javiermartin', 'password3', 'Javier', 'Martín Sánchez', 645678901, 'javiermartin@example.com'),
  (11, 'luciamoreno', 'password3', 'Lucía', 'Moreno Rodríguez', 612345678, 'luciamoreno@example.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
