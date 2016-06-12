# Spotippos Viva Real
Solução para o [VivaReal Code Challenge](https://github.com/VivaReal/code-challenge) - [Desafio back-end](https://github.com/VivaReal/code-challenge/blob/master/backend.md)

## Requisitos

- Java 8
- Maven 3.2.x ou maior

## Execução

- Clone o projeto: `~$ git clone https://github.com/glauberdm/spotippos-vivareal.git`
- Acesse o diretório do projeto: `~$ cd spotippos-vivareal`

Existem duas formas de executar:

### Spring Boot Run

No diretório do projeto, execute: `~/spotippos-vivareal$ mvn clean package spring-boot:run`.

### Java Jar

Se preferir, crie o artefato java: `~/spotippos-vivareal$ mvn clean package`. E depois execute-o: `~/spotippos-vivareal$ java -jar target/spotippos-vivareal-0.1.0.jar`.

## Teste

O [Postman](https://www.getpostman.com/docs/) é uma excelente ferramenta de testes para REST APIs. No arquivo [spotippos-vivareal.postman_collection.json](spotippos-vivareal.postman_collection.json) há alguns testes prontos. Divirta-se!

