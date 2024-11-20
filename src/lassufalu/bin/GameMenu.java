/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lassufalu.bin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import lassufalu.bin.GameGUI;

/**
 *
 * @author Mielec Attila (MNZVUM)
 */
public class GameMenu {
    
    private JFrame mainFrame;
    private JButton newGameButton, loadGameButton, quitButton, backButton;
    private JPanel mainMenuPanel, newGamePanel;
    public static String file;
    public static String directory;
    private static double money;
    private  static int population;
    private static String date;    
    private static String sysdate;
    
    public GameMenu(){
        directory = "src/save_load";
        prepareGUI();
        showMainMenu();
    }
    
    
    public void prepareGUI() {
        mainFrame = new JFrame("Lassu Falu");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new CardLayout());

        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(4, 1));

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                file = "save" + fileCount()+ ".txt";
                createFile(file);
                GameGUI gamegui = new GameGUI(false); 
            }
        });
        mainMenuPanel.add(newGameButton);

        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> filenames = getFileNames();
                File folder = new File(directory);
                    for (File file : folder.listFiles()) {
                        // Check if it's a file and if it's empty
                        if (file.isFile() && file.length() == 0) {
                            if (file.delete()) {
                                System.out.println("Deleted empty file: " + file.getName());
                            } else {
                                System.out.println("Failed to delete file: " + file.getName());
                            }
                        }
                    }
                showNewGamePanel();
                
            }
        });
        mainMenuPanel.add(loadGameButton);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainMenuPanel.add(quitButton);

        mainFrame.add(mainMenuPanel, "Main Menu");

        newGamePanel = new JPanel();
        newGamePanel.setLayout(new BorderLayout());

        JPanel unitContainer = new JPanel();
        unitContainer.setLayout(new BoxLayout(unitContainer, BoxLayout.Y_AXIS));

        ArrayList<String> filenames = getFileNames();
        
        for (String filename : filenames) {
            if(loadFromFile(filename) == 0){
            JPanel unit = new JPanel();
            unit.setLayout(new BoxLayout(unit, BoxLayout.X_AXIS));

            JLabel fileNameLabel = new JLabel(trimFileNames(filename));
            JLabel dateLabel = new JLabel(date);
            JLabel sysDateLabel = new JLabel(sysdate);
            JButton loadButton = new JButton("Load");
            loadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    loadFromFile(filename);
                    file = filename;
                    GameGUI gamegui = new GameGUI(true); 
                    showMainMenu();
                }
            });

            unit.add(fileNameLabel);
            unit.add(Box.createHorizontalStrut(10)); // add some space
            unit.add(dateLabel);
            unit.add(Box.createHorizontalStrut(10)); // add some space
            unit.add(sysDateLabel);
            unit.add(Box.createHorizontalStrut(10)); // add some space
            unit.add(loadButton);

            unitContainer.add(unit);
           }
        }

        JScrollPane scrollPane = new JScrollPane(unitContainer);
        newGamePanel.add(scrollPane, BorderLayout.CENTER);

        // Add back button
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        newGamePanel.add(backButton, BorderLayout.PAGE_END);


        mainFrame.add(newGamePanel, "Load Game");
        mainFrame.setVisible(true);
    }
    
    public void showMainMenu() {
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "Main Menu");
    }

    private void showNewGamePanel() {
        CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
        cardLayout.show(mainFrame.getContentPane(), "Load Game");
        
    }
    
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public JPanel getNewGamePanel() {
        return newGamePanel;
    }
    
    private int fileCount(){
        Path dir = Paths.get(directory);
        long count = 1;

        try (Stream<Path> files = Files.list(dir)) {
            count = files
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".txt"))
                .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (int) count;
    }
    
    public void createFile(String f) {
        Path filePath = Paths.get(directory, f);

        try {
            Files.createFile(filePath);
            System.out.println("File created successfully: " + filePath);
        } catch (FileAlreadyExistsException e) {
            System.err.println("The file already exists: " + filePath);
        } catch (IOException e) {
            // Some other I/O error occurred
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getFileNames(){
        Path dir = Paths.get(directory);

        // Create an ArrayList to store the file names
        ArrayList<String> fileNames = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (!Files.isDirectory(file)) {
                    fileNames.add(file.getFileName().toString());
                }
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println(e);
        }

        // Print the file names
        return fileNames;
    }
    
    public static String trimFileNames(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return fileName.substring(0, lastDotIndex);
        }
        return fileName;
    }
    
    public int loadFromFile(String filename) {
        Path filePath = Paths.get(directory, filename);
        try {
            // check if the file is empty
            if (Files.size(filePath) == 0) {
                return -1;  // return an error code or handle it as you see fit
            }

            try (BufferedReader in = new BufferedReader(new FileReader(filePath.toString()))) {
                String line;
                int i = 0;
                int section = 0; // 0: blocks, 1: residentals, 2: money, 3: population, 4: date, 5: sysdate
                
                while ((line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    section++;
                    continue;
                }
                switch (section) {
                case 2:
                    money = Double.parseDouble(line);
                    break;
                case 3:
                    population = 0;
                    break;
                case 4:
                    date = line;
                    break;
                 case 5:
                    sysdate = line;
                    break;
                case 6 :
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    Time.lDate = LocalDate.parse(line,formatter);
                default:
                    // Handle any other data that may exist
                    break;
            }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // indicate successful operation
        }

    public static double getMoney() {
        return money;
    }

    public static int getPopulation() {
        return population;
    }

    public static String getDate() {
        return date;
    }

    public static String getSysdate() {
        return sysdate;
    }
    
    
}

