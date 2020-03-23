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
@Table(name = "FDW_InterestRatesSchedules_F02_FLEXCUBE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SourceFile(value = "FDW_InterestRatesSchedules_F02_FLEXCUBE", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class FdwInterestRatesSchedulesF02Flexcube implements Serializable {

    private static final long serialVersionUID = -8110416865645189277L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Ignore
    private int id;
    private String tradeReferenceId;
    private String scheduledStartDate;
    private String scheduledEndDate;
    private String cycleTenorUnitCode;
    private String scheduledCycleTenor;
    private String nextInterestRateStartDate;
    private String nextCouponDate;
    private String legTypeCode;
    private String interestUsageCode;
    private String irFixingFrequencyUnitCode;
    private String irFixingFrequency;
    private String irFirstFixingDate;
    private String interestRateCode;
    private String interestFixingRule;
    private String interestCapitalizationRate;
    private String brokenPeriodIndicator;
    private String interestRateCap;
    private String interestRateFloor;
    private String module;
    private String calculationMethod;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Ignore
    private Date dataDate;
}
