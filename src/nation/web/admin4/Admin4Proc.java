package nation.web.admin4;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import nation.web.tool.AES256Util;

public class Admin4Proc {
  Admin4DAO adminDAO;
  Admin4VO admin4VO;
  ArrayList<Admin4VO> list;
  
  public Admin4Proc() {
    adminDAO = new Admin4DAO(); 
    admin4VO = new Admin4VO();
    list = new ArrayList<Admin4VO>();
  }
  
  /**
   * ������ ���
   * @param admin4VO ������ ����
   * @return 1: ��� ����, 0: ��� ����
   */
  public int insert(Admin4VO admin4VO){
 int count = 0;
    
    try {
      AES256Util aes256 = new AES256Util();
      String crypto_passwd = aes256.aesEncode(admin4VO.getPasswd());
      admin4VO.setPasswd(crypto_passwd);
      count = adminDAO.insert(admin4VO);
       
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    
    return count;
    }

  /**
   * Ű�� �����մϴ�. ��) ABC + ����ð�: ABC1234567890123
   * @return
   */
  public String key(){
    String code = "";
    
    //  ASCII: 65 ~ 90, (A ~ Z: 26��)
    Random rnd = new Random();
    int su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
    
    su = rnd.nextInt(26) + 65; // 0 ~ 25 --> 65 ~ 90
    // System.out.println((char)su);
    code = code + (char)su;
 
    code = code + new Date().getTime();
    // System.out.println("CODE: " + code); // CODE: XGL1449819012230 
    
    return code;
  }
  
  /**
   * Master ������ �ִ��� �˻��մϴ�.
   * @return true: ������ ���� ����, false: �������� ����.
   */
  public boolean isMaster(){
    boolean sw = false;
    
    if (adminDAO.isMaster() == 1 ){ // ������ ������ �̹� �ִٸ�
      sw = true; // ���� ����
    }
    
    return sw;
  }
  
  /** 
   * �ܼ� ����� ���մϴ�.
   * @return ���
   */
  public ArrayList<Admin4VO> list(){
    list = adminDAO.list();
    return list;
  }
  
  /**
   * ������ �����մϴ�.
   * @param admin4no ������ ȸ�� ��ȣ
   * @param act ����
   * @return ����� ���ڵ� ��
   */
  public int updateAct(int admin4no, String act){
    int count = adminDAO.updateAct(admin4no, act);
    return count;   
  }
  
  /**
   * ����ڰ� ȸ�� ���� �̸��� ��ũ�� ������ ����� ó��
   * @param email �̸���
   * @param auth ���� �ڵ�
   * @return ó���� ���ڵ� ����
   */
  public int confirm(String email, String auth){
     int count = adminDAO.confirm(email, auth);
     return count;
  }
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param admin4no ��ȸ�� ��ȣ
   * @return
   */
  public Admin4VO read(int admin4no){
    admin4VO = adminDAO.read(admin4no);
    return admin4VO;
  }
  
  /**
   * ȸ���� �����մϴ�.
   * @param admin4no ������ ȸ�� ��ȣ
   * @return ������ ���ڵ� ����
   */
  public int delete(int admin4no){
    int count = adminDAO.delete(admin4no);
    return count;
  }
  
  /**
   * �ߺ� �̸��� ������ ���մϴ�.
   * @param col �˻� �÷�
   * @param word �˻���
   * @return �˻��� ����
   */
  public int countEmail(String word){ 
    int count = 0;
    
    count = adminDAO.count("email", word);
    
    return count;
  }
  
  /**
   * �Ѱ��� ���ڵ� ��ȸ
   * @param email ��ȸ�� �̸���
   * @return
   */
  public Admin4VO read(String email){
    Admin4VO admin4VO = null;
    
    admin4VO = adminDAO.read(email);
    
    return admin4VO;
  }
  
  /** 
   * �α��� ó��
   * @param email �̸���
   * @param passwd �н�����
   * @return ���ڵ� ����
   */
  public int login(String email, String passwd){
    int count = 0;              // ���ڵ� ����
    
    try {
      AES256Util aes256 = new AES256Util();
      String crypto_passwd = aes256.aesEncode(passwd);
      // System.out.println("--> crypto_passwd: " + crypto_passwd);
      count = adminDAO.login(email, crypto_passwd);
      System.out.println("AdimProc.java -> crpyto_passwd : " + crypto_passwd );
      
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) { 
      e.printStackTrace(); 
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
     
    return count; 
  }
  
  /**
   * �н����尡 ��ġ�ϴ��� Ȯ���մϴ�.
   * @param admin4no ȸ�� ��ȣ
   * @param passwd �н�����
   * @return 1: ��ġ, 0: ����ġ
   */
  public int countPasswd(int admin4no, String passwd){
    int count = 0;
    
    try {
      AES256Util aes256 = new AES256Util();
      String crypto_passwd = aes256.aesEncode(passwd);
      count = adminDAO.countPasswd(admin4no, crypto_passwd);
      
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    
    return count;
  }
  
  /**
   * �н����带 �����մϴ�.
   * @param admin4no ������ ȸ�� ��ȣ
   * @param passwd ������ �н�����
   * @return 1: ���� ����, 2: ���� ����
   */
  public int updatePasswd(int admin4no, String passwd){
    int count = 0;
    
    try { 
      AES256Util aes256 = new AES256Util();
      String crypto_passwd = aes256.aesEncode(passwd);
      count = adminDAO.updatePasswd(admin4no, crypto_passwd);
      
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidAlgorithmParameterException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    
    return count;
  }
  
  /**
   * ȸ�� ����
   * @param admin4VO ȸ�� ����
   * @return 1: ��� ����, 0: ��� ����
   */
  public int update(Admin4VO admin4VO){
    int count = adminDAO.update(admin4VO);
    
    return count; 
  }
}