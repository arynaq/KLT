package engine;

import gfx.AttackMoveAnimated;
import gfx.SpriteSheet;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import characters.HumanoidEnemy;


public class EnemyLoader {
    private Map<String, ArrayList<BufferedImage>> images;
    private Map<String, Entity> entities;

    public EnemyLoader(String fileName, Map<String, Entity> entities,
            Map<String, ArrayList<BufferedImage>> images) {

        this.images = images;
        this.entities = entities;
        loadEnemies(fileName);
    }

    private void loadEnemies(String fileName) {
        Scanner readFile = null;
        InputStream is = getClass().getResourceAsStream(fileName);
        readFile = new Scanner(is);
        
        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (line.startsWith("#"))
                continue;
            if (line.isEmpty())
                continue;
            String[] args = line.split(",");
            loadEnemy(args);
        }
        readFile.close();
        System.out.println("Done loading enemies from file.");
    }

    private void loadEnemy(String[] args) {
        String key = args[0];
        int x = Integer.valueOf(args[1])*2;
        int y = Integer.valueOf(args[2]) * 2;
        int dmg = Integer.valueOf(args[3]);
        int health = Integer.valueOf(args[4]);
        int xp = Integer.valueOf(args[5]);


        SpriteSheet sprite = new SpriteSheet(images.get(key + "ANIMATED"), 4, 4);
        AttackMoveAnimated animation = new AttackMoveAnimated(sprite);
        HumanoidEnemy enemy = new HumanoidEnemy(animation, x, y, health, dmg,
                1000, xp);
        entities.put(key + x + y, enemy);

    }
}
