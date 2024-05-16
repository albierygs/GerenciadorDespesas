package br.uneb.gerenciadordespesas.bancodados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tabelas {

    public static void criar() throws SQLException {
        final String tabelaUsuario = "CREATE TABLE IF NOT EXISTS Usuario (email VARCHAR(70) PRIMARY KEY NOT NULL, senha VARCHAR(50) NOT NULL, nome VARCHAR(100) NOT NULL, valortotal DECIMAL(10, 2), valorpendente DECIMAL(10, 2), valorpago DECIMAL(10, 2));";//sql da tebela de usuário; cria a tabela só se ela ainda não existe

        final String tabelaDespesa = "CREATE TABLE IF NOT EXISTS Despesa (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, nome VARCHAR(50) NOT NULL, preco DECIMAL(10, 2) NOT NULL, dataVencimento DATE NOT NULL, categoria VARCHAR(50), pago BOOLEAN, emailUsuario VARCHAR(70) NOT NULL, FOREIGN KEY (emailUsuario) REFERENCES Usuario(email));";//sql da tabela de despesa; cria a tabela só se ela ainda não existe


        execute(tabelaUsuario);
        execute(tabelaDespesa);
    }

    private static void execute(String sql) throws SQLException {
        Connection conexao = ConexaoBanco.conectar();//faz a conexão com o banco

        //prepara o sql e executa o comando dentro do banco
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.execute();
        conexao.commit();

        conexao.close();//fecha a conexão com o banco
    }
}
