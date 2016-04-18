
package br.com.petshop.dao;

/**
 *
 * @author Felipe Jerez
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe que faz a conexão com o banco de dados e demais configurações
public class Conexao {
    
    private Connection con;
    private String url="jdbc:derby://localhost:1527/PI3";
    private String usr="app";
    private String pwd="app";
    
    public Conexao(){
        abrir();
    
    }
    
    //Conexão com o banco de dados e driver necessário para a mesma  
    public Connection abrir(){
        con = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Conexão com o Banco de Dados Estabelecida ");
            con = DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERRO " + cnfe.getMessage());
        } catch (SQLException sqle) {
            System.out.println("ERRO " + sqle.getMessage());
        }

        return con;
    }
    
    //Fecha conexão com o banco de dados
    public void fechar(){
        try {
            if (con != null) {
                con.close();
            }
            System.out.println("Conexão com o Banco de Dados Estabelecida ");

        } catch (SQLException sqle) {
            System.out.println("ERRO " + sqle.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }
    
    
    
}
