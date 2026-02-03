🛡️  API- Cadastro de Usuários
Esta é uma API REST de cadastro de usuários desenvolvida com o objetivo principal de demonstrar a aplicação de Testes Unitários e de Integração sob rigorosos padrões de qualidade. O foco não é apenas o "funcionar", mas o "funcionar corretamente sob qualquer cenário".

🎯 Objetivo do Projeto
Demonstrar a implementação de uma camada de serviço 100% coberta por testes, garantindo que regras de negócio (como e-mails duplicados ou validação de CPF) sejam validadas automaticamente a cada alteração no código.

🧪 Estratégia de Testes
Testes Unitários (JUnit 5 & Mockito): Isolamento total da lógica de negócio na camada de Service através do uso de Mocks.

Testes de Integração: Validação da persistência de dados utilizando o banco em memória H2.

Cobertura de Código (Jacoco): Monitoramento da porcentagem de linhas de código testadas.

Tratamento de Exceções: Testes específicos para garantir que a API retorne os códigos HTTP corretos (400, 404, 409) em casos de erro.

🛠️ Stack Técnica
Backend: Java 17, Spring Boot 3.

Persistência: Spring Data JPA, Hibernate.

Testes: JUnit 5, Mockito, AssertJ.

Banco de Dados: PostgreSQL (Produção) / H2 (Testes).

Documentação: Swagger (OpenAPI 3).
