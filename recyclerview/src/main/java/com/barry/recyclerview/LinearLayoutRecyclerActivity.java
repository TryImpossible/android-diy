package com.barry.recyclerview;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class LinearLayoutRecyclerActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutRecyclerAdapter mAdapter;

    private int mLastVisiblePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_recycler);

        mRecyclerView = findViewById(R.id.recycler_view);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new LinearLayoutRecyclerAdapter(this, new ArrayList<String>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisiblePosition + 2 >= mAdapter.getItemCount()) {
                    LinearLayoutRecyclerActivity.this.loadMore();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisiblePosition = manager.findLastVisibleItemPosition();
            }
        });

        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        mRefreshLayout.setRefreshing(true);
        this.refresh();
    }

    private void refresh() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    data.add(String.format("第%d条数据", i + 1));
                }
                mAdapter.setNewData(data);
            }
        }, 1500);
    }

    private void loadMore() {
        mRefreshLayout.setRefreshing(false);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(String.format("第%d条数据", mAdapter.getData().size() + i + 1));
        }
        mAdapter.addData(data);
    }
}
