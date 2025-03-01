package main;

import building.Building;
import building.BuildingController;
import building.BuildingControllerInterface;
import building.BuildingView;
import building.BuildingViewInterface;
import java.io.IOException;
import java.util.Scanner;
import scanerzus.Request;

/**
 * Run a Connect 4 game interactively on the console. You can make the number of rows and columns
 * configurable by passing them as command-line arguments. It is also OK to hard-code the number of
 * rows and columns to 6 and 7, respectively.
 */
public class Main {
  /**
   * Run a Connect 4 game interactively on the console. Rows = 6, Columns = 7.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    Building model = new Building(10, 7, 5);
    BuildingViewInterface view = new BuildingView();
    BuildingControllerInterface controller = new BuildingController(view, model);
    controller.startElevatorSystem();
  }
}