import javax.swing.*;
import java.awt.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Animazione extends JFrame {
    Tavolozza pnlMain;

    public Animazione() {
        super("Aimazione quadrati");
        setSize(800, 600);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 10;
        gbc.weighty = 7;
        gbc.fill = gbc.BOTH;

        this.pnlMain = new Tavolozza();
        this.add(pnlMain, gbc);
        this.setVisible(true);

        //Temporizzatore tQuadrati = new Temporizzatore(this);
        //tQuadrati.start();

    }

    public Tavolozza getPnlMain() {
        return pnlMain;
    }
    public void setPnlMain(Tavolozza pnlMain) {
        this.pnlMain = pnlMain;
    }

    public static void main(String[] args) {
        Animazione animazione = new Animazione();

    }
}
