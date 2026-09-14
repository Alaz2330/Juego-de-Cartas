import javax.swing.JPanel;

public class Jugador {
    private final int HAND_LENGTH = 10;
    private final int EDGE = 10;
    private final int SPACE = 40;

    private Carta[] hand = new Carta[HAND_LENGTH];

    public void repartir(Baraja baraja){
        for(int i = 0; i < HAND_LENGTH; i++){
            hand[i] = baraja.getCarta();
        }
    }

    public void mostrar(JPanel pnl){
        pnl.removeAll();
        pnl.setLayout(null);
        int position = EDGE + HAND_LENGTH * SPACE;
        for(Carta carta:hand){
            position -= SPACE;
            carta.mostrar(pnl, position, EDGE);
        }        
        pnl.repaint();
    }

    public String getGrupos(){
        String resultado = "No se encontraron grupos";
        int[] contadores = new int[NombreCarta.values().length];
        boolean hayGrupos = false;
        for(Carta carta:hand){
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
            if (!hayGrupos && contadores[posicion] >= 2){
                hayGrupos = true;
            }        
        }

        if (hayGrupos){
            resultado = "Se encontraron los siguientes grupos: \n";
            for(int i = 0; i < contadores.length; i++){
                if(contadores[i] >= 2) {
                    resultado += Grupo.values()[contadores[i]]+ " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return resultado;
    }
}
