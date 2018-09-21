# WebServices Cadastro Clientes.

Proposta:
Desenvolver uma Web Services para operações com entidade Cliente.

Requisitos de Software:
- O cliente deve possuir CPF, Nome, E-mail, Situação atual e múltiplos telefones.
- Situação atual poderá ser ‘Ativo’ ou ’Inativo’.
- Todos os dados são obrigatórios.
- A aplicação deve possibilitar Inclusão, Atualização e Remoção dos clientes da base.

Tenho certeza que esse projeto não está de acordo com os padrões de programação existentes atualmente, existindo a possibilidade de melhorar a aplicação.


## Instruções: 
1. Downloads e Instalação da IDE para desenvolvimento em java de sua preferência, para esse projeto foi utilizado o NetBenas versão 8.2.
>https://netbeans.org/downloads/index.html
2. Importar e executar o projeto na IDE desejada.
>https://codeload.github.com/Dlucap/WebServices-Cadastro-Cliente/zip/master
3. Executar a aplicação


## Java library
1. Gson 2.8.1 -  Biblioteca Java usada para converter objetos Java em sua representação JSON.
>https://github.com/google/gson/tree/master/gson
2. SQLJDBC Versão 4.2 - Conector utilizado para comunicações com o banco de dados MSSQL.
>https://www.microsoft.com/en-us/download/details.aspx?id=54671
3. Servidor GlassFish 4.1.1 - Já vem instalado no NetBeans, também está acessível no link a seguir: 
>http://www.oracle.com/technetwork/pt/middleware/glassfish/overview/index.html

 
## API: 
***GET* /Cliente/{cliente}**
>**Parâmetro(s):** Nome do cliente
>**Ação:** Devolver os dados do cliente desejado no formato Json.
>**Recurso:** CadastroCliente/Cliente/{cliente} 
>http://localhost:22723/WsCliente/webresources/CadastroCliente/Cliente/{cliente}

***GET* /Cliente/ListaCliente**
**Parâmetro(s):** Sem Parâmetros
**Ação:** Devolver um Json com todos os clientes cadastrados no banco de dados até o momento.
>**Recurso:** CadastroCliente/Cliente/ListaCliente 
>http://localhost:22723/WsCliente/webresources/CadastroCliente/Cliente/ListaCliente

***POST** /Cliente/InserirCliente**
**Parâmetro(s):** Nome Cliente, CPF, E-mail, múltiplos telefones e Situação atual (Ativo/Inativo).
**Ação:** Incluir no banco de dados o cliente desejado.
>**Recurso:** CadastroCliente/Cliente/InserirCliente 
>http://localhost:22723/WsCliente/webresources/CadastroCliente/Cliente/InserirCliente

***PUT** /Cliente/AlterarCliente**
**Parâmetro(s):** ID do cliente, Nome Cliente, CPF, E-mail, múltiplos telefones e Situação atual
**Ação:** Editar os dados do Cliente.
>**Recurso:** CadastroCliente/Cliente/AlterarCliente 
>http://localhost:22723/WsCliente/webresources/CadastroCliente/Cliente/AlterarCliente

***DELETE** /Cliente/Excluir/{idCliente}**
**Parâmetro(s):** Id do Cliente
**Ação:** Deletar o cliente informado no método.
>**Recurso:** CadastroCliente/Cliente/Exclui/{Cliente} 
>http://localhost:22723/WsCliente/webresources/CadastroCliente/Cliente/Excluir/{Cliente}

## Banco de dados
Foi utilizado o MSSQL para armazenar os dados da aplicação.
Script de criação do banco e tabela disponibilizado no arquivo Script_Banco_Cadastro_Cliente_MSSQL, dentro da pasta script_banco para criar o banco será necessário abrir o MSSQL, solicitar uma nova consulta e executar o script (F5), lembre-se de alterar o usuário de login do banco de dados no projeto.


## Testes: 
1. Ferramenta utilizada para testes SoapUi 5.4.0.
>https://www.soapui.org/downloads/soapui.html

2. Testando os métodos:

* GET/Cliente/{cliente}
![Testes realizados GET](/screenshots/SoapUI_GET.png?raw=true "Testes realizados GET")


* GET/Cliente/ListaCliente
![Testes realizados GET ListaCliente](/screenshots/SoapUI_GET_ListaCliente.png?raw=true "Testes realizados GET ListaCliente")

* No Banco de dados
![Testes realizados GET ListaCliente](/screenshots/MSSQL_GET_ListaCliente.png?raw=true "Testes realizados GET ListaCliente")


* POST/Cliente/InserirCliente
![Testes realizados POST](/screenshots/SoapUI_POST.png?raw=true "Testes realizados POST")

* No Banco de Dados:
![Testes realizados POST](/screenshots/MSSQL_POST.png?raw=true "Testes realizados POST")


* PUT/Cliente/InserirCliente
![Testes realizados PUT](/screenshots/SoapUI_PUT.png?raw=true "Testes realizados PUT")

* No Banco de Dados:
![Testes realizados PUT](/screenshots/MSSQL_PUT.png?raw=true "Testes realizados PUT")


* PUT/Cliente/AlterarCliente
![Testes realizados PUT](/screenshots/SoapUI_PUT.png?raw=true "Testes realizados PUT")

* No Banco de Dados:
![Testes realizados PUT](/screenshots/MSSQL_PUT.png?raw=true "Testes realizados PUT")


* DELETE/Cliente/Excluir/{idCliente}
![Testes realizados DELETE](/screenshots/SoapUI_DELETE.png?raw=true "Testes realizados DELETE")

* No Banco de Dados:
![Testes realizados DELETE](/screenshots/MSSQL_DELETE.png?raw=true "Testes realizados DELETE")

## Melhorias:
- Validação das Url´s.
- Validações dos dados.
- Tratar exceções de usuário.
- Utilizar de Threads para execução dos serviços.
- Utilizar o Hibernate para comunicação com o banco de dados relacional.
- Criar o front-end ou um aplicativo mobile para integração com a WS.
- Utilizar o maven (repositório para bibliotebas java).
