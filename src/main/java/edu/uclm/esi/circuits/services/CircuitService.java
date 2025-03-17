package edu.uclm.esi.circuits.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uclm.esi.circuits.dao.CircuitDAO;
import edu.uclm.esi.circuits.model.Circuit;

@Service
public class CircuitService {

    @Autowired
    private CircuitDAO circuitDAO;

    public String createCircuit(Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        //result.put("mensaje", "Creating a circuit with " + qubits + " qubits");
        return "Hola";
    }

    public Map<String,Object> generateCode(Circuit circuit, String token) {

        String code = circuit.generateCode();
        Map<String,Object> result = new HashMap<>(); 
        
        return result;
        /*
        Con dos de entrada y uno de salida
        Si es 0,0 y salida 1 entonces generas codigo
        aplicar peurta x a ambos qubits, luego mcx
        y se deshace los cambios

        */
    }
}