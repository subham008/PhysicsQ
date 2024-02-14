package physicsq.app;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.*;


// Custom JPanel to draw a red circle
class Particle extends JPanel {
  
    private int mass=20;
    private int Xvelocity=0;
    private int Yvelocity=0;
    

    public Particle (){
        // Add mouse listener to handle click events
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Display edit dialog when the particle is clicked
                showEditDialog();
            }
        });

     setOpaque(false);

    }
    public void  setMass(int m){
        mass=m;
    }

    public int getMass(){
        return mass;
    }

    public void setVelocity(int Xvel , int Yvel){
        Xvelocity=Xvel;
        Yvelocity=Yvel;
    }

    public void setXVelocity(int vel){
        this.Xvelocity=vel;
    }

    public void setYVelocity(int vel){
        this.Yvelocity=vel;
    }

    public int getXVelocity(){

        return Xvelocity;
    }
   
    public int getYVelocity(){

        return Yvelocity;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set color to red
        g.setColor(Color.RED);
        // Draw a filled circle
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 2;
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }



   // Method to display a dialog for editing mass and velocity
    private void showEditDialog() {
        JTextField massField = new JTextField(String.valueOf(mass));
        JTextField xVelocityField = new JTextField(String.valueOf(Xvelocity));
        JTextField yVelocityField = new JTextField(String.valueOf(Yvelocity));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Mass:"));
        panel.add(massField);
        panel.add(new JLabel("X Velocity:"));
        panel.add(xVelocityField);
        panel.add(new JLabel("Y Velocity:"));
        panel.add(yVelocityField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Particle",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                mass = Integer.parseInt(massField.getText());
                Xvelocity = Integer.parseInt(xVelocityField.getText());
                Yvelocity = Integer.parseInt(yVelocityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid integers.");
            }
        }
    }
}

