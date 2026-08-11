# Integrations - Envio de E-mails com Spring Boot e SendGrid

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg?style=flat&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.2.5-green.svg?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36.svg?style=flat&logo=apache-maven)](https://maven.apache.org/)
[![SendGrid](https://img.shields.io/badge/SendGrid-SDK-blue.svg?style=flat&logo=sendgrid)](https://sendgrid.com/)
[![Postman](https://img.shields.io/badge/Postman-Tested-FF6C37.svg?style=flat&logo=postman)](https://www.postman.com/)

Projeto REST em Java com Spring Boot desenvolvido para integrar e realizar o envio de e-mails em tempo real utilizando a API do **SendGrid**, contando com uma arquitetura flexível baseada em perfis de ambiente (`dev` e `test`).

---

## 🏗️ Arquitetura e Design Patterns

O projeto utiliza o padrão de projeto **Strategy** em conjunto com a injeção de dependência condicional via `@Profile` do Spring:

```text
                [ EmailResource ]
                          │
                          ▼
                   «interface»
                 [ EmailService ]
                  ▲            ▲
                  │            │
   (@Profile("dev"))          (@Profile("test"))
          │                            │
[ SendGridEmailService ]      [ MockEmailService ]
  (Envio Real via API)          (Log no Terminal)
```

* **`EmailService` (Interface):** Define o contrato de envio de e-mails.
* **`SendGridEmailService`:** Implementação concreta que consome a API do SendGrid.
* **`MockEmailService`:** Implementação mockada que apenas simula o envio imprimindo logs no console (ideal para testes locais sem consumo de cota).

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Spring Web)
* **Apache Maven**
* **SendGrid Java SDK** (`com.sendgrid:sendgrid-java`)
* **Postman** (para testes da API REST)

---

## ⚙️ Configuração de Ambientes (Profiles)

A alternância entre o envio real e a simulação é controlada pela propriedade `spring.profiles.active` no arquivo `application.properties`.

```properties
spring.application.name=integrations

# Alterne entre 'test' (Mock) ou 'dev' (SendGrid)
spring.profiles.active=test

# Necessário apenas para o perfil 'dev'
spring.sendgrid.api-key=${SENDGRID_API_KEY}
```

### Perfis Disponíveis:

| Profile | Serviço Injetado | Comportamento |
| :--- | :--- | :--- |
| **`test`** | `MockEmailService` | Simula o envio via logs no console. Não consome API nem requer chave. |
| **`dev`** | `SendGridEmailService` | Dispara o e-mail real via SendGrid API. Requer `SENDGRID_API_KEY`. |

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
3. **Definir a Chave da API (apenas para o perfil `dev`):**
   ```bash
   export SENDGRID_API_KEY="sua-chave-aqui"
   ```
5. **Executar a aplicação via Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
A aplicação estará rodando em `http://localhost:8080`.

---

## 🧪 Testando com o Postman

Dispare uma requisição `POST` para o endpoint `/emails`:

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

O comportamento dos logs varia de acordo com o perfil ativo no `application.properties`:

* **Perfil `test` (`MockEmailService`):**
  Simula o disparo imprimindo apenas as mensagens no terminal:
  ```text
  INFO  - Sending email to: destinatario@gmail.com
  INFO  - Email sent!
  ```
* **Perfil `dev` (`SendGridEmailService`):**
  Realiza a chamada HTTP para o SendGrid.
  * **Sucesso (Status 2xx):**
    ```text
    INFO  - Sending email to: destinatario@gmail.com
    INFO  - Email sent! Status = 202
    ```
* **Erro (Status 4xx/5xx):**
    ```text
    ERROR - Error sending email: {"errors":[{"message":"...","field":"from"}]}
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
