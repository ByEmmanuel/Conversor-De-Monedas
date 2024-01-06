package conversor.conversordemonedas;

import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Freecurrencyapi {

    public static String main() {
        String resultado = "";
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_GTuIFnHhZxuMmgMfbMlXwTH0xFnnRqYAujCopypl";
        HttpURLConnection connection;
        
        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
    
            int codigoDeRespusta = connection.getResponseCode();
    
            if (codigoDeRespusta != 200) {
                resultado = "Error en el código de respuesta: " + codigoDeRespusta;
            } else {
                StringBuilder infoString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
    
                while (scanner.hasNext()) {
                    infoString.append(scanner.nextLine());
                }
                scanner.close();
    
                JSONObject jsonOBJ = new JSONObject(infoString.toString());
                JSONObject jsonRates = jsonOBJ.getJSONObject("data");
                double currencies = jsonRates.getDouble("USD");
                
                resultado = "Tasa de cambio; " + jsonRates.toString();
                System.out.println(resultado);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = "Error al obtener el valor de la API: " + e.getMessage();
        }
    
        return resultado;
    }
    

}

//CLASE EN DESUSO