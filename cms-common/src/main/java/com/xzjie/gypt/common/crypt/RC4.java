//package com.xzjie.gypt.common.crypt;
//
//import org.aspectj.weaver.Utils;
//
//public class RC4 {
//
//	private byte[] key; 
//    private int[] sbox; 
//    private static final int SBOX_LENGTH = 256; 
//    private static final int KEY_MIN_LENGTH = 5; 
//    private int i = 0; 
//    private int j = 0; 
// 
//    public static void main(String[] args) { 
// 
//        RC4 rce = new RC4(Utils.parseAsHexOrBase58("a0a0a0a0a0")); 
//        byte[] result = rce.encrypt("hello there".getBytes()); 
//        //System.out.println("encrypted string:\n" + Utils.bytesToHexString(result)); 
// 
////        byte[] result4 = rce.encrypt("hello there".getBytes()); 
////        System.out.println("encrypted string:\n" + Utils.bytesToHexString(result) + Utils.bytesToHexString(result4)); 
//// 
//// 
//// 
////        RC4 rc2 = new RC4("testkey".getBytes()); 
////        byte[] result2 = rc2.encrypt("hello therehello there".getBytes()); 
////        System.out.println("encrypted string:\n" + Utils.bytesToHexString(result2)); 
//        RC4 rcd = new RC4(Utils.parseAsHexOrBase58("a0a0a0a0a0")); 
// 
//        System.out.println("decrypted string:\n" 
//                + new String(rcd.decrypt(result))); 
// 
//    } 
// 
//    public RC4(byte[] key) { 
// 
//        if (!(key.length >= KEY_MIN_LENGTH && key.length < SBOX_LENGTH)) { 
//            System.out.println("Key length has to be between " 
//                    + KEY_MIN_LENGTH + " and " + (SBOX_LENGTH - 1)); 
//        } 
// 
//        sbox = initSBox(key); 
//    } 
// 
//    public RC4() { 
//    } 
// 
//    public byte[] decrypt(final byte[] msg) { 
//        return encrypt(msg); 
//    } 
// 
//    public byte[] encrypt(final byte[] msg) { 
//        byte[] code = new byte[msg.length]; 
//        for (int n = 0; n < msg.length; n++) { 
//            i = (i + 1) % SBOX_LENGTH; 
//            j = (j + sbox[i]) % SBOX_LENGTH; 
//            swap(i, j, sbox); 
//            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH]; 
//            code[n] = (byte) (rand ^ (int) msg[n]); 
//            //code[n] = (byte) ((rand ^ (int) msg[n]) +SBOX_LENGTH % SBOX_LENGTH); 
//            //System.out.println("Byte: " + msg[n] + " " + code[n]); 
// 
//        } 
//        return code; 
//    } 
// 
//    private int[] initSBox(byte[] key) { 
//        int[] sbox = new int[SBOX_LENGTH]; 
//        int j = 0; 
// 
//        for (int i = 0; i < SBOX_LENGTH; i++) { 
//            sbox[i] = i; 
//        } 
// 
//        for (int i = 0; i < SBOX_LENGTH; i++) { 
//            j = (j + sbox[i] + key[i % key.length] + SBOX_LENGTH) % SBOX_LENGTH; 
//            swap(i, j, sbox); 
//        } 
//        return sbox; 
//    } 
// 
//    private void swap(int i, int j, int[] sbox) { 
//        int temp = sbox[i]; 
//        sbox[i] = sbox[j]; 
//        sbox[j] = temp; 
//    } 
//}
