//100% of this class was written by Danny Matlob

package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {
    private AppFactory factory;
    private Model model;
    private View view;
    protected ControlPanel cp;

    public AppPanel(AppFactory fac) {
        this.setLayout(new GridLayout());
        cp = new AppPanel.ControlPanel();

        factory = fac;
        model = factory.makeModel();
        view = factory.makeView(model);
        model.addPropertyChangeListener(this);

        cp.setPreferredSize(new Dimension(500, 500));
        view.setPreferredSize(new Dimension(500, 500));
        add(cp);
        add(view);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        System.out.println("AppPanel Received Command: " + cmmd);
        Command cmdObject = factory.makeEditCommand(model, cmmd, null);
        if (cmdObject == null) {
            try {
                switch (cmmd) {
                    //File Functions
                    case "Save As": {
                        String fName = Utilities.getFileName((String) null, false);
                        model.setFileName(fName);
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                        os.writeObject(this.model);
                        os.close();
                        break;
                    }
                    case "Save": {
                        if (model.getFileName() == null) {
                            String fName = Utilities.getFileName((String) null, false);
                            model.setFileName(fName);
                        }
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(model.getFileName()));
                        os.writeObject(this.model);
                        os.close();
                        break;
                    }
                    case "Open": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            String fName = Utilities.getFileName((String) null, true);
                            model.setFileName(fName);
                            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                            model.removePropertyChangeListener(this);
                            model = (Model) is.readObject();
                            model.initSupport();
                            model.addPropertyChangeListener(this);
                            view.setModel(model);
                            is.close();
                        }
                        break;
                    }

                    case "New": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            model.removePropertyChangeListener(this);
                            model = factory.makeModel();
                            model.addPropertyChangeListener(this);
                            view.setModel(model);
                        }
                        break;
                    }

                    case "Quit": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                            System.exit(0);
                        break;
                    }

                    case "About": {
                        Utilities.inform(factory.about());
                        break;
                    }

                    case "Help": {
                        Utilities.inform(factory.getHelp());
                        break;
                    }
                    default: {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                }
            } catch (Exception ex) {
                Utilities.error(ex);
            }
        } else {
            try {
                cmdObject.execute();
            } catch (Exception ex) {
                Utilities.error(ex);
            }
        }
    }

    public void display() {
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.pack();
        //frame.setSize(new Dimension(1100,575));
        frame.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    protected class ControlPanel extends JPanel {
        public ControlPanel() {
            this.setBackground(Color.PINK);
        }
    }
}