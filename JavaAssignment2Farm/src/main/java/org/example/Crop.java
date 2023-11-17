package org.example;

public class Crop extends Entity {
public String cropType;
private int quantity;

public static int cropId = 1;

public Crop (String name, String cropType, int quantity){
    super(cropId, name);
    cropId++;
    this.cropType = cropType;
    this.quantity = quantity;
}

public Crop (int id, String name, String cropType, int quantity) {
    super(id, name);
    if (id > cropId) {
        cropId = id + 1;
    }
    this.cropType = cropType;
    this.quantity = quantity;
}

    public void GetDescription(){
        System.out.println("Id: "+id+ ". Name: "+name+ ". Crop Type: "+cropType + ". Quantity: "+quantity+".");
}


    public int reduceQuantity() {
        return getQuantity() - 1;
    }

    public String getCropType() {
        return cropType;
    }
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCSV(){
    return id+","+name+","+cropType+","+quantity;
    }
}
