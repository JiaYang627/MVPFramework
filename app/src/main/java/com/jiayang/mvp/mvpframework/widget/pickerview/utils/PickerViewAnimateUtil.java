package com.jiayang.mvp.mvpframework.widget.pickerview.utils;

import android.view.Gravity;

import com.jiayang.mvp.mvpframework.R;


/**
 * @author Sai
 * @date 15/8/9
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;

    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {

        /*if (gravity == Gravity.BOTTOM){
            return isInAnimation ? R.anim.pickerview_slide_in_bottom : R.anim.pickerview_slide_out_bottom;
        }
        return INVALID;*/

        return gravity == Gravity.BOTTOM ? isInAnimation ? R.anim.pickerview_slide_in_bottom : R.anim.pickerview_slide_out_bottom : INVALID;
    }
}
