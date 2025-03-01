package building;

/**
 * Interface for the view component in the MVC architecture of the building elevator system.
 * This class defines the core functionalities required to display the building's state
 * and messages to the user.
 */
public interface BuildingViewInterface {

  /**
   * Adds the controller features to the view.
   *
   * @param features the controller features to be added to the view
   */
  public void addFeatures(BuildingControllerInterface features);

  /**
   * Updates the view with the current state of the building.
   *
   * @param buildingReport the current state of the building
   */
  public void showBuildingStatus(BuildingReport buildingReport);

  /**
   * Displays quit message.
   */
  public void showQuitMessage();

  /**
   * Displays error message.
   * @param errorMessage the error message to be displayed
   */
  public void showErrorMessage(String errorMessage);
}


