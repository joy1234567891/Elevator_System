package building;

import scanerzus.Request;

/**
 * This interface is used to represent a building.
 * The building can have requests added to it.
 * The building can distribute the requests to the elevators.
 * The building can start the elevator system.
 * The building can stop the elevator system.
 * The building can take an elevator out of service.
 * The building can move the elevators by one step.
 * The building can return a BuildingReport object.
 */
public interface BuildingInterface {

  /**
   * Adds a request to the building.
   *
   * @param request The request to be added.
   * @return true if the building status is ElevatorSystemStatus.running.
   * @throws IllegalArgumentException if the building is in ElevatorSystemStatus.outOfService
   *                                  or ElevatorSystemStatus.stopping state.
   */
  boolean addRequest(Request request) throws IllegalArgumentException;

  /**
   * Distributes the requests to the elevators.
   */
  void distributeRequests();

  /**
   * Starts the elevator system.
   * If ElevatorSystemStatus.running this method has no effect.
   * If ElevatorSystemStatus.stopping then an exception is thrown.
   * If ElevatorSystemStatus.outOfService then the building is started.
   * All the elevator doors are closed and the building system is ready to accept requests
   *
   * @return true if the building status is ElevatorSystemStatus.outOfService.
   * @throws IllegalArgumentException if the building is in ElevatorSystemStatus.stopping state.
   */
  boolean startElevatorSystem() throws IllegalArgumentException;

  /**
   * Stops the elevator system.
   * Tells all the elevators to go to the ground floor and stop servicing requests.
   * If ElevatorSystemStatus.running then the building is stopped.
   * If ElevatorSystemStatus.stopping then then this method has no effect.
   * If ElevatorSystemStatus.outOfService then this method has no effect.
   * All the elevators are put in ELEVATOR.stopping mode.
   * All requests are purged.
   *
   * @throws IllegalArgumentException if the building is in ElevatorSystemStatus.stopping state.
   */
  void stopElevatorSystem();

  /**
   * Moves the elevators by one step.
   */
  void step();

  /**
   * Returns a BuildingReport object that contains all the status of the building.
   *
   * @return a BuildingReport object.
   */
  BuildingReport getElevatorSystemStatus();
}
