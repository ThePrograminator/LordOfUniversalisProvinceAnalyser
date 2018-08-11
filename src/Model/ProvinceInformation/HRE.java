package Model.ProvinceInformation;

public class HRE
{
    private boolean isPartOfHRE;

    public boolean isPartOfHRE() {
        return isPartOfHRE;
    }

    public void setPartOfHRE(boolean partOfHRE) {
        isPartOfHRE = partOfHRE;
    }

    @Override
    public String toString()
    {
        if(isPartOfHRE)
            return  "hre = yes" + "\n" + "\n";
        else
            return  "hre = no" + "\n" + "\n";
    }
}
