package appointments;

import java.util.ArrayList;
import java.util.Random;

/**
 * Factory class which allows generating unique integer IDs.
 *
 * @author Gabriel Glaser
 * @version 14.11.2021
 */
public class ID {

    private static final ArrayList<Integer> ALREADY_USED_IDS = new ArrayList<>();

    public static int getNext() {
	final Random randomGenerator = new Random();
	int randomID = randomGenerator.nextInt();
	while (ALREADY_USED_IDS.contains(randomID)) {
	    randomID = randomGenerator.nextInt();
	}
	ALREADY_USED_IDS.add(randomID);
	return randomID;
    }

}
