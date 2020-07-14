import javax.swing.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Temporizzatore extends Thread {
    public static final int MOVIMENTO=20;
    public static final int QUANTO=1000;

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

            this.pnlTavolozza.vX[0]=this.pnlTavolozza.vX[0]+MOVIMENTO;
            this.pnlTavolozza.vY[0]=this.pnlTavolozza.vY[0]+MOVIMENTO;

            this.pnlTavolozza.vX[1]=this.pnlTavolozza.vX[1]-MOVIMENTO;
            this.pnlTavolozza.vY[1]=this.pnlTavolozza.vY[1]+MOVIMENTO;

            this.pnlTavolozza.vX[2]=this.pnlTavolozza.vX[2]-MOVIMENTO;
            this.pnlTavolozza.vY[2]=this.pnlTavolozza.vY[2]-MOVIMENTO;

            this.pnlTavolozza.vX[3]=this.pnlTavolozza.vX[3]+MOVIMENTO;
            this.pnlTavolozza.vY[3]=this.pnlTavolozza.vY[3]-MOVIMENTO;

            this.pnlTavolozza.repaint();

            try {
                Thread.sleep(QUANTO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
