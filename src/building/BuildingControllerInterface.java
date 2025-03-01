package building;

/**
 * Interface for the controller component in the MVC architecture of the building elevator system.
 * This class defines the core functionalities required to control the building.
 */
public interface BuildingControllerInterface {
  /**
   * This method is used to start the elevator system.
   */
  public void startElevatorSystem();

  /**
   * This method is used to stop the elevator system.
   */
  public void stopElevatorSystem();

  /**
   * This method is used to step the elevator system.
   */
  public void stepElevatorSystem();

  /**
   * This method is used to add a request to the system.
   *
   * @param startFloor The floor the request is made from.
   * @param endFloor The floor the request is made to.
   */
  public void makeRequest(int startFloor, int endFloor);

  /**
   * This method is used to quit the current simulation.
   */
  public void quit();
}
