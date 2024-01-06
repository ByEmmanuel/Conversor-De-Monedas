package conversor.conversordemonedas;

import java.io.IOException;

import org.json.JSONObject;


public class ApiCalls {

    
    // metodo para La API Exchangue Rate API
    public String connection(String selectedOption1, String selectedOption2, String cantidad){
        Exchange_Rate_API apicall = new Exchange_Rate_API();
        String validacion = apicall.getTasaDeConversion(selectedOption1, selectedOption2, cantidad);
        

        System.out.println("Clase DAO EN OPERACION");
        if (validacion != null) {
            
            System.out.println("DAO Funcionando");
        } else {
            
            System.out.println("Algo salio mal en la clase dao");
            throw new RuntimeException();
        }

        // //metodo para free currency API
        // public static String API() throws IOException {
        // Freecurrencyapi apicall = new Freecurrencyapi();
        // return apicall.main();
        // }

        // //metodo para free currency API (PRUEBA)
        // public static String API() throws IOException {
        // PruebaFreeCurrencyApi apicall = new PruebaFreeCurrencyApi();
        // return apicall.ConversorAPI();
        // }

        return validacion;
    } 
}
