import java.util.ArrayList;
import java.util.List;

public class Grafo {
    final static int N_NODI = 10;
    final static int N_JOIN = 3;

    public static void main(String args[])
    {
        Nodo vNodo[] = new Nodo[N_NODI+N_JOIN];
        //Semaforo vSemaforo[] = new Semaforo[N_NODI];
        List<Semaforo> lSemaforiIngresso;
        List<Semaforo> lSemaforiSuccessivi;

        Semaforo vSemaforo[] = new Semaforo[N_NODI+N_JOIN];
        //int vNumSemaforiSuccessivi[] = new int[N_NODI];

        //Si parte dal nodo1 per essere coerenti con le etichette
        for (int cntNodo=1; cntNodo<N_NODI+N_JOIN; cntNodo++){
            vSemaforo[cntNodo] = new Semaforo(0);
        }
        vSemaforo[1] = new Semaforo(1);
        //Nodo7:semafori ingresso 7,10
        //Nodo8:semafori ingresso 8,11
        //Nodo9:semafori ingresso 9,12

        //Fork1
        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[1]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[2]);
        lSemaforiSuccessivi.add(vSemaforo[4]);
        lSemaforiSuccessivi.add(vSemaforo[3]);
        vNodo[1] = new Nodo("A1", lSemaforiIngresso, lSemaforiSuccessivi);

        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[2]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[5]);
        vNodo[2] = new Nodo("A2", lSemaforiIngresso, lSemaforiSuccessivi);

        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[5]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[7]);
        vNodo[5] = new Nodo("A5", lSemaforiIngresso, lSemaforiSuccessivi);

        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[4]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[6]);
        vNodo[4] = new Nodo("A4", lSemaforiIngresso, lSemaforiSuccessivi);

        //Fork6: introduzione 2 semafori non ordinali
        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[6]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[10]);
        lSemaforiSuccessivi.add(vSemaforo[11]);
        vNodo[6] = new Nodo("A6", lSemaforiIngresso, lSemaforiSuccessivi);

        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[3]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[8]);
        vNodo[3] = new Nodo("A3", lSemaforiIngresso, lSemaforiSuccessivi);

        //Join7
        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[7]);
        lSemaforiIngresso.add(vSemaforo[10]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        lSemaforiSuccessivi.add(vSemaforo[9]);
        vNodo[7] = new Nodo("A7", lSemaforiIngresso, lSemaforiSuccessivi);

        //Join8
        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[8]);
        lSemaforiIngresso.add(vSemaforo[11]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        //lSemaforiSuccessivi.add(vSemaforo[9]);
        lSemaforiSuccessivi.add(vSemaforo[12]);
        vNodo[8] = new Nodo("A8", lSemaforiIngresso, lSemaforiSuccessivi);

        //Join9
        lSemaforiIngresso = new ArrayList<Semaforo>();
        lSemaforiIngresso.add(vSemaforo[9]);
        lSemaforiIngresso.add(vSemaforo[12]);
        lSemaforiSuccessivi = new ArrayList<Semaforo>();
        //lSemaforiSuccessivi.add(vuota);
        vNodo[9] = new Nodo("A9", lSemaforiIngresso, lSemaforiSuccessivi);

        for (int cntNodo=1; cntNodo<N_NODI; cntNodo++){
            vNodo[cntNodo].start();
        }
    }

}