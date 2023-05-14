import view.CalculadoraConsola;
import view.CalculadoraRestaGUI;
import view.CalculadoraSumaGUI;
import view.CalculadoraVista;
import controller.CalculadoraController;
import model.CalculadoraModelo;

public class App {
    private enum TipoVista {
        CONSOLA, SUMA_GUI, RESTA_GUI, MULTIPLICACION_GUI, DIVISION_GUI, MODULO_GUI
    }

    public static void main(String[] args) {
        TipoVista tipo = TipoVista.SUMA_GUI;

        CalculadoraVista view = null;
        switch (tipo) {
            case CONSOLA:
                view = new CalculadoraConsola();
                break;
            case SUMA_GUI:
                view = new CalculadoraSumaGUI();
                break;
            case RESTA_GUI:
                view = new CalculadoraRestaGUI();
                break;
            case MULTIPLICACION_GUI:
                // TODO: Implementar una vista para la multiplicación
                throw new UnsupportedOperationException("Implementar una vista para la multiplicación");
            case DIVISION_GUI:
                // TODO: Implementar una vista para la división
                throw new UnsupportedOperationException("Implementar una vista para la división");
            case MODULO_GUI:
                // TODO: Implementar una vista para el módulo (resto de división)
                throw new UnsupportedOperationException("Implementar una vista para el módulo (resto de división)");
            default:
                return;
        }

        CalculadoraModelo model = new CalculadoraModelo();
        CalculadoraController controller = new CalculadoraController(view, model);

        controller.iniciar();
    }
}
