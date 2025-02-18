package edu.uclm.esi.circuits.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.uclm.esi.circuits.model.Circuit;

@Service
public class CircuitService {

    public String createCircuit(Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        //result.put("mensaje", "Creating a circuit with " + qubits + " qubits");
        return "Hola";
    }

    public String generateCode(Circuit circuit) {
        return circuit.generateCode();
        /*
        Con dos de entrada y uno de salida
        Si es 0,0 y salida 1 entonces generas codigo
        aplicar peurta x a ambos qubits, luego mcx
        y se deshace los cambios

        */
    }
}