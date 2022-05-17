package enums;

public enum Discipline {
  CRAWL("CRAWL"),
  BACK_CRAWL("BACK_CRAWL"),
  BREAST_STROKE("BREAST_STROKE"),
  BUTTERFLY("BUTTERFLY");
  private final String discipline;
  
  Discipline(String discipline) {
    this.discipline = discipline;
  }
  
  public String getDiscipline() {
    return discipline;
  }
  
}
