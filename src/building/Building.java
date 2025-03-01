package building;

import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorInterface;
import elevator.ElevatorReport;
import java.util.ArrayList;
import java.util.List;
import scanerzus.Request;


/**
 * This class represents a building, and implements the Building Interface.
 * The building has a number of floors, a number of elevators, elevator capacity,
 * elevator system status, a list of up requests, a list of down requests, and
 * an array of elevators.
 */
public class Building implements BuildingInterface {

  private final int numberOfFloors;
  private final int numberOfElevators;
  private final int elevatorCapacity;
  private ElevatorSystemStatus systemStatus;
  private List<Request> upRequests;
  private List<Request> downRequests;
  private final Elevator[] elevators;

  /**
   * The constructor for the building.
   *
   * @param numberOfFloors    the number of floors in the building.
   *                          must be greater than 2
   *                          must be less than or equal to 30
   * @param numberOfElevators the number of elevators in the building.
   *                          must be greater than 0
   * @param elevatorCapacity  the capacity of the elevators in the building.
   *                          must be greater than 3
   *                          must be less than or equal to 20
   * @throws IllegalArgumentException if the numberOfFloors, numberOfElevators
   *                                  or elevatorCapacity is out of range
   */
  public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity) {
    if (numberOfFloors < 3 || numberOfFloors > 30) {
      throw new IllegalArgumentException("numberOfFloors must be between 2 and 30");
    }
    if (numberOfElevators < 1) {
      throw new IllegalArgumentException("numberOfElevators must be greater than 0");
    }
    if (elevatorCapacity < 4 || elevatorCapacity > 20) {
      throw new IllegalArgumentException("elevatorCapacity must be between 3 and 20");
    }

    this.numberOfFloors = numberOfFloors;
    this.numberOfElevators = numberOfElevators;
    this.elevatorCapacity = elevatorCapacity;
    this.systemStatus = ElevatorSystemStatus.outOfService;
    this.upRequests = new ArrayList<>();
    this.downRequests = new ArrayList<>();
    this.elevators = initializeElevators(numberOfElevators);
  }

  /**
   * This method is used to initialize the elevators in the building.
   *
   * @param numberOfElevators the number of elevators in the building
   * @return an array of elevators
   */
  private Elevator[] initializeElevators(int numberOfElevators) {
    Elevator[] elevators = new Elevator[numberOfElevators];
    for (int i = 0; i < numberOfElevators; i++) {
      elevators[i] = new Elevator(numberOfFloors, elevatorCapacity);
    }
    return elevators;
  }

  @Override
  public boolean addRequest(Request request) throws IllegalArgumentException {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    if (request.getStartFloor() < 0
        || request.getStartFloor() >= numberOfFloors
        || request.getEndFloor() < 0
        || request.getEndFloor() >= numberOfFloors
        || request.getStartFloor() == request.getEndFloor()) {
      throw new IllegalArgumentException("Illegal request");
    }

    if (systemStatus == ElevatorSystemStatus.stopping
        || systemStatus == ElevatorSystemStatus.outOfService) {
      throw new IllegalArgumentException("Building is not accepting requests");
    }

    if (request.getStartFloor() < request.getEndFloor()) {
      upRequests.add(request);
    } else {
      downRequests.add(request);
    }
    return true;
  }

  /*
  @Override
  public void distributeRequests() {
    if (systemStatus == ElevatorSystemStatus.stopping
        || systemStatus == ElevatorSystemStatus.outOfService) {
      throw new IllegalArgumentException("Building cannot distribute requests");
    }

    if (!upRequests.isEmpty() && upRequests.size() <= elevatorCapacity) {
      for (Elevator e : elevators) {
        if (e.getCurrentFloor() == 0 && e.isTakingRequests()) {
          e.processRequests(upRequests);
          upRequests.clear();
        }
      }
    } else if (upRequests.size() > elevatorCapacity) {
      for (Elevator e : elevators) {
        if (e.getCurrentFloor() == 0 && e.isTakingRequests()) {
          e.processRequests(upRequests.subList(0, elevatorCapacity));
          upRequests = upRequests.subList(elevatorCapacity, upRequests.size());
          break;
        }
      }
    }

    if (!downRequests.isEmpty() && downRequests.size() <= elevatorCapacity) {
      for (Elevator e : elevators) {
        if (e.getCurrentFloor() == numberOfFloors - 1 && e.isTakingRequests()) {
          e.processRequests(downRequests);
          downRequests.clear();
        }
      }
    } else if (downRequests.size() > elevatorCapacity) {
      for (Elevator e : elevators) {
        if (e.getCurrentFloor() == numberOfFloors - 1 && e.isTakingRequests()) {
          e.processRequests(downRequests.subList(0, elevatorCapacity));
          downRequests = downRequests.subList(elevatorCapacity, downRequests.size());
          break;
        }
      }
    }
  }
   */

  @Override
  public void distributeRequests() {
    if (!this.upRequests.isEmpty() || !this.downRequests.isEmpty()) {
      for (Elevator elevator : elevators) {
        if (elevator.isTakingRequests()) {
          List requestsToProcess;
          if (elevator.getCurrentFloor() == 0) {
            requestsToProcess = this.getRequests(this.upRequests);
            elevator.processRequests(requestsToProcess);
          } else if (elevator.getCurrentFloor() == this.numberOfFloors - 1) {
            requestsToProcess = this.getRequests(this.downRequests);
            elevator.processRequests(requestsToProcess);
          }
        }
      }

    }
  }

  private List<Request> getRequests(List<Request> requests) {
    List<Request> requestsToReturn = new ArrayList();

    while (!requests.isEmpty() && requestsToReturn.size() < this.elevatorCapacity) {
      requestsToReturn.add(requests.remove(0));
    }

    return requestsToReturn;
  }

  @Override
  public boolean startElevatorSystem() throws IllegalArgumentException {
    if (systemStatus == ElevatorSystemStatus.stopping) {
      throw new IllegalArgumentException("Building is stopping");
    }
    if (systemStatus == ElevatorSystemStatus.outOfService) {
      systemStatus = ElevatorSystemStatus.running;
      for (ElevatorInterface elevator : elevators) {
        elevator.start();
      }
      return true;
    }
    return false;
  }

  @Override
  public void stopElevatorSystem() {
    if (systemStatus == ElevatorSystemStatus.running) {
      systemStatus = ElevatorSystemStatus.stopping;
      this.upRequests.clear();
      this.downRequests.clear();
      for (ElevatorInterface elevator : elevators) {
        elevator.takeOutOfService();
      }
    }
  }

  @Override
  public void step() {
    if (systemStatus == ElevatorSystemStatus.running) {
      this.distributeRequests();
      for (ElevatorInterface elevator : elevators) {
        elevator.step();
      }
    }

    if (systemStatus == ElevatorSystemStatus.stopping) {
      for (ElevatorInterface elevator : elevators) {
        elevator.step();
      }
      boolean allStopped = true;
      for (ElevatorInterface elevator : elevators) {
        if (elevator.getCurrentFloor() != 0) {
          allStopped = false;
        }
      }
      if (allStopped) {
        systemStatus = ElevatorSystemStatus.outOfService;
      }
    }
  }

  @Override
  public BuildingReport getElevatorSystemStatus() {
    ElevatorReport[] elevatorReports = new ElevatorReport[numberOfElevators];
    for (int i = 0; i < numberOfElevators; i++) {
      elevatorReports[i] = elevators[i].getElevatorStatus();
    }

    return new BuildingReport(numberOfFloors, numberOfElevators,
        elevatorCapacity, elevatorReports, upRequests, downRequests, systemStatus);
  }
}


