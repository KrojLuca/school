import javax.swing.*;
import java.awt.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Animazione extends JFrame {
    Tavolozza pnlMain;

    public Animazione() {
        super("Aimazione quadrati");
        setSize(600, 600);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 8;
        gbc.weighty = 8;
        gbc.fill = gbc.BOTH;

        this.pnlMain = new Tavolozza();
        this.add(pnlMain, gbc);
        this.setVisible(true);

        Temporizzatore tQuadrati = new Temporizzatore(this);
        tQuadrati.start();

    }

    public Tavolozza getPnlMain() {
        return pnlMain;
    }
    public void setPnlMain(Tavolozza pnlMain) {
        this.pnlMain = pnlMain;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Animazione animazione = new Animazione();
            }
        });

    }
}
