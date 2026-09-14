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

    public String getGruposByPinta(){        
        Carta[] trebol = new Carta[NombreCarta.values().length];
        Carta[] pica = new Carta[NombreCarta.values().length];
        Carta[] corazon = new Carta[NombreCarta.values().length];
        Carta[] diamante = new Carta[NombreCarta.values().length];
        Carta[][] grupos = {trebol, pica, corazon, diamante};
        
        String resultado = "";
        int counter = 0;
        
        for(Carta carta:hand){
            int pinta = carta.getPinta().ordinal();
            int nombre = carta.getNombre().ordinal();
            grupos[pinta][nombre] = carta;
        }

        for(int i = 0; i <= Pinta.values().length - 1; i++){
            for(int j = 0; j <= NombreCarta.values().length - 1; j++){
                if (grupos[i][j] != null) counter++;
                else {
                    if (counter > 1) {
                        Grupo grupo = Grupo.values()[counter];
                        Pinta pinta = Pinta.values()[i];
                        NombreCarta nombreInicio = grupos[i][j - counter].getNombre();
                        NombreCarta nombreFin = grupos[i][j - 1].getNombre();

                        resultado += grupo + " de " + pinta + " de " + nombreInicio + " a " + nombreFin + "\n";                       
                    } 
                    counter = 0;
                }
            }            
            int posicionFinal = NombreCarta.values().length - 1;
            if (counter > 1) {                    
                resultado += Grupo.values()[counter] + " de " + Pinta.values()[i] + " de " + grupos[i][posicionFinal-counter+1].getNombre() + " a " + grupos[i][posicionFinal].getNombre() + "\n";
            };            
            counter = 0;
        }             
        return resultado;
    }
}
