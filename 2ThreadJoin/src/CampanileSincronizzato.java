/**
 * Created by d.claudio.borgogno on 06/04/2019.
 */
public class CampanileSincronizzato {
    public static final int NUMERO_CAMPANE = 3;

    public static void main(String[] args) {
        int nV = 4;
        Semaforo[] vSemaforo = new Semaforo[NUMERO_CAMPANE];
        vSemaforo[0] = new Semaforo(1);
        vSemaforo[1] = new Semaforo(0);
        vSemaforo[2] = new Semaforo(0);
        CampanaSincronizzata[] vCs = new CampanaSincronizzata[NUMERO_CAMPANE];
        vCs[0] = new CampanaSincronizzata(nV, 0, "Din", vSemaforo);
        vCs[1] = new CampanaSincronizzata(nV, 1, "Don", vSemaforo);
        vCs[2] = new CampanaSincronizzata(nV, 2, "Dan", vSemaforo);
        vCs[0].start();
        vCs[1].start();
        vCs[2].start();

    }

}
