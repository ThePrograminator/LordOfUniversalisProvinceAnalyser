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
        if (this.contents != null)
            this.contents += "\t" + contents + "\n";
        else
            this.contents = "\t" + contents + "\n";
    }

    @Override
    public String toString()
    {
        String toString;
        toString = date + " = {" + "\n";
        toString += contents;
        toString += "}" + "\n" + "\n";

        return  toString;
    }
}
