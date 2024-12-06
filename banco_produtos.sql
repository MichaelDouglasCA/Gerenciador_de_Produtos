-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06/12/2024 às 01:12
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `banco_produtos`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` double(9,2) NOT NULL,
  `quantidade` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `preco`, `quantidade`) VALUES
(14, 'Arroz Integral', 12.99, 300),
(15, 'Feijão Preto', 7.49, 150),
(16, 'Açúcar Refinado', 4.89, 200),
(17, 'Farinha de Trigo', 5.59, 180),
(18, 'Óleo de Soja', 6.99, 120),
(19, 'Macarrão Espaguete', 3.89, 220),
(20, 'Leite Integral', 4.50, 100),
(21, 'Café Torrado', 9.99, 80),
(22, 'Sal Refinado', 2.59, 250),
(23, 'Biscoito Recheado', 6.00, 500),
(24, 'Margarina', 3.75, 190),
(25, 'Suco de Laranja', 5.25, 160),
(26, 'Maionese', 7.00, 110),
(27, 'Chocolate ao Leite', 4.50, 140),
(28, 'Papel Higiênico', 3.00, 300),
(29, 'Batata Doce', 3.80, 230),
(30, 'Macarrão para Lasanha', 4.50, 180),
(31, 'Alho Desidratado', 2.99, 170),
(32, 'Tomate em Lata', 3.75, 120),
(33, 'Molho de Tomate', 4.00, 200),
(34, 'Cebola', 2.20, 250),
(35, 'Carne Moída', 19.99, 60),
(36, 'Frango Congelado', 12.00, 150),
(37, 'Linguiça Calabresa', 9.99, 130),
(38, 'Picanha', 35.00, 80),
(39, 'Peito de Frango', 15.00, 200),
(40, 'Queijo Mussarela', 10.99, 180),
(41, 'Presunto', 8.00, 160),
(42, 'Salsicha', 4.90, 220),
(43, 'Requeijão Cremoso', 6.50, 110),
(44, 'Maçã', 5.50, 300),
(45, 'Banana Prata', 4.00, 250),
(46, 'Laranja', 3.00, 180),
(47, 'Melancia', 7.00, 100),
(48, 'Pera', 6.50, 150),
(49, 'Uva', 8.00, 120),
(50, 'Morango', 10.00, 80),
(51, 'Alface', 2.00, 200),
(52, 'Cenoura', 3.00, 250),
(53, 'Beterraba', 2.80, 230),
(54, 'Abóbora', 3.90, 210),
(55, 'Pepino', 2.50, 300);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
