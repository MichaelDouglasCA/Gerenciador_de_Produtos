-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16/12/2024 às 20:22
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
  `preco` decimal(10,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descricao` text DEFAULT NULL,
  `imagem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `preco`, `quantidade`, `categoria`, `descricao`, `imagem`) VALUES
(2, 'Arroz Integral', 10.99, 200, 'Alimentos', 'Arroz integral de alta qualidade, ideal para dietas saudáveis.', 'https://example.com/arroz_integral.jpg'),
(3, 'Feijão Preto', 8.50, 150, 'Alimentos', 'Feijão preto, ótimo para feijão tropeiro ou caldo de feijão.', 'https://example.com/feijao_preto.jpg'),
(4, 'Açúcar Cristal', 3.75, 300, 'Alimentos', 'Açúcar cristal para adoçar bebidas e preparos culinários.', 'https://example.com/acucar_cristal.jpg'),
(5, 'Óleo de Soja', 5.40, 120, 'Alimentos', 'Óleo de soja para uso culinário, excelente para frituras e refogados.', 'https://example.com/oleo_soja.jpg'),
(6, 'Macarrão Instantâneo', 2.20, 500, 'Alimentos', 'Macarrão instantâneo, rápido e fácil de preparar em qualquer momento.', 'https://example.com/macarrao_instantaneo.jpg'),
(7, 'Suco de Laranja', 4.99, 250, 'Bebidas', 'Suco de laranja natural, sem conservantes.', 'https://example.com/suco_laranja.jpg'),
(8, 'Maçã', 3.50, 100, 'Alimentos', 'Maçãs frescas e doces, ideais para lanches.', 'https://example.com/maca.jpg'),
(9, 'Banana', 2.30, 180, 'Alimentos', 'Bananas maduras e saborosas, ricas em potássio.', 'https://example.com/banana.jpg'),
(10, 'Pão de Forma', 4.00, 350, 'Alimentos', 'Pão de forma macio e fofinho, perfeito para lanches.', 'https://example.com/pao_de_forma.jpg'),
(11, 'Leite Integral', 5.90, 200, 'Bebidas', 'Leite integral, rico em nutrientes para toda a família.', 'https://example.com/leite_integral.jpg'),
(12, 'Café em Pó', 6.80, 150, 'Bebidas', 'Café moído, ideal para preparar uma bebida forte e saborosa.', 'https://example.com/cafe_em_po.jpg'),
(13, 'Batata Doce', 4.50, 180, 'Alimentos', 'Batata doce rica em fibras, ideal para receitas saudáveis.', 'https://example.com/batata_doce.jpg'),
(14, 'Alface', 2.20, 100, 'Alimentos', 'Alface fresca, perfeita para saladas leves.', 'https://example.com/alface.jpg'),
(15, 'Tomate', 4.10, 120, 'Alimentos', 'Tomates maduros e saborosos para suas receitas.', 'https://example.com/tomate.jpg'),
(16, 'Peito de Frango', 13.99, 80, 'Alimentos', 'Peito de frango fresco, ideal para grelhar ou assar.', 'https://example.com/peito_de_frango.jpg'),
(17, 'Picanha', 49.90, 60, 'Alimentos', 'Picanha macia, perfeita para um churrasco.', 'https://example.com/picanha.jpg'),
(18, 'Queijo Mussarela', 10.50, 150, 'Alimentos', 'Queijo mussarela fresco e delicioso, ótimo para pizzas.', 'https://example.com/queijo_mussarela.jpg'),
(19, 'Presunto', 8.40, 180, 'Alimentos', 'Presunto fatiado, ideal para sanduíches e aperitivos.', 'https://example.com/presunto.jpg'),
(20, 'Chocolates Sortidos', 12.50, 90, 'Doces', 'Chocolate sortido com diversos sabores e recheios.', 'https://example.com/chocolates_sortidos.jpg'),
(21, 'Biscoito de Polvilho', 3.00, 250, 'Doces', 'Biscoito de polvilho crocante e saboroso, ideal para o lanche.', 'https://example.com/biscoito_polvilho.jpg'),
(22, 'Bolacha Maria', 2.90, 300, 'Doces', 'Bolacha Maria clássica, deliciosa com café ou chá.', 'https://example.com/bolacha_maria.jpg'),
(23, 'Cereal Matinal', 7.50, 130, 'Alimentos', 'Cereal matinal nutritivo, ideal para o café da manhã.', 'https://example.com/cereal_matinal.jpg'),
(24, 'Macarrão Parafuso', 3.60, 400, 'Alimentos', 'Macarrão paracuso, excelente para saladas frias ou molhos.', 'https://example.com/macarrao_parafuso.jpg'),
(25, 'Ketchup', 2.50, 200, 'Alimentos', 'Ketchup de tomate, para acompanhar hambúrgueres e fritas.', 'https://example.com/ketchup.jpg'),
(26, 'Mostarda', 3.00, 180, 'Alimentos', 'Mostarda, excelente para temperar e dar sabor a carnes.', 'https://example.com/mostarda.jpg'),
(27, 'Molho de Alho', 4.20, 150, 'Alimentos', 'Molho de alho, ideal para temperar carnes e frangos.', 'https://example.com/molho_alho.jpg'),
(28, 'Margarina', 4.80, 250, 'Alimentos', 'Margarina cremosa, perfeita para pães e receitas.', 'https://example.com/margarina.jpg'),
(29, 'Leite Condensado', 5.60, 300, 'Doces', 'Leite condensado para suas receitas de sobremesa.', 'https://example.com/leite_condensado.jpg'),
(30, 'Achocolatado', 7.80, 220, 'Bebidas', 'Achocolatado em pó para fazer um delicioso leite com chocolate.', 'https://example.com/achocolatado.jpg'),
(31, 'Creme de Leite', 4.40, 250, 'Alimentos', 'Creme de leite fresco, ótimo para molhos e sobremesas.', 'https://example.com/creme_de_leite.jpg'),
(32, 'Coco Ralado', 2.90, 180, 'Alimentos', 'Coco ralado desidratado para suas receitas de bolo e doces.', 'https://example.com/coco_ralado.jpg'),
(33, 'Sal', 1.80, 500, 'Alimentos', 'Sal refinado para temperar suas receitas.', 'https://example.com/sal.jpg'),
(34, 'Pipoca de Micro-ondas', 2.20, 300, 'Alimentos', 'Pipoca de micro-ondas para lanches rápidos.', 'https://example.com/pipoca_microondas.jpg'),
(35, 'Molho de Tomate', 3.30, 220, 'Alimentos', 'Molho de tomate pronto para fazer suas receitas de massas.', 'https://example.com/molho_tomate.jpg'),
(36, 'Alho', 2.00, 180, 'Alimentos', 'Alho fresco para dar sabor a suas receitas.', 'https://example.com/alho.jpg'),
(37, 'Cebola', 2.50, 150, 'Alimentos', 'Cebola fresca para suas receitas de pratos salgados.', 'https://example.com/cebola.jpg'),
(38, 'Beterraba', 3.30, 160, 'Alimentos', 'Beterraba fresca e rica em nutrientes para saladas.', 'https://example.com/beterraba.jpg'),
(39, 'Cenoura', 2.80, 200, 'Alimentos', 'Cenoura fresca e crocante, rica em vitamina A.', 'https://example.com/cenoura.jpg'),
(40, 'Abobrinha', 3.00, 220, 'Alimentos', 'Abobrinha fresca, ideal para refogados e assados.', 'https://example.com/abobrinha.jpg'),
(41, 'Brócolis', 4.50, 180, 'Alimentos', 'Brócolis frescos e nutritivos, ricos em antioxidantes.', 'https://example.com/brocolis.jpg'),
(42, 'Couve-Flor', 4.00, 190, 'Alimentos', 'Couve-flor fresca, excelente para saladas e refogados.', 'https://example.com/couve_flor.jpg'),
(43, 'Espaguete', 3.20, 400, 'Alimentos', 'Espaguete de trigo, perfeito para diversas receitas italianas.', 'https://example.com/espaguete.jpg'),
(44, 'Macarrão de Arroz', 5.10, 180, 'Alimentos', 'Macarrão de arroz, ideal para pratos sem glúten.', 'https://example.com/macarrao_arroz.jpg'),
(45, 'Pipoca de Sal', 2.00, 250, 'Alimentos', 'Pipoca salgada para um lanche rápido e saboroso.', 'https://example.com/pipoca_salgada.jpg'),
(46, 'Batata Palha', 6.00, 200, 'Alimentos', 'Batata palha crocante, ideal para acompanhamentos.', 'https://example.com/batata_palha.jpg'),
(47, 'Molho Shoyu', 4.50, 220, 'Alimentos', 'Molho shoyu para dar sabor a pratos orientais.', 'https://example.com/molho_shoyu.jpg'),
(48, 'Molho Barbecue', 5.00, 200, 'Alimentos', 'Molho barbecue para temperar carnes e frangos.', 'https://example.com/molho_barbecue.jpg'),
(49, 'Gelatina', 3.00, 250, 'Doces', 'Gelatina sabor morango, rápida e fácil de preparar.', 'https://example.com/gelatina.jpg'),
(50, 'Sorvete', 9.99, 150, 'Doces', 'Sorvete cremoso de chocolate, ideal para o verão.', 'https://example.com/sorvete.jpg'),
(51, 'Iogurte Grego', 4.40, 200, 'Bebidas', 'Iogurte grego natural, ótimo para dietas e sobremesas.', 'https://example.com/iogurte_grego.jpg');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `username`, `password`, `email`) VALUES
(1, '341234', '124312', '1241243');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
