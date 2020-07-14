import java.util.Date;
import java.util.List;
import java.util.Random;

public class Nodo extends Thread {
    public static final int TMAX = 2000;

    String name;
    List<Semaforo> lSemaforiIngresso;
    List<Semaforo> lSemaforiSuccessivi;
    Random tRandom;

    public Nodo(String name, List<Semaforo> lSemaforiIngresso, List<Semaforo> lSemaforiSuccessivi){
        this.name = name;
        this.lSemaforiIngresso = lSemaforiIngresso;
        this.lSemaforiSuccessivi = lSemaforiSuccessivi;

        tRandom = new Random(new Date().getTime());
        tRandom.nextInt();
    }

    @Override
    public void run() {
        super.run();

        for(Semaforo semaforoIngresso : lSemaforiIngresso)
            semaforoIngresso.P();

        int timeSleep = tRandom.nextInt(TMAX);
        //System.out.println("Nodo " + name + " executed!");
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Nodo " + name + " executed!");

        //Semafori dei nodi successivi
        for(Semaforo semaforoUscita : lSemaforiSuccessivi)
            semaforoUscita.V();
    }
}
