package by.gsu.pms.practice2.comp;

public class Artifact {
    public Artifact() {
        name = "Unknown artifact";
        material = new Material();
        volume = 0.0f;
    }
    
    public Artifact(String name, Material material, float volume) {
        this.name = name;
        this.material = material;
        this.volume = volume;
    }
    
    private String name;
    private Material material;
    private float volume;
    
    public String getName() {
        return name;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public float getVolume() {
        return volume;
    }
    
    public void setName(String value) {
        name = value;
    }
    
    public void setMaterial(Material value) {
        material = value;
    }
    
    public void setVolume(float value) {
        volume = value;
    }
    
    public float getMass() {
        return material.getDensity() * volume;
    }
    
    @Override
    public String toString() {
        return name + ";" + material + ";" + volume + ";" + getMass();
    }
}
