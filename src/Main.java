import Utilidades.JsonUtiles;
import model.Enum.EGrupoSanguineo;
import model.Enum.EdiaSemana;
import model.Enum.Eestado;
import model.Persona.Cliente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Cliente> clientes_harcodeados = new ArrayList<>();

        Cliente cliente1 = new Cliente("Alberto A","111","111-111", Eestado.ACTIVO, EGrupoSanguineo.A_NEGATIVO,"111-111","A Salud",true, LocalDate.of(2001,01,01),"",false,false);
        Cliente cliente2 = new Cliente ("Benito B","222","222-222",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"222-222","B Salud",true,LocalDate.of(2002,02,02),"",false,false);
        Cliente cliente3 = new Cliente("Carla C","333","333-333",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"333-333","C Salud",true,LocalDate.of(2003,03,03),"",true,false);
        Cliente cliente4 = new Cliente ("David D","444","444-444", Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"444-444","D Salud",true,LocalDate.of(2004,04,04),"Es epileptico",false,false);
        Cliente cliente5 = new Cliente("Eugenia E","555","555-555",Eestado.ACTIVO,EGrupoSanguineo.A_POSITIVO,"555-555","E Salud",false,LocalDate.of(2005,05,05),"",false,false);

        clientes_harcodeados.add(cliente1);
        clientes_harcodeados.add(cliente2);
        clientes_harcodeados.add(cliente3);
        clientes_harcodeados.add(cliente4);
        clientes_harcodeados.add(cliente5);

        JSONArray jsonArray = new JSONArray();
        try {
            for (Cliente micliente : clientes_harcodeados) {
                jsonArray.put(micliente.toJsonObj());
            }
        }catch (JSONException e){
            e.printStackTrace();
        }


        System.out.println(jsonArray);
        JsonUtiles.grabar(jsonArray, "Clientes");


    }
}