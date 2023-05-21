import by.gsu.pms.Tribe;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Tribe[] anArray = new Tribe[10];
        anArray[0] = null;
        anArray[1] = new Tribe();
        anArray[2] = new Tribe("Tribe 1", 12, true);
        anArray[3] = new Tribe("Tribe 2", 13, false);
        anArray[4] = new Tribe("Tribe 3", 14, true);
        anArray[5] = new Tribe("Tribe 4", 15, true);
        anArray[6] = new Tribe("Tribe 5", 16, false);
        anArray[7] = new Tribe("Tribe 6", 17, true);
        anArray[8] = new Tribe("Tribe 7", 18, true);
        anArray[9] = new Tribe("Tribe 8", 19, false);
        for (Tribe element : anArray) {
            System.out.println(element);
        }
        Arrays.sort(anArray);
        for (Tribe elem : anArray) {
            System.out.println(elem.getTribeAmount());

        for (int i = 0; i < anArray.length; i++) {
            if (anArray[i] != null){
            System.out.println(anArray[i].getTribeAmount());
            }
        }
    }

    }
}
