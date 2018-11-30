package cn.edu.hebtu.software.snow.bean;

public class Country {
    private String countryName;
    private int countryNumber;

    public Country(String countryName,int countryId) {
        this.countryName = countryName;
        this.countryNumber = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryNumber;
    }

    public void setCountryId(int countryId) {
        this.countryNumber = countryId;
    }
}
