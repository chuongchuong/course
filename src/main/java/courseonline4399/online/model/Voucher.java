package courseonline4399.online.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voucher_code")
    private String voucherCode;

    @ManyToOne
    @JoinColumn(name = "userid")
    @NotNull
    private User user;

    @Column(name = "percent")
    @NotNull
    private Float percent;


    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "descript")
    @NotNull
    @NotEmpty(message = "Mô tả voucher không được để trống")
    private String description;

    @Column(name = "createddate")
    @NotNull
    private Date createdDate;

    @Column(name = "enddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    // Getters and setters
    // Constructors as needed
}
