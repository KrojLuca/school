/**
 * Created by d.claudio.borgogno on 16/03/2019.
 */
public class Campana implements Runnable{

    private String nomeCampana;

    public Campana(String nomeCampana){
        this.nomeCampana = nomeCampana;
    }

    @Override
    public void run() {
        System.out.println("NomeCampana: "+nomeCampana);
    }

    public static void main(String args[]){
        Runnable campanaDin = new Campana("Din");
        //Thread contenitore di una classe eseguibile
        Thread threadCampanaDin = new Thread(campanaDin);
        threadCampanaDin.start();   //invoca run() quando avrà la cpu

        Runnable campanaDon = new Campana("Don");
        Thread threadCampanaDon = new Thread(campanaDon);
        threadCampanaDon.start();

        Runnable campanaDan = new Campana("Dan");
        Thread threadCampanaDan = new Thread(campanaDan);
        threadCampanaDan.start();
    }

}
