# Projeto Gerenciamento Vendas

Projeto particular para aprendizado. 

Tem finalidade para uma empresa/vendedor ter controle de suas vendas/parcelas e clientes.


# ⚙️ Funcionamento e Funcionalidades
  * Empresa lança uma nova venda, selecionando um cliente, onde caso não exista cadastra um novo, coloca um item de venda e data de pagamento;
  * Página inicial tem acesso a todas as parcelas do respectivo mês selecionado, atualizando de forma assíncona conforme alteração do mês;
  * Botão para dar baixa na parcela, após salva faz toda a regra do pagamento, validando o valor, setando status da parcela para paga e impossibilitando acesso novamente ao botão;
  * API para buscar Clientes;
  * API para buscar estado e município caso precise cadastrar um novo cliente;
  * API para buscar as vendas do mês selecionado na página inicial;

# 🛠 Tecnologias
  O que foi usado no projeto:
  
* Java 11
* Spring MVC
* MariaDB
* Vue.js
* Axios
* Javascript
* Ícones: Font Awesome
* API: IBGE API → API de UFs, API de Municípios/ API Clientes/ API Vendas
* IDEs: Eclipse

Documentação: 
* Astah (Diagrama de Classe);
* Diagrama EER;
* Protótipo: Google Jam
