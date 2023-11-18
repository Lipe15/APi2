# Projeto de Gerenciamento de Pacientes
Bem-vindo ao Projeto de Gerenciamento de Pacientes.
Este sistema é responsável por gerenciar informações de pacientes.

## Indice
- <a href="#Visao-Geral">Visão Geral</a>
- <a href="#as-tecnologias-principais-utilizadas-são">As tecnologias principais utilizadas</a>
- <a href="#pré-requisitos">Pré-Requisitos</a>
- <a href="#configuração-do-ambiente">Configuração do Ambiente</a>
- <a href="#USO">Uso</a>


## Visão Geral

A api de Gerenciamento de Pacientes permite o cadastro, atualização, remoção e consulta de informações de pacientes.
### As tecnologias principais utilizadas são:
- [Spring Boot](https://spring.io/projects/spring-boot/)
- [MongoDB](https://www.mongodb.com/pt-br)
- Feign Client.
- [Postman](https://www.postman.com/)

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
      -	Abra o arquivo application.properties no diretório src/main/resources.
      - Ajuste as configurações do MongoDB conforme necessário.
# Uso
##### A API oferece endpoints para manipulação de pacientes e vacinações. Consulte a documentação da API para obter detalhes sobre os endpoints, parâmetros e respostas.

- Endpoint para obter todos os pacientes:
        
      GET http://localhost:8080/api/Pacientes
  
- Get Exemplo de resposta:
        
        {
          "id": "653d254a70793512735a8edf",
          "nome": "Felipe",
          "sobrenome": "Santos",
          "cpf": "40028922",
          "dataNascimento": "2002-09-15",
          "sexo": "Feminino",
          "contato": "(71) 992706607",
          "endereco": {
                  "cep": "42807-027",
                  "uf": "BA",
                  "localidade": "Camaçari",
                  "logradouro": "Rua Abaré",
                  "bairro": "Gleba C"
          }
      

- Endpoint para registrar uma vacinação:
  
      POST http: localhost:8080/api/Pacientes

- Post Exemplo:

      {
      "nome":"Felipe",
      "sobrenome":"Santos",
      "sexo": 5555,
      "cpf":"40028922",
      "dataNascimento":"2002-09-15",
      "contato":"(71) 992706607",
      "cep": "42807027"    
      }

- Endpoint para deletar um paciente por ID:

      Delete http: localhost:8080/api/Pacientes/{ID}

- Delete Exemplo de resposta:

      Status: 204 No Content


- Endpoint para atualizar paciente por ID:

      PUT http: localhost:8080/api/Pacientes/{ID}
         
- PUT exemplo de resposta:

            {
            "nome": "Novo Nome",
            "sobrenome": "Novo Sobrenome",
            "cpf": "Novo CPF",
            "dataNascimento": "yyyy-mm-dd",
            "sexo": "Novo Sexo",
            "contato": "Novo Contato",
            "cep":"Novo CEP"
             }
- Endpoint para Obter paciente por uf:

      GET http: localhost:8080/api/Pacientes/por-uf/{uf}
- Get exemplo de resposta:

       {
       "nome": "Felipe Santos",
       "uf": "BA",
       "bairro": "Gleba C",
       "localidade": "Camaçari",
       "cpf": "40028922",
       "idade": 21
       }

## Pessoas Autoras
- [Felipe Santos](https://github.com/Lipe15)
- [Daylane Silva](https://github.com/daylane)
- [Gilson](https://github.com/gilsongmptj)
- [Matheus Moura](https://github.com/mtcurly)
- [Felipe Olivera](https://github.com/fel1pee)
- [Marcus Vinicius](https://github.com/MarcusViniciusBtt)
