package me.Vinstaal0.Utility;

import java.util.Random;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class EnumHelp {

    static Random random = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> c) {
        int x = random.nextInt(c.getEnumConstants().length);
        return c.getEnumConstants()[x];
    }

}
