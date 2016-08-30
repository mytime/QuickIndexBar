package com.chebao.quickIndexbar;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *
 * 汉字转拼音工具
 *
 * 使用第三方类库 pinyin4j.jar
 *
 *
 *
 * Created by iwan on 16/8/30.
 */
public class PinYinUtil {
    public static String getPinyin(String chinese){
        if (TextUtils.isEmpty(chinese)) return null;

        //用来设置转化的拼音的大小写，或者声调
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE); // 拼音大写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //不带拼音声调



        //1 汉字转为字符数组
        char[] charArray = chinese.toCharArray();
        String pinyin = "";
        for (int i = 0; i < charArray.length; i++) {
            //2 过滤空格,如果是空格，跳过当前空格，继续下一个
            if (Character.isWhitespace(charArray[i]))continue;

            //3 需要判断是汉字,
            //  汉字占2个字节，一个字节范围是-128~127, 一个汉字会大于127，
            if (charArray[i] > 127){
                //可能是汉字
                try {
                    // 可能是多音字（单 dan shan）
                    // 转换后返回的是一个拼音数组
                    String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i],format);
                    if (pinyinArr != null){
                        pinyin += pinyinArr[0]; //即便是多音字，也只能取第一个拼音
                    }

                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                //一定不是汉字,直接与拼音拼接起来
                pinyin += charArray[i];
            }



        }

        return pinyin;
    }

}
