import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {
    
    private int index;

    public Carta(int num){
        index = num;       
    };

    public void mostrar(JPanel pnl, int x, int y){
        String rutaImagen = "img/CARTA" + index + ".JPG";        
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(rutaImagen));

        JLabel lblCarta = new JLabel(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        pnl.add(lblCarta);

        lblCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evento){
                JOptionPane.showMessageDialog(
                    null, 
                    getNombre() + " de " + getPinta()
                );
            }
        });
    };

    public Pinta getPinta(){
        if(index <= 13){
            return Pinta.TREBOL;
        } else if (index <= 26){
            return Pinta.PICA;
        } else if (index <= 39){
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre(){
        int residue = index % 3;
        if (residue == 0){
            residue = 13;
        }
        return NombreCarta.values()[residue - 1];
    }
}
