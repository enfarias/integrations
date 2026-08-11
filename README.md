Markdown
# Integrations - Envio de E-mails com Spring Boot e SendGrid

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg?style=flat&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.2.5-green.svg?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36.svg?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![SendGrid](https://img.shields.io/badge/SendGrid-SDK-blue.svg?style=flat&logo=sendgrid)](https://sendgrid.com/)
[![Postman](https://img.shields.io/badge/Postman-Tested-FF6C37.svg?style=flat&logo=postman)](https://www.postman.com/)

Projeto REST em Java com Spring Boot projetado para integrar e realizar o envio de e-mails em tempo real utilizando o SDK da API do **SendGrid**.

---

## 📌 Sobre o Projeto

O objetivo deste projeto é disponibilizar um endpoint REST (`/emails`) responsável por receber as informações de e-mail através de um `EmailDTO`, mapeá-las para os objetos nativos do SDK do SendGrid e disparar a mensagem via serviço de entrega de e-mails em nuvem.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Spring Web)
* **Apache Maven**
* **SendGrid Java SDK** (`com.sendgrid:sendgrid-java`)
* **Postman** (para testes de requisição HTTP)

---

## ⚙️ Configuração do Ambiente

O projeto utiliza variáveis de ambiente para proteger dados sensíveis como a chave da API do SendGrid.

### 1. Variável de Ambiente e Propriedades

No arquivo `application.properties`, a chave da API está configurada conforme abaixo:

```properties
spring.application.name=integrations
spring.sendgrid.api-key=${SENDGRID_API_KEY}
```
Antes de rodar a aplicação, defina a variável de ambiente SENDGRID_API_KEY no seu sistema operacional ou nas configurações de execução da sua IDE (Run Configurations) informando uma API Key válida fornecida pelo SendGrid.

---

## 🚀 Como Executar o Projeto

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/bds-integrations.git](https://github.com/seu-usuario/integrations.git)
   ```
2. **Entrar no diretório:**
   ```bash
   cd integrations
   ```
3. **Executar a aplicação via Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
A aplicação estará rodando em `http://localhost:8080`.

---

## 🧪 Testando com o Postman

Você pode disparar uma requisição `POST` para o endpoint `/emails` para testar o envio de e-mails.

* **Método:** `POST`
* **URL:** `http://localhost:8080/emails`
* **Headers:** `Content-Type: application/json`

### Body (JSON):

```json
{
    "fromEmail": "nome@dominio.com",
    "fromName": "Nome",
    "replyTo": "nome@dominio.com",
    "to": "destinatario@gmail.com",
    "subject": "Meu assunto",
    "body": "Meu conteúdo do email com <strong>palavra forte</strong> destacada.",
    "contentType": "text/html"
}
```
### Resposta esperada:
* **Status:** `204 No Content`

## 🪵 Funcionamento e Logs

A camada de serviço (`EmailService`) intercepta o DTO, constrói a mensagem utilizando as classes auxiliares do SendGrid (`Mail`, `Email`, `Content`) e realiza a chamada REST diretamente para o endpoint `mail/send` da API do SendGrid.

* **Em caso de sucesso (Status 2xx):**
  O log da aplicação registrará o envio e o status code retornado:
  ```text
  INFO  - Sending email to: destinatario@gmail.com
  INFO  - Email sent! Status = 202
  ```

* **Em caso de erro na integração (Status 4xx / 5xx):**
  O log registrará o corpo do erro retornado pelos servidores do SendGrid:
  ```text
  ERROR - Error sending email: {"errors":[{"message":"The from email does not match a verified Sender Identity.","field":"from","help":null}]}
  ```
---

## 📬 Estrutura do DTO de E-mail

| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `fromEmail` | `String` | E-mail do remetente (deve ser um remetente verificado no SendGrid) |
| `fromName` | `String` | Nome exibido do remetente |
| `replyTo` | `String` | E-mail para resposta |
| `to` | `String` | E-mail do destinatário |
| `subject` | `String` | Assunto da mensagem |
| `body` | `String` | Conteúdo da mensagem (Texto puro ou HTML) |
| `contentType` | `String` | Tipo do conteúdo (`text/plain` ou `text/html`) |
