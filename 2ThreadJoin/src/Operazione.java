/**
 * Created by d.claudio.borgogno on 26/03/2019.
 */
public class Operazione extends Thread{
    private Buffer dati;
    private double b, c;
    private int codiceNodo;

    public Operazione(int codiceNodo, Buffer dati, double a, double b, double c) {
        this.codiceNodo = codiceNodo;
        this.dati = dati;
        this.b = b;
        this.c = c;
    }

    public Buffer getDati() {
        return dati;
    }

    public void setDati(Buffer dati) {
        this.dati = dati;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void run(){
        double ris = 0;
        switch (codiceNodo){
            case 1: ris = c * ( dati.getB() - b);
                    dati.setX(ris);
                    break;
            case 2: ris = c * ( dati.getC() - b);
                    dati.setY(ris);
                    break;
            case 3: ris = c * ( dati.getA() - b);
                    dati.setZ(ris);
                    break;
            case 4: ris = dati.getK() * dati.getT();
                    dati.setK(ris);
                    break;
            case 5: ris = dati.getK() * dati.getT();
                    dati.setT(ris);
                    break;
        }
        System.out.println("Il thread " + codiceNodo + " ha generato " + ris);
    }

}
