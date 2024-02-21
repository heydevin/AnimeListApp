package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Anime;
import model.AnimeList;

// a new window for user to enter anime information to add to display table
public class AddWindow implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel trackingLabel;
    private JLabel recipientLabel;
    private JTextField trackingTextField;
    private JTextField recipientTextField;
    private JButton buttonEnter;
    private String name;
    private String date;
    private DefaultTableModel tableModel;
    private Anime newAnime;
    private AnimeList animeList;

    // MODIFIES: this
    // EFFECTS: UI package constructing for the add window
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AddWindow(DefaultTableModel dtm, AnimeList animeList) {
        tableModel = dtm;
        this.animeList = animeList;

        frame = new JFrame();
        trackingLabel = new JLabel("Anime Name: ");
        recipientLabel = new JLabel("Anime Date: ");
        trackingTextField = new JTextField();
        recipientTextField = new JTextField();
        panel = new JPanel();

        frame = new JFrame("Add An Anime");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        panel.setLayout(null);

        trackingLabel.setBounds(50, 50, 200, 100);
        trackingLabel.setFont(new Font(null, Font.BOLD, 14));
        frame.add(trackingLabel);

        trackingTextField.setBounds(185, 86, 150, 30);
        frame.add(trackingTextField);

        recipientLabel.setBounds(50, 80, 200, 100);
        recipientLabel.setFont(new Font(null, Font.BOLD, 14));
        frame.add(recipientLabel);

        recipientTextField.setBounds(185, 116, 150, 30);
        frame.add(recipientTextField);

        buttonEnter = new JButton("Enter");
        buttonEnter.setBounds(185, 150, 100, 50);
        buttonEnter.addActionListener(new EnterListener());
        frame.add(buttonEnter);

    }

    // EFFECTS: a getter method for anime
    public Anime getNewAnime() {
        return newAnime;
    }

    // EFFECTS: nothing
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // MODIFIES: this
    // EFFECTS: action happens when user click on enter button, add the anime for the user to the table
    private class EnterListener extends AbstractAction {

        // REQUIRES: action event will only happen when user click on the enter button
        // MODIFIES: this
        // EFFECTS: add a new anime to the display table with user input
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonEnter) {
                name = trackingTextField.getText();
                date = recipientTextField.getText();
                newAnime = new Anime(name, Anime.statusA, date);
                animeList.addAnime(newAnime);
                tableModel.addRow(new Object[]{name, Anime.statusA, date});
            }
        }
    }

}

