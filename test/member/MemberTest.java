package member;

import org.junit.jupiter.api.Test;

import static enums.MembershipStatus.ACTIVE;
import static enums.MembershipStatus.PASSIVE;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
  
  @Test
  public void testContingentPassive() {
    MemberMotionist motionistJunior = new member.MemberMotionist("Junior1", now().minusYears(17), PASSIVE);
    MemberMotionist motionistSenior = new member.MemberMotionist("Senior1", now().minusYears(18), PASSIVE);
    MemberMotionist motionistSeniorSixtyPlus = new member.MemberMotionist("Senior Plus1", now().minusYears(61), PASSIVE);
    
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Junior2", now().minusYears(17), PASSIVE);
    MemberCompetitive competitiveSenior = new member.MemberCompetitive("Senior2", now().minusYears(18), PASSIVE);
    MemberCompetitive competitiveSeniorSixtyPuls = new member.MemberCompetitive("Senior Plus2", now().minusYears(61), PASSIVE);
    
    // motionist
    assertEquals(500, motionistJunior.getContingent());
    assertEquals(500, motionistSenior.getContingent());
    assertEquals(500, motionistSeniorSixtyPlus.getContingent());
    
    // competitive
    assertEquals(500, competitiveJunior.getContingent());
    assertEquals(500, competitiveSenior.getContingent());
    assertEquals(500, competitiveSeniorSixtyPuls.getContingent());
  }
  
  @Test
  public void testContingentActiveJunior() {
    // motionist
    MemberMotionist motionistJunior = new member.MemberMotionist("Junior1", now().minusYears(17), ACTIVE);
    assertEquals(1000, motionistJunior.getContingent());
    
    // competitive
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Junior2", now().minusYears(17), ACTIVE);
    assertEquals(1000, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSenior() {
    // motionist
    MemberMotionist motionistJunior = new member.MemberMotionist("Senior test1", now().minusYears(23), ACTIVE);
    assertEquals(1600, motionistJunior.getContingent());
    
    // competitive
    MemberCompetitive competitiveJunior = new member.MemberCompetitive("Senior test2", now().minusYears(24), ACTIVE);
    assertEquals(1600, competitiveJunior.getContingent());
  }
  
  @Test
  public void testContingentActiveSeniorSixtyPlus() {
    // motionist
    MemberMotionist motionistSeniorSixtyPlus = new member.MemberMotionist("Senior nr.1 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), motionistSeniorSixtyPlus.getContingent());
    
    // competitive
    MemberCompetitive competitiveSeniorSixtyPlus = new member.MemberCompetitive("Senior nr.2 60+", now().minusYears(61), ACTIVE);
    assertEquals(1600 * (1 - 0.25), competitiveSeniorSixtyPlus.getContingent());
  }
  
  
}