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
    private int qubits;

    @Column
    private String name;

    @Column
    private int outputQubits;

    public Circuit(int[][] table, int outputQubits) {
        this.table = table;
        this.outputQubits = outputQubits;
        this.qubits = table.get(0).size();
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

<<<<<<< Updated upstream
    public void setTable(int[][] table) {
        this.table = table;
=======
    public int getQubits() {
        return qubits;
    }

    public void setQubits(int qubits) {
        this.qubits = qubits;
>>>>>>> Stashed changes
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

    public String generateCode(String template) {
        template = template.replace("#QUBITS#", "" + this.table[0].length);
        template = template.replace("#OUTPUT QUBITS#", "" + this.outputQubits);
        return "Hola";
    }
}