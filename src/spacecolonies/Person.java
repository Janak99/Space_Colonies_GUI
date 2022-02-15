package spacecolonies;

/**
 * Class Person : Holds individuals' names and traits and preferences for immigration.
 * 
 * @author  Janak Majeethia
 */

 public class Person {
     private String name, planetPref;
     private Skills skills;

     /**
      * Person contructor
      * @param  name    Person name
      * @param  agri    Agriculture trait value
      * @param  medi    Medical trait value
      * @param  tech    Technology trait value
      * @param  pref    Persons planet preference
      */
      public Person(String name, int agri, int medi, int tech, String pref) {
          this.name = name;
          skills = new Skills(agri, medi, tech);
          planetPref = pref;
      }

      /** Get name of Person */
          public String getName() {
          return name;
      }

      /** Get  of Person */
        public Skills getSkills() {
          return skills;
      }

      /** Get name of Person */
      public String getPlanetPref() {
          return planetPref;
      }

      /** Format to string contents of Person */
      public String toString() {
          StringBuilder sb = new StringBuilder();
          if (planetPref == null) {
              sb.append("No-Planet ");
          }
          sb.append(name + " ");
          sb.append("A:" + skills.getAgriculture());
          sb.append(" M:" + skills.getMedical());
          sb.append(" T:" + skills.getTechnology());
          if (planetPref != null) {
              sb.append(" Wants: " + this.getPlanetPref());
          }

          return sb.toString();
      }
 }