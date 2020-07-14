/**
 * Created by d.claudio.borgogno on 02/04/2019.
 */
public class Semaforo {
    int valoreSemaforo;

    public Semaforo(int valoreSemaforo){
        this.valoreSemaforo = valoreSemaforo;
    }

    //synchronized -> verifica accesso singolo entry point
    //Proberen  -> testa (to probe)
    synchronized public void P(){
        //Se il semaforo vale 0 non può entrare
        while (valoreSemaforo==0){
            try {
                this.wait(); //lo mette in coda
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        valoreSemaforo--;
    }

    //Verhogen -> aumentare (to rise)
    synchronized public void V(){
        valoreSemaforo++;
        //this è la mia variabile condizione
        // del monitor Hoare
        this.notify(); //sveglio un altro
        //this.notifyAll(); // non li sveglio tutti
    }


}
