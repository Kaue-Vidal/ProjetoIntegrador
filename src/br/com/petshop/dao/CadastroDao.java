
package br.com.petshop.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.petshop.model.Cadastro;

/**
 *
 * @author Felipe Jerez
 */
public class CadastroDao {
    
    //comando para executar o select
    private PreparedStatement select;
    // comando para executar, delete, insert e update
    private PreparedStatement update;
    //comando que captura as informações encontradas dos comandos acima
    private ResultSet rs;
    //comando que define a identificação de onde está conectado o banco de dados
    private final Conexao con;
    
    public CadastroDao(){
        //Inicializa o método para abertura do banco de dados
        con = new Conexao();
    }
    
    //Método de inserção no banco de dados
    public int cadastrar(String nome,String cpf, String rg, String dataNascimento, String email,
                            String endereco, String cidade, String estado, String cep,
                            String telefone){
        int res = 0;
        
        try{
            if(con.getCon()==null) //Caso conexão não esteja ativa, abre a conexão com o banco
                con.abrir();
        
            String sql = "INSERT INTO TB_CADASTRO (nome, cpf, rg, dataNascimento, email, endereco, "
                        + " cidade, estado, cep, telefone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            //Identifica a conexão e faz o prepareStatement
            update = con.getCon().prepareStatement(sql);
            update.setString(1, nome);
            update.setString(2, cpf);
            update.setString(3, rg);
            update.setString(4, dataNascimento);
            update.setString(5, email);
            update.setString(6, endereco);
            update.setString(7, cidade);
            update.setString(8, estado);
            update.setString(9, cep);
            update.setString(10, telefone);
            
            //Comando que verifica se houve algum erro na entrada de dados acima
            res = update.executeUpdate();
            
            update.close();
            con.fechar();
            
        } catch (SQLException sqle) {
            System.out.println("ERRO " + sqle.getMessage());
        }
        
        return res;
    
    }
    
    //Método que mostra a lista de cadastros
    public ArrayList<Cadastro>listaTodosCadastros(){
        
        //Caso conexão não esteja ativa, abre a conexão com o banco
        if(con.getCon()==null)
            con.abrir();
        
        ArrayList<Cadastro> cadastros = new ArrayList<Cadastro>();
        
                
        try{
            select = con.getCon().prepareStatement("SELECT * FROM TB_CADASTRO");
            rs = select.executeQuery();
            while(rs.next()){
                Cadastro c = new Cadastro();
                c.setId(rs.getString("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setRg(rs.getString("rg"));
                c.setDataNascimento(rs.getString("dataNascimento"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setCep(rs.getString("cep"));
                c.setTelefone(rs.getString("telefone"));
                                        
                
                cadastros.add(c);
            }
            
        }catch(SQLException sqle){
            System.out.println("ERRO ao listar todos os cadastros " + sqle.getMessage());
        
        }
        
        return cadastros;
    
    }

    public Cadastro selecionaCadastro(String id){
        if(con.getCon()==null)
            con.abrir();
        
        Cadastro c = null;
        String sql = "SELECT * FROM TB_CADASTRO WHERE id = (?)";
        try{
            select = con.getCon().prepareStatement(sql);
            select.setString(1, id);
            rs = select.executeQuery();
            while(rs.next()){
                c = new Cadastro();
                c.setId (rs.getString("id")); 
                c.setNome(rs.getString("nome"));                
                c.setCpf(rs.getString("cpf"));
                c.setRg(rs.getString("rg"));
                c.setDataNascimento(rs.getString("dataNascimento"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                c.setCep(rs.getString("cep"));
                c.setTelefone(rs.getString("telefone"));
            }
            select.close();
            con.fechar();
                    
        }catch(SQLException sqle){
            System.out.println("ERRO ao selecionar cadastro"+ sqle.getMessage());
        }
        
        return c;
    
    }

    public int atualizarCadastro(String id, String nome, String cpf, String rg, String dataNascimento, String email,
                            String endereco, String cidade, String estado, String cep,
                            String telefone) {
        if(con.getCon()==null)
            con.abrir();
        
        int res = 0;
        String sql = "UPDATE TB_CADASTRO SET nome=(?), cpf=(?), rg=(?), dataNascimento=(?), email=(?), endereco=(?)"
                + ", cidade=(?) , estado=(?), cep=(?), telefone=(?) WHERE id=(?) ";
        try{
            update = con.getCon().prepareStatement(sql);
            update.setString(1, nome);
            update.setString(2, cpf);
            update.setString(3, rg);
            update.setString(4, dataNascimento);
            update.setString(5, email);
            update.setString(6, endereco);
            update.setString(7, cidade);
            update.setString(8, estado);
            update.setString(9, cep);
            update.setString(10, telefone);
            update.setString(11, id);
            
            res = update.executeUpdate();
            
            update.close();
            con.fechar();
            
        }catch(SQLException sqle){
            System.out.println("ERRO ao atualizar cadastro: "+sqle.getMessage());
        
        }
        
        return res;
    }

    public int excluirCadastro(String id) {
        
        if(con.getCon()==null)
            con.abrir();
        
        int res = 0;
        String sql = "DELETE FROM TB_CADASTRO WHERE id=(?)";
        try{
            update = con.getCon().prepareStatement(sql);
            update.setString(1, id);
            
            res = update.executeUpdate();
            
            update.close();
            con.fechar();
            
        }catch(SQLException sqle){
            System.out.println("ERRO ao excluir cadastro: "+sqle.getMessage());
        
        }
        
        return res;
    }

}

