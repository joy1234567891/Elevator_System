package building;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * This class represents the view component of the building elevator system.
 * This class is responsible for displaying the building status.
 */
public class BuildingView extends JFrame implements BuildingViewInterface {
  private JTextArea reportTextArea;
  private JButton startButton;
  private JButton stopButton;
  private JButton stepButton;
  private JButton requestButton;
  private JButton quitButton;

  /**
   * Constructor for the BuildingView class.
   */
  public BuildingView() {
    setTitle("Building Management System");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    // Add empty border around the main panel
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add 10 pixels of empty border

    // Initialize components with larger font size
    Font textAreaFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);

    // Initialize components
    reportTextArea = new JTextArea(10, 30);
    reportTextArea.setEditable(false);
    reportTextArea.setFont(textAreaFont); // Set font for text area

    startButton = new JButton("Restart");
    stopButton = new JButton("Stop");
    stepButton = new JButton("Step");
    requestButton = new JButton("Request");
    quitButton = new JButton("Quit");

    // Set font for buttons
    Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    startButton.setFont(buttonFont);
    stopButton.setFont(buttonFont);
    stepButton.setFont(buttonFont);
    requestButton.setFont(buttonFont);
    quitButton.setFont(buttonFont);

    // Set size for buttons
    Dimension buttonSize = new Dimension(150, 40);
    startButton.setPreferredSize(buttonSize);
    stopButton.setPreferredSize(buttonSize);
    stepButton.setPreferredSize(buttonSize);
    requestButton.setPreferredSize(buttonSize);
    quitButton.setPreferredSize(buttonSize);

    // Set border for buttons
    LineBorder border = new LineBorder(Color.BLACK, 1); // Black border with thickness 2
    startButton.setBorder(border);
    stopButton.setBorder(border);
    stepButton.setBorder(border);
    requestButton.setBorder(border);
    quitButton.setBorder(border);

    // Add buttons to button panel
    JPanel buttonPanel = new JPanel(
        new GridLayout(1, 5, 20, 10)); // Add 20 pixels horizontal gap and 10 pixels vertical gap
    buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Add 10 pixels of empty border
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);
    buttonPanel.add(stepButton);
    buttonPanel.add(requestButton);
    buttonPanel.add(quitButton);

    // Add components to the main panel
    JScrollPane scrollPane = new JScrollPane(reportTextArea);
    mainPanel.add(scrollPane, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    // Add main panel to the frame
    add(mainPanel);

    setVisible(true);
  }

  @Override
  public void addFeatures(BuildingControllerInterface features) {
    // Add action listeners to buttons
    startButton.addActionListener(evt -> {
      features.startElevatorSystem();
    });

    stopButton.addActionListener(evt -> {
      features.stopElevatorSystem();
    });

    stepButton.addActionListener(evt -> {
      features.stepElevatorSystem();
    });

    requestButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Handle request button click
        String startFloor = JOptionPane.showInputDialog("Enter start floor:");
        String endFloor = JOptionPane.showInputDialog("Enter end floor:");
        // Process the startFloor and endFloor inputs
        features.makeRequest(Integer.parseInt(startFloor), Integer.parseInt(endFloor));
      }
    });

    quitButton.addActionListener(evt -> {
      features.quit();
    });
  }

  @Override
  public void showBuildingStatus(BuildingReport buildingReport) {
    reportTextArea.setText(buildingReport.toString());
  }

  @Override
  public void showQuitMessage() {
    JOptionPane.showMessageDialog(this, "Quitting Building System", "Quit",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.INFORMATION_MESSAGE);
  }

}
