package edu.uclm.esi.circuits.model;

import java.util.List;
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
    private List<List<Integer>> table;

    @Column
    private String name;

    @Column
    private int outputQubits;

    public Circuit(List<List<Integer>> table, int outputQubits) {
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

    public List<List<Integer>> getTable() {
    return table;
}

    public void setTable(List<List<Integer>> table) {
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
    
    public int[][] getTableAsArray() {
        int[][] array = new int[table.size()][];
        for (int i = 0; i < table.size(); i++) {
            List<Integer> row = table.get(i);
            array[i] = row.stream().mapToInt(Integer::intValue).toArray();
        }
        return array;
    }

    public String generateCode(String template) {
        int[][] matrix = getTableAsArray();
        int totalQubits = matrix[0].length;
        int outputQubits = this.outputQubits;
        int inputQubits = totalQubits - outputQubits;
    
        StringBuilder circuitCode = new StringBuilder();
    
        for (int[] row : matrix) {
            boolean isActive = false;
    
            // Ver si hay algún 1 en los bits de salida
            for (int j = inputQubits; j < totalQubits; j++) {
                if (row[j] == 1) {
                    isActive = true;
                    break;
                }
            }
    
            if (!isActive) continue;
    
            // Aplicar X a entradas con 0
            for (int j = 0; j < inputQubits; j++) {
                if (row[j] == 0)
                    circuitCode.append("circuit.x(q[").append(j).append("])\n");
            }
    
            // Aplicar MCX para cada bit de salida en 1
            StringBuilder controls = new StringBuilder();
            for (int j = 0; j < inputQubits; j++) {
                controls.append("q[").append(j).append("], ");
            }
    
            for (int j = inputQubits; j < totalQubits; j++) {
                if (row[j] == 1) {
                    circuitCode.append("circuit.mcx([").append(controls.toString()).append("], q[").append(j).append("])\n");
                }
            }
    
            // Deshacer las X
            for (int j = 0; j < inputQubits; j++) {
                if (row[j] == 0)
                    circuitCode.append("circuit.x(q[").append(j).append("])\n");
            }
    
            circuitCode.append("\n");
        }
    
        template = template.replace("#QUBITS#", String.valueOf(totalQubits));
        template = template.replace("#OUTPUT_QUBITS#", String.valueOf(outputQubits));
        template = template.replace("#CIRCUIT#", circuitCode.toString());
        template = template.replace("#INITIALIZE#", "");
        template = template.replace("#MEASURES#", "");
    
        return template;
    }
    
    
}