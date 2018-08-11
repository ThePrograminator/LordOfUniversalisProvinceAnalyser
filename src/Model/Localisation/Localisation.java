package Model.Localisation;

public class Localisation
{
    private int id;
    private LocalisationType localisationType;
    private String language;
    private String keyName;
    private String valueName;

    public Localisation(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalisationType getLocalisationType() {
        return localisationType;
    }

    public void setLocalisationType(LocalisationType localisationType) {
        this.localisationType = localisationType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}
