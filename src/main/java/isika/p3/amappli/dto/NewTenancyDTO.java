package isika.p3.amappli.dto;

import java.util.List;

import isika.p3.amappli.entities.tenancy.ColorPalette;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewTenancyDTO {
    
    private String tenancyName;

    private List<ValueDTO> values;

    private ColorPalette colorPalette;

    private boolean marketPlace;

    private boolean promos;

    private boolean events;

}
