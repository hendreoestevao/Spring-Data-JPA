-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 02/04/2026 às 02:08
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `demo_spring_jpa`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `atualizar_numero_endereco` (IN `p_id` INT, IN `p_novo_numero` INT, OUT `p_resultado` VARCHAR(50))   BEGIN
    IF EXISTS (
        SELECT 1 FROM enderecos WHERE id_endereco = p_id
    ) THEN
        UPDATE enderecos
        SET numero = p_novo_numero
        WHERE id_endereco = p_id;

        SET p_resultado = 'Endereço atualizado com sucesso';
    ELSE
        SET p_resultado = 'Endereço não encontrado';
    END IF;
END$$

--
-- Funções
--
CREATE DEFINER=`root`@`localhost` FUNCTION `obter_endereco_completo` (`p_id` BIGINT) RETURNS VARCHAR(255) CHARSET utf8mb4 COLLATE utf8mb4_general_ci READS SQL DATA BEGIN
    DECLARE endereco_completo VARCHAR(255);

    SELECT CONCAT(logradouro, ', ', numero, ' - ', bairro, ' - ', cidade, '/', uf) 
    INTO endereco_completo
    FROM enderecos
    WHERE id_endereco = p_id;

    RETURN endereco_completo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `autores`
--

CREATE TABLE `autores` (
  `id_autor` bigint(20) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `sobrenome` varchar(45) NOT NULL,
  `id_info` bigint(20) DEFAULT NULL,
  `criado_por` varchar(255) DEFAULT NULL,
  `data_criacao` datetime(6) DEFAULT NULL,
  `data_modificacao` datetime(6) DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `autores`
--

INSERT INTO `autores` (`id_autor`, `nome`, `sobrenome`, `id_info`, `criado_por`, `data_criacao`, `data_modificacao`, `modificado_por`) VALUES
(1, 'Carlos', 'Silva', 1, NULL, NULL, NULL, NULL),
(2, 'Ana', 'Souza', 2, NULL, NULL, NULL, NULL),
(3, 'Marcos', 'Oliveira', 3, NULL, NULL, NULL, NULL),
(4, 'João', 'Pereira', 4, NULL, NULL, NULL, NULL),
(5, 'Fernanda', 'Costa', 5, NULL, NULL, NULL, NULL),
(6, 'Lucas', 'Mendes', 6, NULL, NULL, NULL, NULL),
(7, 'Patrícia', 'Lima', 7, NULL, NULL, NULL, NULL),
(8, 'Criacao auditoria', 'editado', NULL, NULL, NULL, '2026-03-31 20:42:45.000000', 'editor@email.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` bigint(20) NOT NULL,
  `titulo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `titulo`) VALUES
(2, 'Angular'),
(9, 'Docker'),
(3, 'Java'),
(10, 'Kubernetes'),
(6, 'MongoDB'),
(5, 'MySQL'),
(8, 'Node JS'),
(7, 'Python'),
(1, 'Spring Boot'),
(4, 'Spring Framework');

-- --------------------------------------------------------

--
-- Estrutura para tabela `enderecos`
--

CREATE TABLE `enderecos` (
  `id_endereco` bigint(20) NOT NULL,
  `bairro` varchar(55) DEFAULT NULL,
  `cidade` varchar(55) DEFAULT NULL,
  `logradouro` varchar(120) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `id_autor` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `enderecos`
--

INSERT INTO `enderecos` (`id_endereco`, `bairro`, `cidade`, `logradouro`, `numero`, `uf`, `id_autor`) VALUES
(1, 'Centro', 'Caxias do Sul', 'Av. dos Vinhedos', 785, 'RS', 1),
(2, 'Centro', 'São Paulo', 'Rua das Ortencias', 265, 'SP', 2),
(3, 'Centro', 'Campinas', 'Rua da Esperança', 988, 'SP', 3),
(4, 'Copacabana', 'Rio de Janeiro', 'Rua da Praia', 784, 'RJ', 4),
(5, 'Barra da Tijuca', 'Rio de Janeiro', 'Av. das Ondas', 455, 'RJ', 5),
(6, 'Floresta', 'Porto Alegre', 'Rua das Rosas', 147, 'RS', 6),
(7, 'Zona Sul', 'Porto Alegre', 'Rua das Oliveiras', 88, 'RS', 7);

-- --------------------------------------------------------

--
-- Estrutura para tabela `info_autores`
--

CREATE TABLE `info_autores` (
  `id_info` bigint(20) NOT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `cargo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `info_autores`
--

INSERT INTO `info_autores` (`id_info`, `bio`, `cargo`) VALUES
(1, 'Responsável pelo desenvolvimento de funcionalidades básicas e suporte ao time.', 'Programador Jr'),
(2, 'Desenvolve e mantém sistemas de média complexidade, participa de revisões de código.', 'Programador Pleno'),
(3, 'Lidera projetos complexos, mentorando desenvolvedores juniores e plenos.', 'Programador Senior'),
(4, 'Especialista em administração de bancos de dados, garantindo a performance e segurança.', 'DBA'),
(5, 'Coordena equipes de desenvolvimento, responsável pelo sucesso do projeto.', 'Gerente de Projetos'),
(6, 'Profissional com alto grau de conhecimento em uma tecnologia específica, consultor interno.', 'Especialista'),
(7, 'Inicia no desenvolvimento de APIs RESTful com Spring Boot e Java.', 'Programador Jr');

-- --------------------------------------------------------

--
-- Estrutura para tabela `post`
--

CREATE TABLE `post` (
  `id_post` bigint(20) NOT NULL,
  `titulo` varchar(65) NOT NULL,
  `id_autor` bigint(20) DEFAULT NULL,
  `data_publicacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `post`
--

INSERT INTO `post` (`id_post`, `titulo`, `id_autor`, `data_publicacao`) VALUES
(1, 'Introdução ao Spring Boot', 1, '2022-01-03'),
(2, 'Construindo APIs RESTful com Spring Boot', 1, '2022-02-22'),
(3, 'Aplicações Web com Angular', 2, '2022-03-03'),
(4, 'Avançando com Angular', 2, '2022-03-04'),
(5, 'Fundamentos de Java', 3, '2022-04-05'),
(6, 'Java para Desenvolvimento Web', 3, '2022-05-06'),
(7, 'Guia Completo de Spring Framework', 3, '2022-06-07'),
(8, 'Otimização de Consultas MySQL', 4, '2022-06-08'),
(9, 'Gerenciamento de Banco de Dados com MySQL', 4, '2022-10-09'),
(10, 'Introdução ao MongoDB', 5, '2022-11-10'),
(11, 'Estrutura de Documentos no MongoDB', 5, '2023-01-11'),
(12, 'Desenvolvimento com Python', 1, '2023-01-12'),
(13, 'Python para Data Science', 1, '2023-01-23'),
(14, 'Construindo APIs com Node JS', 2, '2023-02-14'),
(15, 'Node JS e Express: Guia Completo', 2, '2023-02-25'),
(16, 'Containerização com Docker', 3, '2023-03-16'),
(17, 'Orquestração de Containers com Kubernetes', 4, '2023-04-17'),
(18, 'Melhores Práticas em Spring Boot', 5, '2023-04-28'),
(19, 'Segurança em Aplicações Web com Spring', 5, '2023-05-19'),
(20, 'Gerenciamento de Estado no Angular', 2, '2023-05-30'),
(21, 'Manipulação de Banco de Dados com Node JS', 1, '2023-06-21'),
(22, 'Docker para Desenvolvedores', 3, '2023-07-22'),
(23, 'Estruturas de Dados em Python', 5, '2023-07-28'),
(24, 'Integração Contínua com Jenkins e Docker', 2, '2023-08-24'),
(25, 'Segurança de Banco de Dados MySQL', 4, '2023-09-25'),
(26, 'Design Patterns em Java', 3, '2023-10-26'),
(27, 'Avançado em MongoDB: Agregações', 5, '2023-11-27'),
(28, 'O que há de novo no Node JS 14', 4, '2023-12-28'),
(29, 'Spring Boot e Cloud: Integração', 1, '2024-01-29'),
(30, 'Angular e Testes Unitários', 2, '2024-02-24'),
(31, 'Python e Machine Learning', 3, '2024-03-09'),
(32, 'Orquestração com Kubernetes para Microservices', 1, '2024-04-11');

-- --------------------------------------------------------

--
-- Estrutura para tabela `posts_tem_categorias`
--

CREATE TABLE `posts_tem_categorias` (
  `id_post` bigint(20) NOT NULL,
  `id_categoria` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `posts_tem_categorias`
--

INSERT INTO `posts_tem_categorias` (`id_post`, `id_categoria`) VALUES
(1, 1),
(1, 3),
(1, 4),
(2, 1),
(2, 3),
(3, 2),
(3, 6),
(4, 2),
(5, 3),
(6, 3),
(6, 1),
(7, 4),
(8, 5),
(9, 5),
(9, 7),
(10, 6),
(11, 6),
(12, 7),
(12, 5),
(12, 9),
(13, 7),
(14, 8),
(15, 8),
(16, 9),
(17, 10),
(17, 2),
(18, 1),
(19, 1),
(20, 2),
(21, 8),
(22, 9),
(22, 5),
(22, 2),
(23, 7),
(24, 9),
(25, 5),
(25, 1),
(25, 3),
(26, 3),
(26, 1),
(27, 6),
(28, 8),
(29, 1),
(29, 5),
(30, 2),
(31, 7),
(32, 10);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id_autor`),
  ADD UNIQUE KEY `UK4s1xogv45w5caiqc7fsdwggws` (`id_info`);

--
-- Índices de tabela `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`),
  ADD UNIQUE KEY `UKovtw43omtmum2ljucdt7bgo34` (`titulo`);

--
-- Índices de tabela `enderecos`
--
ALTER TABLE `enderecos`
  ADD PRIMARY KEY (`id_endereco`),
  ADD UNIQUE KEY `UKhy3hd9p6w7wibks6sbm2a3qhy` (`id_autor`);

--
-- Índices de tabela `info_autores`
--
ALTER TABLE `info_autores`
  ADD PRIMARY KEY (`id_info`);

--
-- Índices de tabela `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id_post`),
  ADD UNIQUE KEY `UKsqxyxavh9s5yba7902bdeb29p` (`titulo`),
  ADD KEY `FK355bg40y60vdlj5lu9064kdj1` (`id_autor`);

--
-- Índices de tabela `posts_tem_categorias`
--
ALTER TABLE `posts_tem_categorias`
  ADD KEY `FK4obc22w0csjosmpww1r5lnpoq` (`id_categoria`),
  ADD KEY `FKhyp25eukvwgxjedu9bwoeypbg` (`id_post`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `autores`
--
ALTER TABLE `autores`
  MODIFY `id_autor` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `info_autores`
--
ALTER TABLE `info_autores`
  MODIFY `id_info` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `post`
--
ALTER TABLE `post`
  MODIFY `id_post` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `autores`
--
ALTER TABLE `autores`
  ADD CONSTRAINT `FKbxv7nqni1uov78t296dh0lx3k` FOREIGN KEY (`id_info`) REFERENCES `info_autores` (`id_info`);

--
-- Restrições para tabelas `enderecos`
--
ALTER TABLE `enderecos`
  ADD CONSTRAINT `FK6phb4o8sc3ixrwvn2fn4e9xp5` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`);

--
-- Restrições para tabelas `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK355bg40y60vdlj5lu9064kdj1` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`);

--
-- Restrições para tabelas `posts_tem_categorias`
--
ALTER TABLE `posts_tem_categorias`
  ADD CONSTRAINT `FK4obc22w0csjosmpww1r5lnpoq` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`),
  ADD CONSTRAINT `FKhyp25eukvwgxjedu9bwoeypbg` FOREIGN KEY (`id_post`) REFERENCES `post` (`id_post`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
