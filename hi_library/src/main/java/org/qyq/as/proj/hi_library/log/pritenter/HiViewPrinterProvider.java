package org.qyq.as.proj.hi_library.log.pritenter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.qyq.as.proj.hi_library.R;
import org.qyq.as.proj.hi_library.util.HiDisplayUtil;


/**
 * @Author: Net Spirit
 * @Time: 2023/3/13 20:43
 * @FixAuthor:
 * @FixTime:
 * @Desc: 视图日志打印器Provider
 */
public class HiViewPrinterProvider {
    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;
    private RecyclerView recyclerView;

    public HiViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }

    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    /**
     * 显示悬浮窗
     */
    public void showFloatingView() {
        //悬浮窗存在则返回，防止重复添加
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }
        //设置悬浮窗样式
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置悬浮窗底部右侧对齐
        params.gravity = Gravity.BOTTOM | Gravity.END;
        //创建悬浮窗
        View floatingView = genFloatingView();
        //设置TAG
        floatingView.setTag(TAG_FLOATING_VIEW);
        //背景颜色
        floatingView.setBackgroundColor(Color.BLACK);
        //设置透明度
        floatingView.setAlpha(0.8f);
        //底部边距
        params.bottomMargin = HiDisplayUtil.dp2px(100, recyclerView.getResources());
        //使用genFloatingView(),防止floatingView为空的情况
        rootView.addView(genFloatingView(), params);
    }

    /**
     * 关闭悬浮窗
     */
    public void closeFloatingView() {
        rootView.removeView(genFloatingView());
    }

    /**
     * 生成悬浮窗
     *
     * @return floatingView
     */
    private View genFloatingView() {
        //判断悬浮窗是否为空，不空直接返回悬浮窗
        if (floatingView != null) {
            return floatingView;
        }
        //创建TextView
        TextView textView = new TextView(rootView.getContext());
        textView.setOnClickListener(v -> {
            //点击textView,判断状态是否是关闭，是则显示log视图
            if (!isOpen) {
                showLogView();
            }
        });
        textView.setText("HiLog");
        return floatingView = textView;
    }

    /**
     * 显示日志
     */
    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        //视图高度默认设置160，也可动态设置
                        HiDisplayUtil.dp2px(160, rootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        logView.setTag(TAG_LOG_VIEW);
        rootView.addView(genLogView(), params);
        //设置展开状态为true
        isOpen = true;
    }

    /**
     * 创建日志视图
     *
     * @return 返回日志视图
     */
    private View genLogView() {
        if (logView != null) {
            return logView;
        }
        FrameLayout logView = new FrameLayout(rootView.getContext());
        logView.setBackgroundColor(Color.BLACK);
        //添加日志列表
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = HiDisplayUtil.dp2px(16, rootView.getResources());
        logView.addView(recyclerView, params);
        //关闭按钮
        FrameLayout.LayoutParams closeParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        closeParams.gravity = Gravity.END;
        TextView closeView = new TextView(rootView.getContext());
        closeView.setOnClickListener(v -> {
            closeLogView();
        });
        closeView.setText("[ 关闭 ]");
        closeView.setTextColor(Color.WHITE);
        logView.addView(closeView, closeParams);
        //设置高度按钮
        FrameLayout.LayoutParams setHeightParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeightParams.gravity = Gravity.START;
        TextView setHeightView = new TextView(rootView.getContext());
        setHeightView.setOnClickListener(v -> {
            setHeight();
        });
        setHeightView.setText("[ 设置高度 ]");
        setHeightView.setTextColor(Color.WHITE);
        logView.addView(setHeightView, setHeightParams);
        return this.logView = logView;
    }

    /**
     * 重置高度
     */
    private void setHeight() {
        if (logView == null) {
            return;
        }
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) logView.getLayoutParams();
        //显示高度输入框
        HeightNumBerDialog dialog = new HeightNumBerDialog(rootView.getContext());
        dialog.setListener(height -> {
            params.height = height;
            logView.setLayoutParams(params);
            dialog.dismiss();
        });
        dialog.show();
    }

    /**
     * 关掉日志视图
     */
    private void closeLogView() {
        isOpen = false;
        rootView.removeView(genLogView());
    }

    static class HeightNumBerDialog extends BottomSheetDialog {
        //允许设置的最小高度值为100
        private static final int MIN_HEIGHT = 100;
        private HeightChangeListener listener;


        public HeightNumBerDialog(@NonNull Context context) {
            super(context);
            setContentView(R.layout.dialog_view_printer_height);
            EditText etHeight = findViewById(R.id.et_height);
            TextView tvHint = findViewById(R.id.tv_hint);
            Button btnRight = findViewById(R.id.btn_right);
            //最大值允许到屏幕的2/3以防遮挡后面的内容
            int maxHeight = HiDisplayUtil.getDisplayHeightInPx(context) * 2 / 3;
            assert etHeight != null;
            etHeight.setHint("请输入高度（100-" + maxHeight + ")");
            btnRight.setOnClickListener(v -> {
                String heightStr = etHeight.getText().toString().trim();
                int height = TextUtils.isEmpty(heightStr) ? MIN_HEIGHT : Integer.parseInt(heightStr);
                if (height < MIN_HEIGHT || height > maxHeight) {
                    assert tvHint != null;
                    tvHint.setText("请输入100~" + maxHeight + "的整数高度值");
                    return;
                }
                if (listener != null) {
                    listener.heightChangeListener(height);
                }
            });

        }


        public void setListener(HeightChangeListener listener) {
            this.listener = listener;
        }

        interface HeightChangeListener {
            /**
             * 高度值监听器
             *
             * @param height 设置的高
             */
            void heightChangeListener(int height);
        }
    }

}
