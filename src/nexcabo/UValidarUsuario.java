package nexcabo;

public class UValidarUsuario {
    public String validar = "";

    public void invalidaUsuario() {
        this.validar = "NAO";
    }

    public void validaUsuario() {
        this.validar = "SIM";
    }

    public String pegaValidacao() {
        return this.validar;
    }
}
