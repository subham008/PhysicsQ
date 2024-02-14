package physicsq.app;

import java.util.ArrayList;
import java.util.List;

class Movement {
    private List<Particle> particles;
    int windowHeight,windowWidth;
    public Movement(int w, int h) {
        particles = new ArrayList<>();
        this.windowHeight=h;
        this.windowWidth=w;
    }

    // Method to add a particle to the movement system
    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    // Method to move all particles by specified dx and dy
    public void moveParticles(int dx, int dy) {
        // Check collisions and move only if the next position is not occupied
        for (Particle particle : particles) {
            int newX = particle.getX() + dx;
            int newY = particle.getY() + dy;
            if (!isOccupied(newX, newY)) {
                particle.setLocation(newX, newY);
            }
        }
    }

    public void moveParticles() {
       
    
        for (Particle particle : particles) {
            int newX = particle.getX() + particle.getXVelocity();
            int newY = particle.getY() + particle.getYVelocity();
    
            // Check for collisions with the window boundaries
            if (newX < 0 || newX + particle.getWidth() > windowWidth) {
                // Reverse the x-velocity to bounce off the horizontal walls
                particle.setXVelocity(-particle.getXVelocity());
                // Adjust the position to keep the particle inside the window
                newX = Math.max(0, Math.min(windowWidth - particle.getWidth(), newX));
            }
    
            if (newY < 0 || newY + particle.getHeight() > windowHeight) {
                // Reverse the y-velocity to bounce off the vertical walls
                particle.setYVelocity(-particle.getYVelocity());
                // Adjust the position to keep the particle inside the window
                newY = Math.max(0, Math.min(windowHeight - particle.getHeight(), newY));
            }
    
            particle.setLocation(newX, newY);
        }
        handleCollisions();
    }
    // Method to check if the specified location is occupied by another particle
    private boolean isOccupied(int x, int y) {
        for (Particle particle : particles) {
            if (particle.getX() == x && particle.getY() == y) {
                return true;
            }
        }
        return false;
    }


    // Method to handle collisions between particles
private void handleCollisions() {
    for (int i = 0; i < particles.size(); i++) {
        Particle p1 = particles.get(i);
        for (int j = i + 1; j < particles.size(); j++) {
            Particle p2 = particles.get(j);
            if (p1.getBounds().intersects(p2.getBounds())) {
                // Collision occurred, update velocities
                int v1i = p1.getXVelocity();
                int v2i = p2.getXVelocity();
                int m1 = p1.getMass();
                int m2 = p2.getMass();

                // Calculate final velocities using conservation of momentum and kinetic energy
                int v1f = ((m1 - m2) * v1i + 2 * m2 * v2i) / (m1 + m2);
                int v2f = ((m2 - m1) * v2i + 2 * m1 * v1i) / (m1 + m2);

                p1.setXVelocity(v1f);
                p2.setXVelocity(v2f);
            }
        }
    }
}

}