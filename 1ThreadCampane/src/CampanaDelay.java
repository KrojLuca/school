/**
 * Created by d.claudio.borgogno on 16/03/2019.
 */
public class CampanaDelay implements Runnable{

    private String nomeCampana;
    private int svegliaIniziale;
    private int sveglia;
    private int numEsecuzioni;
    private int tempo;


    public CampanaDelay(String nomeCampana, int svegliaIniziale, int sveglia)
    {
        this.nomeCampana = nomeCampana;
        this.svegliaIniziale = svegliaIniziale;
        this.sveglia = sveglia;
        this.numEsecuzioni = 0;
        this.tempo = 0;
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (this.numEsecuzioni == 0) {
                    Thread.sleep(svegliaIniziale);
                    tempo+=svegliaIniziale;
                    System.out.println("Tempo:\t" + tempo + " \t,NomeCampana: " + nomeCampana + " ,ritardo= " + svegliaIniziale);
                } else {
                    Thread.sleep(sveglia);
                    tempo+=sveglia;
                    System.out.println("Tempo:\t" + tempo + " \t,NomeCampana: " + nomeCampana + " ,ritardo= " + sveglia);
                }
                numEsecuzioni++;
            } catch (InterruptedException iEx) {
                System.err.println("Il thread " + nomeCampana + " è stato interrotto per ");
                iEx.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        Runnable campanaDun = new CampanaDelay("Dun", 2, 2);
        //Thread contenitore di una classe eseguibile
        Thread threadCampanaDun = new Thread(campanaDun);
        threadCampanaDun.start();   //invoca run() quando avrà la cpu

        Runnable campanaDin = new CampanaDelay("Din", 1, 6);
        //Thread contenitore di una classe eseguibile
        Thread threadCampanaDin = new Thread(campanaDin);
        threadCampanaDin.start();   //invoca run() quando avrà la cpu

        Runnable campanaDon = new CampanaDelay("Don", 3, 6);
        Thread threadCampanaDon = new Thread(campanaDon);
        threadCampanaDon.start();

        Runnable campanaDan = new CampanaDelay("Dan", 5, 6);
        Thread threadCampanaDan = new Thread(campanaDan);
        threadCampanaDan.start();
    }

}
