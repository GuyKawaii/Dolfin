package enums;

public enum AgeGroup {
  SENIOR("SENIOR"),
  JUNIOR("JUNIOR");
  private final String ageBracket;
  
  AgeGroup(String ageBracket) {
    this.ageBracket = ageBracket;
  }
  
  public String getString() {
    return ageBracket;
  }
}
