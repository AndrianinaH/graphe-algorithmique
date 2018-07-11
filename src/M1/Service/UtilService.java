package M1.Service;

import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class UtilService {

    public static int extremumNumber(List<Integer> numbers, String typeExtremum) {
        int extremum = numbers.get(0);
        if (typeExtremum.equals("maximum")) {
            for (int number : numbers) {
                if (number > extremum) {
                    extremum = number;
                }
            }
        } else {
            for (int number : numbers) {
                if (number < extremum) {
                    extremum = number;
                }
            }
        }
        return extremum;
    }
}
