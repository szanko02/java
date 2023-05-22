package by.gsu.pms.practice2.comp;

public class Runner {
    public static void main(String[] args) {
        Material steel = new Material("steel", 7850);
        Material copper = new Material("copper", 8500);
        Artifact wire = new Artifact("wire", steel, 0.03f);
        System.out.println(wire);
        wire.setMaterial(copper);
        System.out.println(wire.getMass());
        wire.setMaterial(steel);
        System.out.println(wire);
    }
}
