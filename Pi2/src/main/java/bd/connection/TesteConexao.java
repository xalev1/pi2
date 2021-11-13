package bd.connection;

import java.sql.SQLException;

/**
 *
 * @author Giba
 */
public class TesteConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      GerenciadorConexao.abrirConexao();
        System.out.println("ABRIU CONEXÃO! STATUS: " + GerenciadorConexao.getStatusConexao());
    }
    
}
