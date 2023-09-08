package WeaponElements;

import bagel.Image;

public class Bomb extends Weapon {

    private Image bombImage;

    public Bomb() {
        super();
        this.bombImage = new Image("/Users/fanjunyi/Desktop/project-2-skeleton /res/level-1/bomb.png");
    }

    @Override
    public void spawnWeapon() {
        bombImage.draw(corX, corY);
    }
}
