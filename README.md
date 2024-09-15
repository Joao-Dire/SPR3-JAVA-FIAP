Projeto de Gerenciamento de Veículos e Oficinas

Este projeto é uma aplicação Java para gerenciamento de veículos, oficinas e mecânicos. Ele utiliza um banco de dados Oracle para armazenar informações sobre veículos, diagnósticos, orçamentos, peças, oficinas e mecânicos.
Estrutura do Projeto

O projeto é dividido em várias classes e pacotes que lidam com diferentes aspectos do gerenciamento de dados. Abaixo está uma visão geral das principais classes e suas responsabilidades.
Pacote br.com.fiap.model.dto

Contém as classes de Data Transfer Object (DTO), que representam as entidades do banco de dados.

    Cliente: Representa um cliente com informações como ID, nome, email, telefone e senha.
    Carro: Representa um veículo com informações como ID, placa, modelo, ano, cor e ID do cliente.
    Mecanico: Representa um mecânico com informações como ID, nome, telefone, email e ID da oficina.
    Oficina: Representa uma oficina com informações como ID, nome, telefone, endereço e demais detalhes.
    Diagnostico: Representa um diagnóstico com informações como ID, data, resultado, detalhes e ID do veículo.
    Orcamento: Representa um orçamento com informações como ID, valor estimado, descrição dos serviços, data de emissão e ID do diagnóstico.
    Peca: Representa uma peça com informações como ID, nome, descrição e ID da oficina.

Pacote br.com.fiap.model.dao

Contém as classes de Data Access Object (DAO), que lidam com a comunicação com o banco de dados.

    ConnectionFactory: Gerencia a conexão com o banco de dados Oracle.
    ClienteDAO: Realiza operações CRUD (Create, Read, Update, Delete) para a tabela spr3_cliente.
    CarroDAO: Realiza operações CRUD para a tabela spr3_veiculo.
    MecanicoDAO: Realiza operações CRUD para a tabela spr3_mecanico.
    OficinaDAO: Realiza operações CRUD para a tabela spr3_oficina.
    DiagnosticoDAO: Realiza operações CRUD para a tabela spr3_diagnostico.
    OrcamentoDAO: Realiza operações CRUD para a tabela spr3_orcamento.
    PecaDAO: Realiza operações CRUD para a tabela spr3_peca.

Pacote br.com.fiap.main

Contém classes para testes de CRUD e execução principal da aplicação.

    TesteVeiculoCRUD: Testa as operações CRUD para a tabela spr3_veiculo.

Configuração do Banco de Dados

O projeto usa o banco de dados Oracle com o esquema spr3. Aqui estão as tabelas criadas e suas estruturas:

sql

CREATE TABLE spr3_cliente (
    id_cliente NUMBER(10) NOT NULL,
    nome       VARCHAR2(50 CHAR) NOT NULL,
    email      VARCHAR2(100 CHAR) NOT NULL,
    telefone   VARCHAR2(20 CHAR) NOT NULL,
    senha      VARCHAR2(50) NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (id_cliente),
    CONSTRAINT cliente_email_un UNIQUE (email),
    CONSTRAINT telefone CHECK ( LENGTH(telefone) BETWEEN 10 AND 11 )
);

CREATE TABLE spr3_veiculo (
    id_veiculo NUMBER(10) NOT NULL,
    placa      VARCHAR2(10 CHAR) NOT NULL,
    modelo     VARCHAR2(50 CHAR),
    ano        NUMBER(4),
    cor        VARCHAR2(20 CHAR),
    cliente_id_cliente NUMBER(10) NOT NULL,
    CONSTRAINT veiculo_pk PRIMARY KEY (id_veiculo),
    CONSTRAINT veiculo_placa_un UNIQUE (placa),
    CONSTRAINT veiculo_cliente_fk FOREIGN KEY (cliente_id_cliente) REFERENCES spr3_cliente(id_cliente),
    CONSTRAINT ano CHECK (ano BETWEEN 1950 AND 2024)
);

-- Outras tabelas são criadas de forma semelhante.

Como Executar

    Configurar o Banco de Dados: Certifique-se de que o banco de dados Oracle está configurado corretamente e que as tabelas estão criadas conforme o script fornecido.

    Configurar a Conexão: Ajuste a classe ConnectionFactory para refletir as credenciais e a URL de conexão do seu banco de dados Oracle.

    Executar Testes: Utilize as classes no pacote br.com.fiap.main para testar as operações CRUD. Por exemplo, a classe TesteVeiculoCRUD realiza operações de inserção, alteração, exclusão e listagem de veículos.

    Compilar e Executar: Compile o projeto usando o seu IDE ou uma ferramenta de build como Maven ou Gradle, e execute a classe principal para verificar o funcionamento da aplicação.
