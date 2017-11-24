-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2017 a las 19:40:50
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `club_nautico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `id_salida` int(11) NOT NULL,
  `identificacion_navegate` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `numero_matricula` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `destino` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `eliminar` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`id_salida`, `identificacion_navegate`, `numero_matricula`, `fecha`, `hora`, `destino`, `eliminar`) VALUES
(4, '123', 131556, '2017-12-12', '19:30:00', 'Cartagena', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barco`
--

CREATE TABLE `barco` (
  `numero_matricula` int(11) NOT NULL,
  `numero_amarre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `identificacion_propietario` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `eliminar` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `barco`
--

INSERT INTO `barco` (`numero_matricula`, `numero_amarre`, `identificacion_propietario`, `nombre`, `eliminar`) VALUES
(131556, 'A-22', '123', 'Luna Azul', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capitan`
--

CREATE TABLE `capitan` (
  `identificacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `licencia` int(11) NOT NULL,
  `eliminar` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `capitan`
--

INSERT INTO `capitan` (`identificacion`, `licencia`, `eliminar`) VALUES
('321', 654321, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `identificacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`identificacion`, `nombre`, `apellido`, `telefono`) VALUES
('123', 'Jhon', 'Buitrago', '310'),
('321', 'Faby', 'Somavilla', '515446546');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socio`
--

CREATE TABLE `socio` (
  `identificacion` varchar(11) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `eliminar` tinyint(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `socio`
--

INSERT INTO `socio` (`identificacion`, `direccion`, `eliminar`) VALUES
('123', 'Cra 12', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id_salida`);

--
-- Indices de la tabla `barco`
--
ALTER TABLE `barco`
  ADD UNIQUE KEY `numero_matricula` (`numero_matricula`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD UNIQUE KEY `identificacion` (`identificacion`);

--
-- Indices de la tabla `socio`
--
ALTER TABLE `socio`
  ADD UNIQUE KEY `identificacion` (`identificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `id_salida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
