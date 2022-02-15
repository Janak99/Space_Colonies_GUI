package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ColonyReader Class : Handles input from files and initiates GUI with calculator
 * 
 * @author Janak Majeethia
 */
public class ColonyReader {
    private Planet[] planets;

    private ArrayQueue<Person> queue;

    public ColonyReader(String applicantFileName, String planetFileName)
            throws FileNotFoundException,
            SpaceColonyDataException {
        queue = new ArrayQueue<Person>();

        planets = new Planet[ColonyCalculator.NUM_PLANETS];

        planets = readPlanetFile(planetFileName);
        queue = readQueueFile(applicantFileName);

        new SpaceWindow(new ColonyCalculator(queue, planets));

    }

    @SuppressWarnings("resource")
    private Planet[] readPlanetFile(String planetFile)
            throws FileNotFoundException,
            SpaceColonyDataException {

        Planet[] tempPlanets = planets;

        Scanner lineScanner = new Scanner(new File(planetFile));
        Scanner valueScanner = null;

        int scanIndex = 0;

        while (lineScanner.hasNextLine()) {

            valueScanner = new Scanner(lineScanner.nextLine());
            valueScanner.useDelimiter(", ");

            String line = valueScanner.nextLine();
            Scanner values = new Scanner(line);
            values.useDelimiter(", ");

            String planetName = values.next();
            int agri = Integer.valueOf(values.next());
            int medi = Integer.valueOf(values.next());
            int tech = Integer.valueOf(values.next());
            int cap = Integer.valueOf(values.next());
            Planet tester = new Planet(planetName, agri, medi, tech, cap);
            tempPlanets[scanIndex] = tester;
            scanIndex++;
        }

        if (scanIndex != 3) {
            throw new SpaceColonyDataException("Not enough planets");
        }
        return tempPlanets;
    }

    @SuppressWarnings("resource")
    private ArrayQueue<Person> readQueueFile(String queueFile)
            throws FileNotFoundException,
            SpaceColonyDataException {

        ArrayQueue<Person> tempQueue = new ArrayQueue<Person>();

        Scanner personScanner = new Scanner(new File("input.txt"));
        Scanner attributeScanner = null;

        while (personScanner.hasNextLine()) {

            attributeScanner = new Scanner(personScanner.nextLine());
            attributeScanner.useDelimiter(", ");

            String applicant = attributeScanner.nextLine();
            Scanner attributes = new Scanner(applicant);
            attributes.useDelimiter(", *");

            String applicantName = attributes.next();
            int agriTrait = Integer.parseInt(attributes.next());
            int mediTrait = Integer.parseInt(attributes.next());
            int techTrait = Integer.parseInt(attributes.next());
            String planetPreference = null;

            if (attributes.hasNext()) {
                planetPreference = attributes.next();
                if (!(planetPreference.endsWith(planets[0].getName()))
                        && !(planetPreference.endsWith(planets[1].getName()))
                        && !(planetPreference.endsWith(planets[2].getName()))) {
                    planetPreference = null;
                }
            }

            if (agriTrait < ColonyCalculator.MIN_SKILL_LEVEL
                    || agriTrait > ColonyCalculator.MAX_SKILL_LEVEL
                    || techTrait < ColonyCalculator.MIN_SKILL_LEVEL
                    || techTrait > ColonyCalculator.MAX_SKILL_LEVEL
                    || mediTrait < ColonyCalculator.MIN_SKILL_LEVEL
                    || mediTrait > ColonyCalculator.MAX_SKILL_LEVEL) {
                throw new SpaceColonyDataException("Attributes out of range");
            }

            tempQueue.enqueue(new Person(applicantName, agriTrait, mediTrait,
                    techTrait, planetPreference));

            attributes.close();

        }
        personScanner.close();
        attributeScanner.close();
        return tempQueue;
    }
}