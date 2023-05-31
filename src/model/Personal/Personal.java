package model.Personal;

import java.util.Objects;

public class Personal {
    private String usuario;
    private String contrasenia;

    public Personal()
    {
        usuario=" ";
        contrasenia=" ";
    }

    public Personal(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personal personal = (Personal) o;
        return Objects.equals(usuario, personal.usuario) && Objects.equals(contrasenia, personal.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    public boolean verificarContrasenia(String viejaContrasenia, String nuevaContrasenia)
    {
        boolean rta=false;
        if (viejaContrasenia.equals(getContrasenia()))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }
}
