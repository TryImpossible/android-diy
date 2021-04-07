package com.barry.baselib.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.barry.baselib.R;

public class HeaderView extends RelativeLayout implements View.OnClickListener {

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 容器
     */
    private RelativeLayout mLayout;
    /**
     * 左侧
     */
    private LinearLayout mLlBack;
    /**
     * 返回图标
     */
    private ImageView mIvBackIcon;
    /**
     * 返回文案
     */
    private TextView mTvBackText;
    /**
     * 中间
     */
    private FrameLayout mFlTitle;
    /**
     * 标题
     */
    private TextView mTvTitleText;
    /**
     * 右侧
     */
    private LinearLayout mLlMenu;
    /**
     * 菜单图标
     */
    private ImageView mIvMenuIcon;
    /**
     * 菜单文案
     */
    private TextView mTvMenuText;
    /**
     * 底部分隔线
     */
    private View mDivider;
    /**
     * 返回事件
     */
    private OnClickListener mBackClickListener;
    /**
     * 菜单事件
     */
    private OnClickListener mMenuClickListener;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_back_layout) {
            if (null != mBackClickListener) {
                mBackClickListener.onClick(v);
            }
        }
        if (v.getId() == R.id.ll_menu_layout) {
            if (null != mMenuClickListener) {
                mMenuClickListener.onClick(v);
            }
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.view_header_layout, this);
        mLayout = view.findViewById(R.id.layout);
        mLlBack = view.findViewById(R.id.ll_back_layout);
        mLlBack.setOnClickListener(this);
        mIvBackIcon = view.findViewById(R.id.iv_back);
        mTvBackText = view.findViewById(R.id.tv_back);
        mFlTitle = view.findViewById(R.id.fl_title_layout);
        mTvTitleText = view.findViewById(R.id.tv_title);
        mLlMenu = view.findViewById(R.id.ll_menu_layout);
        mLlMenu.setOnClickListener(this);
        mIvMenuIcon = view.findViewById(R.id.iv_menu);
        mTvMenuText = view.findViewById(R.id.tv_menu);
        mDivider = view.findViewById(R.id.divider);

        // 获取系统属性
        final int[] styleable = new int[]{
                android.R.attr.background, // index 0
                android.R.attr.layout_width, // index 1
                android.R.attr.layout_height, // 2
                android.R.attr.layout_margin, // 3
                android.R.attr.padding // 4
        };

        TypedArray array = context.obtainStyledAttributes(attrs, styleable);
//        int backgroundResId = array.getResourceId(array.getIndex(0), -1);
//        if (backgroundResId == -1) {
//            mLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
//        }
//        int n = array.getIndexCount();
//        for (int i = 0; i < n; i++) {
//            int index = array.getIndex(i);
//            int value = array.getDimensionPixelSize(index, 0);
//        }
        array.recycle();

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.HeaderView, defStyleAttr, 0);
        if (null != typedArray) {
            boolean backVisible = typedArray.getBoolean(R.styleable.HeaderView_back_visible, true);
            setBackVisible(backVisible);

            boolean backIconVisible = typedArray.getBoolean(R.styleable.HeaderView_back_icon_visible, true);
            setBackIconVisible(backIconVisible);

            int backIconResId = typedArray.getResourceId(R.styleable.HeaderView_back_icon, 0);
            if (backIconResId != 0) {
                setBackIcon(backIconResId);
            }
            String backText = typedArray.getString(R.styleable.HeaderView_back_text);
            setBackText(backText);

            String title = typedArray.getString(R.styleable.HeaderView_title);
            setTitleText(title);

            boolean menuVisible = typedArray.getBoolean(R.styleable.HeaderView_menu_visible, false);
            setMenuVisible(menuVisible);

            int menuIconResId = typedArray.getResourceId(R.styleable.HeaderView_menu_icon, 0);
            if (menuIconResId != 0) {
                setMenuIcon(menuIconResId);
            }
            String menuText = typedArray.getString(R.styleable.HeaderView_menu_text);
            if (!TextUtils.isEmpty(menuText)) {
                setMenuText(menuText);
            }

            boolean dividerVisible = typedArray.getBoolean(R.styleable.HeaderView_divider_visible, true);
            setDividerVisible(dividerVisible);

            typedArray.recycle();
        }
    }

    /**
     * 是否显示返回
     *
     * @param visible
     */
    public void setBackVisible(boolean visible) {
        mLlBack.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置返回图标
     *
     * @param resId
     */
    public void setBackIcon(@DrawableRes int resId) {
        setBackIcon(ContextCompat.getDrawable(mContext, resId));
    }

    /**
     * 设置返回图标
     *
     * @param drawable
     */
    public void setBackIcon(Drawable drawable) {
        mIvBackIcon.setImageDrawable(drawable);
    }

    /**
     * 设置返回图标是否可见
     *
     * @param visible
     */
    public void setBackIconVisible(boolean visible) {
        mIvBackIcon.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置返回文案
     *
     * @param resId
     */
    public void setBackText(@StringRes int resId) {
        setBackText(mContext.getString(resId));
    }

    /**
     * 设置返回文案
     *
     * @param text
     */
    public void setBackText(String text) {
        mTvBackText.setVisibility(VISIBLE);
        mTvBackText.setText(text);
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitleText(@StringRes int resId) {
        setTitleText(mContext.getString(resId));
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        mTvTitleText.setText(title);
    }

    /**
     * 是否显示返回
     *
     * @param visible
     */
    public void setMenuVisible(boolean visible) {
        mLlMenu.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置菜单图标
     *
     * @param resId
     */
    public void setMenuIcon(@DrawableRes int resId) {
        setMenuIcon(ContextCompat.getDrawable(mContext, resId));
    }

    /**
     * 设置菜单图标
     *
     * @param drawable
     */
    public void setMenuIcon(Drawable drawable) {
        if (mTvMenuText.getVisibility() == VISIBLE) {
            mTvMenuText.setVisibility(GONE);
        }
        mIvMenuIcon.setVisibility(VISIBLE);
        mIvMenuIcon.setImageDrawable(drawable);
    }

    /**
     * 设置菜单文案
     *
     * @param resId
     */
    public void setMenuText(@StringRes int resId) {
        setMenuText(mContext.getString(resId));
    }

    /**
     * 设置菜单文案
     *
     * @param text
     */
    public void setMenuText(String text) {
        if (mIvMenuIcon.getVisibility() == VISIBLE) {
            mIvMenuIcon.setVisibility(GONE);
        }
        mTvMenuText.setVisibility(VISIBLE);
        mTvMenuText.setText(text);
    }

    /**
     * 设置分隔线是否可见
     *
     * @param visible
     */
    public void setDividerVisible(boolean visible) {
        mDivider.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 添加返回事件
     *
     * @param listener
     */
    public void setOnBackClickListener(OnClickListener listener) {
        this.mBackClickListener = listener;
    }

    /**
     * 添加菜单事件
     *
     * @param listener
     */
    public void setOnMenuClickListener(OnClickListener listener) {
        this.mMenuClickListener = listener;
    }

//    @Override
//    public void setBackgroundColor(int color) {
//        super.setBackgroundColor(color);
//    }
}
