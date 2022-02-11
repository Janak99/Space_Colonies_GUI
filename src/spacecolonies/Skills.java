package spacecolonies;

/**
 * Class Skills : Holds skill values and formats skills to string output
 * 
 * @author Janak Majeethia
 * @version 02/10/2022
 */

 public class Skills{
     private int agriculture, medical, technology;

     /**
      * Skills constructor
      * 
      * @param  ag      agricultural trait
      * @param  med     medical trait
      * @param  tech    technical trait
      */
      public Skills(int ag, int med, int tech){
          this.agriculture = ag;
          this.medical = med;
          this.technology = tech;
      }

      /** Return string representation of skills object */
      public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Agriculture: ");
            sb.append(this.getAgriculture());
            sb.append("\n");
            sb.append("Medical: ");
            sb.append(this.getMedical());
            sb.append("\n");
            sb.append("Technological: ");
            sb.append(this.getTechnology());
            return sb.toString();
      }

      /** Return agricultural value */
      public int getAgriculture() {
          return agriculture;
      }

      /** Return medical value */
      public int getMedical() {
            return medical;
      }

      /** Return agricultural value */
      public int getTechnology() {
            return technology;
      }
 }