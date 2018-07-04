package com.krw.spring.constants;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.xml.bind.DatatypeConverter;

public class AuthConstants {

	private static PrivateKey jwtPrivateKey;
	private static PublicKey jwtPublicKey;

	static {
		try {
			KeyFactory kf = KeyFactory.getInstance("RSA");

			jwtPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(
					"MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAll/ZGIs+61LckT/jHpS7lZZuyFVdjR0g2Vmpi3MjLQHwSxgU2d12cZ3ktyvP4OwT/YJAKKh5ewko9fbWKk+vVQIDAQABAkA5Xonpxj4T84H43EHfCeljEYhkX8UYMDO3GHEJpbyWz5z/pMAHHdGPILjg80SWcEClyiZWmBnF5795lUDULPfhAiEA7jKm27xPGeQfKwtjtjfAUY+QeJsvfiNe1XKQtxKSEFkCIQChnOdhw8YdbmWhRn+K9LGZjk0RLAkzjaDps9Ii4w3XXQIhAK6W6b59k+0CS+Yc/wT8cDZX6Ci+dl0aR9RjmLbXIcBhAiEAiibNqMYpWklf+VCBgPCRzVlWygIhz38Xeg/s50O/NkUCICN/u6BfZH5N1CTAFUf+qPabjytHU89j7URtkYqYVhBe")));
			jwtPublicKey = kf.generatePublic(new X509EncodedKeySpec(DatatypeConverter.parseBase64Binary(
					"MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJZf2RiLPutS3JE/4x6Uu5WWbshVXY0dINlZqYtzIy0B8EsYFNnddnGd5Lcrz+DsE/2CQCioeXsJKPX21ipPr1UCAwEAAQ==")));
		} catch (Exception e) {
			jwtPrivateKey = null;
			jwtPublicKey = null;
			// I think it will never happen.
		}
	}

	public static PrivateKey getJwtPrivateKey() {
		return jwtPrivateKey;
	}

	public static PublicKey getJwtPublicKey() {
		return jwtPublicKey;
	}

	// public static void main(String[] args) {
	//
	// try {
	// KeyPairGenerator g = KeyPairGenerator.getInstance("RSA");
	// g.initialize(512);
	// KeyPair keyPair = g.generateKeyPair();
	// String privKey =
	// DatatypeConverter.printBase64Binary(keyPair.getPrivate().getEncoded());
	// String publicKey =
	// DatatypeConverter.printBase64Binary(keyPair.getPublic().getEncoded());
	// System.out.println("///////////Private Key start//////////");
	// System.out.println(privKey);
	// System.out.println("///////////Private Key end//////////");
	//
	// System.out.println("///////////Public Key start//////////");
	// System.out.println(publicKey);
	// System.out.println("///////////Public Key end//////////");
	//
	// KeyFactory kf = KeyFactory.getInstance("RSA");
	//
	// new KeyPair(kf.generatePublic(new
	// X509EncodedKeySpec(DatatypeConverter.parseBase64Binary(publicKey))),
	// kf.generatePrivate(new
	// PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(privKey))));
	//
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// } catch (NoSuchProviderException e) {
	// e.printStackTrace();
	// } catch (InvalidAlgorithmParameterException e) {
	// e.printStackTrace();
	// } catch (InvalidKeySpecException e) {
	// e.printStackTrace();
	// }
	//
	// }

}
