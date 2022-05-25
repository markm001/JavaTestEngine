package components;

import saikaone.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2d.components.Rigidbody2D;
import util.AssetPool;

public class MushroomAI extends Component{
    private transient boolean goingRight = true;
    private transient Rigidbody2D rb;
    private transient Vector2f speed = new Vector2f(1.0f, 0.0f);
    private transient float maxSpeed = 0.8f;
    private transient boolean hitPlayer = false;

    @Override
    public void start() {
        this.rb = gameObject.getComponent(Rigidbody2D.class);
        AssetPool.getSound("assets/sounds/powerup_appears.ogg").play();
    }

    @Override
    public void update(float dt) {
        if(goingRight && Math.abs(rb.getVelocity().x) < maxSpeed) {
            rb.addVelocity(speed);
        } else if(!goingRight && Math.abs(rb.getVelocity().x) < maxSpeed) {
            rb.addVelocity(new Vector2f(-speed.x, speed.y));
        }
    }

    @Override
    public void preSolve(GameObject object, Contact contact, Vector2f hitNormal) {
        //One way door concept:
        PlayerController playerController = object.getComponent(PlayerController.class);
        if(playerController != null) {
            contact.setEnabled(false);
            if(!hitPlayer) {
                if(playerController.isSmall()) {
                    playerController.powerup();
                } else {
                    AssetPool.getSound("assets/sounds/chug1.ogg").play();
                }
                this.gameObject.destroy();
                hitPlayer = true;
            }
        } else if(object.getComponent(Ground.class) == null) {
            contact.setEnabled(false);
            return;
        }
        if(Math.abs(hitNormal.y) < 0.1f) {
            goingRight = hitNormal.x < 0;
        }
    }
}
