package br.com.gbraghim.eventmanager.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="event")
public class Event {
    @Id
    private int id;
    @Column
    private String titulo;
    @Column
    private String descricao;
    @Column
    private Date data;
    @Column
    private int vagasTotais;
    @Column
    private boolean disponivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getVagasTotais() {
        return vagasTotais;
    }

    public void setVagasTotais(int vagasTotais) {
        this.vagasTotais = vagasTotais;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (getId() != event.getId()) return false;
        if (getVagasTotais() != event.getVagasTotais()) return false;
        if (getDisponivel() != event.getDisponivel()) return false;
        if (!getTitulo().equals(event.getTitulo())) return false;
        if (!getDescricao().equals(event.getDescricao())) return false;
        return getData().equals(event.getData());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitulo().hashCode();
        result = 31 * result + getDescricao().hashCode();
        result = 31 * result + getData().hashCode();
        result = 31 * result + getVagasTotais();
        result = 31 * result + (getDisponivel() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", vagasTotais=" + vagasTotais +
                ", disponivel=" + disponivel +
                '}';
    }
}
