# APP-BEST-DEVS

Requisitos para rodar local:

Java jdk 8, Tom Cat 8.5 server(installer), Postgres 12

Gerar Warfile e colocar na pasta WebApps do TomCat.

# Database
Postgres 12 - Em ambiente local deve fazer insert na tabela Usuario para logar, pois não tem script que popula o banco no start. No ambiente AMAZON o BD postgres já tem dados

# Frontend
JSF - PRIMEFACES

# Ambiente Amazon Rodando 
URL: http://54.236.97.207:8090/appbestdevs/
Usuário: MICHEL
SENHA: 123456

# Funcionalidade
Um simples app com CRUD de usuários, usa REST TEMPLATE para buscar USERS da API DO GIT e persiste esses dados no banco de dados na tabela DEVELOPER, mostrando na tela de DEVS os devs.
