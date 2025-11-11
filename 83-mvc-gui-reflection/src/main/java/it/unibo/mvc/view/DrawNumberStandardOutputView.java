package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawResult;

/**
 * Graphical {@link DrawNumberView} implementation.
 */
public final class DrawNumberStandardOutputView implements DrawNumberView {
    /**
     * Builds a new Output-based interactive view.
     */
    @Override
    public void start() {
    }

    @Override
    public void setController(final DrawNumberController observer) {
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription()); // NOPMD we don't use logs
    }
}
