package com.edu.cardback.mycardback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


/**
 * Created by maisu on 2018/8/2.
 */


public class CardFlipActivity extends Activity {

    boolean mShowingBack = false;   //翻转状态 false表示未翻转

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_card_flip);



        if (savedInstanceState == null) {   //第一个片段添加到活动
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }


    }


public void back(View v){
    flipCard();
}

//翻转
    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();    //将片段加入返回栈
            return;
        }

        // 向后翻转。

        mShowingBack = true;

        // 创建并提交添加片段的新片段事务
//卡背面，使用自定义动画，是片段的一部分。
//管理者的后台堆栈。

        getFragmentManager()
                .beginTransaction()

                // 替换默认的动画片段与动画师资源
                /*
                两个动画制作者，当卡片的正面向外和向左，向内和向左动画时。
                你还需要两个动画师，当卡片的背面和右边，右边和右边都有动画。

                设置特定的动画资源来运行片段
*在这个交易中进入和退出。<代码> Popter < /代码>
*和<代码> POPACEX</COD>动画将播放输入/退出
*在弹出后堆栈时具体操作。
*
*
*
* 设置进入/退出的动画效果（资源文件）。这个必须位于replace、add、remove之前，否则效果不起作用。
     * 四个参数分别表示：添加、移除、从BackStack中pop进入、出来的动画效果。
     *
     *
     * 向右沿中心轴rotationY值减少，0为正面
     * 向左沿中心轴rotationY值增大，0为正面
                 */
                .setCustomAnimations(
                        R.animator.card_flip_right_in,  //（对第二个fragment进入）向右进入 开始完全透明，(180-0)向右旋转180，旋转到90(时间的一半时不透明)
                        R.animator.card_flip_right_out, //(对第一个fragment退出)向右退出  开始完全不透明，(0- -180)向右旋转180，旋转到90(时间的一半时透明)
                        R.animator.card_flip_left_in,   //(对第一个fragment回退进入)向左进入  进栈，开始完全透明，(-180 -0)向左旋转180，旋转到90(时间的一半时不透明)
                        R.animator.card_flip_left_out)  //(对第二个fragment出栈)向左退出  出栈动画，开始完全不透明，(0-180)向左旋转180，旋转到90(时间的一半时透明)

                // 用容器替换当前容器视图中的任何片段
//表示下一页的片段（由
//只是增量CurruttPoad变量）。
                .replace(R.id.container, new CardBackFragment())

                // 将此事务添加到后台堆栈中，允许用户按
                //返回到卡片的正面。
                .addToBackStack(null)

                // 提交事务。
                .commit();
    }
}






