import member.Member;
import member.MemberCompetitive;
import member.MemberMotionist;

import java.util.ArrayList;

public class MemberList {
  private ArrayList<MemberMotionist> motionists;
  private ArrayList<MemberCompetitive> competitors;
  
  public MemberList() {
    motionists = new ArrayList<>();
    competitors = new ArrayList<>();
    
  }
  
  public MemberMotionist getMotionist(String name) {
    for (MemberMotionist motionist : motionists)
      if (motionist.getName().equals(name)) return motionist;
    
    // not found
    return null;
  }
  
  public MemberCompetitive getCompetitive(String name) {
    for (MemberCompetitive competitors : competitors)
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
  
  public void addMotionist(MemberMotionist motionist) {
    motionists.add(motionist);
  }
  
  public void addCompetitive(MemberCompetitive competitive) {
    competitors.add(competitive);
  }
  
  public boolean removeMotionist(String name) {
    for (MemberMotionist motionist : motionists)
      if (motionist.getName().equals(name)) {
        motionists.remove(motionist);
        return true;
      }
    
    // not found
    return false;
  }
  
  public boolean removeCompetitive(String name) {
    for (MemberCompetitive competitive : competitors)
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
  
  public ArrayList<MemberCompetitive> getCompetitors() {
    return competitors;
  }
  
  public ArrayList<MemberMotionist> getMotionists() {
    return motionists;
  }
  
  public ArrayList<Member> getMembers() {
    ArrayList<Member> members = new ArrayList<>();
    
    for (MemberCompetitive competitive : competitors)
      members.add((Member) competitive);
    
    for (MemberMotionist motionist : motionists)
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

