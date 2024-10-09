Trabalho-de-JAVA
Este sistema foi desenvolvido para facilitar o processo de agendamento de doações para Organizações Não Governamentais (ONGs). O objetivo é permitir que os doadores possam registrar suas informações, listar os itens que irão doar (como roupas, alimentos, etc.), e agendar a data para a entrega ou retirada da doação.

Funcionalidades Principais:
Cadastro de doadores: Registro de informações básicas do doador (nome, CPF, e-mail, telefone, endereço).
Registro de itens de doação: Doador pode cadastrar os itens que pretende doar (ex.: roupas, alimentos, brinquedos).
Agendamento de doações: O doador pode agendar a data e o local para a entrega dos itens na ONG.
Agendamento de retirada de doações: O sistema permite que o doador solicite a retirada da doação em um local específico.
Listagem de itens de doação: Os itens cadastrados pelos doadores podem ser listados.
Atualização e exclusão de doadores: O sistema permite que os doadores atualizem suas informações ou excluam seus registros.
Tecnologias Utilizadas:
Java: Linguagem de programação principal para desenvolvimento do sistema.
Apache POI: Biblioteca para leitura e gravação de arquivos Excel, utilizada para armazenar os dados dos doadores e itens.
GitHub: Repositório de código para controle de versão e colaboração.
Estrutura do Projeto:
Pacotes e Classes:
model: Contém as classes de modelo do sistema:

Doador: Representa o doador, contendo informações como nome, CPF, e-mail, telefone e endereço.
ItemDoacao: Representa um item de doação, com atributos como nome, descrição e categoria.
service: Contém as classes de serviço que implementam a lógica do sistema:

AgendamentoStrategy: Interface para a estratégia de agendamento.
AgendamentoEntrega: Implementa a estratégia de entrega de doações.
AgendamentoRetirada: Implementa a estratégia de retirada de doações.
utils: Contém classes auxiliares:

ExcelHelper: Classe responsável pela manipulação de arquivos Excel (leitura e escrita).
Main: Classe principal que contém a lógica para interagir com o usuário e fazer chamadas às funcionalidades do sistema.

Funcionalidades do Sistema:
1. Cadastro de Doador:
O sistema permite a criação de novos doadores, armazenando suas informações básicas em um arquivo Excel.

2. Registro de Itens de Doação:
Os doadores podem registrar itens que desejam doar, fornecendo detalhes como nome, descrição e categoria do item.

3. Agendamento de Doações:
O sistema permite que o doador agende a entrega ou solicite a retirada dos itens em uma data específica. A estratégia de agendamento é aplicada conforme o tipo de agendamento (entrega ou retirada).

4. Atualização e Exclusão:
O sistema permite a atualização e exclusão de doadores e seus respectivos dados.

Como Executar:
Clone este repositório.
Certifique-se de ter o Java instalado no seu sistema.
Compile o código usando um ambiente de desenvolvimento Java, como IntelliJ IDEA ou Eclipse.
Execute a classe Main.java para iniciar o sistema.
Siga as instruções no terminal para navegar pelas opções do sistema (Cadastrar doadores, Listar doadores, Agendar doações, etc.).
Princípios de SOLID Aplicados:
Single Responsibility Principle (Responsabilidade Única): Cada classe tem uma única responsabilidade. Por exemplo, a classe Doador é responsável apenas pelos dados do doador, e a classe Agendamento é responsável pela lógica de agendamento.

Dependency Inversion Principle (Inversão de Dependência): As dependências foram gerenciadas por meio de interfaces, como a interface AgendamentoStrategy, permitindo a fácil extensão e modificação das estratégias de agendamento.
