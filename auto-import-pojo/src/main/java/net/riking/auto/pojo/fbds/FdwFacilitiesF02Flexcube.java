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
@Table(name = "FDW_Facilities_F02_FLEXCUBE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SourceFile(value = "FDW_Facilities_F02_FLEXCUBE", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class FdwFacilitiesF02Flexcube implements Serializable {

    private static final long serialVersionUID = -8110416865645189277L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Ignore
    private int id;
    private String facilityId;
    private String customerFacilityRelId;
    private String parentFacilityId;
    private String allocatingFacRelId;
    private String arrearsTypeCode;
    private String drawingEndDate;
    private String facilityPurposeCode;
    private String facilityTypeCode;
    private String fdCommittedInd;
    private String fdCurrencyCode;
    private String financeTypeCode;
    private String forceMajeurInd;
    private String lastRevisedDate;
    private String liquiditySpread;
    private String marginRate;
    private String maturityDate;
    private String multicurrencyInd;
    private String originFacilityRelId;
    private String hoProductSubTypeCode;
    private String revocableInd;
    private String revolvingInd;
    private String selfLiquidatingInd;
    private String seniorityTypeCode;
    private String startDate;
    private String statusCode;
    private String facilityReviewDate;
    private String callTerm;
    private String callTermUnitCode;
    private String fcubsProductCode;
    private String fcubsProductCodeDesc;
    private String agentTradeRelationId;
    private String lastModifiedDate;
    private String lenderTypeCode;
    private String bankRoleCode;
    private String nonPerformingDate;
    private String contractStatus;
    private String tradeDate;
    private String module;
    private String directIndirectExposure;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Ignore
    private Date dataDate;
}
