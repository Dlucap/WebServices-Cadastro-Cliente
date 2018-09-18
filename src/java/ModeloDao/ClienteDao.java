package ModeloDao;

import ModeloBeans.ClienteBeans;
import ModeloConection.ConexaoBd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Lucas
 */
public class ClienteDao {

    public ClienteDao() {
    }

    ConexaoBd conex = new ConexaoBd();
    ClienteBeans clienteBeans = new ClienteBeans();
    PreparedStatement stm;

    public boolean inserir(ClienteBeans clienteBeans) {
        Boolean retorno = false;
        conex.conectarBd();
        String sq001 = "INSERT INTO CLIENTE (NOMECLIENTE, CPF, EMAIL, TELEFONEFIXO, TELEFONECELULAR, TELEFONECOMERCIAL, ATIVO) "
                     + "VALUES (?,?,?,?,?,?,?)";
        try {
            stm = conex.con.prepareStatement(sq001);

            stm.setString(1, clienteBeans.getNomeCliente());
            stm.setString(2, clienteBeans.getCpf());
            stm.setString(3, clienteBeans.getEmail());
            stm.setString(4, clienteBeans.getTelefoneFixo());
            stm.setString(5, clienteBeans.getTelefoneCelular());
            stm.setString(6, clienteBeans.getTelefoneComercial());
            stm.setBoolean(7, clienteBeans.getAtivo());
            stm.execute();

        } catch (SQLException ExSql) {
            System.out.println("Erro ao inserir os dados:\n " + ExSql);
        }
        return retorno;
    }

    public ClienteBeans buscar(ClienteBeans clienteBeans) {
        System.out.println("Entrou no buscar, buscando :" + clienteBeans.getNomeCliente());
        String sql002 = "SELECT IDCLIENTE,NOMECLIENTE,CPF,EMAIL,TELEFONEFIXO,TELEFONECELULAR,TELEFONECOMERCIAL,ATIVO FROM CLIENTE WHERE NOMECLIENTE = ?";
        ClienteBeans retorno = null;

        conex.conectarBd();
        try {
            stm = conex.con.prepareStatement(sql002);
            stm.setString(1, clienteBeans.getNomeCliente());
            ResultSet res = stm.executeQuery();

            if (res.next()) {

                retorno = new ClienteBeans();

                retorno.setIdCliente(res.getInt("IDCLIENTE"));
                retorno.setNomeCliente(clienteBeans.getNomeCliente());
                retorno.setCpf(res.getString("CPF"));
                retorno.setEmail(res.getString("EMAIL"));
                retorno.setTelefoneFixo(res.getString("TELEFONEFIXO"));
                retorno.setTelefoneCelular(res.getString("TELEFONECELULAR"));
                retorno.setTelefoneComercial(res.getString("TELEFONECOMERCIAL"));
                retorno.setAtivo(res.getBoolean("ATIVO"));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar o ClienteBeansiente: " + ex.getMessage());
        }
        return retorno;
    }

    public boolean Atualizar(ClienteBeans clienteBeans) {

        Boolean retorno = false;
        conex.conectarBd();
        String sql003 = "UPDATE CLIENTE SET NOMECLIENTE =?, CPF =?, EMAIL =?, TELEFONEFIXO =?,TELEFONECELULAR =?,TELEFONECOMERCIAL =?, ATIVO =? WHERE IDCLIENTE =?";

        try {
            stm = conex.con.prepareStatement(sql003);

            
            stm.setString(1, clienteBeans.getNomeCliente());
            stm.setString(2, clienteBeans.getCpf());
            stm.setString(3, clienteBeans.getEmail());
            stm.setString(4, clienteBeans.getTelefoneFixo());
            stm.setString(5, clienteBeans.getTelefoneCelular());
            stm.setString(6, clienteBeans.getTelefoneComercial());
            stm.setBoolean(7, clienteBeans.getAtivo());
            stm.setInt(8, clienteBeans.getIdCliente());
            if (stm.executeUpdate() > 0) {
                retorno = true;
            }
            System.out.println("Dados atualizados com sucesso!!!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar os dados! \nErro: " + ex.getMessage()+"\n "+ex.getCause()
            +"\n"+ex.fillInStackTrace()+"\n"+ex.getSQLState()+"\n"+ex.getLocalizedMessage()+"\n"+ex.getStackTrace());
            retorno = false; 
        } finally {
            conex.DesconectarBd();
        }
        return retorno;
    } 

    public boolean Excluir(ClienteBeans clienteBeans) {
        String sql004 = "DELETE FROM CLIENTE WHERE IDCLIENTE = ? ";
        Boolean retorno = false;

        try {
            stm = conex.con.prepareStatement(sql004);
            stm.setInt(1, clienteBeans.getIdCliente());
            stm.execute();

//            if (stm.executeUpdate() > 0) {
//                retorno = true;
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        }
        return retorno;
    }

    public List<ClienteBeans> listar() {

        List<ClienteBeans> retorno = new ArrayList<>();

        String sql005 = "SELECT IDCLIENTE,NOMECLIENTE,CPF,EMAIL,TELEFONEFIXO,TELEFONECELULAR,TELEFONECOMERCIAL,ATIVO FROM CLIENTE";
        conex.conectarBd();
        conex.executaSql(sql005);
        try {
            //conex.rs.first();
            while (conex.rs.next()) {
                ClienteBeans clienteBeans = new ClienteBeans();

                clienteBeans.setIdCliente(conex.rs.getInt("IDCLIENTE"));
                clienteBeans.setNomeCliente(conex.rs.getString("NOMECLIENTE"));
                clienteBeans.setCpf(conex.rs.getString("CPF"));
                clienteBeans.setEmail(conex.rs.getString("EMAIL"));
                clienteBeans.setTelefoneFixo(conex.rs.getString("TELEFONEFIXO"));
                clienteBeans.setTelefoneCelular(conex.rs.getString("TELEFONECELULAR"));
                clienteBeans.setTelefoneComercial(conex.rs.getString("TELEFONECOMERCIAL"));
                clienteBeans.setAtivo(conex.rs.getBoolean("ATIVO"));
                retorno.add(clienteBeans);
            }
        } catch (SQLException ex) {
            System.out.println("ModeloDao.DaoWsPaciente.listar()   " + ex);
        }
        return retorno;
    }

    public ClienteBeans buscarCodCliente(ClienteBeans ClienteBeans) {

        String sql = "SELECT * FROM PACIENTE WHERE NOMEPACIENTE =?";
        ClienteBeans retorno = null;

        conex.conectarBd();
        try {
            stm = conex.con.prepareStatement(sql);
            stm.setInt(1, ClienteBeans.getIdCliente());
            ResultSet res = stm.executeQuery();

            if (res.next()) {

                retorno = new ClienteBeans();

                retorno.setIdCliente(res.getInt("IDCLIENTE"));
                retorno.setNomeCliente(res.getString("NOMECLIENTE"));
                retorno.setCpf(res.getString("CPF"));
                retorno.setEmail(res.getString("EMAIL"));
                retorno.setTelefoneFixo(res.getString("TELEFONEFIXO"));
                retorno.setTelefoneCelular(res.getString("TELEFONECELULAR"));
                retorno.setTelefoneComercial(res.getString("TELEFONECOMERCIAL"));
                retorno.setAtivo(res.getBoolean("ATIVO"));

            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar o ClienteBeansiente: " + ex.getMessage());
        }
        return retorno;
    }
}
