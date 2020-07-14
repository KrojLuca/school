/**
 * Created by d.claudio.borgogno on 16/03/2019.
 */
public class Campanile {

    public static void main(String args[]){
        Thread[] thCampane = new Thread[12];

        thCampane[0] = new CampanaThread("Din");
        thCampane[1] = new CampanaThread("Don");
        thCampane[2] = new CampanaThread("Dan");
        thCampane[3] = new CampanaThread("Din");
        thCampane[4] = new CampanaThread("Don");
        thCampane[5] = new CampanaThread("Dan");
        thCampane[6] = new CampanaThread("Din");
        thCampane[7] = new CampanaThread("Don");
        thCampane[8] = new CampanaThread("Dan");
        thCampane[9] = new CampanaThread("Din");
        thCampane[10] = new CampanaThread("Don");
        thCampane[11] = new CampanaThread("Dan");

        for(int i=0; i<12; i++){
            thCampane[i].start();
            try {
                thCampane[i].sleep(1000*i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
