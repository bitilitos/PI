package pi.crypto;//De modo a isto funcionar, necessitamos de metodos nos nodes para definir as suas chaves primárias.
//RSA vai ser um class estática, pois não precisamos de instaciar, apenas precisamos de aceder aos seus metodos, de modo a criar um chave publica-privada nas instancias de nodes criados.


import pi.userNode.Node;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
	private static Random R;
	private int maxLenght;

	public static void setRSAKeys (BigInteger P, BigInteger Q, Node node, int maxLenght){
		if(!P.isProbablePrime(1))
			P = P.nextProbablePrime();
		if(!Q.isProbablePrime(1))
			Q= Q.nextProbablePrime();

		R = new Random();

		BigInteger N = P.multiply(Q);
		BigInteger PHI = P.subtract(BigInteger.ONE).multiply(  Q.subtract(BigInteger.ONE)); //(P-1)x(Q-1)
		BigInteger e =  BigInteger.probablePrime(maxLenght / 2, R); // 1<e<PHI, maxLenght vem do slider
		while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0) {
            //PHI.gcd(e).compareTo(1) (1 se phi.gcd(e) > 1, 0 se for igual, -1 se for menor (0 ou negativo))
            //e.compareTo(PHI) se o e>PHI devolve 1, se e<PHI -1, se forem iguais 0.
            //that is the same as PHI and e has no other gcd to PHI then 1
            e.add(BigInteger.ONE); //e++
        }

        BigInteger d = e.modInverse(PHI); //This is a MATH function Inverso do Modulo

        node.setPublicKey(P,e,N);
        node.setPrivateKey(Q,d,N);
	}

	public static String bToS(byte[] cipher){
		String temp = "";
		for (byte b : cipher)
			temp+=Byte.toString(b);

		return temp;
	}

	public static byte[] encryptMessage(byte[] message, BigInteger e, BigInteger N){

		return ((new BigInteger(message)).modPow(e,N).toByteArray());
	}

	public static byte[] decryptMessage(byte[] message, BigInteger d, BigInteger N){
		System.out.println("[INSIDE FUNCTION]");
		System.out.println("Message: " + bToS(message) + "\nD: " + d + "\nN" + N);
		byte[] cipherMessage = ((new BigInteger(message)).modPow(d,N).toByteArray());
		System.out.println("Message Decrypted: " + RSA.bToS(cipherMessage));
		return cipherMessage;
	}

	public void setMaxLenght(int maxLenght){
		this.maxLenght = maxLenght;
	}
}