
import java.net.InetAddress;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<Produto> produtos = new ArrayList();
        Produto p = new Produto(1, "asdas", 2);

        String[] listaProduto = {
            "Queijo",
            "Presunto",
            "Arroz",
            "Feijão",
            "Batato",
            "Tomate",};
    }

}

class Produto {

    int idCliente;
    String nomeProduto;
    int qtdVendas;
    float precoProduto;
    InetAddress endereco;
    int porta;

    public Produto(int idCliente, String nomeProduto, int qtdVendas) {
        this.idCliente = idCliente;
        this.nomeProduto = nomeProduto;
        this.qtdVendas = qtdVendas;

    }

    public int getIdcliente() {
        return this.idCliente;
    }

    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    public int getPorta() {
        return this.porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
