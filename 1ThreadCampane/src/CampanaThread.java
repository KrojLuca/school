/**
 * Created by d.claudio.borgogno on 16/03/2019.
 */
public class CampanaThread extends Thread {
    private String nomeCampana;

    public CampanaThread(String nomeCampana){
        super();
        this.nomeCampana = nomeCampana;
    }

    @Override
    public void run(){
        System.out.println("NomeCampana: "+nomeCampana);
    }

    public static void main(String args){
        Thread campanaDin = new CampanaThread("Din");
        campanaDin.start();

        Thread campanaDon = new CampanaThread("Don");
        campanaDon.start();

        Thread campanaDan = new CampanaThread("Dan");
        campanaDan.start();
    }
}
