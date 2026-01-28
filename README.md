# 🎓 Sistema de Controle de Alunos (Student Manager)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

Um sistema de gerenciamento de alunos via linha de comando (CLI), desenvolvido em **Java** puro utilizando o padrão **DAO (Data Access Object)** para comunicação com banco de dados **MySQL**.

Este projeto demonstra operações essenciais de **CRUD** (Create, Read, Update, Delete) de forma segura e estruturada.

## 📋 Funcionalidades

* ✅ **Cadastrar Aluno:** Insere nome e e-mail no banco, retornando o ID gerado automaticamente.
* ✅ **Buscar Aluno:** Localiza registros filtrando por nome e e-mail simultaneamente.
* ✅ **Atualizar E-mail:** Altera o e-mail de um aluno com verificação de segurança (confere dados antigos antes de mudar).
* ✅ **Deletar Aluno:** Remove registros do banco de dados com dupla verificação.
* ✅ **Listagem de Dados:** Exibe matrícula, nome, e-mail e data de cadastro.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 8+)
* **Banco de Dados:** MySQL
* **Conexão:** JDBC (MySQL Connector)
* **Padrão de Projeto:** DAO (Data Access Object)
* **IDE Recomendada:** VS Code, Eclipse ou IntelliJ

## 🗄️ Configuração do Banco de Dados

Para rodar este projeto, você precisa criar o banco de dados e a tabela correta. Execute o script SQL abaixo no seu MySQL Workbench ou terminal:

```sql
CREATE DATABASE sistema_escolar;

USE sistema_escolar;

CREATE TABLE alunos (
    matricula INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);