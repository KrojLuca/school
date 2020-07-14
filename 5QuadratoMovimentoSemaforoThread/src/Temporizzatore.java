import javax.swing.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Temporizzatore extends Thread {
    public static final int MOVIMENTO=1;
    public static final int QUANTO=10;

    public static final int SU=0;
    public static final int DESTRA=1;
    public static final int GIU=2;
    public static final int SINISTRA=3;

    private Animazione frame;
    private Tavolozza pnlTavolozza;

    public Temporizzatore(Animazione frame) {
        this.frame = frame;
        this.pnlTavolozza = frame.getPnlMain();
    }

    // xprec,yprec,direzione,xnew,ynew
    public void generaMovimento(int[] vQx, int[] vQy, int idQuadrato, int direzione ){
        /*
        direzione:
        0->su
        1->destra
        2->giù
        3->sinistra
        */
        switch (direzione){
            case SU:
                vQx[idQuadrato]=vQx[idQuadrato];
                vQy[idQuadrato]=vQy[idQuadrato]-MOVIMENTO;
                break;
            case DESTRA:
                vQx[idQuadrato]=vQx[idQuadrato]+MOVIMENTO;
                vQy[idQuadrato]=vQy[idQuadrato];
                break;
            case GIU:
                vQx[idQuadrato]=vQx[idQuadrato];
                vQy[idQuadrato]=vQy[idQuadrato]+MOVIMENTO;
                break;
            case SINISTRA:
                vQx[idQuadrato]=vQx[idQuadrato]-MOVIMENTO;
                vQy[idQuadrato]=vQy[idQuadrato];
                break;
        }
    }

    @Override
    public void run() {
        super.run();
        while(this.pnlTavolozza.vX[0]<this.pnlTavolozza.getWidth()-this.pnlTavolozza.lato-Tavolozza.OFFSET) {

           // xprec,yprec,direzione,xnew,ynew


            this.generaMovimento(this.pnlTavolozza.vX, this.pnlTavolozza.vY, 0, DESTRA);
            this.generaMovimento(this.pnlTavolozza.vX, this.pnlTavolozza.vY, 1, GIU);
            this.generaMovimento(this.pnlTavolozza.vX, this.pnlTavolozza.vY, 2, SINISTRA);
            this.generaMovimento(this.pnlTavolozza.vX, this.pnlTavolozza.vY, 3, SU);

            /*
            this.pnlTavolozza.vX[0]=this.pnlTavolozza.vX[0]+MOVIMENTO;
            this.pnlTavolozza.vY[0]=this.pnlTavolozza.vY[0]+MOVIMENTO;

            this.pnlTavolozza.vX[1]=this.pnlTavolozza.vX[1]-MOVIMENTO;
            this.pnlTavolozza.vY[1]=this.pnlTavolozza.vY[1]+MOVIMENTO;

            this.pnlTavolozza.vX[2]=this.pnlTavolozza.vX[2]-MOVIMENTO;
            this.pnlTavolozza.vY[2]=this.pnlTavolozza.vY[2]-MOVIMENTO;

            this.pnlTavolozza.vX[3]=this.pnlTavolozza.vX[3]+MOVIMENTO;
            this.pnlTavolozza.vY[3]=this.pnlTavolozza.vY[3]-MOVIMENTO;
            */

            this.pnlTavolozza.repaint();

            try {
                Thread.sleep(QUANTO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
    }
}
