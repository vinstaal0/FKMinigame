package me.Vinstaal0.Utility;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public enum Rarity {

    COMMON(1),
    UNCOMMON(2),
    RARE(3),
    UNIQUE(4),
    ANCIENT(5);

    private final int toInt;

    Rarity(int toInt) {
        this.toInt = toInt;
    }

    public static Rarity getRarity(String string) throws IllegalArgumentException {
        switch(string) {
            case "1" :
                return COMMON;
            case "2" :
                return UNCOMMON;
            case "3" :
                return RARE;
            case "4" :
                return UNIQUE;
            case "5" :
                return ANCIENT;
            default :
                return null;
        }
    }

    public int toInt() {

        return toInt;
    }

}
