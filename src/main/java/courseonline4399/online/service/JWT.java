package courseonline4399.online.service;



import javax.crypto.spec.SecretKeySpec;


import org.json.JSONObject;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWT {
	private  final  String SECRET_KEY = "courseonlineneverdie";
	private  final int EXPERIENCE =5;
	/*
	 * @param map includes info user(email , id );
	 * @param minute is set experiences for token
	 * @return token type string
	 * */
	public  String createJWT(Map<String, Object> map ) {
		byte[] decodedSecret = Base64.getDecoder().decode(SECRET_KEY);
		map.put("exp", (new Date().getTime() + (60000L * EXPERIENCE)));// 5 hour
		String jwtToken = Jwts.builder().setHeaderParam("alg"	, "HS256").setHeaderParam("typ", "JWT")
				.setClaims(map)
				.signWith(SignatureAlgorithm.HS256,decodedSecret).compact();
		return jwtToken;
	}

	public  String decodess(String token) {
		try {
			String[] data =token.split("\\.");
			return  new String(Base64.getUrlDecoder().decode(data[1]));
		} catch (Exception e) {
			return null;
		}

	}


	public  boolean checkSign(String token) {
		String[] data =token.split("\\.");
		SignatureAlgorithm sa = SignatureAlgorithm.HS256;
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), sa.getJcaName());
			DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SignatureAlgorithm.HS256, secretKeySpec);
			if (!validator.isValid(data[0] + "." +data[1], data[2])) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}





}
