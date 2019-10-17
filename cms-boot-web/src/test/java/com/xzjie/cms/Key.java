package com.xzjie.cms;

import com.xzjie.cms.security.SecurityTokenProvider;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;

public class Key extends WebApplicationTests {

    @Autowired
    private SecurityTokenProvider jwtTokenProvider;
    @Autowired
    private KeyPair keyPair;

//    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnp8zg4vAnK1z7FNGulayzuut2wTXjYveYqww6YKTHhzktbKl2Fl4LSX9e6TJXdlYkJ3DPqp8j9T+68aSpyyB092J9qJKq46mNdBGLrD3Bq6IedIxgcMq6LqqD9bGTMmELxIwys5TfR/JBUMTy3SWjrCD3Uib2Pph+TOm2LS6LcSBxDkk1oSwq7DH4Rrm/agQi09wYd9jImK6LmViviPDKUAEI1N1Sz76ekSiLm4374mRZ6SBpKGsHxguf/cUztOSs7yxbsyaGt5IUjBLibpJC6fIryFiIjlubHwKD+ECAo8EUJxQjuzvd+r9d8kjUo+c4X2IWB4QIdJSfgkNizx3vAgMBAAECggEBAISbv/y845FHxkK+w99gefqrRWVoC3SwqgCygniGSq+bcIlSYnOJ25b36mwq7WuqqRXtsFhBr6BkcqUuTr0Scrixl9CmjmPmsQy2ms4sTairVg7axtHdRSygjrTTmEJCXepN60qLKpy1LpOlDDLA4u2jz2twKmspNbrooQ88i400hOU93IZxA0/gw/1OxpMa6H9vQfOec9U62YfVcp30Uglau0eMODjkfkLDK1Nr6uWbz5t7LytEJUVWFURC15+gDSSk1Xw+4wJnaOBXAMke1gURJhV9rTBj5bG4Z38JK/Oug4axuv+h4LH+M9u7c9ZgUXaFT9DzzrpVIYmTq8XHljECgYEA0CN7t2n5fKOHD4tYtucX7qF3c6gmGbebncHlirn2kHn8dnOnWVAyNs9zQkScpmFg+s/F5bVmY/eT0Szu4erGn7uS32UoFQYzkMD+3Y5gQFeY+toEpmB5g9zzYpc1HZIqbvXx/KdrC2eB/rD2TzZrJ3gWWmSS56pAtgLtz1K+M+kCgYEAzjUxLCTJ075uEaijVziJQnCG0YHVmoXJd/2RM+iq6nyAvGL+oVCn5VHCX2/ds4JQHpSq6vtUtHz7Ir9jUWcMM/r53Vswb0imC5cF07eW6PDWNSbjmb/4pKaCYRgncEjuYN0OgiCEFQFKREidzwxswLmM2X8iKwmKp84StfcwVBcCgYAKGw7wstaINLBkEtH133aMAY75MJrY+M83c6ifebP9A888ORVRAtHNU3k3KHmqomPBCcGEC7Cy/dxRdasLkQifzM2uxwyRKMHsL1Kwky8dMKOe2mMLaPe9bUbr7IeJrgUdahebus05uobXRORQqEW/ntcHAIcZdGy76Zx+SngtsQKBgAvwBGK71ntNoO2gx6P6/+c6wHNMguMpL5qksU1CAPMGNiWxzW/lTXL3IruHrFwCBUVf8e0ck7DHM3q1fbhusMQpmWF5L/qhSAuPuKSjEjd2CdCV6cMy2+AcS5v3XAEiF7q/0WEzJLJamsQNOCWOYJVfqInG1GGNcdt5EJg8MS4NAoGARBV1nSE1kgfC1yXqt3qHOmXtNgSHB2QCeYDNCdedTdBfIujF79zsWG7VLnwLl1gT/F5n4zxKcw/L1bFNUhzv/TWONyaqsnzyCW880L1aXKp9NJ5ijbGafrjhw4P61ne0HpJvM9hSltIgUTrSWrH9fpxqjdlOw+pG0WzeYdnJA9c=
//    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCnp8zg4vAnK1z7FNGulayzuut2wTXjYveYqww6YKTHhzktbKl2Fl4LSX9e6TJXdlYkJ3DPqp8j9T+68aSpyyB092J9qJKq46mNdBGLrD3Bq6IedIxgcMq6LqqD9bGTMmELxIwys5TfR/JBUMTy3SWjrCD3Uib2Pph+TOm2LS6LcSBxDkk1oSwq7DH4Rrm/agQi09wYd9jImK6LmViviPDKUAEI1N1Sz76ekSiLm4374mRZ6SBpKGsHxguf/cUztOSs7yxbsyaGt5IUjBLibpJC6fIryFiIjlubHwKD+ECAo8EUJxQjuzvd+r9d8kjUo+c4X2IWB4QIdJSfgkNizx3vAgMBAAECggEBAISbv/y845FHxkK+w99gefqrRWVoC3SwqgCygniGSq+bcIlSYnOJ25b36mwq7WuqqRXtsFhBr6BkcqUuTr0Scrixl9CmjmPmsQy2ms4sTairVg7axtHdRSygjrTTmEJCXepN60qLKpy1LpOlDDLA4u2jz2twKmspNbrooQ88i400hOU93IZxA0/gw/1OxpMa6H9vQfOec9U62YfVcp30Uglau0eMODjkfkLDK1Nr6uWbz5t7LytEJUVWFURC15+gDSSk1Xw+4wJnaOBXAMke1gURJhV9rTBj5bG4Z38JK/Oug4axuv+h4LH+M9u7c9ZgUXaFT9DzzrpVIYmTq8XHljECgYEA0CN7t2n5fKOHD4tYtucX7qF3c6gmGbebncHlirn2kHn8dnOnWVAyNs9zQkScpmFg+s/F5bVmY/eT0Szu4erGn7uS32UoFQYzkMD+3Y5gQFeY+toEpmB5g9zzYpc1HZIqbvXx/KdrC2eB/rD2TzZrJ3gWWmSS56pAtgLtz1K+M+kCgYEAzjUxLCTJ075uEaijVziJQnCG0YHVmoXJd/2RM+iq6nyAvGL+oVCn5VHCX2/ds4JQHpSq6vtUtHz7Ir9jUWcMM/r53Vswb0imC5cF07eW6PDWNSbjmb/4pKaCYRgncEjuYN0OgiCEFQFKREidzwxswLmM2X8iKwmKp84StfcwVBcCgYAKGw7wstaINLBkEtH133aMAY75MJrY+M83c6ifebP9A888ORVRAtHNU3k3KHmqomPBCcGEC7Cy/dxRdasLkQifzM2uxwyRKMHsL1Kwky8dMKOe2mMLaPe9bUbr7IeJrgUdahebus05uobXRORQqEW/ntcHAIcZdGy76Zx+SngtsQKBgAvwBGK71ntNoO2gx6P6/+c6wHNMguMpL5qksU1CAPMGNiWxzW/lTXL3IruHrFwCBUVf8e0ck7DHM3q1fbhusMQpmWF5L/qhSAuPuKSjEjd2CdCV6cMy2+AcS5v3XAEiF7q/0WEzJLJamsQNOCWOYJVfqInG1GGNcdt5EJg8MS4NAoGARBV1nSE1kgfC1yXqt3qHOmXtNgSHB2QCeYDNCdedTdBfIujF79zsWG7VLnwLl1gT/F5n4zxKcw/L1bFNUhzv/TWONyaqsnzyCW880L1aXKp9NJ5ijbGafrjhw4P61ne0HpJvM9hSltIgUTrSWrH9fpxqjdlOw+pG0WzeYdnJA9c=
//    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp6fM4OLwJytc+xTRrpWss7rrdsE142L3mKsMOmCkx4c5LWypdhZeC0l/XukyV3ZWJCdwz6qfI/U/uvGkqcsgdPdifaiSquOpjXQRi6w9wauiHnSMYHDKui6qg/WxkzJhC8SMMrOU30fyQVDE8t0lo6wg91Im9j6Yfkzpti0ui3EgcQ5JNaEsKuwx+Ea5v2oEItPcGHfYyJiui5lYr4jwylABCNTdUs++npEoi5uN++JkWekgaShrB8YLn/3FM7TkrO8sW7MmhreSFIwS4m6SQunyK8hYiI5bmx8Cg/hAgKPBFCcUI7s73fq/XfJI1KPnOF9iFgeECHSUn4JDYs8d7wIDAQAB
//    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp6fM4OLwJytc+xTRrpWss7rrdsE142L3mKsMOmCkx4c5LWypdhZeC0l/XukyV3ZWJCdwz6qfI/U/uvGkqcsgdPdifaiSquOpjXQRi6w9wauiHnSMYHDKui6qg/WxkzJhC8SMMrOU30fyQVDE8t0lo6wg91Im9j6Yfkzpti0ui3EgcQ5JNaEsKuwx+Ea5v2oEItPcGHfYyJiui5lYr4jwylABCNTdUs++npEoi5uN++JkWekgaShrB8YLn/3FM7TkrO8sW7MmhreSFIwS4m6SQunyK8hYiI5bmx8Cg/hAgKPBFCcUI7s73fq/XfJI1KPnOF9iFgeECHSUn4JDYs8d7wIDAQAB


    @Test
    public void keyPair() {
        PrivateKey privateKey = jwtTokenProvider.getKeyPair().getPrivate();
        String strKey = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println(strKey);

        PublicKey publicKey = keyPair.getPublic();
        String strPublicKey = Base64.encodeBase64String(publicKey.getEncoded());
        System.out.println(strPublicKey);
        jwtTokenProvider.generateToken(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        });
    }

    @Test
    public void test() throws Exception {


        FileInputStream is = new FileInputStream(new File("jwt.jks"));

        KeyStore keyStore = KeyStore.getInstance("JKS");
        //这里填设置的keystore密码，两个可以不一致
        keyStore.load(is, "123qwe".toCharArray());

        //加载别名，这里认为只有一个别名，可以这么取；当有多个别名时，别名要用参数传进来。不然，第二次的会覆盖第一次的
        Enumeration aliasEnum = keyStore.aliases();
        String keyAlias = "jwt";
        while (aliasEnum.hasMoreElements()) {
            keyAlias = (String) aliasEnum.nextElement();
            System.out.println("别名" + keyAlias);
        }

        Certificate certificate = keyStore.getCertificate(keyAlias);

        //加载公钥
        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        //加载私钥,这里填私钥密码
        PrivateKey privateKey = ((KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias,
                new KeyStore.PasswordProtection("123qwe".toCharArray()))).getPrivateKey();

        //加载私钥另一写法
        //PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, "123456".toCharArray());

        //base64输出私钥
        String strKey = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println(strKey);

        //测试签名
        String sign = Base64.encodeBase64String(sign("测试msg".getBytes(), privateKey, "SHA1withRSA", null));
        //测试验签
        boolean verfi = verify("测试msg".getBytes(), Base64.decodeBase64(sign), publicKey, "SHA1withRSA", null);
        System.out.println(verfi);

    }

    /**
     * 签名
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey, String algorithm, String provider) throws Exception {
        Signature signature;
        if (null == provider || provider.length() == 0) {
            signature = Signature.getInstance(algorithm);
        } else {
            signature = Signature.getInstance(algorithm, provider);
        }
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    /**
     * 验签
     */
    public static boolean verify(byte[] message, byte[] signMessage, PublicKey publicKey, String algorithm,
                                 String provider) throws Exception {
        Signature signature;
        if (null == provider || provider.length() == 0) {
            signature = Signature.getInstance(algorithm);
        } else {
            signature = Signature.getInstance(algorithm, provider);
        }
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signMessage);
    }



}
