package edu.uclm.esi.circuits.services;

import java.util.HashMap;
import java.util.Map;

public class CircuitService {

    public Map<String, Object> createCircuit(int qubits) {
        Map<String, Object> result = new HashMap<>();
        result.put("mensaje", "Creating a circuit with " + qubits + " qubits");
        return result;
    }
}