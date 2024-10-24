-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 26 2024 г., 14:05
-- Версия сервера: 10.4.32-MariaDB
-- Версия PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `auctionbd`
--

-- --------------------------------------------------------

--
-- Структура таблицы `auction`
--

CREATE TABLE `auction` (
  `auction_date_id` int(11) DEFAULT NULL,
  `host_user_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `auction`
--

INSERT INTO `auction` (`auction_date_id`, `host_user_id`, `id`, `name`) VALUES
(1, 3, 1, 'Кисики'),
(2, 3, 2, 'Ежики'),
(3, 6, 3, 'Барашек'),
(4, 6, 4, 'Халва'),
(5, 6, 5, 'Диван'),
(6, 9, 6, 'Коровка'),
(7, 7, 7, 'Машина v1'),
(8, 7, 8, 'Машина V2'),
(9, 8, 9, 'Котик'),
(10, 8, 10, 'Долма'),
(11, 8, 11, 'Карась'),
(12, 9, 12, 'Сапфировое колье'),
(13, 8, 13, 'Изумрудное колье');

-- --------------------------------------------------------

--
-- Структура таблицы `auction_data`
--

CREATE TABLE `auction_data` (
  `id` int(11) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `sand_msg` int(11) DEFAULT NULL,
  `start_cost` int(11) DEFAULT NULL,
  `thematics_id` int(11) NOT NULL,
  `time_end_delay` int(11) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `auction_data`
--

INSERT INTO `auction_data` (`id`, `is_active`, `sand_msg`, `start_cost`, `thematics_id`, `time_end_delay`, `start_date`, `description`, `photo`) VALUES
(1, b'0', 2, 5, 3, 1, '2024-05-26 19:37:14.000000', 'мама мыла раму', NULL),
(2, b'1', 0, 1, 1, 1, '2024-05-26 20:27:14.000000', 'мама мыла ежика ', NULL),
(3, b'1', 0, 1000, 1, 2, '2024-06-09 19:42:00.000000', 'Продам барашка', '04de55ec-766b-4670-8af7-4b3b1b9fd21f_1674709862_3-125.jpg'),
(4, b'1', 0, 50000, 5, 5, '2024-06-21 19:48:00.000000', 'Супер халва', 'b9c81544-4f78-4a26-a6c9-b1108d92ce00_2b9765e22a60c0db7f456736a1d80a95.jpeg'),
(5, b'1', 0, 10000, 7, 2, '2024-06-14 01:49:00.000000', 'Продам старый дедушкин диван', '24917b5a-5881-453d-adfc-4226dd5daf81_038db901d257a8e355d3902f1a1752ea.jpg'),
(6, b'1', 0, 240000, 1, 1, '2024-06-15 19:50:00.000000', 'Продам коровку', '689cf24f-ce12-4dbc-9fd6-58fab298bf9c_507305_main.jpg'),
(7, b'1', 0, 5000000, 6, 50, '2024-06-22 19:52:00.000000', 'Продам машину', '3b74ccdc-3834-47bb-ba15-312847a3fa40_c82381as-960.jpg'),
(8, b'1', 0, 500505, 6, 23, '2024-06-28 19:53:00.000000', 'Еще машина продаю', '2b623e71-8e1c-4952-a8e2-1440b86dd04b_Samye-effektnye-proekty-na-baze-GAZ-21-Volga.jpg'),
(9, b'1', 0, 25000, 1, 2, '2024-07-07 19:54:00.000000', 'Котик', '50e35a6d-693f-44da-8711-75884eb825aa_1675496674_4-39.jpg'),
(10, b'1', 0, 100, 5, 2, '2024-06-08 19:55:00.000000', 'Долма', NULL),
(11, b'1', 0, 1000, 1, 1, '2024-06-12 19:55:00.000000', 'Карась обыкновенный', NULL),
(12, b'0', 2, 300, 4, 1, '2024-05-26 19:58:00.000000', 'Сапфировое колье', '63d813fd-a014-484a-b64e-ddd3db05e525_XXX_415_1379515661_1.jpg'),
(13, b'0', 2, 10000, 4, 1, '2024-05-26 20:03:00.000000', 'Изумрудное колье', 'dec006db-0a79-44ad-82e9-9d2d06b91718_8e20c05f863258cd52d73f3f585bd316.jpg');

-- --------------------------------------------------------

--
-- Структура таблицы `auction_data_members`
--

CREATE TABLE `auction_data_members` (
  `auction_data_id` int(11) NOT NULL,
  `members_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `auction_data_members`
--

INSERT INTO `auction_data_members` (`auction_data_id`, `members_id`) VALUES
(1, 1),
(1, 2),
(1, 4),
(2, 1),
(2, 2),
(2, 4),
(2, 9),
(2, 6),
(4, 9),
(4, 7),
(3, 9),
(3, 7),
(6, 6),
(6, 7),
(6, 8),
(9, 3),
(7, 8),
(7, 3),
(8, 8),
(8, 3),
(12, 6),
(12, 7),
(12, 8),
(13, 7),
(13, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `readed` bit(1) DEFAULT NULL,
  `recipient_user_id` int(11) DEFAULT NULL,
  `post_date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `notification`
--

INSERT INTO `notification` (`id`, `readed`, `recipient_user_id`, `post_date`, `message`) VALUES
(1, b'0', 1, '2024-05-26 19:37:14.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(2, b'0', 2, '2024-05-26 19:37:14.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(3, b'0', 3, '2024-05-26 19:37:14.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(4, b'0', 4, '2024-05-26 19:37:14.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(5, b'0', 5, '2024-05-26 19:37:14.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(6, b'0', 1, '2024-05-26 19:37:18.000000', 'Аукцион: \'Кисики\' начался'),
(7, b'0', 2, '2024-05-26 19:37:18.000000', 'Аукцион: \'Кисики\' начался'),
(8, b'0', 4, '2024-05-26 19:37:18.000000', 'Аукцион: \'Кисики\' начался'),
(9, b'0', 1, '2024-05-26 19:38:18.000000', 'Аукцион: \'Кисики\' закончился. Победителя нет'),
(10, b'0', 2, '2024-05-26 19:38:18.000000', 'Аукцион: \'Кисики\' закончился. Победителя нет'),
(11, b'0', 4, '2024-05-26 19:38:18.000000', 'Аукцион: \'Кисики\' закончился. Победителя нет'),
(12, b'1', 6, '2024-05-26 19:41:33.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(13, b'1', 7, '2024-05-26 19:41:41.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(14, b'1', 8, '2024-05-26 19:41:49.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(15, b'0', 9, '2024-05-26 19:49:52.000000', 'Добро пожаловать на наш сайт! приятного пользования!'),
(16, b'0', 9, '2024-05-26 19:51:06.000000', 'Вы присоединились к аукциону : Барашек'),
(17, b'0', 9, '2024-05-26 19:51:09.000000', 'Вы присоединились к аукциону : Халва'),
(18, b'0', 9, '2024-05-26 19:51:11.000000', 'Вы присоединились к аукциону : Ежики'),
(19, b'1', 6, '2024-05-26 19:51:52.000000', 'Вы присоединились к аукциону : Ежики'),
(20, b'1', 6, '2024-05-26 19:51:56.000000', 'Вы присоединились к аукциону : Коровка'),
(21, b'1', 7, '2024-05-26 19:53:29.000000', 'Вы присоединились к аукциону : Коровка'),
(22, b'1', 7, '2024-05-26 19:53:32.000000', 'Вы присоединились к аукциону : Халва'),
(23, b'1', 7, '2024-05-26 19:53:35.000000', 'Вы присоединились к аукциону : Барашек'),
(24, b'1', 8, '2024-05-26 19:55:31.000000', 'Вы присоединились к аукциону : Машина V2'),
(25, b'1', 8, '2024-05-26 19:55:34.000000', 'Вы присоединились к аукциону : Машина v1'),
(26, b'1', 8, '2024-05-26 19:55:36.000000', 'Вы присоединились к аукциону : Коровка'),
(27, b'0', 3, '2024-05-26 19:55:59.000000', 'Вы присоединились к аукциону : Котик'),
(28, b'0', 3, '2024-05-26 19:56:02.000000', 'Вы присоединились к аукциону : Машина v1'),
(29, b'0', 3, '2024-05-26 19:56:04.000000', 'Вы присоединились к аукциону : Машина V2'),
(30, b'1', 6, '2024-05-26 19:57:06.000000', 'Вы присоединились к аукциону : Сапфировое колье'),
(31, b'0', 7, '2024-05-26 19:57:12.000000', 'Вы присоединились к аукциону : Сапфировое колье'),
(32, b'1', 8, '2024-05-26 19:57:18.000000', 'Вы присоединились к аукциону : Сапфировое колье'),
(33, b'1', 6, '2024-05-26 19:58:03.000000', 'Аукцион: \'Сапфировое колье\' начался'),
(34, b'0', 7, '2024-05-26 19:58:03.000000', 'Аукцион: \'Сапфировое колье\' начался'),
(35, b'1', 8, '2024-05-26 19:58:03.000000', 'Аукцион: \'Сапфировое колье\' начался'),
(36, b'1', 6, '2024-05-26 19:58:17.000000', 'Ваша ставка была повышена! Аукцион: \'Сапфировое колье\''),
(37, b'0', 7, '2024-05-26 19:58:43.000000', 'Ваша ставка была повышена! Аукцион: \'Сапфировое колье\''),
(38, b'1', 8, '2024-05-26 19:58:55.000000', 'Ваша ставка была повышена! Аукцион: \'Сапфировое колье\''),
(39, b'0', 7, '2024-05-26 19:59:14.000000', 'Ваша ставка была повышена! Аукцион: \'Сапфировое колье\''),
(40, b'1', 6, '2024-05-26 19:59:26.000000', 'Ваша ставка была повышена! Аукцион: \'Сапфировое колье\''),
(41, b'1', 6, '2024-05-26 20:00:28.000000', 'Аукцион: \'Сапфировое колье\' закончился. Победитель: user3'),
(42, b'0', 7, '2024-05-26 20:00:28.000000', 'Аукцион: \'Сапфировое колье\' закончился. Победитель: user3'),
(43, b'1', 8, '2024-05-26 20:00:28.000000', 'Аукцион: \'Сапфировое колье\' закончился. Победитель: user3'),
(44, b'0', 7, '2024-05-26 20:01:50.000000', 'Вы присоединились к аукциону : Изумрудное колье'),
(45, b'1', 6, '2024-05-26 20:01:56.000000', 'Вы присоединились к аукциону : Изумрудное колье'),
(46, b'0', 7, '2024-05-26 20:03:03.000000', 'Аукцион: \'Изумрудное колье\' начался'),
(47, b'1', 6, '2024-05-26 20:03:03.000000', 'Аукцион: \'Изумрудное колье\' начался'),
(48, b'1', 6, '2024-05-26 20:03:48.000000', 'Ваша ставка была повышена! Аукцион: \'Изумрудное колье\''),
(49, b'0', 7, '2024-05-26 20:04:18.000000', 'Ваша ставка была повышена! Аукцион: \'Изумрудное колье\''),
(50, b'0', 7, '2024-05-26 20:05:18.000000', 'Аукцион: \'Изумрудное колье\' закончился. Победитель: user1'),
(51, b'0', 6, '2024-05-26 20:05:18.000000', 'Аукцион: \'Изумрудное колье\' закончился. Победитель: user1');

-- --------------------------------------------------------

--
-- Структура таблицы `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `thematics`
--

CREATE TABLE `thematics` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `thematics`
--

INSERT INTO `thematics` (`id`, `name`) VALUES
(1, 'Животные'),
(3, 'Другое'),
(4, 'Ювелирное изделие'),
(5, 'Кулинария'),
(6, 'Машины'),
(7, 'Мебель');

-- --------------------------------------------------------

--
-- Структура таблицы `transaction`
--

CREATE TABLE `transaction` (
  `auction_id` int(11) DEFAULT NULL,
  `cost` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `transaction`
--

INSERT INTO `transaction` (`auction_id`, `cost`, `id`, `user_id`, `date`) VALUES
(12, 400, 1, 6, '2024-05-26 19:58:06.000000'),
(12, 500, 2, 7, '2024-05-26 19:58:17.000000'),
(12, 1000, 3, 8, '2024-05-26 19:58:43.000000'),
(12, 2000, 4, 7, '2024-05-26 19:58:55.000000'),
(12, 5000, 5, 6, '2024-05-26 19:59:14.000000'),
(12, 10000, 6, 8, '2024-05-26 19:59:26.000000'),
(13, 15000, 7, 6, '2024-05-26 20:03:26.000000'),
(13, 17000, 8, 7, '2024-05-26 20:03:48.000000'),
(13, 18000, 9, 6, '2024-05-26 20:04:18.000000');

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_data_id` int(11) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `user_data_id`, `login`, `password`) VALUES
(1, 1, 'alljay', '$2a$10$tvGikFYykGlZX0v3B4dCCe3HI4YBQDl67ZKzlPergDrOwUmj31b5m'),
(2, 2, '1234', '$2a$10$95blg2nsaynVY3C0PPPF5uJhk5pxua8ONEif.9aefKJWpUyaO4LuG'),
(3, 3, 'mama', '$2a$10$yRfn5RCmDqIENHQj..eO5u7v5HL0FzUFZm.E314gkflQg.oGw3Lfu'),
(4, 4, 'papa', '$2a$10$fqWY17juVC2JDJ/KzkGTn.rFHYW42PegwdT9mDCHr5CtIj0Xxj9Ae'),
(5, 5, 'mylord', '$2a$10$FHOM9VV1LMwfocb77jHkq.LpX3HQplMB1bH4aflL1eFqxyt60J3SG'),
(6, 6, 'user1', '$2a$10$i5jUtddB6YxV1VCZVbTMYeZE6GNXpNpqGNXdl19f6jN3e/6W0Q6a2'),
(7, 7, 'user2', '$2a$10$f19SdpAqc9dlnXOnoQKz..gtjQMz30gokF/c3KSQqz0A8edpPaMfq'),
(8, 8, 'user3', '$2a$10$V22GrI3NoMQG9v4DatsIyumhHSuA8lPRr7SdiqS3Jpyogu2a4nvbq'),
(9, 9, 'miha12', '$2a$10$jkt4IutiSJsIrv565WA4hODs94OOJxg1IiUoarJPKs6d2j/5s3WG2');

-- --------------------------------------------------------

--
-- Структура таблицы `user_data`
--

CREATE TABLE `user_data` (
  `deposit` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `number_phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `user_data`
--

INSERT INTO `user_data` (`deposit`, `id`, `email`, `number_phone`) VALUES
(100000, 1, 'mihaspiva@yandex.ru', '89500947595'),
(100, 2, 'mihaspiva2@yandex.ru', '89500947596'),
(200, 3, 'mihaspiva3@yandex.ru', '89500947597'),
(300, 4, 'mihaspiva4@yandex.ru', '89500947588'),
(400, 5, 'mihaspiva4@yandex.ru', '89900947599'),
(12000, 6, '', ''),
(20000, 7, '', ''),
(0, 8, '', ''),
(100, 9, 'miha@yandex.hu', '88005553535');

-- --------------------------------------------------------

--
-- Структура таблицы `user_roles`
--

CREATE TABLE `user_roles` (
  `roles_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `user_roles`
--

INSERT INTO `user_roles` (`roles_id`, `user_id`) VALUES
(1, 1),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `auction`
--
ALTER TABLE `auction`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_pb9j43w5h122yqifq09kse4ls` (`auction_date_id`),
  ADD KEY `FK4jemeqy0rs6krblmsgn3nl6s4` (`host_user_id`);

--
-- Индексы таблицы `auction_data`
--
ALTER TABLE `auction_data`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqq37aj8dooa31qknik90cu6mi` (`thematics_id`);

--
-- Индексы таблицы `auction_data_members`
--
ALTER TABLE `auction_data_members`
  ADD KEY `FKlfgryo90tr6033r4ua4inga3n` (`members_id`),
  ADD KEY `FKsokqclt6jbum5c2gjnjxkmd68` (`auction_data_id`);

--
-- Индексы таблицы `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgtksickis1kjl98281hxsqsc0` (`recipient_user_id`);

--
-- Индексы таблицы `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `thematics`
--
ALTER TABLE `thematics`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqa8twg42fqfh9n4o10drf3sfg` (`auction_id`),
  ADD KEY `FKsg7jp0aj6qipr50856wf6vbw1` (`user_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_f3ws59u9pcnnru67tx61vd9qn` (`user_data_id`);

--
-- Индексы таблицы `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`roles_id`,`user_id`),
  ADD KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `auction`
--
ALTER TABLE `auction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `auction_data`
--
ALTER TABLE `auction_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT для таблицы `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `thematics`
--
ALTER TABLE `thematics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `user_data`
--
ALTER TABLE `user_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `auction`
--
ALTER TABLE `auction`
  ADD CONSTRAINT `FK4jemeqy0rs6krblmsgn3nl6s4` FOREIGN KEY (`host_user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKaw4y8ssore1sbthj3ferr69jc` FOREIGN KEY (`auction_date_id`) REFERENCES `auction_data` (`id`);

--
-- Ограничения внешнего ключа таблицы `auction_data`
--
ALTER TABLE `auction_data`
  ADD CONSTRAINT `FKqq37aj8dooa31qknik90cu6mi` FOREIGN KEY (`thematics_id`) REFERENCES `thematics` (`id`);

--
-- Ограничения внешнего ключа таблицы `auction_data_members`
--
ALTER TABLE `auction_data_members`
  ADD CONSTRAINT `FKlfgryo90tr6033r4ua4inga3n` FOREIGN KEY (`members_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKsokqclt6jbum5c2gjnjxkmd68` FOREIGN KEY (`auction_data_id`) REFERENCES `auction_data` (`id`);

--
-- Ограничения внешнего ключа таблицы `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FKgtksickis1kjl98281hxsqsc0` FOREIGN KEY (`recipient_user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FKqa8twg42fqfh9n4o10drf3sfg` FOREIGN KEY (`auction_id`) REFERENCES `auction` (`id`),
  ADD CONSTRAINT `FKsg7jp0aj6qipr50856wf6vbw1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKkjrwwk4ag3bq7rvirdd2mk2eq` FOREIGN KEY (`user_data_id`) REFERENCES `user_data` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
