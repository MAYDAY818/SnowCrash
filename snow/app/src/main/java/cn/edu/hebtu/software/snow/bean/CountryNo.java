package cn.edu.hebtu.software.snow.bean;

public class CountryNo {
    private String country;
    private String no;

    public CountryNo(String country, String no) {
        this.country = country;
        this.no = no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
