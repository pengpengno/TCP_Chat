package resource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.Cipher;

public class RSA {
	
	

/**
 * @author 怦怦
 * 部分源代码已弃用，测试阶段结束
 *   已在服务器上成功部署解码文件， 并将会将各个明文的PRIVATEKEY存入数据库
 *
 */
	
	public static final String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9VUyIjwEJxCy70IjF1tyUhkxZCQ5M58"
			+ "z7GpmVJMDel/i52QpSy+6us35FtaXzw6Qc+MlSIVTrYbHbo/TvfaBztPw0LZxCH3bwC4PIlgwRB2UW/XmjU5wl+P6z3izSdnSb"
			+ "cKTpls3LNXCmI9NcBHWglGQ2/65KbLJaqPXZQOeCAwIDAQAB";
	/*public static final String PRIVAE_KEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL1VTIiPAQnELLvQiMXW3J"
			+ "SGTFkJDkznzPsamZUkwN6X+LnZClLL7q6zfkW1pfPDpBz4yVIhVOthsduj9O99oHO0/DQtnEIfdvALg8iWDBEHZRb9eaNTnCX4"
			+ "/rPeLNJ2dJtwpOmWzcs1cKYj01wEdaCUZDb/rkpsslqo9dlA54IDAgMBAAECgYBU2fSJwuUpNlUB+wxA9fE3UxTGVkulZcE0zB"
			+ "AGikkxuRQMXlvFDYYzn1e48Bamr4hOP6Uh/LQtJURrdghgiIGucwgXq8qALrSkWQcVeQVG/yfevPJqMin+dcqP1S+eRF+cLNlm"
			+ "mYShLIMXYC4g/CsiN90B2ijQgYqI9GUooKR9CQJBAPRJWFawLwjKPgnGaz/YGiGDqpASVxhotuFaVVhmPjzLfyO5nGdIDRiIq/"
			+ "hHU2cSwdTwP8CF6vBeFpzyZzpGEOcCQQDGaWS8PaGTxyygo4jUPVw3+EK2uwXwkno1DMhNmLy4YEP2ncApADTMbaOpH7est3285"
			+ "+sWGWJeGfflJ/unkDaFAkEAqCF67V8hYBliDXRFCJIBmiN21uXoj+IyTHPR3Z+xIkKk5L+xB9ytNr+KLL1ah8x/H8Gi/yysgHc"
			+ "hdS/ZIGBs7QJAU/Lp5V+qSs1+C2XGEg74QtIu4APXqoc/X8zNv089okFQ4Dq0wGRO8qP9GCTMYS6t68ywwgPxg+E26uV069vvx"
			+ "QJBAJ2UCzVZAHmiRxmQDjnP6jH2r244bAeTA/0V1gyxvqq9Mf6BDs3ixUwllgIqnfqzJ3nWC/KU/axu4ieIwwYw5sc=";*/
    //public  String local_info_url="E:\\eclipse\\chat\\src\\fxml_view\\user_login_info\\"; 
    public File store_login_info;
    
	public RSA() {
		/*String Requiring_ncryption="wal123";
		Map<String, String> keyMap = RSA.createKeys(1024);
		String publicKey = keyMap.get("publicKey");
		String privateKey = keyMap.get("privateKey");
	        System.out.println("公钥: \n\r" + publicKey);
	        System.out.println("私钥： \n\r" + privateKey);

	        System.out.println("公钥加密——私钥解密");

	        System.out.println("\r明文：\r\n" + Requiring_ncryption);
	        System.out.println("\r明文大小：\r\n" + Requiring_ncryption.getBytes().length);
	        
	        String encodedData = null;
			try {
				encodedData = RSA.publicEncrypt(Requiring_ncryption, RSA.getPublicKey(PUBLIC_KEY));
				System.out.println("密文：\r\n" + encodedData);
				 String decodedData = RSA.privateDecrypt(encodedData, RSA.getPrivateKey(PRIVAE_KEY)); //传入密文和私钥,得到明文
			        System.out.println("解密后文字: \r\n" + decodedData);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}  //传入明文和公钥加密,得到密文
			*/
	        
	}
	/*
	 * 
	 * 加密
	 */
	public  String encrypted(String cleartext_passwords) {
		
		
	    String encodedData = null;
		try {
			
			encodedData = RSA.publicEncrypt(cleartext_passwords, RSA.getPublicKey(PUBLIC_KEY));
			System.out.println("密文：\r\n" + encodedData);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  //传入明文和公钥加密,得到密文
		return encodedData;
	}
	/*
	 * 解密
	 */
	/*public  String decryption(String  ciphertext) {

		String decodedData=null;
		try {

	        decodedData = RSA.privateDecrypt(ciphertext, RSA.getPrivateKey(PRIVAE_KEY)); //传入密文和私钥,得到明文
	        System.out.println("解密后文字: \r\n" + decodedData);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  //传入明文和公钥加密,得到密文
		return decodedData;
	}*/
	
	  	public static final String CHARSET = "UTF-8";
	    public static final String RSA_ALGORITHM = "RSA";

	    public static Map<String, String> createKeys(int keySize) {
	        // 为RSA算法创建一个KeyPairGenerator对象
	        KeyPairGenerator kpg;
	        try {
	            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
	        } catch (NoSuchAlgorithmException e) {
	            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
	        }

	        // 初始化KeyPairGenerator对象,密钥长度
	        kpg.initialize(keySize);
	        // 生成密匙对
	        KeyPair keyPair = kpg.generateKeyPair();
	        // 得到公钥
	        Key publicKey = keyPair.getPublic();
	        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
	        // 得到私钥
	        Key privateKey = keyPair.getPrivate();
	        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
	        // map装载公钥和私钥
	        Map<String, String> keyPairMap = new HashMap<String, String>();
	        keyPairMap.put("publicKey", publicKeyStr);
	        keyPairMap.put("privateKey", privateKeyStr);
	        // 返回map
	        return keyPairMap;
	    }

	    /*
	     * 得到公钥
	     * @param publicKey  密钥字符串（经过base64编码）
	     * 
	     */
	    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
	        // 通过X509编码的Key指令获得公钥对象
	        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
	        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
	        return key;
	    }

	    /*
	     * 得到私钥
	     * @param privateKey  密钥字符串（经过base64编码）
	     * 
	     */
	    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
	        // 通过PKCS#8编码的Key指令获得私钥对象
	        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
	        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
	        return key;
	    }

	    /*
	     * 公钥加密
	     * @param data
	     * @param publicKey
	     * 
	     */
	    public static String publicEncrypt(String data, RSAPublicKey publicKey) {
	        try {
	            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
	            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
	        } catch (Exception e) {
	            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
	        }
	    }

	    /*
	     * 私钥解密
	     * @param data
	     * @param privateKey
	     * 
	     */

	    public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
	        try {
	            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
	            cipher.init(Cipher.DECRYPT_MODE, privateKey);
	            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data), privateKey.getModulus().bitLength()), CHARSET);
	        } catch (Exception e) {
	            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
	        }
	    }

	    /*
	     * 私钥加密
	     * @param data
	     * @param privateKey
	     * 
	     */

	    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
	        try {
	            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
	            //每个Cipher初始化方法使用一个模式参数opmod，并用此模式初始化Cipher对象。此外还有其他参数，包括密钥key、包含密钥的证书certificate、算法参数params和随机源random。
	            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
	            return Base64.getEncoder().encodeToString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
	        } catch (Exception e) {
	            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
	        }
	    }

	    /**
	     * 公钥解密
	     * @param data
	     * @param publicKey
	     * @return
	     */

	    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
	        try {
	            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
	            cipher.init(Cipher.DECRYPT_MODE, publicKey);
	            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.getDecoder().decode(data), publicKey.getModulus().bitLength()), CHARSET);
	        } catch (Exception e) {
	            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
	        }
	    }

	    //rsa切割解码  , ENCRYPT_MODE,加密数据   ,DECRYPT_MODE,解密数据
	    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
	        int maxBlock = 0;  //最大块
	        if (opmode == Cipher.DECRYPT_MODE) {
	            maxBlock = keySize / 8;
	        } else {
	            maxBlock = keySize / 8 - 11;
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int offSet = 0;
	        byte[] buff;
	        int i = 0;
	        try {
	            while (datas.length > offSet) {
	                if (datas.length - offSet > maxBlock) {
	                    //可以调用以下的doFinal（）方法完成加密或解密数据：
	                    buff = cipher.doFinal(datas, offSet, maxBlock);
	                } else {
	                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
	                }
	                out.write(buff, 0, buff.length);
	                i++;
	                offSet = i * maxBlock;
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
	        }
	        byte[] resultDatas = out.toByteArray();
	        ///IOUtils.closeQuietly(out);
	        return resultDatas;
	    }


	    // 简单测试__
	    public static void main(String[] args) throws Exception {
	      
	    	 new RSA();
	    }
}
