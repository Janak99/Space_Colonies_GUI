package spacecolonies;

import java.io.FileNotFoundException;

public class ProjectRunner {

    public static void main(String[] args)
            throws FileNotFoundException,
            SpaceColonyDataException {
        if (args.length != 0) {
            new ColonyReader(args[0], args[1]);
        } else {
            new ColonyReader("input.txt", "planets.txt");
        }
    }
}