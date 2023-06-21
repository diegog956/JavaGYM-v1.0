package model.Personal;

import model.ActivYrutina.Actividad;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.Persona.Cliente;
import model.interfaces.I_toJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
/**Clase padre de Administrativo y Encargado, que hereda de Personal*/
public class Usuario extends Personal implements Serializable, I_toJson {
    private String usuario;
    private String contrasenia;
/**Constructor de la clase Usuario*/
    public Usuario(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre,dni,genero,telefono,domicilio,email,Eestado.ACTIVO,grupo_sanguineo,contacto_emergencia,obra_social,fecha_nacimiento,comentario,CUIL);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario()
    {
        this("","",null,"","","",null,"","",null,"","","","");
    }
/**Constructor que permite crear un usuario desde un JSONObject*/
    public Usuario(JSONObject jo) throws JSONException {
        super(jo);
    }

    public String getUsuario() {
        return usuario;
    }

    private String getContrasenia() {
        return contrasenia;
    }
/**Valida usuario y contraseña
@return true si son iguales. false si no lo son.*/
    public boolean ValidarCredenciales(String usuario, String contrasenia)
    {
        return (usuario.equals(getUsuario()) && contrasenia.equals(getContrasenia()));
    }
    private void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**Compara usuarios a traves de sus nombres de usuario y contraseña*/
    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if(o!=null){
            if(o instanceof Usuario){
                Usuario aux = (Usuario) o;
                if(aux.getUsuario().equals(getUsuario()) && aux.getContrasenia().equals(getContrasenia())){
                    rta = true;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }
    /**permite el cambio de contraseña
    @param viejaContrasenia Contraseña a ser modificada
    @param nuevaContrasenia Contrasenia nueva*/
    public boolean cambiarContrasenia(String viejaContrasenia, String nuevaContrasenia) {
        boolean rta=false;
        if (viejaContrasenia.equals(getContrasenia()))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }
    
    /**Crea un usuario a partir de un JSONObject
    @param jo JSONObject con la informacion del usuario.*/
    @Override
    public Usuario fromJson(JSONObject jo) throws JSONException {
        Usuario usuario = new Usuario(jo);
        usuario.setUsuario(jo.getString("Usuario"));
        usuario.setContrasenia(jo.getString("Contraseña"));
        return usuario;
    }

    /**Crea un JSONObject con la informacion del usuario*/
    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = super.toJsonObj();
        jsonObject.put("Usuario", getUsuario());
        jsonObject.put("Contraseña", getContrasenia());
        return jsonObject;
    }

      /**Permite cambiar contraseña de un usuario
      *@param contrasenia_nueva Contraseña nueva*/
    public void cambiarContrasenia(String contrasenia_nueva){
        setContrasenia(contrasenia_nueva);
    }


}
