package physicsq.app;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Animation implements ActionListener {
    private Movement movement;
    private Timer timer;
    int delay=500;

    public Animation(Movement movement) {
        this.movement = movement;

    }

    // Method to start the animation
    public void startAnimation() {
        if (timer == null) {
            // Create a Timer with delay of 50 milliseconds
            timer = new Timer(delay, this);
            timer.start(); // Start the timer
        }
    }

    // Method to stop the animation
    public void stopAnimation() {
        if (timer != null) {
            timer.stop(); // Stop the timer
            timer = null; // Reset the timer
        }

    }

    // Method to resume the animation
    public void resumeAnimation() {
        if (timer == null) {
            // Start the timer if it's not already running
            startAnimation();
        }
    }

    public void changeDelay(int delay) {
        if (timer != null) {
            this.delay=delay;
            timer=new Timer(delay, this);
            timer.start();     
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move particles in each timer tick
        movement.moveParticles();
    }
}
