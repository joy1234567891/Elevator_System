package building;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import scanerzus.Request;

/**
 * A JUnit test class for the Building class.
 */
public class BuildingTest {
  private Building building;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    building = new Building(11, 8, 5);
  }

  /**
   * Test the constructor exceptions.
   * Number of floors must be greater than 2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void elevatorConstructorThrowsExceptionForLessThan3Floors() {
    new Building(2, 5, 4);
  }

  /**
   * Test the constructor exceptions.
   * Number of floors must be less than or equal to 30.
   */
  @Test(expected = IllegalArgumentException.class)
  public void elevatorConstructorThrowsExceptionForMoreThan30Floors() {
    new Building(31, 5, 4);
  }

  /**
   * Test the constructor exceptions.
   * Number of elevators must be greater than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void elevatorConstructorThrowsExceptionFor0Elevators() {
    new Building(30, 0, 4);
  }

  /**
   * Test the constructor exceptions.
   * Elevator capacity must be greater than 3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void elevatorConstructorThrowsExceptionForLessThan4Capacity() {
    new Building(29, 5, 3);
  }

  /**
   * Test the constructor exceptions.
   * Elevator capacity must be less than or equal to 20.
   */
  @Test(expected = IllegalArgumentException.class)
  public void elevatorConstructorThrowsExceptionForMoreThan20Capacity() {
    new Building(28, 10, 21);
  }

  /**
   * Test the startElevatorSystem method returns true.
   */
  @Test
  public void startElevatorSystemReturnsTrue() {
    assertTrue(building.getElevatorSystemStatus().getSystemStatus()
        == ElevatorSystemStatus.outOfService);
    assertTrue(building.startElevatorSystem());
    assertTrue(building.getElevatorSystemStatus().getSystemStatus()
        == ElevatorSystemStatus.running);

    for (ElevatorReport elevatorReport : building.getElevatorSystemStatus().getElevatorReports()) {
      assertFalse(elevatorReport.isOutOfService());
    }
  }

  /**
   * Test the startElevatorSystem method returns false.
   */
  @Test
  public void startElevatorSystemReturnsFalse() {
    assertTrue(building.startElevatorSystem());
    assertFalse(building.startElevatorSystem());
  }

  /**
   * Test the startElevatorSystem method throws an exception when ElevatorSystemStatus.stopping.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startElevatorSystemWhenSystemStopping() {
    building.startElevatorSystem();
    building.stopElevatorSystem();
    building.startElevatorSystem();
  }

  /**
   * Test the addRequest exceptions.
   * Request cannot be null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addNullRequest() {
    building.addRequest(null);
  }

  /**
   * Test the addRequest exceptions.
   * Start floor cannot be negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWithNegativeStartFloor() {
    Request negativeStartFloor = new Request(-1, 5);
    building.addRequest(negativeStartFloor);
  }

  /**
   * Test the addRequest exceptions.
   * Start floor cannot be greater than or equal to the number of floors.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWithStartFloorEqualToNumberOfFloors() {
    Request startFloorEqualToNumberOfFloors = new Request(11, 5);
    building.addRequest(startFloorEqualToNumberOfFloors);
  }

  /**
   * Test the addRequest exceptions.
   * End floor cannot be negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWithNegativeEndFloor() {
    Request negativeEndFloor = new Request(1, -5);
    building.addRequest(negativeEndFloor);
  }

  /**
   * Test the addRequest exceptions.
   * End floor cannot be greater than or equal to the number of floors.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWithEndFloorEqualToNumberOfFloors() {
    Request endFloorEqualToNumberOfFloors = new Request(1, 11);
    building.addRequest(endFloorEqualToNumberOfFloors);
  }

  /**
   * Test the addRequest exceptions.
   * Start floor and end floor cannot be the same.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWithStartFloorEqualToEndFloor() {
    Request startFloorEqualToEndFloor = new Request(1, 1);
    building.addRequest(startFloorEqualToEndFloor);
  }

  /**
   * Test the addRequest exceptions.
   * Cannot add request when ElevatorSystemStatus.stopping.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addRequestWhenStopping() {
    building.startElevatorSystem();
    building.stopElevatorSystem();
    Request request = new Request(1, 5);
    building.addRequest(request);
  }

  /**
   * Test the addRequest method.
   */
  @Test
  public void addRequest() {
    building.startElevatorSystem();
    Request upRequest = new Request(1, 5);
    Request downRequest = new Request(3, 2);
    assertTrue(building.addRequest(upRequest));
    assertTrue(building.addRequest(downRequest));
    assertEquals(upRequest, building.getElevatorSystemStatus().getUpRequests().get(0));
    assertEquals(downRequest, building.getElevatorSystemStatus().getDownRequests().get(0));
  }

  /**
   * Test the distributeRequests method throws an exception when ElevatorSystemStatus.outOfService.
   */
  @Test(expected = IllegalArgumentException.class)
  public void distributeRequestsWhenSystemOutOfService() {
    building.distributeRequests();
  }

  /**
   * Test the distributeRequests method throws an exception when ElevatorSystemStatus.stopping.
   */
  @Test(expected = IllegalArgumentException.class)
  public void distributeRequestsWhenSystemStopping() {
    building.startElevatorSystem();
    building.stopElevatorSystem();
    building.distributeRequests();
  }

  /**
   * Test the distributeRequests method correctly distributes one up request.
   */
  @Test
  public void distributeUpRequests() {
    building.startElevatorSystem();
    Request upRequest = new Request(1, 5);
    building.addRequest(upRequest);
    building.distributeRequests();
    assertEquals(0, building.getElevatorSystemStatus().getUpRequests().size());
    assertTrue(building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()[1]);
    assertTrue(building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()[5]);
  }

  /**
   * Test the distributeRequests method correctly distributes one down request.
   */
  @Test
  public void distributeDownRequests() {
    building.startElevatorSystem();
    Request downRequest = new Request(10, 0);
    for (int i = 0; i < 16; i++) {
      building.step();
    }
    building.addRequest(downRequest);
    building.distributeRequests();
    assertEquals(0, building.getElevatorSystemStatus().getDownRequests().size());
    assertTrue(building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()[10]);
    assertTrue(building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()[0]);
  }

  /**
   * Test the distributeRequests method correctly distributes
   * multiple up requests exceeding capacity.
   */
  @Test
  public void distributeMultipleUpRequests() {
    building.startElevatorSystem();
    Request upRequest1 = new Request(1, 5);
    Request upRequest2 = new Request(2, 6);
    Request upRequest3 = new Request(3, 7);
    Request upRequest4 = new Request(4, 8);
    Request upRequest5 = new Request(5, 9);
    Request upRequest6 = new Request(6, 10);

    building.addRequest(upRequest1);
    building.addRequest(upRequest2);
    building.addRequest(upRequest3);
    building.addRequest(upRequest4);
    building.addRequest(upRequest5);
    building.addRequest(upRequest6);
    assertEquals(6, building.getElevatorSystemStatus().getUpRequests().size());

    building.distributeRequests();
    assertEquals(1, building.getElevatorSystemStatus().getUpRequests().size());
    boolean[] floorRequests1 = {false, true, true, true, true, true, true, true, true, true, false};
    assertTrue(Arrays.equals(floorRequests1,
        building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()));

    building.distributeRequests();
    assertEquals(0, building.getElevatorSystemStatus().getUpRequests().size());
    boolean[] floorRequests2 =
          {false, false, false, false, false, false, true, false, false, false, true};
    assertTrue(Arrays.equals(floorRequests2,
        building.getElevatorSystemStatus().getElevatorReports()[1].getFloorRequests()));
  }

  /**
   * Test the distributeRequests method correctly distributes
   * multiple up requests exceeding capacity.
   */
  @Test
  public void distributeMultipleDownRequests() {
    building.startElevatorSystem();
    Request downRequest1 = new Request(10, 0);
    Request downRequest2 = new Request(9, 1);
    Request downRequest3 = new Request(8, 2);
    Request downRequest4 = new Request(7, 3);
    Request downRequest5 = new Request(6, 4);
    Request downRequest6 = new Request(5, 4);

    building.addRequest(downRequest1);
    building.addRequest(downRequest2);
    building.addRequest(downRequest3);
    building.addRequest(downRequest4);
    building.addRequest(downRequest5);
    building.addRequest(downRequest6);

    assertEquals(6, building.getElevatorSystemStatus().getDownRequests().size());

    for (int i = 0; i < 16; i++) {
      building.step();
    }

    building.distributeRequests();
    assertEquals(1, building.getElevatorSystemStatus().getDownRequests().size());
    boolean[] floorRequests1 = {true, true, true, true, true, false, true, true, true, true, true};
    assertTrue(Arrays.equals(floorRequests1,
        building.getElevatorSystemStatus().getElevatorReports()[0].getFloorRequests()));

    building.step();
    assertEquals(0, building.getElevatorSystemStatus().getDownRequests().size());

    boolean[] floorRequests2 =
          {false, false, false, false, true, true, false, false, false, false, false};
    assertTrue(Arrays.equals(floorRequests2,
        building.getElevatorSystemStatus().getElevatorReports()[1].getFloorRequests()));
  }

  /**
   * Test the stopElevatorSystem method.
   */
  @Test
  public void stopElevatorSystem() {
    building.startElevatorSystem();
    building.stopElevatorSystem();
    assertEquals(ElevatorSystemStatus.stopping,
        building.getElevatorSystemStatus().getSystemStatus());
  }

  /**
   * Test the step method when ElevatorSystemStatus.running.
   */
  @Test
  public void stepWhenSystemRunning() {
    building.startElevatorSystem();
    Request upRequest = new Request(8, 9);
    building.addRequest(upRequest);
    assertEquals(1, building.getElevatorSystemStatus().getUpRequests().size());
    building.step();
    assertEquals(0, building.getElevatorSystemStatus().getUpRequests().size());
  }

  /**
   * Test the step method when ElevatorSystemStatus.stopping and step util out of service.
   */
  @Test
  public void stepWhenSystemStopping() {
    building.startElevatorSystem();
    for (int i = 0; i < 15; i++) {
      building.step();
    }
    building.stopElevatorSystem();
    building.step();
    assertEquals(9, building.getElevatorSystemStatus().getElevatorReports()[0].getCurrentFloor());
    for (int i = 0; i < 9; i++) {
      building.step();
    }
    assertTrue(
        building.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.outOfService);
  }

  /**
   * Test the getElevatorSystemStatus method.
   */
  @Test
  public void getElevatorSystemStatus() {
    building.startElevatorSystem();
    assertEquals(11, building.getElevatorSystemStatus().getNumFloors());
    assertEquals(8, building.getElevatorSystemStatus().getNumElevators());
    assertEquals(5, building.getElevatorSystemStatus().getElevatorCapacity());
    assertEquals(8, building.getElevatorSystemStatus().getElevatorReports().length);
    assertEquals(0, building.getElevatorSystemStatus().getUpRequests().size());
    assertEquals(0, building.getElevatorSystemStatus().getDownRequests().size());
    assertEquals(ElevatorSystemStatus.running,
        building.getElevatorSystemStatus().getSystemStatus());
  }
}
