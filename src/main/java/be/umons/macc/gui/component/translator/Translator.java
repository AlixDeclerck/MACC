package be.umons.macc.gui.component.translator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Translator {

    protected void translateButtons(Map<String, String> library, List<Button> buttons) {
        library.forEach((k, v) -> {
            List<Button> cloned_list = new LinkedList<>(buttons);
            for (Button b : cloned_list) {
                if (b.getId() != null)
                    if (b.getId().equals(k)) {
                        b.setText(v);
                        buttons.remove(b);
                }
            }
        });
    }

    protected void translateLabels(Map<String, String> library, List<Label> labels) {
        library.forEach((k, v) -> {
            List<Label> cloned_list = new LinkedList<>(labels);
            for (Label b : cloned_list) {
                if (b.getId() != null)
                    if (b.getId().equals(k)) {
                        b.setText(v);
                        labels.remove(b);
                    }
            }
        });
    }

}
