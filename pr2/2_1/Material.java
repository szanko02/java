package by.gsu.pms.practice2.comp;

public class Material {
    public Material() {
        this.name = "Unnamed material";
        this.density = 0.0f;
    }
    
    public Material(String name, float density) {
        this.name = name;
        this.density = density;
    }
    
    private String name;
    private float density;
    
    public String getName() {
        return name;
    }
    
    public float getDensity() {
        return density;
    }
    
    public void setName(String value) {
        name = value;
    }
    
    public void setDensity(float value) {
        density = value;
    }
    
    @Override
    public String toString() {
        return name + ";" + density;
    }
}
