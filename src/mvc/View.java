//100% of this class was written by Danny Matlob
package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class View extends JPanel implements PropertyChangeListener {
    protected Model model;
    public View(Model m) {
        model = m;
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model m) {
        model.removePropertyChangeListener(this);
        model = m;
        model.addPropertyChangeListener(this);
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
