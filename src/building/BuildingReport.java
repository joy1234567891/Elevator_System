package building;

import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.util.List;
import scanerzus.Request;


/**
 * This is the reporting class for the building.
 */
public class BuildingReport {
  int numFloors;
  int numElevators;

  int elevatorCapacity;

  ElevatorReport[] elevatorReports;

  List<Request> upRequests;

  List<Request> downRequests;

  ElevatorSystemStatus systemStatus;

  /**
   * This constructor is used to create a new BuildingReport object.
   *
   * @param numFloors        The number of floors in the building.
   * @param numElevators     The number of elevators in the building.
   * @param elevatorCapacity The capacity of the elevators.
   * @param elevatorsReports The status of the elevators.
   * @param upRequests       The up requests for the elevators.
   * @param downRequests     The down requests for the elevators.
   * @param systemStatus     The status of the elevator system.
   */
  public BuildingReport(int numFloors,
                        int numElevators,
                        int elevatorCapacity,
                        ElevatorReport[] elevatorsReports,
                        List<Request> upRequests,
                        List<Request> downRequests,
                        ElevatorSystemStatus systemStatus) {
    this.numFloors = numFloors;
    this.numElevators = numElevators;
    this.elevatorCapacity = elevatorCapacity;
    this.elevatorReports = elevatorsReports;
    this.upRequests = upRequests;
    this.downRequests = downRequests;
    this.systemStatus = systemStatus;
  }

  /**
   * This method is used to get the number of floors in the building.
   *
   * @return the number of floors in the building
   */
  public int getNumFloors() {
    return this.numFloors;
  }

  /**
   * This method is used to get the number of elevators in the building.
   *
   * @return the number of elevators in the building
   */
  public int getNumElevators() {
    return this.numElevators;
  }

  /**
   * This method is used to get the max occupancy of the elevator.
   *
   * @return the max occupancy of the elevator.
   */
  public int getElevatorCapacity() {
    return this.elevatorCapacity;
  }

  /**
   * This method is used to get the status of the elevators.
   *
   * @return the status of the elevators.
   */
  public ElevatorReport[] getElevatorReports() {
    return this.elevatorReports;
  }

  /**
   * This method is used to get the up requests for the elevators.
   *
   * @return the requests for the elevators.
   */
  public List<Request> getUpRequests() {
    return this.upRequests;
  }

  /**
   * This method is used to get the down requests for the elevators.
   *
   * @return the requests for the elevators.
   */
  public List<Request> getDownRequests() {
    return this.downRequests;
  }

  /**
   * This method is used to get the status of the elevator system.
   *
   * @return the status of the elevator system.
   */
  public ElevatorSystemStatus getSystemStatus() {
    return this.systemStatus;
  }

  private String centreString(String s, int width) {
    int leftPadding = (width - 2 - s.length()) / 2;
    int rightPadding = width - leftPadding - s.length();
    return "*" + " ".repeat(leftPadding) + s + " ".repeat(rightPadding) + "*";
  }

  /**
   * This method is used to get the status of the elevator system.
   *
   * @return the status of the elevator system.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 62; i++) {
      sb.append("-");
    }
    sb.append("\n");
    sb.append(this.centreString("Floors: " + numFloors
        + ", Elevators: " + numElevators
        + ", Capacity: " + elevatorCapacity, 60));
    sb.append("\n");
    sb.append(this.centreString("Elevator System: " + systemStatus, 60));
    sb.append("\n");
    for (int i = 0; i < 62; i++) {
      sb.append("-");
    }
    sb.append("\n");
    sb.append("*Requests: \n");
    sb.append("Up: ");
    for (Request request : upRequests) {
      sb.append(request.toString());
      sb.append(", ");
    }

    // Find the index of the last character in the string
    int i = sb.length() - 1;

    // Find the index of the first non-whitespace character before the comma
    while (i >= 0 && sb.charAt(i) == ' ') {
      i--;
    }

    // Check if the character before the comma is a comma
    if (i >= 0 && sb.charAt(i) == ',') {
      // Delete the comma and any whitespace characters after it
      sb.delete(i, sb.length());
    }

    sb.append("\nDown: ");
    for (Request request : downRequests) {
      sb.append(request.toString());
      sb.append(", ");
    }
    // Find the index of the last character in the string
    int j = sb.length() - 1;

    // Find the index of the first non-whitespace character before the comma
    while (j >= 0 && sb.charAt(j) == ' ') {
      j--;
    }

    // Check if the character before the comma is a comma
    if (j >= 0 && sb.charAt(j) == ',') {
      // Delete the comma and any whitespace characters after it
      sb.delete(j, sb.length());
    }

    sb.append("\n");
    for (int k = 0; k < 62; k++) {
      sb.append("-");
    }
    sb.append("\n*Elevator Status: \n");
    for (ElevatorReport elevatorReport : elevatorReports) {
      sb.append("Elevator " + elevatorReport.getElevatorId() + ": ");
      sb.append(elevatorReport.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}