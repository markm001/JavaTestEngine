package components;

import saikaone.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;
import physics2d.components.Rigidbody2D;
import util.AssetPool;

public class Flower extends Component {
    private transient Rigidbody2D rb;

    @Override
    public void start() {
        this.rb = gameObject.getComponent(Rigidbody2D.class);
        AssetPool.getSound("assets/sounds/powerup_appears.ogg").play();
        this.rb.setIsSensor(); // no collision
    }

    @Override
    public void beginCollision(GameObject object, Contact contact, Vector2f hitNormal) {
        PlayerController playerController = object.getComponent(PlayerController.class);
        if(playerController != null) {
            playerController.powerup();
            this.gameObject.destroy();
        }
    }
}
