package Model.ProvinceInformation;

public class Modifier
{
    private String keywordName;
    private String modifierName;
    private int duration;

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        String toString;
        toString = keywordName + " = {" + "\n";
        toString += "\tname = " + modifierName + "\n";
        toString += "\tduration = " + duration + "\n";
        toString += "}" + "\n" + "\n";

        return  toString;
    }
}
