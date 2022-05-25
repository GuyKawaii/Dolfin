package member;


import java.util.ArrayList;

public class MemberList {
  private ArrayList<Motionist> motionists;
  private ArrayList<Competitive> competitors;
  private int idCounter;
  
  public MemberList() {
    motionists = new ArrayList<>();
    competitors = new ArrayList<>();
    idCounter = 1;
  }
  
  // ID specific
  public void setIdCounter(int idCounter) {
    this.idCounter = idCounter;
  }
  
  public int getIdCounter() {
    // not used for member ID assignment!
    return idCounter;
  }
  
  public int createID() {
    // used for member ID assignment! (increments idCounter)
    return idCounter++;
  }
  
  // get members
  public Motionist getMotionist(int ID) {
    for (Motionist motionist : motionists)
      if (motionist.getID() == ID) return motionist;
    
    // not found
    return null;
  }
  
  public Motionist getMotionist(String name) {
    for (Motionist motionist : motionists)
      if (motionist.getName().equals(name)) return motionist;

    // not found
    return null;
  }
  
  public Competitive getCompetitive(int ID) {
    for (Competitive competitors : competitors)
      if (competitors.getID() == ID) return competitors;

    // not found
    return null;
  }
  
  public Competitive getCompetitive(String name) {
    for (Competitive competitors : competitors)
      if (competitors.getName().equals(name)) return competitors;
    
    // not found
    return null;
  }
  
  public Member getMember(String name) {
    Member member;

    member = (Member) getMotionist(name);
    if (member != null) return member;

    member = (Member) getCompetitive(name);
    if (member != null) return member;

    return null;
  }
  
  public Member getMember(int ID) {
    Member member;
    
    member = (Member) getMotionist(ID);
    if (member != null) return member;
    
    member = (Member) getCompetitive(ID);
    if (member != null) return member;
    
    return null;
  }
  
  // add members
  public boolean addMotionist(Motionist motionist) {
    // only add if not already present
    if (motionists.contains(motionist))
      return false;
    
    else {
      motionists.add(motionist);
      return true;
    }
    
  }
  
  public boolean addCompetitive(Competitive competitive) {
    // only add if not already present
    if (competitors.contains(competitive))
      return false;
    
    else {
      competitors.add(competitive);
      return true;
    }
  }
  
  // remove members
  public Motionist removeMotionist(int ID) {
    for (Motionist motionist : motionists)
      if (motionist.getID() == ID) {
        motionists.remove(motionist);
        return motionist;
      }
    
    // not found
    return null;
  }
  
//  public boolean removeMotionist(String name) {
//    for (Motionist motionist : motionists)
//      if (motionist.getName().equals(name)) {
//        motionists.remove(motionist);
//        return true;
//      }
//
//    // not found
//    return false;
//  }
//
  public Competitive removeCompetitive(int ID) {
    for (Competitive competitive : competitors)
      if (competitive.getID() == ID) {
        competitors.remove(competitive);
        return competitive;
      }

    // not found
    return null;
  }
  
//  public Member removeCompetitive(String name) {
//    for (Competitive competitive : competitors)
//      if (competitive.getName().equals(name)) {
//        competitors.remove(competitive);
//        return competitive;
//      }
//
//    // not found
//    return null;
//  }
  
  public Member removeMember(int ID) {
    Member removed;
    
    removed = (Member) removeCompetitive(ID);
    if (removed != null) return removed;
    
    removed = (Member) removeMotionist(ID);
    if (removed != null) return removed;
    
    return null;
  }
  
//  public boolean removeMember(String name) {
//    boolean removed;
//
//    removed = removeCompetitive(name);
//    if (removed) return true;
//
//    removed = removeMotionist(name);
//    if (removed) return true;
//
//    return false;
//  }
  
  // general getters setters
  public int getMemberAmount() {
    return getMotionistAmount() + getCompetitiveAmount();
  }
  
  public int getMotionistAmount() {
    return motionists.size();
  }
  
  public int getCompetitiveAmount() {
    return competitors.size();
  }
  
  public ArrayList<Competitive> getCompetitors() {
    return competitors;
  }
  
  public ArrayList<Motionist> getMotionists() {
    return motionists;
  }
  
  public ArrayList<Member> getMembers() {
    ArrayList<Member> members = new ArrayList<>();
    
    for (Competitive competitive : competitors)
      members.add((Member) competitive);
    
    for (Motionist motionist : motionists)
      members.add((Member) motionist);
    
    return members;
  }
  
  public void setCompetitors(ArrayList<Competitive> competitors) {
    // clear all already present
    this.competitors = competitors;
  }

  public void setMotionists(ArrayList<Motionist> motionists) {
    // clear all already present
    this.motionists = motionists;
  }

  @Override
  public String toString() {
    return "member.MemberList{" +
           "motionists=" + motionists +
           ", competitives=" + competitors +
           '}';
  }
}

