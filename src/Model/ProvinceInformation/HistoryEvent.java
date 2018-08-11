package Model.ProvinceInformation;

public class HistoryEvent
{
    private String date;
    private String contents;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void addContents(String contents)
    {
        this.contents += contents + "\n";
    }

    @Override
    public String toString()
    {
        String toString;
        toString = date + " = {" + "\n";
        toString += contents + "\n";
        toString += "}" + "\n" + "\n";

        return  toString;
    }
}
