package isika.p3.amappli.dto.amappli;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.user.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewTenancyDTO {
    
    private String tenancyName;

    private String tenancyAlias;

    private String tenancySlogan;

    private BigDecimal membershipFeePrice;

    private Address address;

    private List<ValueDTO> values;

    private ColorPalette colorPalette;

    private MultipartFile logo;

    private String firstHomePageTitle;

    private String firstHomePageText;

    private MultipartFile firstHomePagePic;

    private boolean option1;

    private boolean option2;

    private boolean option3;

    private FontChoice fontChoice;

}
