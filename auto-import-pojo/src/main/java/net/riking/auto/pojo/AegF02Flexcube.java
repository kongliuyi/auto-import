package net.riking.auto.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.riking.auto.commmon.annotation.FieldFormat;
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
 * @Date 2020/3/2 0002 13:37
 */
@Entity
@Table(name = "AEG_F02_FLEXCUBE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SourceFile(value = "AEG_F02_FLEXCUBE", type = FileType.TXT_DELIMITER, delimiter = "|", head = 3)
public class AegF02Flexcube implements Serializable {

    private static final long serialVersionUID = -8110416865645189277L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Ignore
    private int id;

    @Column(name = "SEQUENCE_NUMBER_OF_THE_EVENT")
    @Ignore(false)
    private String sequenceNumberOfTheEvent;

    @Column(name = "BRANCH_CODE")
    private String branchCode;

    @Column(name = "EVENT")
    private String event;

    @Column(name = "EVENT_DESCRIPTION")
    private String eventDescription;

    @Column(name = "FCUBS_PRODUCT_CODE")
    private String fcubsProductCode;

    @Column(name = "FCUBS_PRODUCT_CODE_DESCRIPTION")
    private String fcubsProductCodeDescription;

    @Column(name = "TRADE_REF_ID")
    private String tradeRefId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @FieldFormat("ddMMyyyy")
    @Column(name = "VALUE_DATE")
    private Date valueDate;

    @Column(name = "BOOK_DATE")
    private String bookDate;

    @Column(name = "DC_IND")
    private String dcInd;

    @Column(name = "AMOUNT_TAG")
    private String amountTag;

    @Column(name = "ACCOUNT_ROLE_CODE")
    private String accountRoleCode;

    @Column(name = "TRANSACTION_CURRENCY")
    private String transactionCurrency;

    @Column(name = "TRANSACTION_AMOUNT")
    private String transactionAmount;

    @Column(name = "PORTFOLIO")
    private String portfolio;

    @Column(name = "RELATED_PORTFOLIO")
    private String relatedPortfolio;

    @Column(name = "LINKED_FACILITY_ID")
    private String linkedFacilityId;

    @Column(name = "FINANCE_PRODUCT_CODE")
    private String financeProductCode;

    @Column(name = "RELATED_TRADE_REF")
    private String relatedTradeRef;

    @Column(name = "TYPE_OF_GL")
    private String typeOfGl;

    @Column(name = "AC_NO")
    private String acNo;

    @Column(name = "IN_DEC_AMT")
    private String inDecAmt;

    @Column(name = "OPERATION_CODE")
    private String operationCode;

    @Column(name = "AC_ACCOUNT_CLASS_TYPE")
    private String acAccountClassType;

    @Column(name = "CHG_COMM")
    private String chgComm;

    @Column(name = "INT_COMM_PAYMENT_TYPE")
    private String intCommPaymentType;

    @Column(name = "ORIGINAL_EVENT")
    private String originalEvent;

    @Column(name = "BC_LC_CONFIRM_STATUS")
    private String bcLcConfirmStatus;

    @Column(name = "MODULE")
    private String module;

    @Column(name = "CONTRACT_STATUS")
    private String contractStatus;

    @Column(name = "WITH_RECOURSE")
    private String withRecourse;

    @Column(name = "LC_CCY")
    private String lcCcy;

    @Column(name = "LC_AMOUNT")
    private String lcAmount;

    @Column(name = "FI_PORTFOLIO")
    private String fiPortfolio;

    @Column(name = "OFFSET_CCY")
    private String offsetCcy;

    @Column(name = "OFFSET_AMOUNT")
    private String offsetAmount;

    @Column(name = "I_PRODUCT")
    private String iProduct;

    @Column(name = "EFFECTIVE_STATUS_DATE")
    private String effectiveStatusDate;

    @Column(name = "ORIG_FCUBS_PRODUCT_CODE")
    private String origFcubsProductCode;

    @Column(name = "RELATED_AC_NO")
    private String relatedAcNo;

    @Column(name = "RELATED_AC_CLASS")
    private String relatedAcClass;

    @Column(name = "COR_LOV")
    private String corLov;

    @Column(name = "XCCY_REF_ID")
    private String xccyRefId;

    @Column(name = "NETTING")
    private String netting;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "data_date")
    @Ignore
    private Date dataDate;


}
