package ua.pancakes.pancakesbackend.payload.googleauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

public class GoogleAuthenticationUtil {
    private static String GRANT_TYPE = "authorization_code";
    private static String CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
    private static String CLIENT_SECRET = System.getenv("GOOGLE_SECRET");
    private static String REDIRECT_URI = System.getenv("GOOGLE_REDIRECT_URI");

    @Data
    static public class GoogleTokenRequestBody {
        @JsonProperty("grant_type")
        private String grantType = GRANT_TYPE;
        @JsonProperty("client_id")
        private String clientId = CLIENT_ID;
        @JsonProperty("client_secret")
        private String clientSecret = CLIENT_SECRET;
        @JsonProperty("redirect_uri")
        private String redirectUri = REDIRECT_URI;
        private String code;

        public GoogleTokenRequestBody(String code) {
            this.code = code;
        }
    }

    @Data
    @ToString
    static public class GoogleTokenResponse {
        @JsonProperty("access_token")
        String accessToken;
        @JsonProperty("expires_in")
        Long expiresIn;
        String scope;
        @JsonProperty("token_type")
        String tokenType;
        @JsonProperty("id_token")
        String idToken;
    }

    @Data
    @ToString
    static public class GoogleUser {
        String id;
        String email;
        @JsonProperty("verified_email")
        boolean verifiedEmail;
        String name;
        @JsonProperty("given_name")
        String firstName;
        @JsonProperty("family_name")
        String lastName;
        String picture;
        String locale;
    }
}
