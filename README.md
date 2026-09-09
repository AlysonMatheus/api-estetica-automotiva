# 🚗 API Estética Automotiva

API REST desenvolvida para gerenciamento de uma estética automotiva, permitindo controlar clientes, veículos, serviços e agendamentos.

O projeto está sendo desenvolvido com foco no aprendizado e aplicação de boas práticas de desenvolvimento backend utilizando **Java, Spring Boot, JPA/Hibernate e PostgreSQL**.

> 🚧 Projeto em desenvolvimento.

---

## 📌 Sobre o projeto

A API tem como objetivo centralizar o gerenciamento de uma estética automotiva.

A aplicação permite trabalhar com:

- Clientes
- Veículos
- Serviços
- Agendamentos
- Status dos agendamentos
- Serviços vinculados a um agendamento
- Histórico do preço cobrado por serviço

Uma das principais regras de negócio do projeto é preservar o preço praticado no momento em que um serviço é adicionado a um agendamento.

---

## 🛠️ Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Git
- GitHub

---

## 🏗️ Arquitetura

O projeto segue uma separação em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Entity

Representa as entidades e os relacionamentos persistidos no banco de dados.

### RequestDTO

Representa os dados recebidos pela API.

### ResponseDTO

Representa os dados devolvidos pela API.

### Service

Responsável pelas regras de negócio e pela coordenação das operações da aplicação.

### Repository

Responsável pelo acesso aos dados através do Spring Data JPA.

---

## 📦 Estrutura do projeto

```text
src/main/java/com/alyson/apiestetica
│
├── entity
│   ├── Agendamento.java
│   ├── AgendamentoServico.java
│   ├── AgendamentoServicoId.java
│   ├── Carro.java
│   ├── Cliente.java
│   └── Servico.java
│
├── entity/request
│   ├── AgendamentoRequestDTO.java
│   ├── CarroRequestDTO.java
│   ├── ClienteRequestDTO.java
│   └── ServicoRequestDTO.java
│
├── entity/response
│   ├── AgendamentoResponseDTO.java
│   ├── AgendamentoServicoResponseDTO.java
│   ├── CarroResponseDTO.java
│   ├── ClienteResponseDTO.java
│   └── ServicoResponseDTO.java
│
├── enums
│   └── StatusAgendamento.java
│
├── repository
│   ├── AgendamentoRepository.java
│   ├── CarroRepository.java
│   ├── ClienteRepository.java
│   └── ServicoRepository.java
│
└── services
    ├── AgendamentoService.java
    ├── CarroService.java
    ├── ClienteService.java
    └── ServicoService.java
```

---

## 🗃️ Modelagem

As principais entidades do sistema são:

```text
Cliente
   │
   └── Carro
          │
          └── Agendamento
                    │
                    └── AgendamentoServico
                              │
                              └── Servico
```

### Cliente e Carro

Um cliente pode possuir vários veículos.

```text
Cliente 1 ───── N Carro
```

### Carro e Agendamento

Um veículo pode possuir vários agendamentos.

```text
Carro 1 ───── N Agendamento
```

### Agendamento e Serviço

Um agendamento pode possuir vários serviços e um serviço pode participar de vários agendamentos.

Como esse relacionamento possui uma informação própria, foi criada a entidade associativa `AgendamentoServico`.

```text
Agendamento
     1
     │
     N
AgendamentoServico
     N
     │
     1
  Servico
```

---

## 💰 Histórico de preços

Um dos pontos importantes da modelagem é a separação entre:

```text
Servico.preco
```

e:

```text
AgendamentoServico.precoCobrado
```

`Servico.preco` representa o preço atual do serviço.

`AgendamentoServico.precoCobrado` representa o preço que foi cobrado no momento daquele agendamento.

### Exemplo

Um polimento custa atualmente:

```text
R$ 300,00
```

Porém, um cliente realizou um agendamento anteriormente quando o serviço custava:

```text
R$ 250,00
```

O sistema mantém:

```text
Servico.preco = 300.00

AgendamentoServico.precoCobrado = 250.00
```

Dessa forma, alterações futuras no preço de um serviço não modificam o histórico dos agendamentos anteriores.

---

## 🔑 Chave composta

A entidade `AgendamentoServico` utiliza uma chave primária composta representada por:

```java
AgendamentoServicoId
```

A chave é formada por:

```text
idAgendamento + idServico
```

Exemplo:

```text
(10, 3)
```

representa o serviço `3` vinculado ao agendamento `10`.

A implementação utiliza:

- `@EmbeddedId`
- `@Embeddable`
- `@MapsId`

Essa abordagem permite transformar a tabela intermediária em uma entidade com informações próprias.

---

## 💵 Cálculo do valor do agendamento

O valor total de um agendamento é calculado utilizando os preços registrados em `AgendamentoServico`.

Exemplo:

```text
Lavagem        R$  80,00
Polimento      R$ 250,00
Higienização   R$ 180,00
------------------------
Total           R$ 510,00
```

O cálculo utiliza `BigDecimal` para representar valores monetários.

---

## 📊 Status do agendamento

Os estados do agendamento são representados pelo enum:

```java
StatusAgendamento
```

Isso evita trabalhar com valores de status arbitrários e facilita a implementação das regras de transição entre estados.

---

## ⚙️ Configuração do banco de dados

Por segurança, o arquivo real:

```text
src/main/resources/application.properties
```

não é versionado.

O projeto possui:

```text
application-example.properties
```

como modelo de configuração.

Após clonar o projeto, crie seu `application.properties` com base no arquivo de exemplo e configure seu PostgreSQL.

Exemplo:

```properties
spring.application.name=apiestetica

spring.datasource.url=jdbc:postgresql://localhost:5432/NOME_DO_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

server.port=8081
```

> Nunca publique senhas ou credenciais reais no repositório.

---

## ▶️ Executando o projeto

### Pré-requisitos

É necessário possuir:

- Java instalado
- PostgreSQL instalado
- Git

Clone o repositório:

```bash
git clone https://github.com/AlysonMatheus/api-estetica-automotiva.git
```

Entre na pasta:

```bash
cd api-estetica-automotiva
```

Configure o arquivo:

```text
src/main/resources/application.properties
```

Depois, no Windows, execute:

```bash
mvnw.cmd spring-boot:run
```

Ou execute `ApiesteticaApplication` diretamente pela IDE.

Por padrão, a aplicação está configurada para utilizar:

```text
http://localhost:8081
```

---

## 🚧 Próximas etapas

O projeto ainda está em desenvolvimento. Entre as próximas melhorias planejadas estão:

- Implementação dos Controllers REST
- Validação dos DTOs
- Tratamento global de exceções
- Validação de conflitos de horários
- Regras completas de status dos agendamentos
- Consulta somente de serviços ativos
- Testes unitários e de integração
- Documentação da API com Swagger/OpenAPI
- Autenticação e autorização

---

## 🎯 Objetivo do projeto

Além de resolver um problema real de gerenciamento de uma estética automotiva, este projeto está sendo utilizado para aprofundar conhecimentos em:

- Desenvolvimento backend com Java
- Spring Boot
- APIs REST
- Orientação a Objetos
- JPA/Hibernate
- Modelagem de banco de dados
- PostgreSQL
- DTOs
- Relacionamentos entre entidades
- Regras de negócio
- Git e GitHub

---

## 👨‍💻 Autor

**Alyson Matheus**

Projeto desenvolvido como parte dos estudos e evolução em desenvolvimento backend com Java e Spring Boot.
