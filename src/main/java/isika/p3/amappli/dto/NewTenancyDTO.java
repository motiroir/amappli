package isika.p3.amappli.dto;

import java.util.List;

import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.user.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewTenancyDTO {
    
    private String tenancyName;

    private List<ValueDTO> values;

    private ColorPalette colorPalette;

    private boolean option1;

    private boolean option2;

    private boolean option3;

    private FontChoice fontChoice;

    private boolean newAdress;

    private Address address;
}
