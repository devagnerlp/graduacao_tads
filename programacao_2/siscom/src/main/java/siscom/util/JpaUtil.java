package siscom.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    // entityManagerFactory é a fábrica de conexões com o banco de dados, que é criada uma única vez e compartilhada entre todas as partes da aplicação.

    private static EntityManagerFactory buildEntityManagerFactory() { 
        Properties props = new Properties(); // objeto para armazenar as propriedades de configuração do banco de dados, como URL, usuário e senha.

        try (FileInputStream fis = new FileInputStream("db.properties")) { // abre o arquivo db.properties para leitura
            props.load(fis); // carrega as propriedades do arquivo db.properties para o objeto props
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo db.properties", e);
        }

        Map<String, Object> settings = new HashMap<>();
        settings.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver"); //indica o bd que vai ser usado
        settings.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));//indica o caminho do bd
        settings.put("jakarta.persistence.jdbc.user", props.getProperty("db.user"));// indica o usuario do bd
        settings.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));// indica a senha
        settings.put("hibernate.hbm2ddl.auto", "update");// cria ou atualiza as tabelas do bd de acordo com as entidades
        settings.put("hibernate.show_sql", "true"); //mostra no console o sql gerado pelo hibernate
        settings.put("hibernate.format_sql", "true");// formata o sql gerado pelo hibernate

        return Persistence.createEntityManagerFactory("siscom-pu", settings);
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void close() { // fecha a conexão com o banco
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}