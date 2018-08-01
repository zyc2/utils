package pers.zyc.common;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author YanchaoZhang
 * @date 2018/8/1 18:37
 */
public class PasswordTool {

    private int saltLength;

    /**
     * 设置加盐长度
     */
    public PasswordTool(int saltLength) {
        this.saltLength = saltLength;
    }

    public String encode(String rawPassword) {
        String randomHex = Randoms.getRandomHex(saltLength);
        String md5Hex = DigestUtils.md5Hex(randomHex + rawPassword);
        return randomHex + md5Hex;
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() != saltLength + 32) return false;
        String salt = encodedPassword.substring(0, saltLength);
        return DigestUtils.md5Hex(salt + rawPassword).equals(encodedPassword.substring(saltLength));
    }
}
