import javax.swing.*;
import java.awt.*;

/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Tavolozza extends JPanel {
    public static final int N_QUADRATI=4;
    public static final int LATO=100;
    public static final int OFFSET=50;

    public int vX[];
    public int vY[];
    public int lato;
    private int cntMovimenti;

    public Tavolozza(){
        this.vX=new int[N_QUADRATI];
        this.vY=new int[N_QUADRATI];
        this.lato=LATO;
        this.cntMovimenti=0;
    }

    public void disegnaQuadrato (Graphics g, Quadrato q, boolean disegna) {
        if (disegna) {
            g.setColor(new Color(0, 0, 0));}
        else {
            g.setColor(this.getBackground());
        }
        g.drawLine(q.getX1(), q.getY1(), q.getX2(), q.getY2());
        g.drawLine(q.getX2(), q.getY2(), q.getX3(), q.getY3());
        g.drawLine(q.getX3(), q.getY3(), q.getX4(), q.getY4());
        g.drawLine(q.getX4(), q.getY4(), q.getX1(), q.getY1());
    }

    public void disegnaQuadratiIniziali(){
        vX[0]=OFFSET;
        vY[0]=OFFSET;

        vX[1]=getWidth()-OFFSET-lato;
        vY[1]=OFFSET;

        vX[2]=getWidth()-OFFSET-lato;
        vY[2]=getHeight()-OFFSET-lato;

        vX[3]=OFFSET;
        vY[3]=getHeight()-OFFSET-lato;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //disegnare i quadrati all'apertura della finestra
        if(cntMovimenti==0){
            this.disegnaQuadratiIniziali();
        }
        cntMovimenti++;

        Quadrato quadrato0 = new Quadrato(this.vX[0], this.vY[0], lato);
        disegnaQuadrato(g, quadrato0, true);
        Quadrato quadrato1 = new Quadrato(this.vX[1], this.vY[1], lato);
        disegnaQuadrato(g, quadrato1, true);
        Quadrato quadrato2 = new Quadrato(this.vX[2], this.vY[2], lato);
        disegnaQuadrato(g, quadrato2, true);
        Quadrato quadrato3 = new Quadrato(this.vX[3], this.vY[3], lato);
        disegnaQuadrato(g, quadrato3, true);

    }
}
