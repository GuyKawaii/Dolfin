package member;

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

  public void setMembers(ArrayList<Member> members) {
    Motionist motionist;
    Competitive competitive;
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i) instanceof Competitive) {
        competitive = (Competitive) members.get(i);
        this.competitors.add(competitive);
      }
      if(members.get(i) instanceof Motionist) {
        motionist = (Motionist) members.get(i);
        this.motionists.add(motionist);
      }
    }
  }

  
  
  @Override
  public String toString() {
    return "member.MemberList{" +
           "motionists=" + motionists +
           ", competitives=" + competitors +
           '}';
  }
}

