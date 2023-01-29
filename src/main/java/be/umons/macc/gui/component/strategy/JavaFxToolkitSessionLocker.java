package be.umons.macc.gui.component.strategy;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.SECONDS;

public interface JavaFxToolkitSessionLocker {

    default void countDownLatch() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            countDownLatch.countDown();
        });

        if (!countDownLatch.await(10L, SECONDS))
            throw new ExceptionInInitializerError();
    }
}
