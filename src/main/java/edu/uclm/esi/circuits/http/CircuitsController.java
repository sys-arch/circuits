package edu.uclm.esi.circuits.http;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
/* 
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
    public Map<String,Object> generateCode(HttpServletRequest request, @RequestParam(required=false) String name, @RequestBody Circuit circuit) {

       if(name != null) {
           circuit.setName(name);
       }

       String token = request.getHeader("Authorization");
       if(token == null) {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se ha proporcionado un token de autenticación");
       }

       try {
        return this.service.generateCode(circuit, token);
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "No hay suficientes creditos para generar el circuito");
       }

    
    }
       */
    @PostMapping("/generate")
    public Map<String, Object> generate(
            @RequestBody Circuit circuit,
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "credits", required = false, defaultValue = "false") boolean credits) {

        try {
            return this.service.generateCode(circuit, token, credits);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, e.getMessage());
        }
    }
    
    @PostMapping("/savecode")
    public void saveCode(@RequestBody Circuit circuit) {
        service.saveCode(circuit); 
    }
    
    @GetMapping("/my-circuits")
    public List<Circuit> getMyCircuits(@RequestHeader("Authorization") String token) throws Exception {
    	System.out.println("Token recibido: " + token);
        return service.getMyCircuits(token);
    }


    
}