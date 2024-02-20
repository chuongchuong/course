package courseonline4399.online.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usedvoucher")
public class UsedVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "voucherid",referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private User user;

    @Column(name = "createdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private Boolean status;

    // Getters and setters
    // Constructors as needed
}
