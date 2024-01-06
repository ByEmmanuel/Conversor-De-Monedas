package conversor.conversordemonedas;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.Scanner;

import org.json.JSONObject;

public class Exchange_Rate_API {
    
  
    Exchange_Rate_API(){
        getTasaDeConversion(selectedoption1, selectedoption2, cantidadDeDinero);
    }


    Controller botonesClass = new Controller();

    String tasaDeConversionStr;
    String convertedAmountStr;
    
    private String selectedoption1;
    private String selectedoption2;
    String cantidadDeDinero;
    String monedasMundiales;
    
    public String getTasaDeConversion(String selectedOption1String, String selectedOption2String, String cantidadDeDinero) {
        
        this.selectedoption1 = selectedOption1String;
        this.selectedoption2 = selectedOption2String;
        this.cantidadDeDinero = botonesClass.getCantidad();

       
        String apiKey = "1dc4c1849cbb6c3e417665f8";
        String url_str = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + selectedoption1 + "/" + selectedoption2 + "/"+ cantidadDeDinero;
        String a = url_str +apiKey + "/codes";
        String resultado;

        if (selectedoption1 != null && selectedoption2 != null && cantidadDeDinero != null) {
         System.out.println("LA primera opcion es: "+selectedoption1+" y la 2 es ; "+selectedoption2
         + " Y la cantidad de dinero es; " + cantidadDeDinero);   
        }else{
            System.out.println("Error en la clase de la API");
        }
            
        
        HttpURLConnection request;

        try {
            URL url = new URL(url_str);
            request = (HttpURLConnection) url.openConnection();
            request.connect();

            int codigoDeRespuesta = request.getResponseCode();

            if (codigoDeRespuesta != 200) {
                resultado = "Error en el código de respuesta: " + codigoDeRespuesta;
            } else {
                StringBuilder infoString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    infoString.append(scanner.nextLine());
                }
                scanner.close();

                //Codigo aparte
                // Crear un objeto JSONObject a partir de la respuesta de la API
        JSONObject jsonObject = new JSONObject(infoString.toString());

        // // Obtener el valor de las monedas
        // this.monedasMundiales = jsonObject.getString("supported_codes");
        
        //         System.out.println(monedasMundiales);

        // Obtener el valor de la tasa de conversión como BigDecimal
        BigDecimal conversionRate = jsonObject.getBigDecimal("conversion_rate");

        // Obtener el resultado de la conversión como BigDecimal (si es un número)
        BigDecimal convertedAmount = jsonObject.optBigDecimal("conversion_result", BigDecimal.ZERO);
        this.convertedAmountStr = convertedAmount.toString();
                System.out.println(convertedAmountStr);

               
                
                return convertedAmountStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = "Error al obtener el valor de la API: " + e.getMessage();
        }
        return convertedAmountStr;

        
    }
}