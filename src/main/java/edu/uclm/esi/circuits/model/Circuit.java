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
    

    @Column(length = 10000) // o usa @Lob si el código es largo
    private String code;
    
    @Column
    private String userId;


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
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getUserId() {
    	return userId;
    }
    
    public void setUserId(String userId) {
    	this.userId = userId;
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
    
            // Verifica si hay algún 1 en los bits de salida
            for (int j = inputQubits; j < totalQubits; j++) {
                if (row[j] == 1) {
                    isActive = true;
                    break;
                }
            }
    
            if (!isActive) continue;
    
            // Aplicar X a entradas con valor 0
            for (int j = 0; j < inputQubits; j++) {
                if (row[j] == 0)
                    circuitCode.append("circuit.x(qreg[").append(j).append("])\n");
            }
    
            // Crear lista de controles
            StringBuilder controls = new StringBuilder();
            for (int j = 0; j < inputQubits; j++) {
                if (j > 0) controls.append(", ");
                controls.append("qreg[").append(j).append("]");
            }
    
            // Añadir puertas MCX para cada qubit de salida en 1
            for (int j = inputQubits; j < totalQubits; j++) {
                if (row[j] == 1) {
                    circuitCode.append("circuit.mcx([")
                        .append(controls)
                        .append("], qreg[").append(j).append("])\n");
                }
            }
    
            // Deshacer X
            for (int j = 0; j < inputQubits; j++) {
                if (row[j] == 0)
                    circuitCode.append("circuit.x(qreg[").append(j).append("])\n");
            }
    
            circuitCode.append("\n");
        }
    
        // Sección de medidas
        StringBuilder measures = new StringBuilder();
        for (int i = 0; i < outputQubits; i++) {
            int qubitIndex = inputQubits + i;
            measures.append("circuit.measure(qreg[").append(qubitIndex)
                    .append("], creg[").append(i).append("])\n");
        }
    
        // Sustitución en la plantilla
        template = template.replace("#QUBITS#", String.valueOf(totalQubits));
        template = template.replace("#OUTPUT_QUBITS#", String.valueOf(outputQubits));
        template = template.replace("#CIRCUIT#", circuitCode.toString());
        template = template.replace("#INITIALIZE#", ""); // opcional para futuro
        template = template.replace("#MEASURES#", measures.toString());
    
        return template;
    }
    
    
    
}