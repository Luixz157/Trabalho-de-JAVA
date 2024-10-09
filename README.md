# Trabalho de JAVA
Este sistema foi desenvolvido para facilitar o processo de agendamento de doações para Organizações Não Governamentais (ONGs). O objetivo é permitir que os doadores registrem suas informações, cadastrem itens que irão doar (como roupas, alimentos, etc.), e agendem a data para a entrega ou retirada das doações. O sistema também valida as entradas de dados, como CPF e data, garantindo maior precisão nas informações fornecidas.

## Funcionalidades Principais
- **Cadastro de doadores:** Registro de informações básicas do doador, como nome, CPF, e-mail, telefone e endereço. O sistema valida o CPF para garantir que tenha o formato correto (11 dígitos numéricos).

- **Registro de itens de doação:** O doador pode cadastrar os itens que pretende doar, fornecendo detalhes como nome, descrição e categoria (roupas, alimentos, brinquedos, etc.). São oferecidas opções padrão para categorias de doação.

- **Agendamento de doações: O doador** O doador pode agendar a data e o local para a entrega dos itens na ONG. A data é validada para garantir que esteja no formato correto (dd/MM/yyyy).

- **Agendamento de retirada de doações:** O sistema permite que o doador solicite que a ONG faça a retirada da doação em um local específico, utilizando uma estratégia de agendamento para diferenciar entre entrega e retirada.

- **Listagem de itens de doação:** Exibe a lista de itens de doação cadastrados no sistema.

- **Atualização e exclusão de doadores:** O sistema permite que os doadores atualizem suas informações ou excluam seus registros, tudo gerenciado via um arquivo Excel.

## Tecnologias Utilizadas
- **Java:** Linguagem de programação principal utilizada no desenvolvimento.

- **Apache POI:** Biblioteca para leitura e gravação de arquivos Excel, utilizada para armazenar os dados dos doadores e itens de doação. O arquivo Excel é usado como base de dados para garantir persistência das informações.

- **JUnit:** Biblioteca utilizada para criar e executar testes unitários, garantindo que as funções principais (como validação de CPF e data, e operações CRUD) funcionem corretamente. 

- **GitHub:** Repositório para controle de versão e colaboração entre membros da equipe.

## Estrutura do Projeto
O projeto segue uma estrutura de pacotes que separa as responsabilidades de cada parte do sistema:

### Pacotes e Classes
`model:` Contém as classes de modelo do sistema:

- `Doador:` Representa o doador, contendo informações como nome, CPF, e-mail, telefone e endereço.

- `ItemDoacao:` Representa um item de doação, com atributos como nome, descrição e categoria.

`service:` Contém as classes que implementam a lógica do sistema:

- `AgendamentoStrategy:` Interface para definir a estratégia de agendamento.

- `AgendamentoEntrega:` Implementa a estratégia de entrega das doações.

- `AgendamentoRetirada:` Implementa a estratégia de retirada das doações.

`utils:` Contém classes auxiliares:

- `ExcelHelper:` Classe responsável pela manipulação de arquivos Excel (leitura e escrita dos dados dos doadores e itens).

- `Main:` Classe principal que contém a lógica para interagir com o usuário e coordenar as funcionalidades do sistema. Também contém métodos para validação de CPF e data.

- `Validador` Contém métodos auxiliares para validar CPF e datas, garantindo que os dados inseridos pelos usuários estão no formato correto (ex.: CPF com 11 dígitos e datas no formato dd/MM/yyyy).

`test:` Contém as classes de teste:

  - `MainTest: ` Classe responsável por executar os testes unitários para validação de CPF, validação de data, criação de doador e agendamentos (entrega e retirada).

## Funcionalidades do Sistema
- **Cadastro de Doador:** Permite a criação de novos doadores, armazenando suas informações básicas em um arquivo Excel. O CPF é validado antes de o registro ser salvo.

- **Registro de Itens de Doação:** O doador pode registrar itens que deseja doar, fornecendo detalhes como nome, descrição e categoria. O sistema oferece opções padrão para facilitar o cadastro.

- **Agendamento de Doações:** O doador pode agendar a entrega dos itens ou solicitar a retirada dos itens em um local específico. A data do agendamento é validada para garantir que esteja no formato correto (dd/MM/yyyy). O sistema utiliza a estratégia de agendamento conforme o tipo de doação (entrega ou retirada).

- **Atualização e Exclusão:** O sistema permite que os dados dos doadores sejam atualizados ou excluídos conforme necessário. As alterações são refletidas no arquivo Excel utilizado como base de dados.

- **Listagem de Itens de Doação:** Permite que os itens de doação registrados sejam listados e exibidos ao usuário.

## Como Executar
1. Clone este repositório:

```
git clone <url-do-repositorio>
```
2. Certifique-se de ter o Java instalado no seu sistema.
3. Compile o código usando um ambiente de desenvolvimento Java, como IntelliJ IDEA ou Eclipse.
4. Execute a classe Main.java para iniciar o sistema.
5. Siga as instruções exibidas no terminal para navegar pelas opções do sistema:
  - Cadastrar doadores
  - Listar doadores
  - Agendar doações (entrega ou retirada)
  - Atualizar ou excluir doadores
  - Registrar e listar itens de doação

## Testes Unitários

Os testes unitários foram implementados com JUnit para garantir a validação e a funcionalidade do sistema. O arquivo MainTest.java inclui testes para:

- **Validação de CPF:** Testa se o CPF inserido está no formato correto (11 dígitos).
- **Validação de Data:** Testa se a data está no formato correto (dd/MM/yyyy) e se é uma data válida.
- **Criação de Doador:** Verifica se as informações de um novo doador são corretamente armazenadas.
- **Agendamento de Doações:** Testa os agendamentos de entrega e retirada, garantindo que as datas e os tipos de agendamento estão funcionando corretamente.
  
### Como Executar os Testes
- **Via IntelliJ IDEA ou Eclipse:** Clique com o botão direito no arquivo MainTest.java e selecione "Run 'MainTest'". Isso executará todos os testes e mostrará os resultados.

- **Via Maven:** Execute os testes na linha de comando com:

```
  mvn test
```
## Princípios de SOLID Aplicados

- Single Responsibility Principle (Princípio da Responsabilidade Única): Cada classe tem uma única responsabilidade. Por exemplo, a classe Doador é responsável apenas pelos dados do doador, enquanto a classe Agendamento cuida da lógica de agendamento.
  
- Dependency Inversion Principle (Princípio da Inversão de Dependência): As dependências foram gerenciadas por meio de interfaces, como a AgendamentoStrategy, permitindo fácil extensão e modificação das estratégias de agendamento.
