@api
Feature: Teste API Servest

@235467
Scenario: CT0001_Criar Usuario Servest
  When preencher os dados e fazer a chamada da api
  Then devo validar a criação do usuario corretamente