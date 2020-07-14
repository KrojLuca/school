import javax.swing.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Temporizzatore extends Thread {
    private Animazione frame;
    private Tavolozza pnlTavolozza;

    public Temporizzatore(Animazione frame) {
        this.frame = frame;
        this.pnlTavolozza = frame.getPnlMain();
    }

    @Override
    public void run() {
        super.run();
        while(true) {

            this.pnlTavolozza.repaint();
        }
    }
}
