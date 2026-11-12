public enum SingletonEnum {
    INSTANCIA;

    private String idioma = "pt-BR";

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
