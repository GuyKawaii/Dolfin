package team;

public class Trainer {
  private String name;
  
  public Trainer(String name) {
    setName(name);
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
