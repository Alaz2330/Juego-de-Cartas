import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuegos extends JFrame {
    private final int[] CANTIDAD_BARAJAS = {1, 2, 3};

    private JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;
    private JComboBox cmbBarajas;
    
    public FrmJuegos(){
        setSize(500,350);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 45, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120,45,100,25);
        add(btnVerificar);

        JLabel lblBarajas = new JLabel("Cantidad de barajas");
        lblBarajas.setBounds(10, 10, 125, 25);
        add(lblBarajas);

        cmbBarajas = new JComboBox();
        for (int Barajas:CANTIDAD_BARAJAS){
            cmbBarajas.addItem(Barajas);            
        }    
        cmbBarajas.setBounds(140,10,50,25);
        add(cmbBarajas);

        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10,80,470,200);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(0,255,0));
        tpJugadores.add("Martin Estrada Contreras", pnlJugador1);

        pnlJugador2 = new JPanel();
        pnlJugador1.setBackground(new Color(0,255,255));
        tpJugadores.add("Raul Vidal", pnlJugador2);

        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });
    }

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();
    private Baraja baraja;

    private void repartir(){
        int deckQuantity = cmbBarajas.getSelectedIndex() + 1;
        
        baraja = new Baraja(deckQuantity);
        baraja.resetBaraja();

        jugador1.repartir(baraja);
        jugador1.mostrar(pnlJugador1);
        
        jugador2.repartir(baraja);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar(){
        String mensaje = "";
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                mensaje = jugador1.getGrupos();
                break;
            case 1:
                mensaje = jugador2.getGrupos();
                break;            
        }
        if(!mensaje.isEmpty()){
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }
}