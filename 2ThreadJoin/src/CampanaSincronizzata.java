/**
 * Created by d.claudio.borgogno on 06/04/2019.
 */
public class CampanaSincronizzata extends Thread {
    private int nVolte;
    private int idCampana;
    private String nomeCampana;
    private Semaforo[] vSemaforo;

    public CampanaSincronizzata(int nVolte, int idCampana, String nomeCampana, Semaforo[] vSemaforo) {
        this.nVolte = nVolte;
        this.idCampana = idCampana;
        this.nomeCampana = nomeCampana;
        this.vSemaforo = vSemaforo;
    }

    @Override
    public void run() {
        int idCampanaSuccessivo;
        super.run();
        for(int cntVolte =0 ; cntVolte<nVolte ; cntVolte++){
            vSemaforo[idCampana].P();
            System.out.println(System.currentTimeMillis() + " - " + idCampana + " - " + nomeCampana+" - "+ cntVolte);
            if(idCampana==CampanileSincronizzato.NUMERO_CAMPANE-1){
                idCampanaSuccessivo=0;
            }else{
                idCampanaSuccessivo=idCampana+1;
            }
            vSemaforo[idCampanaSuccessivo].V();
        }
        //System.out.println(System.currentTimeMillis() + " - " + idCampana + " - " + nomeCampana+" - "+ nVolte + " Ho finito");
    }



}
