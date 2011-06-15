package fi.laverca;

import javax.xml.namespace.QName;

import org.apache.axis.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * As per FiCom Soveltamisohje v2.0
 */
public class FiComErrorCodes {
    private static final Log log = LogFactory.getLog(FiComErrorCodes.class);

    public static final String FICOM_NS_URI = "http://mss.ficom.fi/TS102204/v1.0.0#";
    public static final String FICOM_PREFIX = "fi";

    public final static int NO_SECONDARY_CODE = -1;

    public final static StatusCode      WRONG_PARAM                      = new StatusCode(101);
    public final static StatusCode      INVALID_NOSPAMCODE               = new TwoLevelStatusCode(101, 1011);
    public final static StatusCode      MISSING_NOSPAMCODE               = new TwoLevelStatusCode(101, 1012);
    public final static StatusCode      ILLEGAL_MESSAGINGMODE            = new TwoLevelStatusCode(101, 1013);
    public final static StatusCode      UNKNOWN_ADDITIONALSERVICE        = new TwoLevelStatusCode(101, 1014);
    public final static StatusCode      DATATOBEDISPLAYED_NOT_SUPPORTED  = new TwoLevelStatusCode(101, 1015);
    public final static StatusCode      UNSUPPORTED_MIMETYPE_OR_ENCODING = new TwoLevelStatusCode(101, 1016);
    public final static StatusCode      MSS_FORMAT_UNSUPPORTED_FOR_THESE = new TwoLevelStatusCode(101, 1017);

    public final static StatusCode      MISSING_PARAM                    = new StatusCode(102);
    public final static StatusCode      DATATOBEDISPLAYED_MISSING        = new TwoLevelStatusCode(102, 1021);

    public final static StatusCode      WRONG_DATA_LENGTH                = new StatusCode(103);

    public final static StatusCode      UNAUTHORIZED_ACCESS              = new StatusCode(104);
    public final static StatusCode      USER_DISABLED_SIGNATUREPROFILE   = new TwoLevelStatusCode(104, 1041);
    public final static StatusCode      USER_DISABLED_ADDITIONALSERVICE  = new TwoLevelStatusCode(104, 1042);
    public final static StatusCode      SERVICE_SUSPENDED                = new TwoLevelStatusCode(104, 1043);

    public final static StatusCode      UNKNOWN_CLIENT                   = new StatusCode(105);
    public final static StatusCode      MALFORMATTED_USER_IDENTIFIER     = new TwoLevelStatusCode(105, 1051);
    public final static StatusCode      USER_IDENTIFIER_DOES_NOT_EXIST   = new TwoLevelStatusCode(105, 1052);
    public final static StatusCode      UNREGISTERED_USER                = new TwoLevelStatusCode(105, 1053);
    public final static StatusCode      INCOMPATIBLE_SIM_CARD            = new TwoLevelStatusCode(105, 1054);

    public final static StatusCode      INAPPROPRIATE_DATA               = new StatusCode(107);

    public final static StatusCode      INCOMPATIBLE_INTERFACE           = new StatusCode(108);

    public final static StatusCode      ROAMING_HEADER_BLOCK_IS_MISSING  = new StatusCode(701);
    public final static StatusCode      IDENTITY_ISSUER_HEADER_IS_MISSING= new StatusCode(702);
    public final static StatusCode      HOME_MSSP_HEADER_BLOCK_IS_MISSING= new StatusCode(703);
    public final static StatusCode      APPROPRIATE_INPUT_INFO_IS_MISSING= new StatusCode(710);
    public final static StatusCode      VALIDITY_DATE_OF_TXN_HAS_EXPIRED = new StatusCode(720);

    public final static StatusCode      UNSUPPORTED_PROFILE              = new StatusCode(109);

    public final static StatusCode      EXPIRED_TRANSACTION              = new StatusCode(208);
    public final static StatusCode      SERVER_TIMEOUT                   = new TwoLevelStatusCode(208, 2081);
    public final static StatusCode      USER_TIMEOUT                     = new TwoLevelStatusCode(208, 2082);

    public final static StatusCode      OTA_ERROR                        = new StatusCode(209);
    public final static StatusCode      UNKNOWN_OTA_ERROR                = new TwoLevelStatusCode(209, 2091);
    public final static StatusCode      CARD_NOT_FOUND                   = new TwoLevelStatusCode(209, 2092);
    public final static StatusCode      ME_COMMUNICATION_ERROR           = new TwoLevelStatusCode(209, 2093);
    public final static StatusCode      INVALID_CAPABILITIES             = new TwoLevelStatusCode(209, 2094);

    public final static StatusCode      USER_CANCEL                      = new StatusCode(401);
    public final static StatusCode      USER_CANCEL_                     = new TwoLevelStatusCode(401, 4011);
    public final static StatusCode      INCORRECT_POP                    = new TwoLevelStatusCode(401, 4012);
    public final static StatusCode      POSTPONED_SIGNATURE              = new TwoLevelStatusCode(401, 4013);

    public final static StatusCode      PIN_NR_BLOCKED                   = new StatusCode(402);
    public final static StatusCode      PIN_BLOCKED                      = new TwoLevelStatusCode(402, 4021);

    public final static StatusCode      CARD_BLOCKED                     = new StatusCode(403);
    public final static StatusCode      CARD_PUK_BLOCKED                 = new TwoLevelStatusCode(403, 4031);
    public final static StatusCode      CARD_PIN_BLOCKED                 = new TwoLevelStatusCode(403, 4032);

    public final static StatusCode      NO_KEY_FOUND                     = new StatusCode(404);
    public final static StatusCode      REQUESTED_KEY_NOT_FOUND          = new TwoLevelStatusCode(404, 4041);
    public final static StatusCode      INCORRECT_KEY_USAGE              = new TwoLevelStatusCode(404, 4042);

    public final static StatusCode      NO_CRL_FOUND                     = new StatusCode(405);
    public final static StatusCode      PB_SIGNATURE_PROCESS             = new StatusCode(406);

    public final static StatusCode      REGISTRATION_ERROR               = new StatusCode(407);
    public final static StatusCode      UNKNOWN_REGISTRATION_ERROR       = new TwoLevelStatusCode(407, 4071);
    public final static StatusCode      REGISTRATION_FAILED_ON_SERVER    = new TwoLevelStatusCode(407, 4072);
    public final static StatusCode      REGISTRATION_FAILED_ON_SIM       = new TwoLevelStatusCode(407, 4073);

    public final static StatusCode      NO_CERT_FOUND                    = new StatusCode(422);
    public final static StatusCode      CRL_PB                           = new StatusCode(423);
    public final static StatusCode      CRL_EXPIRED                      = new StatusCode(424);
    public final static StatusCode      ERROR_CERTIFICATE                = new StatusCode(425);

    public final static StatusCode      UNABLE_TO_PROVIDE_RE_SERVICES    = new StatusCode(750);
    public final static StatusCode      UNABLE_TO_PROVIDE_II_SERVICES    = new StatusCode(760);
    public final static StatusCode      UNABLE_TO_PROVIDE_VE_SERVICES    = new StatusCode(770);
    public final static StatusCode      UNABLE_TO_PROVIDE_SERVICES       = new StatusCode(780);

    public final static StatusCode      INTERNAL_ERROR                   = new StatusCode(900);
    public final static StatusCode      UNKNOWN_INTERNAL_ERROR           = new TwoLevelStatusCode(900, 9001);
    public final static StatusCode      SERVER_ERROR                     = new TwoLevelStatusCode(900, 9002);
    public final static StatusCode      SIM_APPLICATION_ERROR            = new TwoLevelStatusCode(900, 9003);
    public final static StatusCode      SIM_CONFIGURATION_ERROR          = new TwoLevelStatusCode(900, 9004);
    public final static StatusCode      DTBD_MISSING                     = new TwoLevelStatusCode(900, 9005);
    public final static StatusCode      INVALID_KEY_LENGTH               = new TwoLevelStatusCode(900, 9006);
    public final static StatusCode      INVALID_HASH_TYPE                = new TwoLevelStatusCode(900, 9007);
    public final static StatusCode      INVALID_KEY_ALGORITHM            = new TwoLevelStatusCode(900, 9008);


    static class StatusCode {
        public final int primaryCode;

        public StatusCode(int primary) {
            this.primaryCode = primary;
        }

        public boolean is(AxisFault af) {
            if(af == null)
                return false;
            QName[] subcodes = af.getFaultSubCodes();
            if(subcodes == null || subcodes.length == 0)
                return false;
            return subcodeMatch(this.primaryCode, subcodes[0]);
        }
        
        static boolean subcodeMatch(int i, QName subcode) {
            String underscored = "_"+i;
            boolean itsaFiComCode = false;
            if(FICOM_NS_URI.equals(subcode.getNamespaceURI()))
                itsaFiComCode = true; // definite match
            else if(FICOM_PREFIX.equals(subcode.getPrefix())) {
                log.debug("got an uncertain ficom prefix. Using it.");
                itsaFiComCode = true;
            }

            if(itsaFiComCode) {
                String localPart = subcode.getLocalPart();
                if(underscored.equals(localPart)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class TwoLevelStatusCode extends StatusCode {
        public final int secondaryCode;

        public TwoLevelStatusCode(int primary, int secondary) {
            super(primary);
            this.secondaryCode = secondary;
        }
        
        @Override
        public boolean is(AxisFault af) {
            QName[] subcodes = af.getFaultSubCodes();
            if(subcodes == null || subcodes.length != 1)
                return false;
            return 
            subcodeMatch(this.primaryCode,   subcodes[0]) &&
            subcodeMatch(this.secondaryCode, subcodes[1]);
        }
    }

}
