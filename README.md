# APP-BEST-DEVS

Requisitos para rodar local:

Java jdk 8, Tom Cat 8.5 server(installer), Postgres 12

Gerar Warfile e colocar na pasta WebApps do TomCat.

# Database
Postgres 12 - Em ambiente local deve fazer insert na tabela Usuario para logar, pois não tem script que popula o banco no start. No ambiente AMAZON o BD postgres já tem dados de usário congifurados.

CREATE DATABASE teste
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    
 CREATE TABLE public.perfil(
    codperfil integer NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default",
    descricao_final character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT perfil_pkey PRIMARY KEY (codperfil)
)

CREATE TABLE public.usuario(
    codusuario integer NOT NULL,
    adminsistema character varying(255) COLLATE pg_catalog."default",
    ativo boolean,
    login character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    senha character varying(255) COLLATE pg_catalog."default",
    codperfil integer,
    CONSTRAINT usuario_pkey PRIMARY KEY (codusuario),
    CONSTRAINT fk_7u826ttt3eayjlearjh5sulgc FOREIGN KEY (codperfil)
        REFERENCES public.perfil (codperfil) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    
Inserts:
INSERT INTO perfil(
	codperfil, descricao, descricao_final)
	VALUES (1, 'ROLE_ADMIN', 'ADMINISTRADOR');
	
INSERT INTO usuario(
	codusuario, adminsistema, ativo, login, nome, senha, codperfil)
	VALUES (1, 'S', TRUE, 'MICHEL', 'MICHEL', '123456', 1);
  


# Frontend
JSF - PRIMEFACES + CSS + XHTML 

# Ambiente Amazon Rodando 
URL: http://54.236.97.207:8090/appbestdevs/
Usuário: MICHEL
SENHA: 123456

# Funcionalidade
Um simples app com CRUD de usuários, usa REST TEMPLATE para buscar USERS da API DO GIT e persiste esses dados no banco de dados na tabela DEVELOPER, mostrando na tela de DEVS os devs em uma DATATABLE.
