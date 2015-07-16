-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 16-Jul-2015 às 22:31
-- Versão do servidor: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `laveroupasapp`
--
CREATE DATABASE IF NOT EXISTS `laveroupasapp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `laveroupasapp`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_endereco`
--

CREATE TABLE IF NOT EXISTS `t_endereco` (
  `COD_PESSOA` int(11) DEFAULT NULL,
  `ENDERECO` varchar(45) DEFAULT NULL,
  `BAIRRO` varchar(45) DEFAULT NULL,
  `CIDADE` varchar(45) DEFAULT NULL,
  `CEP` varchar(45) DEFAULT NULL,
  `ESTADO` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_pagamento`
--

CREATE TABLE IF NOT EXISTS `t_pagamento` (
  `COD_PAGAMENTO` int(11) NOT NULL,
  `NUMERO_CARTAO` varchar(20) DEFAULT NULL,
  `CODIGO_SEGURANCA` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_pedido`
--

CREATE TABLE IF NOT EXISTS `t_pedido` (
  `COD_PEDIDO` int(11) NOT NULL,
  `COD_CLIENTE` int(11) NOT NULL,
  `COD_SERVICO` int(11) DEFAULT NULL,
  `COD_FUNCIONARIO` int(11) NOT NULL,
  `QUANTIDADE` int(11) NOT NULL DEFAULT '1',
  `COD_PAGAMENTO` int(11) DEFAULT NULL,
  `DATA_ENTRADA` datetime DEFAULT NULL,
  `DATA_SAIDA` datetime DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `t_pedido`
--

INSERT INTO `t_pedido` (`COD_PEDIDO`, `COD_CLIENTE`, `COD_SERVICO`, `COD_FUNCIONARIO`, `QUANTIDADE`, `COD_PAGAMENTO`, `DATA_ENTRADA`, `DATA_SAIDA`, `STATUS`) VALUES
(1, 2, 2, 1, 2, NULL, '2015-07-16 01:20:32', '2015-07-16 01:26:51', 'PAGO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_pessoa`
--

CREATE TABLE IF NOT EXISTS `t_pessoa` (
  `CODIGO` int(11) NOT NULL,
  `NOME` varchar(80) DEFAULT NULL,
  `CPF` varchar(15) DEFAULT NULL,
  `TIPO` tinyint(1) NOT NULL DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `t_pessoa`
--

INSERT INTO `t_pessoa` (`CODIGO`, `NOME`, `CPF`, `TIPO`) VALUES
(1, 'testado testando testador', '000.000.000-00', 1),
(2, 'Testante Testado Testei', '111.111.111-44', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_servico`
--

CREATE TABLE IF NOT EXISTS `t_servico` (
  `COD_SERVICO` int(11) NOT NULL,
  `DESCRICAO` varchar(45) DEFAULT NULL,
  `VALOR` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `t_servico`
--

INSERT INTO `t_servico` (`COD_SERVICO`, `DESCRICAO`, `VALOR`) VALUES
(1, 'LAVAR/PASSAR', 15),
(2, 'LAVAR/SECAR', 13),
(3, 'SÓ PASSAR', 8),
(4, 'SÓ SECAR', 5),
(5, 'SÓ LAVAR', 7);

-- --------------------------------------------------------

--
-- Estrutura da tabela `t_usuarios`
--

CREATE TABLE IF NOT EXISTS `t_usuarios` (
  `MATRICULA` int(11) NOT NULL,
  `SENHA` varchar(45) DEFAULT NULL,
  `DATA_ULTIMO_ACESSO` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TIPO_ACESSO` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `t_usuarios`
--

INSERT INTO `t_usuarios` (`MATRICULA`, `SENHA`, `DATA_ULTIMO_ACESSO`, `TIPO_ACESSO`) VALUES
(1, '827ccb0eea8a706c4c34a16891f84e7b', '2015-07-16 13:04:42', 1),
(2, '827ccb0eea8a706c4c34a16891f84e7b', '2015-07-14 15:29:59', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_endereco`
--
ALTER TABLE `t_endereco`
  ADD KEY `FK_PESSOA_MATRICULA_idx` (`COD_PESSOA`);

--
-- Indexes for table `t_pagamento`
--
ALTER TABLE `t_pagamento`
  ADD PRIMARY KEY (`COD_PAGAMENTO`);

--
-- Indexes for table `t_pedido`
--
ALTER TABLE `t_pedido`
  ADD PRIMARY KEY (`COD_PEDIDO`), ADD KEY `FK_SERVICO_COD_SERVICO_idx` (`COD_SERVICO`), ADD KEY `FK_PESSOA_MATRICULA_CLIENTE_idx` (`COD_CLIENTE`), ADD KEY `FK_PAGAMENTO_COD_PAGAMENTO_idx` (`COD_PAGAMENTO`);

--
-- Indexes for table `t_pessoa`
--
ALTER TABLE `t_pessoa`
  ADD PRIMARY KEY (`CODIGO`);

--
-- Indexes for table `t_servico`
--
ALTER TABLE `t_servico`
  ADD PRIMARY KEY (`COD_SERVICO`);

--
-- Indexes for table `t_usuarios`
--
ALTER TABLE `t_usuarios`
  ADD PRIMARY KEY (`MATRICULA`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_pagamento`
--
ALTER TABLE `t_pagamento`
  MODIFY `COD_PAGAMENTO` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `t_pedido`
--
ALTER TABLE `t_pedido`
ADD CONSTRAINT `t_pedido_ibfk_1` FOREIGN KEY (`COD_SERVICO`) REFERENCES `t_servico` (`COD_SERVICO`),
ADD CONSTRAINT `t_pedido_ibfk_2` FOREIGN KEY (`COD_PAGAMENTO`) REFERENCES `t_pagamento` (`COD_PAGAMENTO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
