# Projeto Kieki Delivery - Microservices

Este repositório contém um conjunto de microservices desenvolvidos para gerenciar e rastrear entregas de forma eficiente, escalável e modular.

## Visão Geral do Projeto

O sistema é dividido em microservices independentes, cada um focado em uma responsabilidade específica, facilitando manutenção, escalabilidade e deploys independentes.

## Microservices

### Courier-Management

Microservice responsável pela gestão dos entregadores, incluindo cadastro, atualização e monitoramento. Desenvolvido com Spring Boot, utiliza Spring Data JPA para persistência e Spring Validation para garantir a integridade dos dados.

### Delivery-Tracking

Microservice responsável pelo rastreamento das entregas, fornecendo informações em tempo real sobre o status e localização das encomendas. Também desenvolvido com Spring Boot e utilizando tecnologias similares ao Courier-Management.

## Tecnologias e Frameworks

- Java 24
- Spring Boot 3.5.3
- Spring Data JPA
- Spring Validation
- PostgreSQL (driver runtime)
- Lombok (para redução de boilerplate)
- Maven para gerenciamento de dependências e build

## Estrutura do Projeto

- `Microservices/Courier-Management/`: Código fonte, configuração e scripts do microservice de gestão de entregadores.
- `Microservices/Delivery-Tracking/`: Código fonte, configuração e scripts do microservice de rastreamento de entregas.
- `docker-compose.yml`: Arquivo para orquestração dos microservices em containers Docker.
- `Docs/`: Pasta reservada para documentação adicional do projeto.

## Como Rodar o Projeto

### Pré-requisitos

- Docker
- Docker Compose
- Java 24 (para desenvolvimento local)

### Passos para execução

1. Clone este repositório.
2. Navegue até a raiz do projeto.
3. Execute o comando abaixo para iniciar todos os microservices via Docker Compose:

```bash
docker-compose up --build
```

 Os serviços estarão disponíveis conforme configurado no arquivo `docker-compose.yml`.

## Desenvolvimento

Cada microservice possui sua própria estrutura de projeto Maven, permitindo desenvolvimento e deploy independentes.

- Para o `Courier-Management`, utilize os comandos Maven disponíveis no diretório correspondente.
- Para o `Delivery-Tracking`, o mesmo se aplica.

## Referências e Documentação

Para mais informações sobre as tecnologias utilizadas, consulte:

- [Apache Maven Documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
- [Spring Boot Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Spring Validation](https://docs.spring.io/spring-boot/3.5.3/reference/io/validation.html)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Validation Guide](https://spring.io/guides/gs/validating-form-input/)

## Observações

- Lombok é utilizado para reduzir código boilerplate, com configuração adequada para o compilador Maven.

---