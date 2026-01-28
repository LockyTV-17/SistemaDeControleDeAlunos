
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String nome;
        String email;
        int opcao = 0;
        AlunoDAO alunoDAO = new AlunoDAO();

        while (opcao != 5) {
            System.out.println("\n--- SISTEMA DE ALUNOS ---");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Buscar Aluno");
            System.out.println("3 - Atualizar E-mail");
            System.out.println("4 - Deletar Aluno");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o Nome DO aluno: ");
                    nome = sc.nextLine();
                    System.out.print("Digite o Email do Aluno: ");
                    email = sc.nextLine();
                    alunoDAO.createAluno(nome, email);
                }
                case 2 -> {
                    System.out.print("Digite o Nome DO aluno: ");
                    nome = sc.nextLine();
                    System.out.print("Digite o Email do Aluno: ");
                    email = sc.nextLine();
                    alunoDAO.selectAluno(nome, email);
                }
                case 3 -> {
                    String novoEmail;
                    System.out.print("Digite o Nome DO aluno: ");
                    nome = sc.nextLine();
                    System.out.print("Digite o Email do Aluno: ");
                    email = sc.nextLine();
                    System.out.print("Digite o novo Email do Aluno: ");
                    novoEmail = sc.nextLine();
                    alunoDAO.updateAluno(nome, email, novoEmail);
                }
                case 4 -> {
                    System.out.print("Digite o Nome DO aluno: ");
                    nome = sc.nextLine();
                    System.out.print("Digite o Email do Aluno: ");
                    email = sc.nextLine();
                    alunoDAO.deleteAluno(nome, email);
                }
                case 5 ->
                    System.out.println("Saindo");
                default ->
                    System.out.println("Opção invalida");
            }

        }
        sc.close();
    }
}
