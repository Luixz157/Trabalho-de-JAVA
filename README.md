# Trabalho de JAVA
Este sistema foi desenvolvido para facilitar o processo de agendamento de doações para Organizações Não Governamentais (ONGs). O objetivo é permitir que os doadores registrem suas informações, cadastrem itens que irão doar (como roupas, alimentos, etc.), e agendem a data para a entrega ou retirada das doações.

## Funcionalidades Principais
- **Cadastro de doadores:** Registro de informações básicas do doador, como nome, CPF, e-mail, telefone e endereço.

- **Registro de itens de doação:** O doador pode cadastrar os itens que pretende doar, fornecendo detalhes como nome, descrição e categoria (roupas, alimentos, brinquedos, etc.).

- **Agendamento de doações: O doador** pode agendar a data e o local para a entrega dos itens na ONG.

- **Agendamento de retirada de doações:** O sistema permite que o doador solicite que a ONG faça a retirada da doação em um local específico.

- **Listagem de itens de doação:** Exibe a lista de itens de doação cadastrados.

- **Atualização e exclusão de doadores:** O sistema permite que os doadores atualizem suas informações ou excluam seus registros.

## Tecnologias Utilizadas
- **Java:** Linguagem de programação principal utilizada no desenvolvimento.

- **Apache POI:** Biblioteca para leitura e gravação de arquivos Excel, utilizada para armazenar os dados dos doadores e itens de doação.

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

- `Main:` Classe principal que contém a lógica para interagir com o usuário e coordenar as funcionalidades do sistema.

## Funcionalidades do Sistema
- **Cadastro de Doador:** Permite a criação de novos doadores, armazenando suas informações básicas em um arquivo Excel.

- **Registro de Itens de Doação:** O doador pode registrar itens que deseja doar, fornecendo detalhes como nome, descrição e categoria.

- **Agendamento de Doações:** O doador pode agendar a entrega dos itens ou solicitar a retirada dos itens em um local específico. O sistema utiliza uma estratégia de agendamento conforme o tipo de doação.

- **Atualização e Exclusão:** O sistema permite que os dados dos doadores sejam atualizados ou excluídos conforme necessário.

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

## Princípios de SOLID Aplicados

- Single Responsibility Principle (Princípio da Responsabilidade Única): Cada classe tem uma única responsabilidade. Por exemplo, a classe Doador é responsável apenas pelos dados do doador, enquanto a classe Agendamento cuida da lógica de agendamento.

- Dependency Inversion Principle (Princípio da Inversão de Dependência): As dependências foram gerenciadas por meio de interfaces, como a AgendamentoStrategy, permitindo fácil extensão e modificação das estratégias de agendamento.
