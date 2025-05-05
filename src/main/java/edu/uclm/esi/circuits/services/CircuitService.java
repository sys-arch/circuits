package edu.uclm.esi.circuits.services;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uclm.esi.circuits.dao.CircuitDAO;
import edu.uclm.esi.circuits.model.Circuit;

@Service
public class CircuitService {

    @Autowired
    private CircuitDAO circuitDAO;
/* 
    public String createCircuit(Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        //result.put("mensaje", "Creating a circuit with " + qubits + " qubits");
        return "Hola";
    }

    public Map<String,Object> generateCode(Circuit circuit, String token) throws Exception {

        String templateCode = this.readFile("ibm.local.txt");
        
        String code = circuit.generateCode(templateCode);
        Map<String,Object> result = new HashMap<>(); 
        
        return result;
        /*
        Con dos de entrada y uno de salida
        Si es 0,0 y salida 1 entonces generas codigo
        aplicar peurta x a ambos qubits, luego mcx
        y se deshace los cambios

        
    }

    private String readFile(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        return new String(Files.readAllBytes(path));
    }
    */
    public Map<String, Object> generateCode(Circuit circuit, String token, Boolean credits) throws Exception {
        List<List<Integer>> table = circuit.getTable();
        int totalQubits = table.get(0).size(); // número de columnas
        int outputQubits = circuit.getOutputQubits();
        int inputQubits = totalQubits - outputQubits;
    
        if (totalQubits > 6) {
            if (!credits) {
                throw new Exception("El usuario no tiene crédito suficiente para circuitos de más de 6 cúbits.");
            }
        }
        
        
    
        Map<String, Object> result = new HashMap<>();
        result.put("inputQubits", inputQubits);
        result.put("outputQubits", outputQubits);
        result.put("totalQubits", totalQubits);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("ibm.local.txt");
        if (inputStream == null) throw new Exception("No se pudo cargar el archivo ibm.local.txt desde resources");
        String templateCode = new String(inputStream.readAllBytes());
        String code = circuit.generateCode(templateCode);
        result.put("code", code);
        System.out.println(">>> Código generado:\n" + code);


    
        return result;
    }

    
    public Circuit saveCode(Circuit circuit) {
        return circuitDAO.save(circuit);
    }
    
    public List<Circuit> getMyCircuits(String token) throws Exception {
        ProxyBEUsuarios proxy = ProxyBEUsuarios.get();
        String userId = proxy.getUserId(token);
        return circuitDAO.findByUserId(userId);
    }
    
    
}