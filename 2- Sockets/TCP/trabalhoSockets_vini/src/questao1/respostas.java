package questao1;
public class respostas {
}
/*
 Analise os exemplos dados em aula de Sockets TCP, altere os mesmos, e responda as seguintes perguntas:

1.	O que acontece quando um cliente tenta se conectar a um servidor inativo/inexistente?

Gera um erro ou exceção no try catch.


2.	As operações send e receive são bloqueantes ou não? Se sim, por quê?

Sim, as operações send e receive são bloqueantes. Porque quando feita a instrução read(receive) pelo servidor, 
o mesmo fica esperando uma mensagem do cliente, e acontece o mesmo quando o read é feito pelo cliente, 
porém esperando uma mensagem do servidor.


3.	Os timeouts são aplicados por default em operações connect, send e receive?

É possível alterá-los (ou defini-los, caso não sejam utilizados)?
Sim, os timeouts são aplicados por padrão nas operações connect, send e receive, podendo também ser alterado para um 
tempo de escolha do programador.


4.	O servidor, ao chamar o método accept para aguardar conexões, fica esperando para sempre ou existe algum timeout?
É possível definir/modificar isso?

Sim, é possível definir um timeout através do método server.setSoTimeout(tempo); Cujo parâmetro “tempo” é em milissegundos.
*/