package siscom;

import siscom.util.JpaUtil;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Iniciando teste de conexão...");
            JpaUtil.getEntityManagerFactory();
            System.out.println("Conexão com o banco realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar no banco:");
            e.printStackTrace();
        } finally {
            JpaUtil.close();
            System.out.println("EntityManagerFactory fechada.");
        }
    }
}