package view;

import controller.CalculadoraController;

public interface CalculadoraVista {

    public String getNumeroUno();

    public String getNumeroDos();

    public void setResultado(String resultado);

    public void iniciar(CalculadoraController controller);

    public void mostrarExcepcion(Exception ex);

}