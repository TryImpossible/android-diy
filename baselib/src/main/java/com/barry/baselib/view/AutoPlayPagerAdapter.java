/*
 *
 * *******************************************************************
 *   @项目名称: BHex Android
 *   @文件名称: AutoPlayPagerAdapter.java
 *   @Date: 11/29/18 3:21 PM
 *   @Author: chenjun
 *   @Copyright（C）: 2018 BlueHelix Inc.   All rights reserved.
 *   注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的.
 *  *******************************************************************
 *
 */

package io.bhex.baselib.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

public abstract class AutoPlayPagerAdapter<V extends View, D> extends PagerAdapter {
    private static final int DEFAULT_ADAPTER_COUNT = 1000;//Integer.MAX_VALUE;

    private List<V> viewList;

    public List<D> dataList;

    public AutoPlayPagerAdapter(List<D> datas) {
        this.dataList = datas;
        viewList = new LinkedList<>();
    }

    @Override
    public int getCount() {
        return DEFAULT_ADAPTER_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        V view;

        if (viewList.size() != 0) {
            view = viewList.remove(0);
        } else {
            view = createNewItem();
        }
        container.addView(view);

        loadData(view, getItem(position));

        return view;
    }

    int getDefaultPos() {
        return getCount() / 2;
    }

    /**
     * 创建新的条目
     *
     * @return
     */
    protected abstract V createNewItem();

    /**
     * 加载数据
     *
     * @param view
     * @param data
     */
    protected abstract void loadData(V view, D data);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        V view = (V) object;

        container.removeView(view);
        viewList.add(view);
    }


    public D getItem(int viewPosition) {
        int dataPos = getDataPosition(viewPosition);
        if (dataPos < 0)
            return null;

        return dataList.get(dataPos);
    }

    /**
     * Position有两种，一种是View的Position，对应的是{@link #DEFAULT_ADAPTER_COUNT} {@link ViewPager#getCurrentItem()}
     * 另外是数据的Position，即真实数据的位置，对应的是 {@link #dataList}
     * 获取实际数据对应的位置
     *
     * @param position View的Position
     * @return
     */
    public int getDataPosition(int position) {
        int dataSize = dataList.size();

        if (dataSize <= 0)
            return -1;

        int offset;
        if (position >= getDefaultPos()) {
            offset = (position - getDefaultPos()) % dataSize;
        } else {
            offset = dataSize - (getDefaultPos() - position) % dataSize;
            if (offset == dataSize)
                offset = 0;
        }

        return offset;
    }
}
