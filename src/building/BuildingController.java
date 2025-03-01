package building;

import scanerzus.Request;

/**
 * Implementation of the controller component in the MVC architecture of
 * the building elevator system.
 * This class implements the core functionalities required to control the building.
 */
public class BuildingController implements BuildingControllerInterface {
  private BuildingViewInterface view;
  private Building model;

  /**
   * Constructor for the BuildingController class.
   *
   * @param view The view component of the building elevator system.
   * @param model The model component of the building elevator system.
   */
  public BuildingController(BuildingViewInterface view, Building model) {
    this.view = view;
    this.model = model;
    view.addFeatures(this);
  }

  @Override
  public void startElevatorSystem() {
    try {
      model.startElevatorSystem();
      view.showBuildingStatus(model.getElevatorSystemStatus());
    } catch (IllegalArgumentException e) {
      view.showErrorMessage(e.getMessage());
      view.showBuildingStatus(model.getElevatorSystemStatus());
    }
  }

  @Override
  public void stopElevatorSystem() {
    model.stopElevatorSystem();
    view.showBuildingStatus(model.getElevatorSystemStatus());
  }

  @Override
  public void stepElevatorSystem() {
    model.step();
    view.showBuildingStatus(model.getElevatorSystemStatus());
  }

  @Override
  public void makeRequest(int startFloor, int endFloor) {
    try {
      model.addRequest(new Request(startFloor, endFloor));
      view.showBuildingStatus(model.getElevatorSystemStatus());
    } catch (IllegalArgumentException e) {
      view.showErrorMessage(e.getMessage());
      view.showBuildingStatus(model.getElevatorSystemStatus());
    }
  }

  @Override
  public void quit() {
    view.showQuitMessage();
    System.exit(0);
    ;
  }
}
