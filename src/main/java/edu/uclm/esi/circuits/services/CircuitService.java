package edu.uclm.esi.circuits.services;

<<<<<<< Updated upstream
=======
import java.io.InputStream;
>>>>>>> Stashed changes
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.circuits.dao.CircuitDAO;
import edu.uclm.esi.circuits.model.Circuit;

@Service
public class CircuitService {

    @Autowired
    private CircuitDAO circuitDAO;

<<<<<<< Updated upstream
    public String createCircuit(Map<String, Object> body) {
        Map<String, Object> result = new HashMap<>();
        //result.put("mensaje", "Creating a circuit with " + qubits + " qubits");
        return "Hola";
=======
    private ProxyBEUsuarios proxy = ProxyBEUsuarios.get();
 
    public Circuit createCircuit(Map<String, Object> body) {

        // Extraer la matriz del mapa
        List<List<Integer>> table = (List<List<Integer>>) body.get("table");

        // Extraer el número de qubits de salida del mapa
        int outputQubits = (int) body.get("outputQubits");

        Circuit circuit = new Circuit(table, outputQubits);

        return circuit;
>>>>>>> Stashed changes
    }

    public Map<String,Object> generateCode(Circuit circuit, String token) throws Exception {

        String templateCode = this.readFile("ibm.local.txt")
        
        String code = circuit.generateCode(templateCode);
        Map<String,Object> result = new HashMap<>(); 
        
        return result;
        /*
        Con dos de entrada y uno de salida
        Si es 0,0 y salida 1 entonces generas codigo
        aplicar peurta x a ambos qubits, luego mcx
        y se deshace los cambios
<<<<<<< Updated upstream

        */
=======
        */
        
>>>>>>> Stashed changes
    }

    private String readFile(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        return new String(Files.readAllBytes(path));
    }
<<<<<<< Updated upstream
=======
    
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
    
    public class MatrixService {
    
        public int tamanoTabla(Map<String, Object> body) {
            // Extraer la matriz del mapa
            List<List<Integer>> table = (List<List<Integer>>) body.get("table");
    
            // Verificar si la matriz es nula
            if (table == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La tabla es nula.");
            }
    
            // Verificar si la matriz está vacía
            if (table.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La tabla está vacía.");
            }
    
            // Verificar si la primera fila es nula o vacía
            List<Integer> firstRow = table.get(0);
            if (firstRow == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La primera fila de la tabla es nula.");
            }
    
            if (firstRow.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La primera fila de la tabla está vacía.");
            }
    
            // Obtener la longitud de la primera fila
            int rowLength = firstRow.size();
            return rowLength;
        }
    }

    public void checkToken(String token) throws Exception {
        proxy.checkToken(token);
    }
    
    
    
    
>>>>>>> Stashed changes
}