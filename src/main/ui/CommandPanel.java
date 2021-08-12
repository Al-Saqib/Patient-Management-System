package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// Represents a command panel that provides operational functionality for user

public class CommandPanel extends JPanel {

    private static final String ADD = "Add a patient";
    private static final String VIEW = "View a patient";
    private static final String EDIT = "Edit a patient";
    private static final String DELETE = "Delete a patient";
    private static final String LOAD = "Load a patient from file";
    private static final String SAVE = "Save a patient to file";

    private ActionListener listener;

    // EFFECTS: constructs the command panel

    public CommandPanel(ActionListener listener) {
        this.listener = listener;

        setPreferredSize(new Dimension((PatientDatabaseGUI.WIDTH / 4), PatientDatabaseGUI.HEIGHT));
        setBackground(Color.RED);

        createButtons();

    }

    // EFFECTS: creates buttons for commands with the horizontal plane

    private void createButtons() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;

        JButton buttonAdd = createButton("add", "ADD_GUI", c, 1);
        JButton buttonView = createButton("view", c, 2);
        JButton buttonEdit = createButton("edit", "edit_gui", c, 3);
        JButton buttonDelete = createButton("delete", "Delete_GUI", c, 4);
        JButton buttonLoad = createButton("load", c, 5);
        JButton buttonSave = createButton("save", c, 6);
    }

    // EFFECTS: creates buttons for commands with the vertical plane

    private JButton createButton(String name, String action, GridBagConstraints c, int y) {
        JButton button = new JButton(name.toUpperCase());
        button.addActionListener(listener);
        button.setActionCommand(action.toUpperCase());
        c.gridy = y;
        add(button, c);
        add(button);
        return button;
    }

    // EFFECTS: creates the command buttons with with the vertical plane

    private JButton createButton(String name, GridBagConstraints c, int y) {
        JButton button = new JButton(name.toUpperCase());
        button.addActionListener(listener);
        button.setActionCommand(name.toUpperCase());
        c.gridy = y;
        add(button, c);
        return button;
    }

}
