package spacecolonies;

import java.awt.Color;
import CS2114.*;
import list.AList;

/**
 * Space Window class
 * 
 * @author Dilan Prasad
 * @version 2018.11.24
 *
 */
public class SpaceWindow {

    private Window window;
    private ColonyCalculator colonyCalculator;
    private Button accept;
    private Button reject;
    private AList<CircleShape> personCircles;

    private Shape planet1;
    private Shape planet2;
    private Shape planet3;

    private TextShape planet1String;
    private TextShape planet2String;
    private TextShape planet3String;

    private TextShape planet1Skills;
    private TextShape planet2Skills;
    private TextShape planet3Skills;

    private Planet planet1Obj;
    private Planet planet2Obj;
    private Planet planet3Obj;

    private ArrayQueue<Person> localQueue;

    /**
     * Main Space Window constructor
     * 
     * @param colonyCalc
     *                   object where data is read from
     */
    public SpaceWindow(ColonyCalculator colonyCalc) {
        this.colonyCalculator = colonyCalc;

        window = new Window("Space Colonies");

        accept = new Button("Accept");
        accept.onClick(this, "clickedAccept");
        localQueue = colonyCalc.getQueue();
        reject = new Button("Reject");
        reject.onClick(this, "clickedReject");

        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);

        planet1 = new Shape(50, 200, 100, 100);
        planet1.setBackgroundColor(Color.pink);
        planet2 = new Shape(250, 200, 100, 100);
        planet2.setBackgroundColor(Color.GREEN);
        planet3 = new Shape(450, 200, 100, 100);
        planet3.setBackgroundColor(Color.cyan);

        planet1String = new TextShape(50, 180, ColonyCalculator.getPlanets()[0]
                .getName());
        planet1String.setBackgroundColor(Color.WHITE);
        planet2String = new TextShape(250, 180, ColonyCalculator.getPlanets()[1]
                .getName());
        planet2String.setBackgroundColor(Color.WHITE);
        planet3String = new TextShape(450, 180, ColonyCalculator.getPlanets()[2]
                .getName());
        planet3String.setBackgroundColor(Color.WHITE);

        planet1Skills = new TextShape(55, 300, ColonyCalculator.getPlanets()[0]
                .getSkills().toString());
        planet1Skills.setBackgroundColor(Color.WHITE);
        planet2Skills = new TextShape(255, 300, ColonyCalculator.getPlanets()[1]
                .getSkills().toString());
        planet2Skills.setBackgroundColor(Color.white);
        planet3Skills = new TextShape(455, 300, ColonyCalculator.getPlanets()[2]
                .getSkills().toString());
        planet3Skills.setBackgroundColor(Color.white);

        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);
        window.addShape(planet1String);
        window.addShape(planet2String);
        window.addShape(planet3String);
        window.addShape(planet1Skills);
        window.addShape(planet2Skills);
        window.addShape(planet3Skills);

        TextShape planet1Avail = new TextShape(120, 180, "/" + String.valueOf(
                ColonyCalculator.getPlanets()[0].getAvailability()));
        TextShape planet2Avail = new TextShape(330, 180, "/" + String.valueOf(
                ColonyCalculator.getPlanets()[1].getAvailability()));
        TextShape planet3Avail = new TextShape(520, 180, "/" + String.valueOf(
                ColonyCalculator.getPlanets()[2].getAvailability()));

        planet1Avail.setBackgroundColor(Color.white);
        planet2Avail.setBackgroundColor(Color.WHITE);
        planet3Avail.setBackgroundColor(Color.WHITE);

        window.addShape(planet1Avail);
        window.addShape(planet2Avail);
        window.addShape(planet3Avail);

        planet1Obj = ColonyCalculator.getPlanets()[0];
        planet2Obj = ColonyCalculator.getPlanets()[1];
        planet3Obj = ColonyCalculator.getPlanets()[2];

        updateApplicants();
        updateAvailabilities();
        updateButtons();
        addShades();

    }

    /**
     * What to do when accept button is clicked
     * Accepts dequeued person and updates the window
     */
    public void clickedAccept(Button acceptButton) {
        System.out.println(colonyCalculator.getQueue().getFront().getName()
                + " was added to " + colonyCalculator.getPlanetForPerson(
                        colonyCalculator.getQueue().getFront()).getName());

        colonyCalculator.accept();

        window.removeAllShapes();

        updateApplicants();
        updatePlanets();
        updateAvailabilities();
        updateButtons();
        addShades();
    }

    /**
     * Rejects the dequeued person
     * Updates window
     */
    public void clickedReject(Button rejectButton) {
        System.out.println(colonyCalculator.getQueue().getFront().getName()
                + " was placed on the bus home!");

        colonyCalculator.reject();
        window.removeAllShapes();

        updateApplicants();
        updateAvailabilities();
        updatePlanets();
        updateButtons();
        addShades();
    }

    /**
     * Changes the visibility of the accept/reject options
     */
    public void updateButtons() {
        if (!(colonyCalculator.getQueue().isEmpty())) {
            Person current = localQueue.getFront();
            if (current.getPlanetName() != null) {
                String planetPref = current.getPlanetName();
                Planet currentPref = null;
                if (planetPref != null) {
                    if (planetPref.equals(planet1Obj.getName())) {
                        currentPref = planet1Obj;
                    } else if (planetPref.equals(planet2Obj.getName())) {
                        currentPref = planet2Obj;
                    } else if (planetPref.equals(planet3Obj.getName())) {
                        currentPref = planet3Obj;
                    }
                    if (currentPref.getSkills().isBelow(current.getSkills())) {
                        accept.enable();
                    } else {
                        accept.disable();
                    }
                }
            }

        } else {
            reject.disable();
            accept.disable();
        }
    }

    /**
     * Updates planets every time the window gets updated
     */
    private void updatePlanets() {
        planet1 = new Shape(50, 200, 100, 100);
        planet1.setBackgroundColor(Color.pink);
        planet2 = new Shape(250, 200, 100, 100);
        planet2.setBackgroundColor(Color.GREEN);
        planet3 = new Shape(450, 200, 100, 100);
        planet3.setBackgroundColor(Color.cyan);

        planet1String = new TextShape(50, 180, planet1Obj.getName());
        planet1String.setBackgroundColor(Color.WHITE);
        planet2String = new TextShape(250, 180, planet2Obj.getName());
        planet2String.setBackgroundColor(Color.WHITE);
        planet3String = new TextShape(450, 180, planet3Obj.getName());
        planet3String.setBackgroundColor(Color.WHITE);

        planet1Skills = new TextShape(55, 300, planet1Obj.getSkills()
                .toString());
        planet1Skills.setBackgroundColor(Color.WHITE);
        planet2Skills = new TextShape(255, 300, planet2Obj.getSkills()
                .toString());
        planet2Skills.setBackgroundColor(Color.white);
        planet3Skills = new TextShape(455, 300, planet3Obj.getSkills()
                .toString());
        planet3Skills.setBackgroundColor(Color.white);

        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);
        window.addShape(planet1String);
        window.addShape(planet2String);
        window.addShape(planet3String);
        window.addShape(planet1Skills);
        window.addShape(planet2Skills);
        window.addShape(planet3Skills);

        TextShape planet1Avail = new TextShape(120, 180, "/" + planet1Obj
                .getCapacity());
        TextShape planet2Avail = new TextShape(330, 180, "/" + planet2Obj
                .getCapacity());
        TextShape planet3Avail = new TextShape(520, 180, "/" + planet3Obj
                .getCapacity());

        planet1Avail.setBackgroundColor(Color.white);
        planet2Avail.setBackgroundColor(Color.WHITE);
        planet3Avail.setBackgroundColor(Color.WHITE);

        window.addShape(planet1Avail);
        window.addShape(planet2Avail);
        window.addShape(planet3Avail);
    }

    /**
     * Updates availabiltities of the various planets
     */
    private void updateAvailabilities() {

        int planet1Population = planet1Obj.getPopulationSize();
        int planet2Population = planet2Obj.getPopulationSize();
        int planet3Population = planet3Obj.getPopulationSize();

        TextShape planet1Size = new TextShape(110, 180, String.valueOf(
                planet1Population));
        TextShape planet2Size = new TextShape(320, 180, String.valueOf(
                planet2Population));
        TextShape planet3Size = new TextShape(510, 180, String.valueOf(
                planet3Population));

        planet1Size.setBackgroundColor(Color.WHITE);
        planet2Size.setBackgroundColor(Color.WHITE);
        planet3Size.setBackgroundColor(Color.WHITE);

        window.addShape(planet1Size);
        window.addShape(planet2Size);
        window.addShape(planet3Size);
        window.moveToFront(planet1Size);
        window.moveToFront(planet2Size);
        window.moveToFront(planet3Size);

    }

    /**
     * Updates applicant queue
     */
    private void updateApplicants() {
        if (!(colonyCalculator.getQueue().isEmpty())) {

            TextShape applicantInfo = new TextShape(10, 10, colonyCalculator
                    .getQueue().getFront().toString());
            applicantInfo.setBackgroundColor(Color.WHITE);
            window.addShape(applicantInfo);

            personCircles = new AList<CircleShape>();
            int shapeX = 50;
            int shapeY = 120;

            int numberOfShapes = colonyCalculator.getQueue().getSize();

            if (colonyCalculator.getQueue().getSize() < 9) {
                numberOfShapes = colonyCalculator.getQueue().getSize();
            }

            ArrayQueue<Person> temp = colonyCalculator.getQueue();
            Object[] tempArray = temp.toArray();

            for (int i = 0; i < numberOfShapes; i++) {
                int shapeSize = 50;

                CircleShape personShape = new CircleShape(shapeX, shapeY
                        - shapeSize, shapeSize);

                String preferredPlanet = ((Person) tempArray[i]).getPlanetName();

                if (preferredPlanet != null) {
                    switch (preferredPlanet) {
                        case "Planet1":
                            personShape.setForegroundColor(Color.pink);
                            break;
                        case "Planet2":
                            personShape.setForegroundColor(Color.GREEN);
                            break;
                        case "Planet3":
                            personShape.setForegroundColor(Color.cyan);
                            break;
                        default:
                            personShape.setForegroundColor(Color.RED);

                    }
                } else {
                    personShape.setForegroundColor(Color.GRAY);
                }

                personCircles.add(personShape);

                shapeX = shapeX + shapeSize + 5;
            }

            for (int i = 0; i < personCircles.getLength(); i++) {
                window.addShape(personCircles.getEntry(i));
            }
        } else {
            finishScreen();

        }

    }

    /**
     * What is printed when all applicants have been processed
     */
    private void finishScreen() {
        updateButtons();
        window.removeAllShapes();
        TextShape finishScreen = new TextShape(180, 75,
                "All applicants have been processed!");
        finishScreen.setBackgroundColor(Color.white);
        window.addShape(finishScreen);

        TextShape results = new TextShape(255, 125, "Final results:");
        results.setBackgroundColor(Color.white);
        window.addShape(results);

        System.out.println("~ Queue is finished ~");

    }

    /**
     * Adds shades representing each planets population size to the planet shape
     * object
     */
    private void addShades() {
        double fraction1 = (100 * ColonyCalculator.getPlanets()[0]
                .getPopulationSize()) / ColonyCalculator.getPlanets()[0]
                        .getCapacity();

        double fraction2 = (100 * ColonyCalculator.getPlanets()[1]
                .getPopulationSize()) / ColonyCalculator.getPlanets()[1]
                        .getCapacity();

        double fraction3 = (100 * ColonyCalculator.getPlanets()[2]
                .getPopulationSize()) / ColonyCalculator.getPlanets()[2]
                        .getCapacity();

        Shape planet1Shade = new Shape(50, (int) (300 - fraction1), 100,
                (int) fraction1, Color.BLACK);
        Shape planet2Shade = new Shape(250, (int) (300 - fraction2), 100,
                (int) fraction2, Color.BLACK);
        Shape planet3Shade = new Shape(450, (int) (300 - fraction3), 100,
                (int) fraction3, Color.BLACK);

        window.addShape(planet1Shade);
        window.addShape(planet2Shade);
        window.addShape(planet3Shade);

        window.moveToFront(planet1Shade);
        window.moveToFront(planet2Shade);
        window.moveToFront(planet3Shade);

    }

}