-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.3.0 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.7.0.6850
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
CREATE DATABASE IF NOT EXISTS `publicaciones` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.categorias: ~4 rows (aproximadamente)
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
  CONSTRAINT `fk_eventos_publicadores` FOREIGN KEY (`idPublicador`) REFERENCES `publicadores` (`idPublicador`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.eventos: ~30 rows (aproximadamente)
INSERT INTO `eventos` (`idEvento`, `titulo`, `descripcion`, `fechaAlta`, `fechaEvento`, `horaEvento`, `activo`, `precio`, `idPublicador`, `idCategoria`, `idLocalizacion`, `capacidadActual`) VALUES
(1, 'Concierto de Rock', 'Un increíble concierto de rock en el parque central', '2024-05-26', '2024-05-26', '20:00', 1, 100, 1, 1, 1, 300),
(2, 'Clase de Yoga', 'Clase de yoga para principiantes en el centro comunitario', '2024-05-26', '2024-05-26', '10:00', 1, 20, 2, 2, 2, 20),
(3, 'Torneo de Ajedrez', 'Competición de ajedrez abierta a todos los niveles', '2024-05-26', '2024-05-26', '15:00', 1, 5, 3, 3, 3, 50),
(4, 'Mercadillo Artesanal', 'Venta de productos artesanales locales', '2024-05-26', '2024-05-26', '09:00', 1, 0, 4, 4, 4, 200),
(5, 'Concierto de Jazz', 'Concierto de jazz en el auditorio municipal', '2024-05-27', '2024-05-27', '19:00', 1, 80, 2, 1, 1, 150),
(6, 'Taller de Cocina Italiana', 'Aprende a cocinar platos italianos tradicionales', '2024-05-27', '2024-05-27', '18:00', 1, 30, 3, 2, 2, 15),
(7, 'Carrera 5K', 'Carrera de 5 kilómetros por la ciudad', '2024-05-27', '2024-05-27', '08:00', 1, 10, 1, 3, 3, 100),
(8, 'Feria del Libro', 'Feria con stands de librerías y editoriales locales', '2024-05-27', '2024-05-27', '11:00', 1, 0, 4, 4, 4, 500),
(9, 'Concierto de Música Clásica', 'Concierto de la orquesta sinfónica de la ciudad', '2024-05-28', '2024-05-28', '20:00', 1, 150, 2, 1, 1, 250),
(10, 'Clase de Zumba', 'Clase de zumba para todas las edades', '2024-05-28', '2024-05-28', '17:00', 1, 25, 3, 2, 2, 30),
(11, 'Torneo de Videojuegos', 'Competición de videojuegos con premios para los ganadores', '2024-05-28', '2024-05-28', '14:00', 1, 20, 1, 3, 3, 60),
(12, 'Exposición de Arte', 'Exposición de arte contemporáneo en la galería central', '2024-05-28', '2024-05-28', '10:00', 1, 5, 4, 4, 4, 100),
(13, 'Concierto de Pop', 'Concierto de una popular banda de pop local', '2024-05-29', '2024-05-29', '21:00', 1, 120, 3, 1, 1, 200),
(14, 'Taller de Fotografía', 'Curso intensivo de fotografía para aficionados', '2024-05-29', '2024-05-29', '16:00', 1, 40, 1, 2, 2, 20),
(15, 'Competición de Natación', 'Torneo de natación en la piscina olímpica', '2024-05-29', '2024-05-29', '09:00', 1, 15, 2, 3, 3, 80),
(16, 'Feria de Artesanía', 'Feria con productos artesanales y actividades para niños', '2024-05-29', '2024-05-29', '10:00', 1, 0, 4, 4, 4, 300),
(17, 'Concierto de Música Electrónica', 'Evento de música electrónica con DJ en vivo', '2024-05-26', '2024-05-26', '22:00', 1, 90, 2, 1, 1, 350),
(18, 'Clase de Pintura', 'Taller de pintura al óleo para todos los niveles', '2024-05-26', '2024-05-26', '13:00', 1, 35, 3, 2, 2, 25),
(19, 'Maratón', 'Maratón por las calles principales de la ciudad', '2024-05-26', '2024-05-26', '07:00', 1, 50, 1, 3, 3, 200),
(20, 'Feria Gastronómica', 'Feria con food trucks y degustaciones de comida internacional', '2024-05-26', '2024-05-26', '12:00', 1, 0, 4, 4, 4, 500),
(21, 'Concierto de Reggae', 'Concierto de reggae en la playa', '2024-05-27', '2024-05-27', '18:00', 1, 70, 1, 1, 1, 300),
(22, 'Taller de Cerámica', 'Taller práctico de cerámica para principiantes', '2024-05-27', '2024-05-27', '14:00', 1, 45, 2, 2, 2, 10),
(23, 'Competición de Baile', 'Torneo de baile con jurado profesional', '2024-05-27', '2024-05-27', '20:00', 1, 10, 3, 3, 3, 100),
(24, 'Mercado de Pulgas', 'Mercado de segunda mano y antigüedades', '2024-05-27', '2024-05-27', '09:00', 1, 0, 4, 4, 4, 250),
(25, 'Concierto Acústico', 'Concierto íntimo de música acústica', '2024-05-28', '2024-05-28', '19:00', 1, 60, 3, 1, 1, 150),
(26, 'Clase de Danza Contemporánea', 'Clase abierta de danza contemporánea', '2024-05-28', '2024-05-28', '15:00', 1, 20, 1, 2, 2, 15),
(27, 'Torneo de Tenis', 'Competición de tenis en el club deportivo', '2024-05-28', '2024-05-28', '08:00', 1, 10, 2, 3, 3, 50),
(28, 'Festival de Cine', 'Proyección de cortometrajes y películas independientes', '2024-05-28', '2024-05-28', '11:00', 1, 0, 4, 4, 4, 400),
(29, 'Concierto de Hip-Hop', 'Evento de hip-hop con artistas locales', '2024-05-29', '2024-05-29', '20:00', 1, 85, 1, 1, 1, 200),
(30, 'Taller de Escritura Creativa', 'Curso de escritura creativa para principiantes', '2024-05-29', '2024-05-29', '17:00', 1, 30, 2, 2, 2, 12),
(31, 'Competición de Surf', 'Torneo de surf en la playa principal', '2024-05-29', '2024-05-29', '07:00', 1, 20, 3, 3, 3, 40),
(32, 'Feria del Vino', 'Degustación de vinos locales y nacionales', '2024-05-29', '2024-05-29', '10:00', 1, 0, 4, 4, 4, 150),
(33, 'Concierto de Indie', 'Concierto de bandas indie en el bar cultural', '2024-05-26', '2024-05-26', '21:00', 1, 50, 1, 1, 1, 100),
(34, 'Clase de Pilates', 'Clase de pilates para todos los niveles', '2024-05-26', '2024-05-26', '11:00', 1, 15, 2, 2, 2, 20),
(35, 'Competición de Escalada', 'Torneo de escalada en el muro del gimnasio', '2024-05-26', '2024-05-26', '16:00', 1, 10, 3, 3, 3, 30),
(36, 'Festival de Comida Vegana', 'Festival con degustaciones y venta de comida vegana', '2024-05-26', '2024-05-26', '13:00', 1, 0, 4, 4, 4, 400),
(37, 'Concierto de Blues', 'Concierto de blues en el bar local', '2024-05-27', '2024-05-27', '21:00', 1, 75, 2, 1, 1, 150),
(38, 'Taller de Macramé', 'Taller de macramé para principiantes', '2024-05-27', '2024-05-27', '10:00', 1, 25, 3, 2, 2, 15),
(39, 'Torneo de Fútbol', 'Competición de fútbol entre equipos locales', '2024-05-27', '2024-05-27', '14:00', 1, 10, 1, 3, 3, 60),
(40, 'Feria de Antigüedades', 'Venta y exhibición de antigüedades', '2024-05-27', '2024-05-27', '09:00', 1, 0, 4, 4, 4, 200);

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
  `idPublicador` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int NOT NULL,
  `fechaAlta` date DEFAULT NULL,
  `numEventos` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPublicador`),
  KEY `fk_publicadores_usuarios` (`idUsuario`),
  CONSTRAINT `fk_publicadores_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.publicadores: ~4 rows (aproximadamente)
INSERT INTO `publicadores` (`idPublicador`, `idUsuario`, `fechaAlta`, `numEventos`, `descripcion`) VALUES
  (1, 1, '2023-01-01', 5, 'Descripción del publicador 1'),
  (2, 2, '2023-01-02', 3, 'Descripción del publicador 2'),
  (3, 3, '2023-01-02', 3, 'Descripción del publicador 2'),
  (4, 4, '2023-01-02', 3, 'Descripción del publicador 2');

-- Volcando estructura para tabla publicaciones.puntuaciones
CREATE TABLE IF NOT EXISTS `puntuaciones` (
  `idSuscriptor` int NOT NULL,
  `idUsuario` int NOT NULL,
  `puntuacion` int DEFAULT NULL,
  `idEvento` int NOT NULL,
  PRIMARY KEY (`idSuscriptor`,`idUsuario`,`idEvento`),
  KEY `fk_puntuaciones_usuarios` (`idUsuario`),
  KEY `fk_puntuaciones_eventos` (`idEvento`),
  CONSTRAINT `fk_puntuaciones_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_puntuaciones_suscriptores` FOREIGN KEY (`idSuscriptor`) REFERENCES `suscriptores` (`idSuscriptor`),
  CONSTRAINT `fk_puntuaciones_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.puntuaciones: ~30 rows (aproximadamente)
INSERT INTO `puntuaciones` (`idSuscriptor`, `idUsuario`, `puntuacion`, `idEvento`) VALUES
(5, 5, 5, 1),
(6, 6, 4, 2),
(7, 7, 3, 3),
(8, 8, 2, 4),
(9, 9, 5, 5),
(10, 10, 4, 6),
(5, 5, 3, 7),
(6, 6, 5, 8),
(7, 7, 4, 9),
(8, 8, 3, 10),
(9, 9, 2, 11),
(10, 10, 5, 12),
(5, 5, 4, 13),
(6, 6, 3, 14),
(7, 7, 2, 15),
(8, 8, 5, 16),
(9, 9, 4, 17),
(10, 10, 3, 18),
(5, 5, 5, 19),
(6, 6, 4, 20),
(7, 7, 3, 21),
(8, 8, 2, 22),
(9, 9, 5, 23),
(10, 10, 4, 24),
(5, 5, 3, 25),
(6, 6, 5, 26),
(7, 7, 4, 27),
(8, 8, 3, 28),
(9, 9, 2, 29),
(10, 10, 5, 30),
(5, 5, 4, 31),
(6, 6, 3, 32),
(7, 7, 2, 33),
(8, 8, 5, 34),
(9, 9, 4, 35),
(10, 10, 3, 36),
(5, 5, 5, 37),
(6, 6, 4, 38),
(7, 7, 3, 39),
(8, 8, 2, 40);

-- Volcando estructura para tabla publicaciones.resenas
CREATE TABLE IF NOT EXISTS `resenas` (
  `comentario` varchar(255) DEFAULT NULL,
  `idSuscriptor` int NOT NULL,
  `idEvento` int NOT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idSuscriptor`,`idEvento`),
  KEY `fk_resenas_eventos` (`idEvento`),
  CONSTRAINT `fk_resenas_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_resenas_suscriptores` FOREIGN KEY (`idSuscriptor`) REFERENCES `suscriptores` (`idSuscriptor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.resenas: ~30 rows (aproximadamente)
INSERT INTO `resenas` (`comentario`, `idSuscriptor`, `idEvento`, `fecha`) VALUES
('Increíble concierto, la banda fue espectacular', 5, 1, '2024-05-26'),
('La clase de yoga fue muy relajante y bien organizada', 6, 2, '2024-05-26'),
('El torneo de ajedrez estuvo bien organizado', 7, 3, '2024-05-26'),
('El mercadillo fue divertido, había muchas cosas interesantes', 8, 4, '2024-05-26'),
('El concierto de jazz fue maravilloso', 9, 5, '2024-05-27'),
('Aprendí mucho en el taller de cocina italiana', 10, 6, '2024-05-27'),
('La carrera 5K fue desafiante pero gratificante', 5, 7, '2024-05-27'),
('La feria del libro fue excelente, muchos libros interesantes', 6, 8, '2024-05-27'),
('El concierto de música clásica fue sublime', 7, 9, '2024-05-28'),
('La clase de zumba fue muy divertida', 8, 10, '2024-05-28'),
('El torneo de videojuegos estuvo muy competitivo', 9, 11, '2024-05-28'),
('La exposición de arte fue inspiradora', 10, 12, '2024-05-28'),
('El concierto de pop fue electrizante', 5, 13, '2024-05-29'),
('El taller de fotografía fue muy educativo', 6, 14, '2024-05-29'),
('La competición de natación estuvo muy reñida', 7, 15, '2024-05-29'),
('La feria de artesanía fue una gran experiencia', 8, 16, '2024-05-29'),
('La música electrónica estuvo genial', 9, 17, '2024-05-26'),
('El taller de pintura fue muy relajante', 10, 18, '2024-05-26'),
('El maratón fue agotador pero satisfactorio', 5, 19, '2024-05-26'),
('La feria gastronómica fue deliciosa', 6, 20, '2024-05-26'),
('El concierto de reggae fue muy alegre', 7, 21, '2024-05-27'),
('El taller de cerámica fue muy práctico', 8, 22, '2024-05-27'),
('La competición de baile fue espectacular', 9, 23, '2024-05-27'),
('El mercado de pulgas fue entretenido', 10, 24, '2024-05-27'),
('El concierto acústico fue muy íntimo', 5, 25, '2024-05-28'),
('La clase de danza contemporánea fue desafiante', 6, 26, '2024-05-28'),
('El torneo de tenis fue muy emocionante', 7, 27, '2024-05-28'),
('El festival de cine mostró películas increíbles', 8, 28, '2024-05-28'),
('El concierto de hip-hop fue enérgico', 9, 29, '2024-05-29'),
('El taller de escritura creativa fue muy inspirador', 10, 30, '2024-05-29'),
('La competición de surf fue impresionante', 5, 31, '2024-05-29'),
('La feria del vino fue una gran experiencia', 6, 32, '2024-05-29'),
('El concierto de indie fue muy íntimo', 7, 33, '2024-05-26'),
('La clase de pilates fue muy relajante', 8, 34, '2024-05-26'),
('La competición de escalada fue desafiante', 9, 35, '2024-05-26'),
('El festival de comida vegana fue delicioso', 10, 36, '2024-05-26'),
('El concierto de blues fue muy emotivo', 5, 37, '2024-05-27'),
('El taller de macramé fue muy interesante', 6, 38, '2024-05-27'),
('El torneo de fútbol fue muy competitivo', 7, 39, '2024-05-27'),
('La feria de antigüedades fue muy interesante', 8, 40, '2024-05-27');

-- Volcando estructura para tabla publicaciones.suscripciones
CREATE TABLE IF NOT EXISTS `suscripciones` (
  `idSuscriptor` int NOT NULL,
  `idEvento` int NOT NULL,
  `fechaSus` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idSuscriptor`,`idEvento`),
  KEY `fk_suscripciones_eventos` (`idEvento`),
  CONSTRAINT `fk_suscripciones_eventos` FOREIGN KEY (`idEvento`) REFERENCES `eventos` (`idEvento`),
  CONSTRAINT `fk_suscripciones_suscriptores` FOREIGN KEY (`idSuscriptor`) REFERENCES `suscriptores` (`idSuscriptor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.suscripciones: ~2 rows (aproximadamente)
INSERT INTO `suscripciones` (`idSuscriptor`, `idEvento`, `fechaSus`, `estado`) VALUES
(5, 1, '2024-05-01', 1),
(6, 2, '2024-05-02', 1),
(7, 3, '2024-05-03', 1),
(8, 4, '2024-05-04', 1),
(9, 5, '2024-05-05', 1),
(10, 6, '2024-05-06', 1),
(5, 7, '2024-05-07', 1),
(6, 8, '2024-05-08', 1),
(7, 9, '2024-05-09', 1),
(8, 10, '2024-05-10', 1),
(9, 11, '2024-05-11', 1),
(10, 12, '2024-05-12', 1),
(5, 13, '2024-05-13', 1),
(6, 14, '2024-05-14', 1),
(7, 15, '2024-05-15', 1),
(8, 16, '2024-05-16', 1),
(9, 17, '2024-05-17', 1),
(10, 18, '2024-05-18', 1),
(5, 19, '2024-05-19', 1),
(6, 20, '2024-05-20', 1),
(7, 21, '2024-05-21', 1),
(8, 22, '2024-05-22', 1),
(9, 23, '2024-05-23', 1),
(10, 24, '2024-05-24', 1),
(5, 25, '2024-05-25', 1),
(6, 26, '2024-05-26', 1),
(7, 27, '2024-05-27', 1),
(8, 28, '2024-05-28', 1),
(9, 29, '2024-05-29', 1),
(10, 30, '2024-05-30', 1),
(5, 31, '2024-05-31', 1),
(6, 32, '2024-06-01', 1),
(7, 33, '2024-06-02', 1),
(8, 34, '2024-06-03', 1),
(9, 35, '2024-06-04', 1),
(10, 36, '2024-06-05', 1),
(5, 37, '2024-06-06', 1),
(6, 38, '2024-06-07', 1),
(7, 39, '2024-06-08', 1),
(8, 40, '2024-06-09', 1);

-- Volcando estructura para tabla publicaciones.suscriptores
CREATE TABLE IF NOT EXISTS `suscriptores` (
  `idSuscriptor` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int DEFAULT NULL,
  `numSuscripciones` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idSuscriptor`),
  KEY `fk_suscriptores_usuarios` (`idUsuario`),
  CONSTRAINT `fk_suscriptores_usuarios` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla publicaciones.suscriptores: ~6 rows (aproximadamente)
INSERT INTO `suscriptores` (`idSuscriptor`, `idUsuario`, `numSuscripciones`, `direccion`, `ciudad`, `pais`) VALUES
  (5, 5, '5', 'Calle Mayor, 10', 'Madrid', 'España'),
  (6, 6, '3', 'Carrer de Provença, 20', 'Barcelona', 'España'),
  (7, 7, '7', 'Avenida de la Constitución, 15', 'Sevilla', 'España'),
  (8, 8, '4', 'Calle Real, 5', 'Valencia', 'España'),
  (9, 9, '4', 'Calle Real, 5', 'Valencia', 'España'),
  (10, 10, '4', 'Calle Real, 5', 'Valencia', 'España');

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

-- Volcando datos para la tabla publicaciones.usuarios: ~11 rows (aproximadamente)
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
