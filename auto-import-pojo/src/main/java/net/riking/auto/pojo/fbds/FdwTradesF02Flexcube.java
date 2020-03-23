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
@Table(name = "FDW_Trades_F02_FLEXCUBE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SourceFile(value = "FDW_Trades_F02_FLEXCUBE", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class FdwTradesF02Flexcube implements Serializable {

    private static final long serialVersionUID = -8110416865645189277L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Ignore
    private int id;
    private String tradeReferenceId;
    private String agentTradeRelationId;
    private String customerTradeRelationId;
    private String earlyPrePayAllowDate;
    private String earlyTerminationDate;
    private String guarantorTradeRelationId;
    private String investorTradeRelationId;
    private String lenderTypeCode;
    private String maturityDate;
    private String effectiveStatusDate;
    private String contractStatus;
    private String noticePeriod;
    private String noticePeriodUnitCode;
    private String prefCondAccInd;
    private String hoProductSubTypeCode;
    private String productSubTypeCode;
    private String rateDrivenAccountInd;
    private String withRecourse;
    private String revolvingInd;
    private String startDate;
    private String tradeDate;
    private String tradeFacilityId;
    private String alternateSourceSystemCode;
    private String alternateTradeReferenceId;
    private String tradeUsageIndustryTypeCode;
    private String tradeUsageLocationCode;
    private String tradeUsageShipmentLoc;
    private String loanMaximumMaturity;
    private String riskAssesmentId;
    private String beneficiaryRelLongName;
    private String beneficiaryRelCountryCode;
    private String flexcubeContractStatus;
    private String flexcubeProductCode;
    private String fcubsProductCodeDesc;
    private String lastModifiedDate;
    private String financeProductCode;
    private String abkPartyCifid;
    private String lcAccPartyCifid;
    private String apbPartyCifid;
    private String appPartyCifid;
    @Column(name = "AS1_PARTY_CIFID")
    private String as1PartyCifid;
    @Column(name = "AS2_PARTY_CIFID")
    private String as2PartyCifid;
    @Column(name = "AS3_PARTY_CIFID")
    private String as3PartyCifid;
    private String atbPartyCifid;
    private String benPartyCifid;
    private String clbPartyCifid;
    private String cobPartyCifid;
    private String isbPartyCifid;
    private String oabPartyCifid;
    private String rebPartyCifid;
    @Column(name = "TR1_PARTY_CIFID")
    private String tr1PartyCifid;
    @Column(name = "TR2_PARTY_CIFID")
    private String tr2PartyCifid;
    @Column(name = "TR3_PARTY_CIFID")
    private String tr3PartyCifid;
    private String accbankPartyCifid;
    private String bcAccPartyCifid;
    private String cinPartyCifid;
    private String clabankPartyCifid;
    private String colbankPartyCifid;
    private String confbankPartyCifid;
    private String disbankPartyCifid;
    private String draweePartyCifid;
    private String drawerPartyCifid;
    private String forbankPartyCifid;
    private String forhousePartyCifid;
    private String guarantorPartyCifid;
    private String issbankPartyCifid;
    private String lcappPartyCifid;
    private String lcbenPartyCifid;
    private String negbankPartyCifid;
    private String precolPartyCifid;
    private String predraPartyCifid;
    private String reimbankPartyCifid;
    private String reimtbankPartyCifid;
    private String remtbankPartyCifid;
    private String thrbankPartyCifid;
    private String transbankPartyCifid;
    private String productTypeLc;
    private String tenorBc;
    private String productTypeBc;
    private String documentBc;
    private String underLc;
    private String operationCode;
    private String lcConfirmStatus;
    private String expiryDate;
    private String bcMaturityDate;
    private String issueDate;
    private String baseDate;
    private String effectiveDate;
    private String transactionDate;
    private String liquidationDate;
    private String valueDate;
    private String tenorDays;
    private String versionNo;
    private String customerType;
    private String backToBackLc;
    private String issueDateLd;
    private String bookDateLd;
    private String maturityDateLd;
    private String openEndedGuarantee;
    private String relatedRefNo;
    private String txnSelfLiquidating;
    private String module;
    private String applicantLongName;
    private String applicantRegCountryCode;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Ignore
    private Date dataDate;

}
