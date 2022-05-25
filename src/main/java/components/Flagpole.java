package components;

import saikaone.GameObject;
import org.jbox2d.dynamics.contacts.Contact;
import org.joml.Vector2f;

public class Flagpole extends Component {
    private boolean isTop = false;

    public Flagpole(boolean isTop) {
        this.isTop = isTop;
    }

    @Override
    public void beginCollision(GameObject object, Contact contact, Vector2f hitNormal) {
        PlayerController playerController = object.getComponent(PlayerController.class);
        if(playerController != null) {
            playerController.playWinAnimation(this.gameObject);
        }
    }
}
