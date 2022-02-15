package spacecolonies;
/**
 * Class Planet : Holds planet details for immigation
 * 
 * @author Janak Majeethia
 */
public class Planet implements Comparable<Planet> {
    private String name;
    private Skills minSkills;
    private Person[] people;
    private int popSize;
    private final int capacity;

    /**
     * Main Planet constructor
     * 
     * @param planetName
     *                   name of planet
     * @param planetAgri
     *                   minimum agriculture skill
     * @param planetMedi
     *                   minimum medical skill
     * @param planetTech
     *                   minimum technical skill
     * @param planetCap
     *                   planets capacity
     */
    public Planet(
            String planetName,
            int planetAgri,
            int planetMedi,
            int planetTech,
            int planetCap) {
        name = planetName;
        minSkills = new Skills(planetAgri, planetMedi, planetTech);
        capacity = planetCap;
        people = new Person[capacity];
    }

    /** Set name value */
    public void setName(String newName) {
        this.name = newName;
    }

    /** Get name value */
    public String getName() {
        return this.name;
    }

    /** Get skills */
    public Skills getSkills() {
        return minSkills;
    }

    /** Get Persons */
    public Person[] getPeople() {
        return people;
    }

    /** Get population size */
    public int getPopulation() {
        return popSize;
    }

    /** Get capacity */
    public int getCapacity() {
        return capacity;
    }

    /** Get availibility */
    public int getAvailibility() {
        return capacity - popSize;
    }

    /** Return whether planet full */
    public boolean isFull() {
        return capacity == popSize;
    }

    /** Add Person to Planet */
    public boolean addPerson(Person newPerson) {
        if(getAvailibility() > 0 && minSkills.isBelow(newPerson.getSkills())) {
            people[popSize] = newPerson;
            popSize++;
            return true;
        }
        return false;
    }

    /** Return Planet as String information */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(", population ");
        sb.append(popSize);
        sb.append(" (cap: ");
        sb.append(capacity);
        sb.append("), Requires: A >= ");
        sb.append(minSkills.getAgriculture());
        sb.append(", M >= ");
        sb.append(minSkills.getMedical());
        sb.append(", T >= ");
        sb.append(minSkills.getTechnology());
        return sb.toString();
    }

    /** Check if planets equal */
    public boolean equals(Object other) {

        if (other == null || ((Planet) other).getName() == null) {
            return false;
        }
        return this.getName() == ((Planet) other).getName() && this.getSkills()
                .equals(((Planet) other).getSkills())
                && this
                        .getCapacity() == ((Planet) other).getCapacity()
                && this
                        .getPopulation() == (((Planet) other)
                                .getPopulation());
    }

    /** Compare planets based on availibility 
    * @return -1 if this < other, 0 if equal, 1 if this > other
    */
    public int compareTo(Planet other) {
        return Integer.compare(this.getAvailibility(), other.getAvailibility());
    }
}
