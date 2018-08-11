package Model.ProvinceInformation;

import java.util.ArrayList;

public class Owner
{
    private ArrayList<String> core;
    private String owner;
    private String controller;

    public Owner() {
        this.core = new ArrayList<>();
    }

    public ArrayList<String> getCore() {
        return core;
    }

    public void setCore(ArrayList<String> core) {
        this.core = core;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "core='" + core + '\'' +
                ", owner='" + owner + '\'' +
                ", controller='" + controller + '\'' +
                '}';
    }
}
