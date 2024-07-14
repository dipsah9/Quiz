-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2024-06-20 23:43:05
-- 服务器版本： 10.4.32-MariaDB
-- PHP 版本： 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `quiz_game`
--

-- --------------------------------------------------------

--
-- 表的结构 `game_sessions`
--

CREATE TABLE `game_sessions` (
  `session_id` int(11) NOT NULL,
  `player_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `player_answer` varchar(100) DEFAULT NULL,
  `is_correct` tinyint(1) DEFAULT NULL,
  `answer_time` int(11) DEFAULT NULL,
  `points` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 表的结构 `players`
--

CREATE TABLE `players` (
  `player_id` int(11) NOT NULL,
  `player_name` varchar(50) DEFAULT NULL,
  `score` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `players`
--

INSERT INTO `players` (`player_id`, `player_name`, `score`) VALUES
(1, 'Player1', 150),
(2, 'Player2', 300),
(3, 'Player3', 250);

-- --------------------------------------------------------

--
-- 表的结构 `questions`
--

CREATE TABLE `questions` (
  `unique_id` int(11) NOT NULL,
  `category` varchar(30) NOT NULL,
  `question_id` int(11) NOT NULL,
  `question` varchar(200) NOT NULL,
  `true_answer` varchar(100) NOT NULL,
  `fake_answer_1` varchar(100) NOT NULL,
  `fake_answer_2` varchar(100) NOT NULL,
  `fake_answer_3` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 转存表中的数据 `questions`
--

INSERT INTO `questions` (`unique_id`, `category`, `question_id`, `question`, `true_answer`, `fake_answer_1`, `fake_answer_2`, `fake_answer_3`) VALUES
(1, 'history', 1, 'Wann begann der Zweite Weltkrieg?', '1939', '1914', '1945', '1950'),
(2, 'history', 2, 'Wer war der erste Präsident der Vereinigten Staaten?', 'George Washington', 'Thomas Jefferson', 'Abraham Lincoln', 'John Adams'),
(3, 'history', 3, 'Welches Jahr wird allgemein als das Jahr des Mauerfalls in Berlin betrachtet?', '1989', '1985', '1987', '1991'),
(4, 'history', 4, 'Welche antike Zivilisation baute die Pyramiden von Gizeh?', 'Ägypter', 'Mesopotamier', 'Griechen', 'Römer'),
(5, 'history', 5, 'Wer schrieb die 95 Thesen, die die Reformation einleiteten?', ' Martin Luther', ' Johannes Calvin', 'Ulrich Zwingli', 'Philipp Melanchthon'),
(6, 'science', 1, 'Wer entwickelte die allgemeine Relativitätstheorie?', 'Albert Einstein', 'Isaac Newton', 'Niels Bohr', 'Stephen Hawking'),
(7, 'science', 2, 'Was ist die chemische Formel für Wasser?', 'H₂O', 'CO₂', 'NaCl', 'CH₄'),
(8, 'science', 3, 'Wer gilt als der Vater der modernen Evolutionstheorie?', 'Charles Darwin', 'Gregor Mendel', 'Alfred Russel Wallace', 'Jean-Baptiste Lamarck'),
(9, 'science', 4, 'Was ist das häufigste Element im Universum?', ' Wasserstoff', 'Helium', 'Sauerstoff', 'Kohlenstoff'),
(10, 'science', 5, 'Welches Organ ist für das Pumpen von Blut im menschlichen Körper verantwortlich?', 'Herz', 'Lunge', 'Leber', 'Niere'),
(11, 'Common sense', 1, 'Wo stammt der Kaffee ursprünglich her?', 'Äthiopien', 'Italien', 'Frankreich', 'Robusta'),
(12, 'Video Game', 1, 'Wie lautet der vollständige Name von \"FUSE\" in Apex Legends?', 'Walter Fitzroy Jr.', 'August Montgomery Brinkman', 'Blódhundr', 'Makoa Gibraltar'),
(13, 'Video Game', 2, 'In DOTA2, zu welchem Helden gehört die Fähigkeit \'EYE OF THE STORM\'?', 'RAZOR', 'ZEUS', 'STORM SPIRIT', 'DISRUPTOR'),
(14, 'Mathematik', 1, 'Wenn x > 1 ist, vergleichen Sie die Größe von 3^(x+1) und 4^x.', '3x+1 < 4x', '3x+1 = 4x', 'Kann nicht bestimmt werden', '3x+1 > 4x'),
(15, 'Video Game', 3, 'Im Spiel „Final Fantasy VII“, wie lautet der vollständige Name von Tifa?\r\n\r\n', 'Tifa Lockhart', 'Tifa Strife', 'Tifa Jerusalem', 'Tifa Gainsborough'),
(16, 'Video Game', 4, ' Im Switch-Spiel „Splatoon 3“ (auch bekannt als „Splatton 3“), welche der folgenden Waffentypen existiert nicht?', 'Wurfmesser', 'Regenschirm', 'Scharfschützengewehr', 'Pinsel'),
(17, 'Mathematik', 2, 'Dezimalzahl 23 in Binär umgewandelt ist', '10111', '11000', '11001', '10110'),
(18, 'Common sense', 2, 'Welcher der folgenden Planeten im Sonnensystem hat keinen Mond?', 'Venus', 'Erde', 'Mars', 'Saturn'),
(19, 'Common sense', 3, 'Welches ist das einzige Land der Welt mit drei Hauptstädten?', 'Südafrika', 'Australien', 'Brasilien', 'Japan'),
(20, 'Video Game', 5, 'Im Online-Spiel League of Legends, wie heißt die ultimative Fähigkeit des beliebten Charakters Lee Sin?', 'Zorn des Drachen', 'Schallwelle', 'Resonanzschlag', 'Orkan'),
(21, 'Video Game', 6, 'In Overwatch, was ist der Codename von Reaper im Hintergrund der Geschichte des Soldier Enhancement Programms?', 'Soldat: 24', 'Geist', 'Soldat: 76', 'Phantom'),
 -- Following questions are AI-Generated
(22, 'history', 6, 'Welches Jahr wird allgemein als das Ende des Römischen Reiches betrachtet?', '476 n. Chr.', '1453 n. Chr.', '1492 n. Chr.', '800 n. Chr.'),
(23, 'history', 7, 'Wer war der Anführer der Hunnen, der das Römische Reich bedrohte?', 'Attila', 'Genghis Khan', 'Alaric', 'Tamerlane'),
(24, 'history', 8, 'Welches Land war das erste, das das Frauenwahlrecht einführte?', 'Neuseeland', 'USA', 'Deutschland', 'Frankreich'),
(25, 'history', 9, 'In welchem Jahr begann die Französische Revolution?', '1789', '1776', '1804', '1799'),
(26, 'history', 10, 'Wer entdeckte Amerika im Jahr 1492?', 'Christopher Columbus', 'Leif Erikson', 'Amerigo Vespucci', 'Ferdinand Magellan'),
(27, 'history', 11, 'Welche Schlacht markierte das Ende der Napoleonischen Kriege?', 'Schlacht bei Waterloo', 'Schlacht bei Trafalgar', 'Schlacht von Leipzig', 'Schlacht von Borodino'),
(28, 'history', 12, 'Wer war der Premierminister von Großbritannien während des größten Teils des Zweiten Weltkriegs?', 'Winston Churchill', 'Neville Chamberlain', 'Clement Attlee', 'David Lloyd George'),
(29, 'history', 13, 'Welche antike Stadt wurde 79 n. Chr. durch den Ausbruch des Vesuvs zerstört?', 'Pompeji', 'Herculaneum', 'Stabiae', 'Oplontis'),
(30, 'history', 14, 'Wer war der Führer der Bolschewiki während der Russischen Revolution von 1917?', 'Wladimir Lenin', 'Joseph Stalin', 'Leon Trotzki', 'Nikolai Bucharin'),
(31, 'history', 15, 'Welcher Vertrag beendete den Ersten Weltkrieg?', 'Vertrag von Versailles', 'Vertrag von Trianon', 'Vertrag von Brest-Litowsk', 'Vertrag von Saint-Germain'),
(32, 'history', 16, 'Welche Dynastie regierte China während des Baus der Großen Mauer?', 'Ming-Dynastie', 'Qing-Dynastie', 'Tang-Dynastie', 'Song-Dynastie'),
(33, 'history', 17, 'Wer war der erste Kaiser von Rom?', 'Augustus', 'Julius Caesar', 'Nero', 'Tiberius'),
(34, 'history', 18, 'In welchem Jahr wurde die Berliner Mauer gebaut?', '1961', '1949', '1953', '1971'),
(35, 'history', 19, 'Welche Königin von England war bekannt für ihre sechs Ehen?', 'Heinrich VIII.', 'Maria I.', 'Elisabeth I.', 'Victoria'),
(36, 'history', 20, 'Welcher antike griechische Philosoph war der Lehrer von Alexander dem Großen?', 'Aristoteles', 'Platon', 'Sokrates', 'Pythagoras'),
(37, 'Video Game', 7, 'Welches Spiel wird oft als der erste echte Ego-Shooter betrachtet?', 'Wolfenstein 3D', 'Doom', 'Quake', 'Half-Life'),
(38, 'Video Game', 8, 'In welchem Jahr wurde das erste Spiel der "Final Fantasy"-Serie veröffentlicht?', '1987', '1985', '1989', '1991'),
(39, 'Video Game', 9, 'Welches Spiel hat die Charaktere Mario und Luigi eingeführt?', 'Mario Bros.', 'Super Mario 64', 'Donkey Kong', 'Super Mario Bros.'),
(40, 'Video Game', 10, 'Welches Spiel ist bekannt für den berühmten Cheat-Code "Up, Up, Down, Down, Left, Right, Left, Right, B, A, Start"?', 'Contra', 'Mortal Kombat', 'Street Fighter II', 'Sonic the Hedgehog'),
(41, 'Video Game', 11, 'In welchem Spiel kommt der Charakter "Master Chief" vor?', 'Halo', 'Call of Duty', 'Gears of War', 'Half-Life'),
(42, 'Video Game', 12, 'Welches Spiel gilt als das meistverkaufte Videospiel aller Zeiten?', 'Minecraft', 'Tetris', 'Grand Theft Auto V', 'The Legend of Zelda: Breath of the Wild'),
(43, 'Video Game', 13, 'Welche Firma entwickelte das Spiel "The Legend of Zelda"?', 'Nintendo', 'Sega', 'Sony', 'Microsoft'),
(44, 'Video Game', 14, 'In welchem Spiel kämpft der Protagonist gegen den Bösewicht Ganon?', 'The Legend of Zelda', 'Super Mario Bros.', 'Metroid', 'Final Fantasy'),
(45, 'Video Game', 15, 'Welche Serie beinhaltet den Charakter "Lara Croft"?', 'Tomb Raider', 'Uncharted', 'Assassins Creed', 'Far Cry'),
(46, 'Video Game', 16, 'In welchem Spiel spielt man als Geralt von Rivia?', 'The Witcher', 'Dark Souls', 'Elder Scrolls', 'Diablo'),
(47, 'Video Game', 17, 'Welches Spiel ist bekannt für das Zitat "The cake is a lie"?', 'Portal', 'Half-Life 2', 'BioShock', 'Mass Effect'),
(48, 'Video Game', 18, 'Welche Konsole wurde zuerst veröffentlicht?', 'Atari 2600', 'Nintendo Entertainment System (NES)', 'Sega Genesis', 'PlayStation'),
(49, 'Video Game', 19, 'In welchem Spiel gibt es die Charaktere Solid Snake und Big Boss?', 'Metal Gear Solid', 'Splinter Cell', 'Hitman', 'Resident Evil'),
(50, 'Video Game', 20, 'In welchem Spiel ist der Protagonist ein pinkes, rundes Wesen, das Feinde einsaugen und deren Fähigkeiten übernehmen kann?', 'Kirby', 'Pikachu', 'Jigglypuff', 'Meta Knight'),
(51, 'science', 6, 'Wie viele Planeten gibt es in unserem Sonnensystem?', '8', '7', '9', '10'),
(52, 'science', 7, 'Welches Element hat das chemische Symbol "O"?', 'Sauerstoff', 'Gold', 'Silber', 'Kalium'),
(53, 'science', 8, 'Wer ist bekannt für die Entdeckung der Schwerkraft?', 'Isaac Newton', 'Galileo Galilei', 'Nikolaus Kopernikus', 'Johannes Kepler'),
(54, 'science', 9, 'Was ist das stärkste bekannte Magnetfeld im Universum?', 'Magnetar', 'Schwarzes Loch', 'Neutronenstern', 'Weißer Zwerg'),
(55, 'science', 10, 'Welches Gas ist hauptsächlich für den Treibhauseffekt verantwortlich?', 'Kohlendioxid', 'Sauerstoff', 'Wasserstoff', 'Stickstoff'),
(56, 'science', 11, 'Was ist die Einheit der elektrischen Stromstärke?', 'Ampere', 'Volt', 'Watt', 'Ohm'),
(57, 'science', 12, 'Welcher Planet ist der heißeste in unserem Sonnensystem?', 'Venus', 'Merkur', 'Mars', 'Jupiter'),
(58, 'science', 13, 'Welches Tier hat das größte Gehirn im Verhältnis zu seiner Körpergröße?', 'Ameise', 'Elefant', 'Blauwal', 'Delfin'),
(59, 'science', 14, 'Welcher Vogel ist der einzige, der rückwärts fliegen kann?', 'Kolibri', 'Adler', 'Spatz', 'Taube'),
(60, 'science', 15, 'Wer entdeckte Penicillin?', 'Alexander Fleming', 'Marie Curie', 'Louis Pasteur', 'Gregor Mendel'),
(61, 'science', 16, 'Welches ist das schwerste bekannte Element?', 'Oganesson', 'Uran', 'Plutonium', 'Gold'),
(62, 'science', 17, 'Wie heißt die größte Wüste der Welt?', 'Antarktische Wüste', 'Sahara', 'Gobi', 'Arktische Wüste'),
(63, 'science', 18, 'Was ist die häufigste Blutgruppe?', '0 positiv', 'A positiv', 'B positiv', 'AB negativ'),
(64, 'science', 19, 'Welches ist das längste Knochen im menschlichen Körper?', 'Femur (Oberschenkelknochen)', 'Tibia (Schienbein)', 'Humerus (Oberarmknochen)', 'Fibula (Wadenbein)'),
(65, 'science', 20, 'Was ist das am weitesten entfernte Objekt, das von Menschen gemacht wurde?', 'Voyager 1', 'Hubble-Weltraumteleskop', 'Apollo 11', 'International Space Station (ISS)'),
(66, 'Mathematik', 3, 'Was ist das Ergebnis von 7 * 8?', '56', '54', '48', '64'),
(67, 'Mathematik', 4, 'Wie lautet die Quadratwurzel von 81?', '9', '8', '7', '6'),
(68, 'Mathematik', 5, 'Was ist der Wert von π (Pi) auf zwei Dezimalstellen gerundet?', '3,14', '3,13', '3,15', '3,16'),
(69, 'Mathematik', 6, 'Was ist die Summe der Winkel in einem Dreieck?', '180 Grad', '360 Grad', '90 Grad', '270 Grad'),
(70, 'Mathematik', 7, 'Was ist die Ableitung von x^2?', '2x', 'x', 'x^2', '2'),
(71, 'Mathematik', 8, 'Wie lautet die Formel für den Flächeninhalt eines Kreises?', 'πr^2', '2πr', 'πd', 'πr'),
(72, 'Mathematik', 9, 'Was ist 15% von 200?', '30', '25', '20', '35'),
(73, 'Mathematik', 10, 'Was ist das Produkt von 12 und 12?', '144', '124', '136', '148'),
(74, 'Mathematik', 11, 'Was ist der kleinste Primzahl?', '2', '1', '3', '5'),
(75, 'Mathematik', 12, 'Was ist die Lösung der Gleichung 3x - 9 = 0?', 'x = 3', 'x = 2', 'x = 0', 'x = -3'),
(76, 'Mathematik', 13, 'Was ist das Ergebnis von 2^5?', '32', '25', '10', '20'),
(77, 'Mathematik', 14, 'Wie lautet die Formel für den Umfang eines Rechtecks?', '2(l + b)', 'l + b', '2lb', 'l^2 + b^2'),
(78, 'Mathematik', 15, 'Was ist die Lösung der Gleichung x^2 - 4 = 0?', 'x = ±2', 'x = 2', 'x = -2', 'x = 4'),
(79, 'Mathematik', 16, 'Wie viele Seiten hat ein Oktagon?', '8', '6', '7', '9'),
(80, 'Mathematik', 17, 'Was ist das Ergebnis von 100 geteilt durch 4?', '25', '20', '30', '24'),
(81, 'Mathematik', 18, 'Was ist die Summe von 7 und 13?', '20', '21', '19', '18'),
(82, 'Mathematik', 19, 'Was ist die Hälfte von 86?', '43', '42', '44', '45'),
(83, 'Mathematik', 20, 'Was ist der Logarithmus von 100 zur Basis 10?', '2', '10', '1', '0'),
(84, 'Common sense', 4, 'Welche Farbe entsteht durch Mischen von Blau und Gelb?', 'Grün', 'Lila', 'Orange', 'Braun'),
(85, 'Common sense', 5, 'Wie viele Kontinente gibt es auf der Erde?', '7', '6', '5', '8'),
(86, 'Common sense', 6, 'Welcher Planet ist bekannt als der "Rote Planet"?', 'Mars', 'Jupiter', 'Saturn', 'Venus'),
(87, 'Common sense', 7, 'Wie viele Wochen hat ein Jahr?', '52', '50', '48', '54'),
(88, 'Common sense', 8, 'Was ist H2O?', 'Wasser', 'Kohlendioxid', 'Sauerstoff', 'Wasserstoff'),
(89, 'Common sense', 9, 'Welche Währung wird in Japan verwendet?', 'Yen', 'Dollar', 'Euro', 'Won'),
(90, 'Common sense', 10, 'Was ist die Hauptstadt von Frankreich?', 'Paris', 'London', 'Berlin', 'Madrid'),
(91, 'Common sense', 11, 'Welcher Ozean liegt zwischen Afrika und Australien?', 'Indischer Ozean', 'Pazifischer Ozean', 'Atlantischer Ozean', 'Arktischer Ozean'),
(92, 'Common sense', 12, 'Was ist die größte Spezies von Landtieren?', 'Elefant', 'Giraffe', 'Nilpferd', 'Nashorn'),
(93, 'Common sense', 13, 'Wie viele Farben hat ein Regenbogen?', '7', '5', '6', '8'),
(94, 'Common sense', 14, 'Wer erfand das Telefon?', 'Alexander Graham Bell', 'Thomas Edison', 'Nikola Tesla', 'Guglielmo Marconi'),
(95, 'Common sense', 15, 'Was ist der größte Kontinent der Welt?', 'Asien', 'Afrika', 'Nordamerika', 'Europa'),
(96, 'Common sense', 16, 'Wie viele Zähne hat ein erwachsener Mensch normalerweise?', '32', '28', '30', '34'),
(97, 'Common sense', 17, 'Welche Jahreszeit folgt auf den Sommer?', 'Herbst', 'Winter', 'Frühling', 'Sommer'),
(98, 'Common sense', 18, 'Wie viele Stunden hat ein Tag?', '24', '12', '36', '48'),
(99, 'Common sense', 19, 'Wie heißt der größte Ozean der Welt?', 'Pazifischer Ozean', 'Atlantischer Ozean', 'Indischer Ozean', 'Arktischer Ozean'),
(100, 'Common sense', 20, 'Was ist die chemische Formel für Kochsalz?', 'NaCl', 'H2O', 'CO2', 'O2');

--
-- 转储表的索引
--

--
-- 表的索引 `game_sessions`
--
ALTER TABLE `game_sessions`
  ADD PRIMARY KEY (`session_id`),
  ADD KEY `player_id` (`player_id`),
  ADD KEY `question_id` (`question_id`);

--
-- 表的索引 `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`player_id`);

--
-- 表的索引 `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`unique_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `game_sessions`
--
ALTER TABLE `game_sessions`
  MODIFY `session_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `players`
--
ALTER TABLE `players`
  MODIFY `player_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `questions`
--
ALTER TABLE `questions`
  MODIFY `unique_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- 限制导出的表
--

--
-- 限制表 `game_sessions`
--
ALTER TABLE `game_sessions`
  ADD CONSTRAINT `game_sessions_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`),
  ADD CONSTRAINT `game_sessions_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`unique_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
