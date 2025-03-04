package edu.uclm.esi.circuits.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.circuits.model.Circuit;
import edu.uclm.esi.circuits.services.CircuitService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/circuits")
public class CircuitsController {

    @Autowired
    private CircuitService service;

    @PostMapping("/createCircuit")
    public String createCircuit(@RequestBody Map<String, Object> body) {
        if (!body.containsKey("table") || !body.containsKey("outputQubits") || !body.containsKey("qubits")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La petición debe contener los campos table, qubits y outputQubits");
        }

        int qubits = (int) body.get("qubits");
        if (qubits < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "El numero de qubits debe ser mayor que 0");
        }
        
        return this.service.createCircuit(body);
    }

    @PostMapping("/generateCode")
    public Map<String,Object> generateCode(@RequestParam(required=false) String name, @RequestBody Circuit circuit) {

       if(name != null) {
           circuit.setName(name);
       }
        
        return this.service.generateCode(circuit);
    }
}