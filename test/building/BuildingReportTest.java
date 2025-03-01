package building;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import building.enums.Direction;
import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import scanerzus.Request;

/**
 * A JUnit test class for the BuildingReport class.
 */
public class BuildingReportTest {
  private BuildingReport report;

  /**
   * Sets up the test.
   */
  @Before
  public void setUp() {
    ElevatorReport[] elevatorReports = new ElevatorReport[1];
    elevatorReports[0] = new ElevatorReport(
        0,  // elevatorId
        0,  // currentFloor
        Direction.UP,  // direction
        false, // doorClosed
        new boolean[] {false, true, true},  // floorRequests
        1,  // doorOpenTimer
        1,  // endWaitTimer
        false,  // outOfService
        false  // isTakingRequests
    );
    report = new BuildingReport(
        3,  // numFloors
        1,  // numElevators
        5,  // elevatorCapacity
        elevatorReports,  // elevatorReports
        Arrays.asList(new Request(1, 2)),  // upRequests
        Arrays.asList(new Request(3, 1)),  // downRequests
        ElevatorSystemStatus.running  // systemStatus
    );
  }

  /**
   * Tests the getNumFloors method.
   */
  @Test
  public void getNumFloors() {
    assertEquals(3, report.getNumFloors());
  }

  /**
   * Tests the getNumElevators method.
   */
  @Test
  public void getNumElevators() {
    assertEquals(1, report.getNumElevators());
  }

  /**
   * Tests the getElevatorCapacity method.
   */
  @Test
  public void getElevatorCapacity() {
    assertEquals(5, report.getElevatorCapacity());
  }

  /**
   * Tests the getElevatorReports method.
   */
  @Test
  public void getElevatorReports() {
    ElevatorReport[] elevatorReports = new ElevatorReport[1];
    elevatorReports[0] = new ElevatorReport(
        0,  // elevatorId
        0,  // currentFloor
        Direction.UP,  // direction
        false, // doorClosed
        new boolean[] {false, true, true},  // floorRequests
        1,  // doorOpenTimer
        1,  // endWaitTimer
        false,  // outOfService
        false  // isTakingRequests
    );
    assertTrue(Arrays.equals(elevatorReports, report.getElevatorReports()));
  }

  /**
   * Tests the getUpRequests method.
   */
  @Test
  public void getUpRequests() {
    assertEquals(1, report.getUpRequests().size());
    String upRequest = "1->2";
    assertEquals(upRequest, report.getUpRequests().get(0).toString());
  }

  /**
   * Tests the getDownRequests method.
   */
  @Test
  public void getDownRequests() {
    assertEquals(1, report.getDownRequests().size());
    String downRequest = "3->1";
    assertEquals(downRequest, report.getDownRequests().get(0).toString());
  }

  /**
   * Tests the getSystemStatus method.
   */
  @Test
  public void getSystemStatus() {
    assertEquals(ElevatorSystemStatus.running, report.getSystemStatus());
  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString() {
    String expected = "Floors: 3, Elevators: 1, Capacity: 5\n"
        + "Elevator System: Running\n"
        + "Up: 1->2, \n"
        + "Down: 3->1, \n"
        + "Elevator Status: \n"
        + "Waiting[Floor 0, Time 1]\n";
    assertEquals(expected, report.toString());
  }
}
