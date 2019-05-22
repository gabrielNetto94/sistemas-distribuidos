package Lista1_exe3;

import java.io.Serializable;

public class ContaCliente implements Serializable {

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    String descricao;
    float saldo = 0;
}
