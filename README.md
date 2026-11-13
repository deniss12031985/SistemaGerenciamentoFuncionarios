# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

Projeto acadêmico desenvolvido em Java 17, Maven e JUnit 5 para demonstrar Programação Orientada a Objetos, arquitetura em camadas e testes automatizados.

## Funcionalidades
- Cadastro de funcionários CLT, PJ e comissionados.
- Listagem, busca por código/CPF/nome e remoção.
- Cálculo de remuneração individual.
- Relatório e total da folha de pagamento.
- Validação de dados e exceções personalizadas.

## Tecnologias
- Java 17
- Maven
- JUnit 5

## Organização
- `model`: entidades, classe abstrata e interfaces.
- `repository`: armazenamento em memória com `LinkedHashMap`.
- `service`: regras de negócio.
- `view`: menu de interação pelo terminal.
- `exception`: exceções personalizadas.

## Como executar
1. Instale o JDK 17 ou superior e o Maven.
2. Abra a pasta do projeto na IDE ou pelo terminal.
3. Execute os testes:

   ```bash
   mvn test
   ```

4. Execute a aplicação:

   ```bash
   mvn compile
   java -cp target/classes br.com.empresa.Main
   ```

   Alternativamente, execute a classe `br.com.empresa.Main` pela IDE.

## Regras de cálculo adotadas neste exemplo acadêmico
- **CLT:** salário-base + benefícios - desconto didático de 10% do salário-base.
- **PJ:** valor por projeto × quantidade de projetos.
- **Comissionado:** salário-base + comissão sobre vendas - desconto didático de 8% sobre o bruto.

**Importante:** os percentuais de desconto são hipóteses simplificadas para fins didáticos. Não representam cálculo oficial de INSS, IRRF ou tributação de pessoa jurídica. As regras reais dependem de legislação, faixa salarial, regime tributário e demais condições.

## Testes e TDD
Os testes JUnit 5 estão em `src/test/java`. Para demonstrar o processo TDD no Git, desenvolva e registre commits nesta sequência:
1. Teste inicial que falha (Red).
2. Implementação mínima para passar (Green).
3. Refatoração mantendo os testes verdes (Refactor).

O pacote entregue contém os testes e a implementação final. Para evidenciar o histórico TDD exigido, os commits devem ser realizados durante o desenvolvimento, sem criar um histórico fictício retroativo.

## Exemplo de commits
- `test: adiciona testes iniciais para cálculo CLT`
- `feat: implementa cálculo de remuneração CLT`
- `refactor: melhora validações da classe Funcionario`
- `test: adiciona testes do repository e service`
- `feat: implementa cadastro e folha de pagamento`
