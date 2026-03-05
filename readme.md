# 🎬 WatchTV

> Sua plataforma pessoal para buscar e salvar filmes e séries favoritos — direto no terminal.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=flat-square&logo=mysql)
![Lombok](https://img.shields.io/badge/Lombok-✓-red?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-purple?style=flat-square)

---

## 📖 Sobre o Projeto

**Watch** é uma aplicação de linha de comando (CLI) construída com Spring Boot que permite buscar informações sobre filmes e séries a partir da API do **OMDb**, visualizar detalhes como gênero, avaliação e data de lançamento, e gerenciar uma lista de favoritos persistida em banco de dados MySQL.

Tudo isso com suporte a tradução de sinopses via **Google Gemini AI** (opcional).

---

## ✨ Funcionalidades

- 🎥 **Buscar Filmes** — pesquise qualquer filme pelo nome e veja detalhes completos
- 📺 **Buscar Séries** — pesquise séries e descubra número de temporadas, avaliação e mais
- 🧾 **Lista de Favoritos** — adicione e remova filmes e séries de uma lista pessoal persistida no banco
- 🌐 **Gêneros em Português** — os gêneros são automaticamente traduzidos para PT-BR
- 🤖 **Tradução de Sinopse com IA** — integração com o Google Gemini para traduzir a sinopse

---

## 🛠️ Tecnologias e Dependências

### Core
| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 4.0.2 | Framework base da aplicação |
| Spring Data JPA | — | Persistência de dados com ORM |
| Spring Validation | — | Validação de dados |
| Spring Web MVC | — | Suporte web (estrutura MVC) |

### Banco de Dados
| Tecnologia | Versão | Descrição |
|---|---|---|
| MySQL | — | Banco de dados relacional |
| mysql-connector-j | — | Driver JDBC para MySQL |
| Flyway *(via migration)* | — | Migração e versionamento do schema |

### Utilitários
| Tecnologia | Versão | Descrição |
|---|---|---|
| Lombok | — | Reduz boilerplate com anotações (`@Getter`, `@Setter`, etc.) |
| Jackson Databind | 2.20.1 | Serialização/desserialização de JSON |

### Inteligência Artificial
| Tecnologia | Versão | Descrição |
|---|---|---|
| Google GenAI SDK | 1.0.0 | Integração com o modelo Gemini para tradução de sinopses |

---

## 🌐 APIs Utilizadas

### 🎬 OMDb API
A [Open Movie Database (OMDb)](https://www.omdbapi.com/) é uma API RESTful que fornece informações detalhadas sobre filmes e séries, como título, gênero, avaliação no IMDb, data de lançamento, sinopse e muito mais.

- **Base URL:** `https://www.omdbapi.com/`
- **Autenticação:** API Key via query param `&apikey=SUA_KEY`
- **Como obter sua chave:** Acesse [omdbapi.com/apikey.aspx](https://www.omdbapi.com/apikey.aspx) e crie uma conta gratuita

### 🤖 Google Gemini AI
O [Google Gemini](https://ai.google.dev/) é utilizado para traduzir as sinopses dos filmes e séries do inglês para o português brasileiro, usando o modelo `gemini-2.5-flash-lite`.

- **SDK:** `com.google.genai:google-genai:1.0.0`
- **Como obter sua chave:** Acesse o [Google AI Studio](https://aistudio.google.com/) e gere uma API Key
- A variável de ambiente necessária é: `GOOGLE_API_KEY` (lida automaticamente pelo SDK)

---

## ⚙️ Configuração do Ambiente

Antes de rodar o projeto, configure as seguintes **variáveis de ambiente** no seu sistema:

| Variável | Descrição |
|---|---|
| `APIKEY_OMDB` | Sua chave da API OMDb |
| `DB_HOST` | Host do banco MySQL (ex: `localhost:3306`) |
| `DB_NAME` | Nome do banco de dados (ex: `watch_db`) |
| `DB_USER` | Usuário do MySQL |
| `DB_PASSWORD` | Senha do MySQL |
| `GOOGLE_API_KEY` | Chave da API Gemini *(apenas se usar a tradução de IA)* |

### No Linux/macOS:
```bash
export APIKEY_OMDB=sua_chave_aqui
export DB_HOST=localhost:3306
export DB_NAME=watch_db
export DB_USER=root
export DB_PASSWORD=sua_senha
```

### No Windows (PowerShell):
```powershell
$env:APIKEY_OMDB="sua_chave_aqui"
$env:DB_HOST="localhost:3306"
$env:DB_NAME="watch_db"
$env:DB_USER="root"
$env:DB_PASSWORD="sua_senha"
```

---

## 🗄️ Banco de Dados

Crie o banco de dados no MySQL antes de rodar a aplicação:

```sql
CREATE DATABASE watch_db;
```

A tabela `lista_favoritos` é criada automaticamente pelo script de migração em `src/main/resources/db/migration/V1__create_table.sql`:

```sql
CREATE TABLE lista_favoritos (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(180),
    genero VARCHAR(120),
    tipo   VARCHAR(10)
);
```

> O `spring.jpa.hibernate.ddl-auto=update` também está configurado para manter o schema atualizado automaticamente.

---

## 🚀 Como Rodar

### Pré-requisitos

- ✅ Java 17+
- ✅ Maven 3.9+ (ou use o wrapper `./mvnw` incluso)
- ✅ MySQL rodando localmente ou em um container

### Passo a passo

**1. Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/watch.git
cd watch
```

**2. Configure as variáveis de ambiente** *(conforme a seção acima)*

**3. Compile o projeto:**
```bash
./mvnw clean install
```

**4. Execute a aplicação:**
```bash
./mvnw spring-boot:run
```

Ou, alternativamente, rode o JAR gerado:
```bash
java -jar target/Watch-0.0.1-SNAPSHOT.jar
```

**5. Interaja com o menu no terminal:**
```
----------------------------
1🎥 - Buscar filmes
2📺 - Buscar séries
3🧾 - Mostrar lista de favoritos
4❌ - Sair
----------------------------
>> Digite um número para busca:
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/example/watch/Watch/
├── main/
│   ├── PrincipalConsole.java     # Menu principal da aplicação
│   ├── PrincipalFilme.java       # Lógica de busca de filmes
│   └── PrincipalSerie.java       # Lógica de busca de séries
├── model/
│   ├── DadosFilmes.java          # Record com dados da API (filmes)
│   ├── DadosSeries.java          # Record com dados da API (séries)
│   ├── Filme.java                # Entidade Filme
│   ├── Serie.java                # Entidade Série
│   ├── Lista.java                # Entidade JPA para persistência
│   ├── Genero.java               # Enum com gêneros (EN → PT-BR)
│   └── Tipo.java                 # Enum Filme/Série
├── repository/
│   └── ListaRepository.java      # Repositório JPA
├── services/
│   ├── ApiService.java           # Requisições HTTP para a OMDb
│   ├── ConverteDados.java        # Desserialização JSON com Jackson
│   ├── IConverteDados.java       # Interface do conversor
│   ├── ListaService.java         # Serviço da lista de favoritos
│   └── ConsultaGemini.java       # Integração com Google Gemini AI
└── WatchApplication.java         # Entry point da aplicação
```

---

*Feito com Java☕ e Spring Boot*