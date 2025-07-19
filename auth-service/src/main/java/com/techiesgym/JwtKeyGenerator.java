package com.techiesgym;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtKeyGenerator {

	public static void main(String[] args) {
		var key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String base64Key = Encoders.BASE64.encode(key.getEncoded());
		
		System.out.println("Generated JWT Secret Key (Base64):");
        System.out.println(base64Key);
	}

}
