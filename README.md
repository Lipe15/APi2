# Projeto de Gerenciamento de Pacientes
Bem-vindo ao Projeto de Gerenciamento de Pacientes.
Este sistema é responsável por gerenciar informações de pacientes.

## Indice
- <a href="#Visao-Geral">Visão Geral</a>
- <a href="#Tecnologias">As tecnologias principais utilizadas</a>
- <a href="#Requisitos">Pré-Requisitos</a>
- <a href="#Ambiente">Configuração do Ambiente</a>


## Visão Geral

A api de Gerenciamento de Pacientes permite o cadastro, atualização, remoção e consulta de informações de pacientes, bem como o registro de vacinações associadas a esses pacientes.
## As tecnologias principais utilizadas são:
- [Spring Boot](https://spring.io/projects/spring-boot/)
- [MongoDB](https://www.mongodb.com/pt-br)
- Feign Client.

## Configuração:
Siga estas instruções para configurar o ambiente de desenvolvimento e executar a aplicação.
## Pré-requisitos
- JDK 11 ou superior
- Maven
- MongoDB
# Configuração do Ambiente
1.	Clone o repositório:
      git clone https://github.com/Lipe15/APi2
2.	Acesse o diretório do projeto:
      cd API2

3.	Configure as propriedades do aplicativo:
      •	Abra o arquivo application.properties no diretório src/main/resources.
      •	Ajuste as configurações do MongoDB conforme necessário.
# Uso
##### A API oferece endpoints para manipulação de pacientes e vacinações. Consulte a documentação da API para obter detalhes sobre os endpoints, parâmetros e respostas.
      
- Exemplo de uso:


- Endpoint para obter todos os pacientes:

      GET http://localhost:8080/api/Pacientes

- Endpoint para registrar uma vacinação:
  
      POST http://localhost:8080/api/Pacientes

- Endpoint para deletar um paciente por ID:

      Delete http: //localhost:8080/api/Pacientes/{ID}

- Endpoint para atualizar paciente por ID:

      PUT http: //localhost:8080/api/Pacientes/{ID}

## Pessoas Autoras
- [Felipe Santos](https://github.com/Lipe15)
- [Daylane Silva](https://github.com/daylane)
- [Gilson](https://github.com/gilsongmptj)
- [Matheus Moura](https://github.com/mtcurly)
- [Felipe Olivera](https://github.com/fel1pee)
- [Marcus Vinicius]()
