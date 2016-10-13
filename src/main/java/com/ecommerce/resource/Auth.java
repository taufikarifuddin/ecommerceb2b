package com.ecommerce.resource;

import java.security.Key;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.ecommerce.member.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Auth {

    private static final String API_KEY = "ecommerceApp";
    public static final String ISSUER = "ecommerce-app";
    public static final String LOGIN_SUBJECT = "login-user";
    public static final int EXPIRED_TIME_ONEYEAR = 1000 * 60 * 24 * 30 * 12;

    public Auth() {
    }

    public static String createJWT(String id, String issuer, String subject, long ttlMillis, Member user) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(API_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey)
                .claim(Constant.USER_ROLE_PARAMS, user.member_rolemember_role_id)
                .claim(Constant.USER_VERIFIED_PARAMS, user.member_is_verified)
                .claim(Constant.USER_EMAIL_PARAMS, user.member_email_address)
                .claim(Constant.USER_NAME_PARAMS, user.member_name);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Jws<Claims> getData(String tokenSession) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(API_KEY))
                    .parseClaimsJws(tokenSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

    public static String getId(String tokenSession) {
        return getData(tokenSession).getBody().getId();
    }

    public static Member getUserData(String tokenSession){
    	Claims dataJwt = getData(tokenSession).getBody();
    	if( dataJwt != null ){
    		try{
	    		Member member = new Member();    	
	    		member.member_id = Integer.parseInt(dataJwt.getId());    		
	    		member.member_name = ( String ) dataJwt.get(Constant.USER_NAME_PARAMS);
	    		member.member_email_address = ( String ) dataJwt.get(Constant.USER_EMAIL_PARAMS);	    		
	    		member.member_rolemember_role_id = ( int ) dataJwt.get(Constant.USER_ROLE_PARAMS);
	    		member.member_is_verified = ( int ) dataJwt.get(Constant.USER_VERIFIED_PARAMS);    		
	    		return member;
    		}catch (NullPointerException e) {
    			e.printStackTrace();
			}catch ( Exception e ){
				e.printStackTrace();
			}
    	}
    	return null;
    }
}
