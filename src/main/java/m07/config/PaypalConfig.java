package m07.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
/*
    private String clientId = "AVdKmDAOunS_N7PFp8jkPg-Uw-Nzp-i-u8KCYtU1VNzYDWAGkzbfe5LU7ywZunBSZs8ht7-GXtBhmVaj";
    private String clientSecret = "EPObCNYf8SBcxlN48Img6l4aVThfaZa8KR3R5TEnE1FZniNjKBglVOVC5v5_qkpzqVNHwfsvzxAVmIzN";*/
	
	private String clientId = "AZmf4zwRBswrf1LAy_7HEJzlhj-QzxrvcMpMuMvEo0Jt8qWubE6b2_eQ8uR1tykZVyO14f-uFMSBF7DQ";
    private String clientSecret = "EMjmeYQuxztC62E7_6h90E-rBxHCEO5AjvOTuCCpwjWN46OPNZNG0I-b5ZdVSLACk16V0xiTuH1f-pO0";
    private String mode = "sandbox";

    @Bean
    public Map<String, String> paypalSdkConfig(){
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);
        return sdkConfig;
    }

    @Bean
    public OAuthTokenCredential authTokenCredential(){
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
        apiContext.setConfigurationMap(paypalSdkConfig());
        return apiContext;
    }
}
