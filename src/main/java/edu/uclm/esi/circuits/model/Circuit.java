package edu.uclm.esi.circuits.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Circuit {
    @Id @GeneratedValue
    private UUID id;

    @Transient
    private int[][] table;

    @Column
    private int outputQubits;

    public Circuit(int[][] table, int outputQubits) {
        this.table = table;
        this.outputQubits = outputQubits;
    }

    public Circuit() {
    }

    public UUID getId() {
        return id;
    }

    public void setUUID(UUID id) {
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

    public String generateCode() {
        return "Hola";
    }
}