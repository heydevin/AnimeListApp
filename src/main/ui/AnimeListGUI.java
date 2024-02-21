package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import model.Anime;
import model.AnimeList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// An Anime List Application with UI for Anime fans to check information of their anime
public class AnimeListGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/animeListGUI.json";
    private Anime newAnime;
    private AnimeList animeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private AddWindow add;

    private JFrame frame;
    private JPanel panel;
    private JPanel panelTable;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonStatus;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel animeListTable;
    private int selectedRow;

    private ImageIcon image;
    private JLabel imageLabel;

    // MODIFIES: this
    // EFFECTS: creates a GUI with table that shows information and interact with the user inputs
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AnimeListGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        animeListTable = new DefaultTableModel();
        animeList = new AnimeList();

        animeListTable.addColumn("Anime Name");
        animeListTable.addColumn("Anime Status");
        animeListTable.addColumn("Anime Date");

        animeListTable.addRow(new Object[]{"Kazuha", Anime.statusA, "11/21"});
        newAnime = new Anime("Kazuha", "a", "11/21");
        animeList.addAnime(newAnime);
        animeListTable.addRow(new Object[]{"Ayaka", Anime.statusB, "10/28"});
        newAnime = new Anime("Ayaka", "b", "10/28");
        animeList.addAnime(newAnime);

        table = new JTable(animeListTable);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.setBackground(Color.white);
        table.setVisible(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);

        panel = new JPanel();
        panelTable = new JPanel();
        frame = new JFrame("Anime List");
        frame.setSize(710, 790);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelTable.add(scrollPane);
        panelTable.setBounds(105, 15, 500, 300);
        panel.setBackground(Color.white);
        panelTable.setBackground(Color.orange);
        frame.add(panelTable);
        frame.add(panel);

        panel.setLayout(null);

        buttonSave = new JButton("Save");
        buttonSave.setBounds(600, 590, 100, 75);
        buttonSave.addActionListener(new SaveListener());
        panel.add(buttonSave);

        buttonLoad = new JButton("Load");
        buttonLoad.setBounds(600, 675, 100, 75);
        buttonLoad.addActionListener(new LoadListener());
        panel.add(buttonLoad);

        buttonAdd = new JButton("Add");
        buttonAdd.setBounds(600, 335, 100, 75);
        buttonAdd.addActionListener(new AddListener());
        panel.add(buttonAdd);

        buttonRemove = new JButton("Remove");
        buttonRemove.setBounds(600, 420, 100, 75);
        buttonRemove.addActionListener(new RemoveListner());
        panel.add(buttonRemove);

        buttonStatus = new JButton("Mark Status");
        buttonStatus.setBounds(600, 505, 100, 75);
        buttonStatus.addActionListener(new StatusListener());
        panel.add(buttonStatus);

        image = new ImageIcon("/Users/deshenglin/IdeaProjects/project_z3m1r/data/friends.jpeg");
        image.setImage(image.getImage().getScaledInstance(710,521,Image.SCALE_DEFAULT));
        imageLabel = new JLabel(image);
        imageLabel.setBounds(0, 315, 710, 521);
        panel.add(imageLabel);

        frame.setBackground(Color.orange);
        frame.setVisible(true);

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    // EFFECTS: nothing
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // EFFECTS: main constructor creates a new GUI to run
    public static void main(String[] args) {
        new AnimeListGUI();
    }

    private void printLog() {
        for (Event e: EventLog.getInstance()) {
            System.out.println(e.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on load button, loads data from last time for the user
    private class LoadListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: load the data saved from last time
        @Override
        public void actionPerformed(ActionEvent e) {
            animeList = new AnimeList();
            try {
                animeList = jsonReader.read();
                System.out.println("        Successfully loaded Anime List from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            List<Anime> pullAnimeList = animeList.getAnimeList();

            int rowCount = animeListTable.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                animeListTable.removeRow(i);
            }
            for (Anime pull : pullAnimeList) {
                animeListTable.addRow(new Object[]{pull.getName(), pull.getStatus(), pull.getDate()});
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on save button, save data for the user
    private class SaveListener extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: save the anime list table for the user for next time to load
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(animeList);
                jsonWriter.close();
                System.out.println("        Successfully saved Anime List to " + JSON_STORE + "\n");
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on add button, add a new anime for the user
    private class AddListener extends AbstractAction {

        // REQUIRES: action event must be user click on the add button
        // MODIFIES: this
        // EFFECTS: create a new window for add function
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonAdd) {
                add = new AddWindow(animeListTable, animeList);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on remove button, remove an anime from the list for the user
    private class RemoveListner extends AbstractAction {

        // REQUIRES: action event will only happen when user click on the enter button
        // MODIFIES: this
        // EFFECTS: remove the selected row for user
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonRemove) {
                selectedRow = table.getSelectedRow();
                animeList.remove(animeListTable.getValueAt(selectedRow,0).toString());
                animeListTable.removeRow(selectedRow);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on mark status button, mark the status of an anime
    private class StatusListener extends AbstractAction {

        // REQUIRES: action event will only happen when user click on the mark status button
        // MODIFIES: this
        // EFFECTS: mark the anime status for the user
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == buttonStatus) {
                selectedRow = table.getSelectedRow();
                if (animeListTable.getValueAt(selectedRow,1) == Anime.statusA) {
                    animeList.mark(animeListTable.getValueAt(selectedRow,0).toString());
                } else {
                    animeList.mark(animeListTable.getValueAt(selectedRow,0).toString());
                }
            }

            List<Anime> pullAnimeList = animeList.getAnimeList();

            int rowCount = animeListTable.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                animeListTable.removeRow(i);
            }
            for (Anime pull : pullAnimeList) {
                animeListTable.addRow(new Object[]{pull.getName(), pull.getStatus(), pull.getDate()});
            }

        }
    }

}
