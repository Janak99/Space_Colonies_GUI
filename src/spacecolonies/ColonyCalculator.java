package spacecolonies;

import com.sun.syndication.feed.atom.Link;
import list.AList;

/**
 * Colony Calculator object that handles calculation of applicants' profiles
 * 
 * @author Janak Majeethia
 */
public class ColonyCalculator {
    /**
     * Static int to hold max number of planets
     */
    public static final int NUM_PLANETS = 3;
    /**
     * Static int to hold minimum skill level
     */
    public static final int MIN_SKILL_LEVEL = 1;
    /**
     * Static int to hold maximum skill level
     */
    public static final int MAX_SKILL_LEVEL = 5;

    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private static Planet[] planets = new Planet[NUM_PLANETS + 1];

    /**
     * Main Colony Calculator constructor
     * 
     * @param appQueue
     *                     queue of applicants
     * @param planetsArray
     *                     Planet[] holder
     */
    @SuppressWarnings("static-access")
    public ColonyCalculator(
            ArrayQueue<Person> appQueue,
            Planet[] planetsArray) {
        if (appQueue.getSize() == 0) {
            throw new IllegalArgumentException();
        }
        rejectBus = new AList<Person>();
        this.applicantQueue = appQueue;
        this.planets = planetsArray;
    }

    /**
     * Return applicant queue
     * 
     * @return ArrayQueue<Person> representation of the queue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }

    /**
     * Returns array of available planets
     * 
     * @return Planet[] planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }

    /**
     * Returns the particular planet for someone
     * 
     * @param nextPerson
     *                   Person being judged
     * @return Planet object for the applicant
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        if (nextPerson == null || applicantQueue.isEmpty()) {
            return null;
        }
        String stringPref = nextPerson.getPlanetName();
        for (int i = 0; i < ColonyCalculator.NUM_PLANETS; i++) {
            if (planets[i] == null) {
                return null;
            }
            if (planets[i].getName().equals(nextPerson.getPlanetName())
                    && (planets[i].getSkills().isBelow(nextPerson.getSkills())
                            && !planets[i].isFull())) {
                return planets[i];
            }
        }

        if (stringPref != null) {
            for (int i = 0; i < ColonyCalculator.NUM_PLANETS; i++) {
                if (planets[i].getSkills().isBelow(nextPerson.getSkills())
                        && !planets[i].isFull()) {
                    return planets[i];
                }
            }
        } else {
            for (int i = 0; i < ColonyCalculator.NUM_PLANETS; i++) {
                if (planets[i].getSkills().isBelow(nextPerson.getSkills())
                        && !planets[i].isFull()) {
                    return planets[i];
                }
            }

        }
        return null;
    }

    /**
     * Accepts the applicant into their specified planet
     * 
     * @return boolean if acceptance was successful or not
     */
    public boolean accept() {
        if (!(applicantQueue.isEmpty())) {

            Planet referencePlanet = this.getPlanetForPerson(applicantQueue
                    .getFront());
            if (referencePlanet != null) {
                referencePlanet.addPerson(applicantQueue.dequeue());
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * Rejects applicant into their planet and sends them on the bus home
     */
    public void reject() {
        rejectBus.add(applicantQueue.dequeue());
    }

    /**
     * Return Planet based off slot number
     * 
     * @param planet
     *               either 1, 2, or 3
     * @return Planet associated with the parameterized slot number
     */
    public Planet planetByNumber(int planet) {
        if (planet < 1 || planet > NUM_PLANETS) {
            return null;
        }
        return planets[planet - 1];
    }

    /**
     * Gets slot number of parameterized planet
     * 
     * @param planet
     *               String
     * @return slot number of parameterized planet
     */
    public int getPlanetIndex(String planet) {
        for (int i = 0; i < NUM_PLANETS; i++) {
            if (planets[i].getName() == planet) {
                return i;
            }
        }
        return 0;
    }

}