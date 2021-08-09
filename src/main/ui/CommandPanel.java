package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommandPanel extends JPanel {

    private static final String ADD = "Add a patient";
    private static final String VIEW = "View a patient";
    private static final String EDIT = "Edit a patient";
    private static final String DELETE = "Delete a patient";
    private static final String LOAD = "Load a patient from file";
    private static final String SAVE = "Save a patient to file";

    private ActionListener listener;

    public CommandPanel(ActionListener listener) {
        this.listener = listener;

        setPreferredSize(new Dimension((PatientDatabaseGUI.WIDTH / 4), PatientDatabaseGUI.HEIGHT));
        setBackground(Color.RED);

        createButtons();

    }

    private void createButtons() {
        JButton buttonAdd = createButton("add", "ADD_GUI");
        JButton buttonView = createButton("view");
        JButton buttonEdit = createButton("edit", "edit_gui");
        JButton buttonDelete = createButton("delete", "Delete_GUI");
        JButton buttonLoad = createButton("load");
        JButton buttonSave = createButton("save");
    }

    private JButton createButton(String name, String action) {
        JButton button = new JButton(name.toUpperCase());
        button.addActionListener(listener);
        button.setActionCommand(action.toUpperCase());
        add(button);
        return button;
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name.toUpperCase());
        button.addActionListener(listener);
        button.setActionCommand(name.toUpperCase());
        add(button);
        return button;
    }

}
