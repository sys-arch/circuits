package edu.uclm.esi.circuits.model;

public class Circuit {
    private int[][] table;
    private int outputQubits;

    public Circuit(int[][] table, int outputQubits) {
        this.table = table;
        this.outputQubits = outputQubits;
    }

    public Circuit() {
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