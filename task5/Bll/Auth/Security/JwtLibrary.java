package oss.transaction.Bll.Auth.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import oss.transaction.Bll.Auth.Model.JwtUser;

public class JwtLibrary {

    private static String secret = "youtube";

    public static long getUserId(String token) {

        JwtUser jwtUser = null;
        String parsedToken = token.substring(6);
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(parsedToken)
                    .getBody();

            jwtUser = new JwtUser();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser.getId();
    }

}
