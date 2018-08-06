package Model;

public class Owner
{
    private String core;
    private String owner;
    private String controller;

    public Owner(String core, String owner, String controller) {
        this.core = core;
        this.owner = owner;
        this.controller = controller;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
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
}
