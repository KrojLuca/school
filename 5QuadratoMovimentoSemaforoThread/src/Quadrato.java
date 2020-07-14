/**
 * Created by d.claudio.borgogno on 09/04/2019.
 */
public class Quadrato {
    private int xLeft,
            yHigh,
            lato;

    public Quadrato(int xLeft, int yHigh, int lato) {
        this.xLeft = xLeft;
        this.yHigh = yHigh;
        this.lato = lato;
    }

    public int getX1() {
        return xLeft;
    }
    public int getY1() {
        return yHigh;
    }
    public int getX2 () {
        return xLeft+lato;
    }
    public int getY2() {
        return yHigh;
    }
    public int getX3() {
        return xLeft+lato;
    }
    public int getY3() {
        return yHigh+lato;
    }
    public int getX4() {
        return xLeft;
    }
    public int getY4() {
        return yHigh+lato;
    }







}
