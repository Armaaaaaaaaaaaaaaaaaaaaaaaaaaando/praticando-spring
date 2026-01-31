# 🐾 Projeto Simples – API de Animais e Serviços

Este projeto é uma **API REST simples** desenvolvida com **Spring Boot**, com o objetivo de praticar conceitos fundamentais de **backend**, como:

- CRUD
- Spring Data JPA
- Relacionamentos entre entidades
- Tratamento de exceções
- Boas práticas de controller, service e repository

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Banco de Dados Relacional ( PostgreSQL)
- Maven

---

## 📚 Modelo de Domínio

### 🐶 Animal
Representa o pet cadastrado no sistema.

Principais campos:
- `id`
- `nome`
- `nomeDono`
- `raca`

---

### 🩺 Servico
Representa um serviço/consulta realizada para um animal.

Principais campos:
- `id`
- `data`
- `tipoConsulta`
- `descricao`
- `animal` (relacionamento `ManyToOne`)

Um **animal pode ter vários serviços**, mas um serviço pertence a **apenas um animal**.

---

## 🔗 Relacionamento entre Entidades

```java
@ManyToOne
@JoinColumn(name = "animal_id")
private Animal animal;
