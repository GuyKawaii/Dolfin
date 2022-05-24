package filehandling;

import member.Competitive;
import member.Motionist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlingMemberListTest {
  FileHandlingMemberList fileHandlingMemberList;
  
  @BeforeEach
  void setup(){
    fileHandlingMemberList = new FileHandlingMemberList();
  }
  
  @Test
  void loadCompetitive() { // todo how to assert when empty csv?
    ArrayList<Competitive> competitors;
  
    competitors = fileHandlingMemberList.loadCompetitors();
  
    System.out.println(competitors);
    
//    assert(!competitors.isEmpty());
  }
  
  @Test
  void loadMotionist() {
    ArrayList<Motionist> motionists = new ArrayList<>();
  
    motionists = fileHandlingMemberList.loadMotionists();
  
    System.out.println(motionists);
  
//    assert(!motionists.isEmpty());
  }
  
  
}