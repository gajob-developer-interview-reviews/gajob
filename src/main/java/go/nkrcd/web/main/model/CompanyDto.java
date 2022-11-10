package go.nkrcd.web.main.model;

import lombok.Data;


@Data
public class CompanyDto {
    private String cId;
    private String name;
    private String address;
    private String logo;

    public CompanyDto(String cId, String name, String address) {
        this.cId = cId;
        this.name = name;
        this.address = address;
    }
}
