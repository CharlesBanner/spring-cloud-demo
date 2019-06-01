package com.charles.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author: GanZiB
 * Date: 2019-05-30
 * Time: 19:08
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelHeader {
    /**
     * 起始行
     * @return
     */
    int rowStartNum();

    /**
     * 结束行
     * @return
     */
    int rowEndNum();

    /**
     * 起始列
     * @return
     */
    int cellStartNum();

    /**
     * 结束列
     * @return
     */
    int cellEndNum();

    /**    字体颜色
     *     BLACK,BROWN,OLIVE_GREEN,DARK_GREEN,DARK_TEAL,DARK_BLUE,INDIGO,GREY_80_PERCENT,
     *     ORANGE,DARK_YELLOW,GREEN,TEAL,BLUE,BLUE_GREY,GREY_50_PERCENT,RED,LIGHT_ORANGE,
     *     LIME,SEA_GREEN,AQUA,LIGHT_BLUE,VIOLET,GREY_40_PERCENT,PINK,GOLD,YELLOW,BRIGHT_GREEN,
     *     TURQUOISE,DARK_RED,SKY_BLUE,PLUM,GREY_25_PERCENT,ROSE,LIGHT_YELLOW,LIGHT_GREEN,
     *     LIGHT_TURQUOISE,PALE_BLUE,LAVENDER,WHITE,CORNFLOWER_BLUE,LEMON_CHIFFON,MAROON,
     *     ORCHID,CORAL,ROYAL_BLUE,LIGHT_CORNFLOWER_BLUE,TAN,AUTOMATIC
      * @return
     */
    String fontColor() default "BLACK";

    /**
     * 是否合并单元格
     * @return
     */
    boolean isMerged() default true;
}
