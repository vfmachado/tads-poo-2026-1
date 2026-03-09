public class Ex25 {

    public static void main(String[] args) {

        // Criando autores
        Autor autor1 = new Autor("George Orwell");
        Autor autor2 = new Autor("J. R. R. Tolkien");

        // Criando livros associados aos autores
        Livro livro1 = new Livro("1984", autor1);
        Livro livro2 = new Livro("O Senhor dos Anéis", autor2);

        // Criando biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Cadastrando livros na biblioteca (passagem por parâmetro)
        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);

        // Exibindo livros cadastrados
        biblioteca.listarLivros();
    }
}