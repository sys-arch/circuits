package edu.uclm.esi.circuits.services;

import java.util.UUID;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

public class ProxyBEUsuarios {

    private static ProxyBEUsuarios yo;
    private final String url = "http://localhost:8081/tokens/";

    //Constructor privado
    private ProxyBEUsuarios() {}

    //Metodo publico que devuelve una instancia de la clase
    public static ProxyBEUsuarios get() {
        if (yo == null)
            yo = new ProxyBEUsuarios();

        return yo;
    }

    public void checkToken(String token) throws Exception {
        HttpGet httpGet = new HttpGet(url + "validarToken");
        httpGet.setHeader("Authorization", token); // Agregar la cabecera Authorization
    
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpGet)) {
    
            int code = response.getCode();
            if (code != 200) {
                throw new Exception("El servicio solicitado requiere pago");
            }
    
        } catch (Exception e) {
            throw new Exception("No se ha podido validar el token");
        }
    }

    public UUID getUserId(String token) throws Exception {
        HttpGet httpGet = new HttpGet(url + "getUserId");
        httpGet.setHeader("Authorization", token); // Agregar la cabecera Authorization
    
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpGet)) {
    
            int code = response.getCode();
            if (code != 200) {
                throw new Exception("Error al obtener el ID de usuario");
            }

            UUID userId = UUID.fromString(response.getEntity().getContent().toString());

            return userId;
    
        } catch (Exception e) {
            throw new Exception("No se ha podido validar el token");
        }
    } 
}
