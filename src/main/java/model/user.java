package model;

public class user {
    private String nome;
    private String senha;
    private String email;

    @Override
    public String toString() {
        return "user{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public user(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;

    }


}
