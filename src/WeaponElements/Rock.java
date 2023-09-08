package WeaponElements;


import bagel.Image;

public class Rock extends Weapon {

    private Image RockImage;

    public Rock() {
        super();
        this.RockImage = new Image("/Users/fanjunyi/Desktop/project-2-skeleton /res/level-1/rock.png");
    }

    @Override
    public void spawnWeapon() {
        RockImage.draw(corX, corY);
    }
}
