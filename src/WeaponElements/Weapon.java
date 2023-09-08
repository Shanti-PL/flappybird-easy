package WeaponElements;

import Game.ShadowFlap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weapon {

    public int shootSpeed;
    public int corX = ShadowFlap.WIDTH;
    public int corY;

    public List<Weapon> weapons;

    /**
     * Help to create random integers
     */
    private Random random = new Random();


    public Weapon() {
        this.corY = random.nextInt(401) + 100;
        this.weapons = new ArrayList<>();
    }

    public void drawWeapons(int frame) {
        Weapon curWeapon = null;
        int createConfirm = random.nextInt(2) + 2; // 2 3
        if( frame % createConfirm == 0 ) {
            curWeapon = chooseWeaponType();
            curWeapon.spawnWeapon();
        }
    }

    public Weapon chooseWeaponType() {
        Weapon weaponChosen = null;
        int typeChoose = random.nextInt(2);
        if (typeChoose == 0) {
            weaponChosen = new Rock();
        } else if (typeChoose == 1) {
            weaponChosen =  new Bomb();
        }
        return weaponChosen;
    }

    public void spawnWeapon() {
    }


}
