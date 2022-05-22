package enums;

public enum MembershipStatus {
  ACTIVE("ACTIVE"),
  PASSIVE("PASSIVE");
  private final String subscription;
  
  MembershipStatus(String subscription) {
    this.subscription = subscription;
  }
  
  public String getString() {
    return subscription;
  }
}
