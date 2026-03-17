# 🚀 Framework de Automação de Testes (API + WEB)

Este projeto é um **framework de automação de testes** desenvolvido em **Java**, com suporte para testes **API** e **WEB**, utilizando abordagem **BDD (Behavior Driven Development)**.

O objetivo do projeto é fornecer uma estrutura simples e escalável para automação de testes com geração de **evidências automáticas**, **logs estruturados** e **organização clara do código**.

---

# 🧰 Tecnologias Utilizadas

* Java
* Cucumber (BDD)
* Selenium WebDriver
* HttpClient (Java)
* JUnit
* Log4j2
* iText (geração de PDF)
* WebDriverManager

---

# 📂 Estrutura do Projeto

```
src
 └── test
      ├── java
      │    └── com.example.demo
      │         ├── api
      │         │     ├── logic
      │         │     ├── steps
      │         │     └── model
      │         │
      │         ├── web
      │         │     ├── driver
      │         │     ├── logic
      │         │     └── steps
      │         │
      │         ├── hooks
      │         │
      │         ├── report
      │         │
      │         └── runners
      │
      └── resources
           └── features
```

---

# 🧪 Tipos de Teste

O framework suporta dois tipos de testes:

### Testes de API

Utiliza **HttpClient** para realizar chamadas HTTP e validar respostas.

Exemplo de cenário:

```gherkin
@api
Scenario: Consultar post
Given que preciso consultar um post
When chamar a api com os parametros corretos
Then então devo receber a resposta desejada
```

---

### Testes WEB

Utiliza **Selenium WebDriver** para automação de navegador.

Exemplo de cenário:

```gherkin
@web
Scenario: Abrir Linkedin
Given que preciso acessar o linkedin
```

---

# 🏷 Uso de Tags

As tags permitem executar apenas tipos específicos de testes.

| Tag         | Descrição                    |
| ----------- | ---------------------------- |
| @api        | Executa apenas testes de API |
| @web        | Executa apenas testes WEB    |
| @smoke      | Testes rápidos               |
| @regression | Testes completos             |

Exemplo:

```
@web @smoke
Scenario: Abrir Linkedin
```

---

# ▶ Executando os Testes

Os testes são executados através das classes **Runner**.

Exemplo:

```
WebRunner.java
```

Configuração:

```java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.demo",
    plugin = {"json:target/cucumber-report.json"},
    monochrome = true,
    tags = "@web"
)
```

Para executar testes de API:

```
tags = "@api"
```

---

# 📊 Logs de Execução

Os logs são gerados utilizando **Log4j2** e exibem informações importantes como:

* Endpoint da requisição
* Método HTTP
* Status da resposta
* Corpo da resposta

Exemplo:

```
========== REQUEST ==========
Endpoint: https://jsonplaceholder.typicode.com/posts/1
Method: GET

========== RESPONSE ==========
Status Code: 200
Body: {...}
```

---

# 📸 Evidências de Execução

O framework gera evidências automaticamente.

Estrutura:

```
evidence
 └── 2026-03-16
      ├── screenshots
      └── relatorio-api.pdf
```

As evidências incluem:

* Screenshot por step (testes WEB)
* Logs de execução
* Relatório em PDF
* Request e Response das APIs

---

# 📷 Screenshots Automáticos

Screenshots são capturados automaticamente após cada step para cenários com tag **@web**.

Isso é feito através de **Hooks do Cucumber**.

---

# 📄 Relatório em PDF

Os testes de API geram automaticamente um relatório contendo:

* Endpoint
* Status Code
* Response Body
* Data da execução

---

# ⚙ Driver do Selenium

O gerenciamento do navegador é feito pela classe:

```
DriverFactory
```

Utilizando **WebDriverManager** para baixar automaticamente o driver correto.

---

# 🧱 Boas Práticas Utilizadas

* Separação entre **Steps** e **Logic**
* Estrutura baseada em **BDD**
* Logs centralizados
* Evidência automática
* Uso de **tags para execução seletiva**
* Código modular e reutilizável

---

# 🚧 Melhorias Futuras

Algumas evoluções planejadas para o framework:

* Integração com planilhas para **data-driven tests**
* Relatório visual avançado
* Integração com CI/CD
* Execução paralela de testes
* Melhor organização de evidências

---

# 👨‍💻 Autor

Projeto desenvolvido para estudo e evolução de automação de testes utilizando Java.

---
