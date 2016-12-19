package com.xzjie.gypt.common.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RC4 {

	// private int[] S = new int[256];
	// private int[] T = new int[256];
	// private int keylen;
	//
	// public RC4(byte[] key) throws Exception {
	// if (key.length < 1 || key.length > 256) {
	// throw new Exception("key must be between 1 and 256 bytes");
	// } else {
	// keylen = key.length;
	// for (int i = 0; i < 256; i++) {
	// S[i] = i;
	// T[i] = key[i % keylen];
	// }
	// int j = 0;
	// for (int i = 0; i < 256; i++) {
	// j = (j + S[i] + T[i]) % 256;
	// S[i] ^= S[j];
	// S[j] ^= S[i];
	// S[i] ^= S[j];
	// }
	// }
	// }
	//
	// public int[] encrypt(int[] plaintext) {
	// int[] ciphertext = new int[plaintext.length];
	// int i = 0, j = 0, k, t;
	// for (int counter = 0; counter < plaintext.length; counter++) {
	// i = (i + 1) % 256;
	// j = (j + S[i]) % 256;
	// S[i] ^= S[j];
	// S[j] ^= S[i];
	// S[i] ^= S[j];
	// t = (S[i] + S[j]) % 256;
	// k = S[t];
	// ciphertext[counter] = plaintext[counter] ^ k;
	// }
	// return ciphertext;
	// }
	//
	// public int[] decrypt(int[] ciphertext) {
	// return encrypt(ciphertext);
	// }
	//
	// public static void main(String args[]) throws Exception {
	// String keyword = "hello";
	// byte[] keytest = keyword.getBytes(); //convert keyword to byte
	//
	// int[] text = {1, 2, 3, 4, 5}; // text as 12345
	//
	// RC4 rc4 = new RC4(keytest);
	//
	// System.out.print("\noriginal text: ");
	// for (int i = 0; i < text.length; i++) {
	// System.out.print(text[i]);
	// }
	//
	// int[] cipher = rc4.encrypt(text); //encryption
	// System.out.print("\ncipher: ");
	// for (int i = 0; i < cipher.length; i++) {
	// System.out.print(cipher[i]);
	// }
	//
	// int[] backtext = rc4.decrypt(cipher); //decryption
	// System.out.print("\nback to text: ");
	// for (int i = 0; i < backtext.length; i++) {
	// System.out.print(backtext[i]);
	// }
	// System.out.println();
	// }

	static private int keylength = 8; // keylength for WEP; deprecated

	private byte[] S;
	private int the_i;
	private int the_j;
	private int next_j = -666; // not really needed after all

	public RC4() {
		S = new byte[256];
		the_i = the_j = 0;
	}

	public RC4(byte[] S) {
		this.S = S;
		the_i = the_j = 0;
	}

	/**
	 * run the key scheduler for n rounds, using key[0]...key[n-1]
	 */
	public void ksa(int n, byte[] key, boolean printstats) {
		int keylength = key.length; // NOT keylength above!!
		int i = 0;
		for (i = 0; i < 256; i++)
			S[i] = (byte) i;
		the_j = 0;
		for (the_i = 0; the_i < n; the_i++) {
			the_j = (the_j + posify(S[the_i]) + posify(key[the_i % keylength])) % 256;
			sswap(S, the_i, the_j);
		}
		if (n != 256) {
			next_j = (the_j + posify(S[n]) + posify(key[n % keylength])) % 256;
		}
		if (printstats) {
			System.out.print("S_" + (n - 1) + ":");
			for (int k = 0; k <= n; k++)
				System.out.print(" " + posify(S[k]));
			System.out.print("   j_" + (n - 1) + "=" + the_j);
			System.out.print("   j_" + n + "=" + next_j);
			System.out.print("   S_" + (n - 1) + "[j_" + (n - 1) + "]=" + posify(S[the_j]));
			System.out.print("   S_" + (n - 1) + "[j_" + (n) + "]=" + posify(S[next_j]));
			if (S[1] != 0)
				System.out.print("   S[1]!=0");
			System.out.println();
		}
	}

	public void ksa(byte[] key) {
		ksa(256, key, false);
	}

	public void init() {
		the_i = the_j = 0;
	}

	byte nextVal() {
		the_i = (the_i + 1) % 256;
		the_j = (the_j + posify(S[the_i])) % 256;
		sswap(S, the_i, the_j);
		byte value = S[(posify(S[the_i]) + posify(S[the_j])) % 256];
		return value;
	}

	// returns i for which x = S[i]
	byte inverse(byte x) {
		int i = 0;
		while (i < 256) {
			if (x == S[i])
				return (byte) i;
			i++;
		}
		return (byte) 0; // never get here
	}

	int the_i() {
		return this.the_i;
	}

	int the_j() {
		return this.the_j;
	}

	int next_j() {
		return this.next_j;
	}

	int S(int n) {
		return posify(S[(byte) n]);
	}

	private static void sswap(byte[] S, int i, int j) {
		byte temp = S[i];
		S[i] = S[j];
		S[j] = temp;
	}

	// returns value of b as an unsigned int
	public static int posify(byte b) {
		if (b >= 0)
			return b;
		else
			return 256 + b;
	}

	/**
	 * buildkey is for WEP keys only
	 */
	public static byte[] buildkey(byte[] IV, byte[] shortkey) {
		byte[] key = new byte[keylength];
		int ivlen = IV.length;
		int i = 0;
		for (i = 0; i < ivlen; i++)
			key[i] = IV[i];
		for (i = ivlen; i < keylength; i++)
			key[i] = shortkey[i - ivlen];
		return key;
	}

	static public String byte2string(byte b) {
		int high = (b >> 4) & 0x0F;
		int low = (b & 0x0F);
		String convert = "0123456789abcdef";
		// convert = "0123456789ABCDEF"; // uncomment if you want uppercase
		String result = "";
		result += convert.charAt(high);
		result += convert.charAt(low);
		return result;
	}

	static public String byte2string(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += byte2string(b[i]);
		}
		return result;
	}

	// given a char '0' ...'f' or 'F', returns 0..15
	static private int hexval(char c) {
		if ('0' <= c && c <= '9')
			return (c - '0');
		if ('a' <= c && c <= 'f')
			return (c - 'a' + 10);
		if ('A' <= c && c <= 'F')
			return (c - 'A' + 10);
		return 0;
	}

	static public byte[] string2byte(String s) {
		int length = s.length();
		length = (length / 2);
		byte[] buf = new byte[length];
		for (int i = 0; i < length; i++) {
			int nyb1 = hexval(s.charAt(2 * i));
			int nyb2 = hexval(s.charAt(2 * i + 1));
			buf[i] = (byte) ((nyb1 * 16) + nyb2);
		}
		return buf;
	}

	/**
	 * encrypt is for testing; key can be any length
	 */
	static public byte[] encrypt(byte[] key, byte[] message) {
		byte[] outbuf = new byte[message.length];
		RC4 r = new RC4();
		r.ksa(key);
		r.init();
		for (int i = 0; i < message.length; i++) {
			outbuf[i] = (byte) (message[i] ^ r.nextVal());
		}
		return outbuf;
	}

	/**
	 * reads column 2 (blank-separated) of a table of 256 rows; returns as a
	 * byte[] reads from the named file modify as needed!
	 */
	static byte[] readvalues(String filename) {
		byte[] values = new byte[256];
		FileReader fr;
		BufferedReader br;
		String line;
		try {
			fr = new FileReader(filename);
		} catch (FileNotFoundException fnfe) {
			System.err.println("cannot open file " + '"' + filename + '"');
			return null;
		}
		br = new BufferedReader(fr);
		for (int i = 0; i < 256; i++) {
			try {
				line = br.readLine();
			} catch (IOException ioe) {
				line = "";
			}

			StringTokenizer st = new StringTokenizer(line);
			st.nextToken();
			values[i] = (byte) Integer.parseInt(st.nextToken());
		}
		return values;
	}
	
	public static void main(String[] args) {
		byte[] bs= RC4.encrypt("dddd".getBytes(), "12348909089@qq.com".getBytes());
		
		String var= RC4.byte2string(bs);
		
		System.out.println(var);
	}
}
