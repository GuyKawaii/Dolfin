import member.Member;
import member.Competitive;
import member.Motionist;

import java.util.ArrayList;

public class MemberList {
  private ArrayList<Motionist> motionists;
  private ArrayList<Competitive> competitors;
  
  public MemberList() {
    motionists = new ArrayList<>();
    competitors = new ArrayList<>();
    
  }
  
  public Motionist getMotionist(String name) {
    for (Motionist motionist : motionists)
      if (motionist.getName().equals(name)) return motionist;
    
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
  
  public void addMotionist(Motionist motionist) {
    motionists.add(motionist);
  }
  
  public void addCompetitive(Competitive competitive) {
    competitors.add(competitive);
  }
  
  public boolean removeMotionist(String name) {
    for (Motionist motionist : motionists)
      if (motionist.getName().equals(name)) {
        motionists.remove(motionist);
        return true;
      }
    
    // not found
    return false;
  }
  
  public boolean removeCompetitive(String name) {
    for (Competitive competitive : competitors)
      if (competitive.getName().equals(name)) {
        competitors.remove(competitive);
        return true;
      }
    
    // not found
    return false;
  }
  
  public boolean removeMember(String name) {
    boolean removed;
    
    removed = removeCompetitive(name);
    if (removed) return true;
    
    removed = removeMotionist(name);
    if (removed) return true;
    
    return false;
  }
  
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
  
  
  @Override
  public String toString() {
    return "MemberList{" +
           "motionists=" + motionists +
           ", competitives=" + competitors +
           '}';
  }
}

