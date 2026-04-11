@web
Feature: Teste Web

@34659
Scenario: Fazer login site Orange HRM
  Given que precise logar no site orange
  When preencher os dados de ususario e senha corretamente
  Then entao devo validar que loguei corretamente