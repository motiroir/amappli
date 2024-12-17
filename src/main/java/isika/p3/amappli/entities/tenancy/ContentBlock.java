package isika.p3.amappli.entities.tenancy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentBlock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentBlockId;

    private String contentTitle;

    private String contentText;

    private String contentImgType;

    private Byte[] contentImg;

    @ManyToOne
    @JoinColumn( name = "homePageContentId", nullable = false)
    private HomePageContent homePageContent;


}
