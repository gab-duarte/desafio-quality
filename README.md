<h1 align="center"> Desafio Quality </h1>
<p align="justify"> Desafio de Qualidade de Software do Bootcamp Meli. </p>

## Descrição

API desenvolvida para estudo de Qualidade de Software e Testes

- Controller com endpoints para cada requisito do desafio. Endpoint extra para salvar uma Propriedade.
- Os endpoints utilizam o nome de uma Propriedade como Path Variable. Usar a coleção do Postman para testar, caso necessário.

### Postman Collection:
https://www.getpostman.com/collections/f9ed54f2dfb19454c681

Caso achem relevante testar a API pelo Postman

## Requisitos

For building and running the application you need:

- [JDK 11]
- [Maven 3](https://maven.apache.org)


## Units

### US-0001

Para o requisito US-00001 deverá ser executado o teste "shouldReturnTotalM2Propriedade" do arquivo "PropriedadeServiceTest"

### US-0002

Para o requisito US-00002 deverá ser executado o teste "shouldReturnValorPropriedade" do arquivo "PropriedadeServiceTest"

### US-0003

Para o requisito US-00003 deverá ser executado o teste "shouldReturnMaiorComodo" do arquivo "PropriedadeServiceTest"

### US-0004

Para o requisito US-00004 deverá ser executado o teste "shouldReturnTotalM2PorComodo" do arquivo "PropriedadeServiceTest"

### Outros testes de unidade do arquivo "PropriedadeServiceTest": Testam os métodos complementares aos requisitos do desafio

- salvaPropriedade()
- verifyIfPropriedadeExists()
- verifyIfBairroExistsTest()

## Integration

### US-0001

Para o requisito US-00001 deverá ser executado o teste "shouldReturnTotalM2Propriedade" do arquivo "PropriedadeControllerIT"

### US-0002

Para o requisito US-00002 deverá ser executado o teste "shouldReturnValorPropriedade" do arquivo "PropriedadeControllerIT"

### US-0003

Para o requisito US-00003 deverá ser executado o teste "shouldReturnMaiorComodo" do arquivo "PropriedadeControllerIT"

### US-0004

Para o requisito US-00004 deverá ser executado o teste "shoudReturnM2PorComodo" do arquivo "PropriedadeControllerIT"
