# Integrations - Envio de E-mails com Spring Boot e SendGrid

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg?style=flat&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green.svg?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36.svg?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![SendGrid](https://img.shields.io/badge/SendGrid-API-blue.svg?style=flat&logo=sendgrid)](https://sendgrid.com/)
[![Postman](https://img.shields.io/badge/Postman-Tested-FF6C37.svg?style=flat&logo=postman)](https://www.postman.com/)

Projeto de teste desenvolvido em Java com Spring Boot para simulação e integração com serviço de envio de e-mails via API Key do SendGrid.

---

## 📌 Sobre o Projeto

O objetivo deste projeto é fornecer um endpoint REST para recebimento de dados de e-mail (`EmailDTO`) e processar o envio através de provedores externos, utilizando a API do **SendGrid**.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot** (Spring Web MVC)
* **Apache Maven**
* **SendGrid API**
* **Postman** (para testes de requisição HTTP)

---

## ⚙️ Configuração do Ambiente

O projeto utiliza variáveis de ambiente para proteger dados sensíveis como a chave da API do SendGrid.

### 1. Variável de Ambiente

No arquivo `application.properties`, o parâmetro de API está mapeado como:

```properties
spring.application.name=integrations
spring.sendgrid.api-key=${SENDGRID_API_KEY}
```
Antes de rodar a aplicação, defina a variável de ambiente `SENDGRID_API_KEY` na sua máquina ou na sua IDE com a sua chave válida fornecida pelo SendGrid.

---

## 🚀 Como Executar o Projeto

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/integrations.git](https://github.com/seu-usuario/integrations.git)
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

Você pode disparar uma requisição `POST` para o endpoint `/emails` para testar o envio.

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
