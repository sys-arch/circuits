package edu.uclm.esi.circuits.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Circuit {
    @Id
    @Column(length=36)
    private String id = UUID.randomUUID().toString();

    @Transient
    private int[][] table;

    @Column
    private String name;

    @Column
    private int outputQubits;

    public Circuit(int[][] table, int outputQubits) {
        this.table = table;
        this.outputQubits = outputQubits;
    }

    public Circuit() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int getOutputQubits() {
        return outputQubits;
    }

    public void setOutputQubits(int outputQubits) {
        this.outputQubits = outputQubits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String generateCode() {
        return "Hola";
    }
}