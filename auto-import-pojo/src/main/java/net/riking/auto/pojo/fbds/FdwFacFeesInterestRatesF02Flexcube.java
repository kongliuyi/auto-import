package net.riking.auto.pojo.fbds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.riking.auto.commmon.annotation.Ignore;
import net.riking.auto.commmon.annotation.SourceFile;
import net.riking.auto.commmon.enums.FileType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @Description
 * @Author kongLiuYi
 * @Date 2020/3/4 0004 17:06
 */
@Entity
@Table(name = "FDW_FacFeesInterestRates_F02_FLEXCUBE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SourceFile(value = "FDW_FacFeesInterestRates_F02_FLEXCUBE", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class FdwFacFeesInterestRatesF02Flexcube implements Serializable {

    private static final long serialVersionUID = -8110416865645189277L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Ignore
    private int id;
    private String tradeRefId;
    private String interestBasisCode;
    private String interestRate;
    private String interestRateCurrencyCode;
    private String interestRateEndDate;
    private String interestRateStartDate;
    private String interestUsageCode;
    private String tenor;
    private String tenorUnitCode;
    private String module;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Ignore
    private Date dataDate;
}
