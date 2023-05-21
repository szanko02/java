package by.gsu.pms;

public class Tribe {
    String tribeName;
    int tribeAmount;
    boolean isFamiliarWithFire;

    public Tribe(){
        this.tribeName = "A tribe";
        this.tribeAmount = 0;
        this.isFamiliarWithFire = false;
    }
    public Tribe(String tribeName, int tribeAmount, boolean isFamiliarWithFire) {
        this.tribeName = tribeName;
        this.tribeAmount = tribeAmount;
        this.isFamiliarWithFire = isFamiliarWithFire;
    }

    public String getTribeName() {
        return tribeName;
    }

    public void setTribeName(String tribeName) {
        this.tribeName = tribeName;
    }

    public int getTribeAmount() {
        return tribeAmount;
    }

    public void setTribeAmount(int tribeAmount) {
        this.tribeAmount = tribeAmount;
    }

    public boolean isFamiliarWithFire() {
        return isFamiliarWithFire;
    }

    public void setFamiliarWithFire(boolean familiarWithFire) {
        isFamiliarWithFire = familiarWithFire;
    }

    @Override
    public String toString() {
        return tribeName + ";" + tribeAmount + ";" + isFamiliarWithFire + ";";
    }
}
