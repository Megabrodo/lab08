package it.unibo.mvc;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
//import it.unibo.mvc.view.DrawNumberStandardOutputView;
//import it.unibo.mvc.view.DrawNumberSwingView;
//import java.lang.Class;
import java.lang.reflect.InvocationTargetException;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException to add general security
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) 
        throws 
        ClassNotFoundException, 
        NoSuchMethodException, 
        InstantiationException, 
        IllegalAccessException, 
        InvocationTargetException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final var swingV = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final var outV = Class.forName("it.unibo.mvc.view.DrawNumberStandardOutputView");
        final var swingVConstructor = swingV.getConstructor();
        final var outVConstructor = outV.getConstructor();

        for (int i = 0; i < 3; i++) {
            final DrawNumberView newViewSwing = (DrawNumberView) swingVConstructor.newInstance();
            final DrawNumberView newViewOut = (DrawNumberView) outVConstructor.newInstance();
            app.addView(newViewSwing);
            app.addView(newViewOut);
        }
        /*app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());*/
    }
}
