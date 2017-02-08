package week14;

/**
 * Created on 08/02/2017.
 */
public enum Skill {
    NEWBIE(0.25f),
    AMATEUR(0.5f),
    PRO(0.1f),
    YODA(0.001f);

    float multiplier;

    Skill(float multiplier) {
        this.multiplier = multiplier;
    }

    public float getMultiplier() {
        return multiplier;
    }
}
