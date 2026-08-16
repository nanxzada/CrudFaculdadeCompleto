public class Aluno {
    private int id;
    private String nome;
    private int curso;
    private double valor;

    public Aluno() {
    }

    public Aluno(int id, String nome, int curso, double valor) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
