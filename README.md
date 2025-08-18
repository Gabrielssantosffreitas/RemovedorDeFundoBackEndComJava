# SendPngNotBackground

Um projeto Spring Boot para remover o fundo de imagens PNG ou JPG utilizando a API remove.bg. O sistema recebe uma imagem em base64 via requisição HTTP, envia para a API externa, e retorna a imagem processada (sem fundo) também em base64.

## Sumário
- [Descrição](#descrição)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Funciona](#como-funciona)
- [Configuração](#configuração)
- [Endpoints](#endpoints)
- [Estrutura de Pastas](#estrutura-de-pastas)
- [Exemplo de Uso](#exemplo-de-uso)
- [Dependências](#dependências)
- [YAML de Configuração](#yaml-de-configuração)
- [XML do Projeto (pom.xml)](#xml-do-projeto-pomxml)

---

## Descrição
Este projeto foi desenvolvido para receber imagens em base64, remover o fundo utilizando a API remove.bg, e retornar a imagem processada em base64. O projeto é extensível e pode ser integrado a outros sistemas que necessitem manipulação de imagens.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.4
- Maven
- Jackson (para manipulação de JSON)
- RestTemplate (para requisições HTTP)
- remove.bg API

## Como Funciona
1. O usuário envia uma imagem em base64 via POST para o endpoint `/img_post`.
2. O backend decodifica o base64, detecta o tipo da imagem (PNG/JPG), salva temporariamente, e envia para a API remove.bg.
3. A resposta da API (imagem sem fundo) é convertida novamente para base64 e retornada ao usuário.

## Configuração
### application.properties
```properties
spring.application.name=SendPngNotBackground
server.port=8080
sever.address=0.0.0.0
api.key=${API_KEY}
account.sid=${ACC_SID}
auth.token=${AUT_TOK}
```
- **API_KEY**: Chave da API remove.bg (defina como variável de ambiente ou diretamente no arquivo).

## Endpoints
### GET `/test`
Retorna uma mensagem de teste.

### POST `/img_post`
Recebe um JSON com a imagem em base64 e retorna a imagem processada em base64.

#### Exemplo de requisição:
```json
{
  "imagemBase64": "<base64_da_imagem>",
  "mensagem": "Texto opcional"
}
```

#### Exemplo de resposta:
```json
{
  "imagemBase64": "<base64_da_imagem_sem_fundo>",
  "mensagem": "Aqui esta sua foto"
}
```

## Estrutura de Pastas
```
src/
  main/
    java/
      com/
        gabriel/
          SendPngNotBackground/
            SendPngNotBackgroundApplication.java
            controller/
              ImagemController.java
            model/
              models/
                ImagemModels.java
            services/
              Imagem/
                ImagemService.java
                httpRequestApi/
                  RequestApiBackgroundBg.java
    resources/
      application.properties
      static/
      templates/
```

## Dependências
- spring-boot-starter-web
- spring-boot-devtools
- spring-boot-starter-test
- jackson-core
- jackson-databind
- jackson-annotations
- twilio (opcional, para integração futura)

## YAML de Configuração
```yaml
spring:
  application:
    name: SendPngNotBackground
server:
  port: 8080
  address: 0.0.0.0
api:
  key: ${API_KEY}
account:
  sid: ${ACC_SID}
auth:
  token: ${AUT_TOK}
```

## XML do Projeto (pom.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.5.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.gabriel</groupId>
	<artifactId>SendPngNotBackground</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>SendPngNotBackground</name>
	<description>Um projeto que retira o fundo das imagem</description>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.17.0</version>
		</dependency>
		<dependency>
			<groupId>com.twilio.sdk</groupId>
			<artifactId>twilio</artifactId>
			<version>9.3.0</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.17.0</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-annotations</artifactId>
			<version>2.17.0</version>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

---

## Observações
- O projeto não inclui informações sobre a pasta `test`.
- Para rodar, defina a variável de ambiente `API_KEY` com sua chave da API remove.bg.
- O projeto está pronto para ser expandido com novas integrações (ex: Twilio).
- O endpoint principal é `/img_post`.

---

## Autor
Gabriel

