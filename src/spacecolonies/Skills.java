package spacecolonies;

/**
 * Class Skills : Holds skill values and formats skills to string output
 * 
 * @author Janak Majeethia
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

      /** Return true if this attributes are less than parameter attributes */
      public boolean isBelow(Skills other) {
          return (this.getAgriculture() <= other.getAgriculture() && this
                  .getMedical() <= other.getMedical() && this
                          .getTechnology() <= other.getTechnology());
      }

      /** Return true if this skills object equals argument skills object */
      public boolean equals(Object obj) {
          return (this.getAgriculture() == ((Skills) obj).getAgriculture() && this
                  .getMedical() == ((Skills) obj).getMedical() && this
                          .getTechnology() == ((Skills) obj).getTechnology());
      }
 }