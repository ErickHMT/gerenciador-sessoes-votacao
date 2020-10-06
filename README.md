# gerenciador-sessoes-votacao

Aplicação back end responsável por gerenciar sessões de votação.

[Documentação API](https://sessoes-votacao.herokuapp.com/swagger-ui.html);

Responsabilidades:
 - Cadastrar uma nova pauta.
 - Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default).
 - Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
 - Contabilizar os votos e dar o resultado da votação na pauta.

## Requisitos

Para realizar o build e executar a aplicação você irá precisar de:

- [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org)

## Executar a aplicação localmente

Há diversas maneiras de se executar uma aplicação Spring Boot localmente. Uma delas é executando o método `main` em `com.gerenciadorsessoesvotacao` a partir da sua IDE.

Você também pode utilizar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da seguinte maneira:

```shell
mvn spring-boot:run
```
