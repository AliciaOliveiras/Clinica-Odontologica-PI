package com.example.Clinica.Odontologica.model;

import com.example.Clinica.Odontologica.dto.DentistaDto;
import com.example.Clinica.Odontologica.dto.PacienteDto;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Consulta")
public class ConsultaModel {

    @Id
    @SequenceGenerator(name = "consulta_sequence",sequenceName = "consulta_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "consulta_sequence")
    private Long id;
    private Date dataConsulta;
    private DentistaDto dentista;
    private PacienteDto paciente;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente1;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "dentista_id")
    private DentistaModel dentista1;

    public ConsultaModel() {
    }

    public ConsultaModel(Date dataConsulta, DentistaDto dentista, PacienteDto paciente) {
        this.dataConsulta = dataConsulta;
        this.dentista = dentista;
        this.paciente = paciente;
    }

    public ConsultaModel(Long id, Date dataConsulta, DentistaDto dentista, PacienteDto paciente) {
        this.id = id;
        this.dataConsulta = dataConsulta;
        this.dentista = dentista;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public DentistaDto getDentista() {
        return dentista;
    }

    public void setDentista(DentistaDto dentista) {
        this.dentista = dentista;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "ConsultaModel{" +
                "id=" + id +
                ", dataConsulta=" + dataConsulta +
                ", dentista=" + dentista +
                ", paciente=" + paciente +
                '}';
    }
}
