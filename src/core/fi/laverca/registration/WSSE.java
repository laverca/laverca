//
//  (c) Copyright 2003-2018 Methics Oy. All rights reserved.
//

package fi.laverca.registration;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import fi.laverca.jaxb.wsssecext.AttributedString;
import fi.laverca.jaxb.wsssecext.Password;
import fi.laverca.jaxb.wsssecext.Security;
import fi.laverca.jaxb.wsssecext.UsernameTokenType;

/**
 * Helpers for WSSecurity header processing
 */
public class WSSE {

    /** Namespace of WS-Security - SecExt 1.0 */
    public static final String NSsecext10 = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    /** QName of WSSE header wrapper element */
    public static final QName securityQN = new QName(NSsecext10, "Security");

    public static final fi.laverca.jaxb.wsssecext.ObjectFactory wsexOf = new fi.laverca.jaxb.wsssecext.ObjectFactory();
    public static final fi.laverca.jaxb.kiuruwsse1.ObjectFactory kwsOf = new fi.laverca.jaxb.kiuruwsse1.ObjectFactory();

    public static JAXBElement<UsernameTokenType> makeUsernameToken(final String username, final String password) {
        return wsexOf.createUsernameToken(makeUsernameTokenType(username, password));
    }
    
    public static Security makeSecurityUsernameToken(final String username, final String password) {
        final JAXBElement<UsernameTokenType> userToken = makeUsernameToken(username, password);
        final Security s = wsexOf.createSecurity();
        s.setOriginatingPersonToken(userToken.getValue());
        return s;
    }
    
    public static UsernameTokenType makeUsernameTokenType(final String username, final String password) {
        final UsernameTokenType usertoken = wsexOf.createUsernameTokenType();

        final AttributedString un = wsexOf.createAttributedString();
        un.setValue(username);
        usertoken.setUsername(un);
        
        final Password pw = wsexOf.createPassword();
        pw.setValue(password);
        usertoken.getPasswordsAndAnies().add(pw);
        
        return usertoken;
    }

    /**
     * Creates a standard WSSE security header.
     * @param originatingPersonUsername person who runs the application as an administrator
     * @param originatingPersonPassword password of the application administrator
     * @param clientUsername is the username of the application, not a person
     * @param clientPassword password of the application
     * @return the created security header
     */
    public static Security makeSecurityHeader(final String originatingPersonUsername,
                                              final String originatingPersonPassword,
                                              final String clientUsername,
                                              final String clientPassword)
    {
        // Security is an element.
        final Security s = wsexOf.createSecurity();

        if (originatingPersonUsername != null) {
            final UsernameTokenType utt = makeUsernameTokenType(originatingPersonUsername, originatingPersonPassword);
            final JAXBElement<UsernameTokenType> opt = kwsOf.createOriginatingPersonToken(utt); 
            s.setOriginatingPersonToken(opt.getValue());
        }

        if (clientUsername != null) {
            final UsernameTokenType utt = makeUsernameTokenType(clientUsername, clientPassword);
            final JAXBElement<UsernameTokenType> ct = kwsOf.createClientToken(utt);
            s.setClientToken(ct.getValue());
        }
        return s;
    }


    public static UsernameTokenType findClientToken(Security security) {
        if (security == null) {
            return null;
        }
        return security.getClientToken();
    }
    
    /**
     * Returns the client token username if found, null otherwise.
     * @param security is the standard WSSE security header from which to find the client token username
     * @return the client token username if found, null otherwise
     */
    public static String findUsername(Security security) {
        return findUsername(findClientToken(security));
    }

    public static String findUsername(UsernameTokenType ut) {
        if (ut == null || ut.getUsername() == null) {
            return null;
        }
        return ut.getUsername().getValue();
    }

    /**
     * @param security 
     * 
     */
    public static UsernameTokenType findOriginatingPersonToken(Security security) {
        if (security == null) {
            return null;
        }
        return security.getOriginatingPersonToken();
    }

    public static UsernameTokenType findUsernameToken(Security security) {
        return findClientToken(security);
    }

    /**
     * Returns the originating person's username if found, null otherwise.
     * @param security is the standard WSSE security header from which to find the originating person's username
     * @return the originating person's username if found, null otherwise
     */
    public static String findOriginatingPersonUsername(final Security security) {
        final UsernameTokenType opt = findOriginatingPersonToken(security);
        if (opt == null) return null;
        // Value should always exist as we found a ClientToken
        final AttributedString un = opt.getUsername();
        if (un != null)
            return un.getValue();
        return null;
    }

    public static String findPassword(JAXBElement<UsernameTokenType> ut) {
        if (ut == null) {
            return null;
        }
        return findPassword(ut.getValue());
    }

    /**
     * Returns the password of the Instalink specific UsernameTokenType if found, null otherwise.
     * @param ut Instalink specific UsernameTokenType from which to find the password
     * @return the password of the Instalink specific UsernameTokenType if found, null otherwise
     */
    public static String findPassword(UsernameTokenType utt) {
        if (utt == null) {
            return null;
        }
        for (final Object oo : utt.getPasswordsAndAnies()) {
            if (oo instanceof Password) {
                final Password pw = (Password) oo;
                return pw.getValue();
            }
        }
        return null;
    }
    
}
