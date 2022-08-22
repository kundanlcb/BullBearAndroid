package com.bootninza.bullbear.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bootninza.bullbear.R;
import com.bootninza.bullbear.adapters.EquityAdapter;
import com.bootninza.bullbear.adapters.FeedAdapter;
import com.bootninza.bullbear.databinding.FragmentDashboardBinding;
import com.bootninza.bullbear.ui.home.HomeFragment;
import com.bootninza.bullbear.webservices.models.Equity;
import com.bootninza.bullbear.webservices.models.Feed;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    protected RecyclerView mRecyclerView;
    protected EquityAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Equity> mDataset = new ArrayList<>();
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    @Override
    public void onResume() {
        super.onResume();
        dashboardViewModel.callEquityApi();
        Log.i("HomeFragment", "onResume");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mRecyclerView = binding.equityList;

        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new EquityAdapter(mDataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        dashboardViewModel.getEquities().observe(getViewLifecycleOwner(), new Observer<List<Equity>>() {
            @Override
            public void onChanged(List<Equity> equities) {
                Log.i("feed observer", equities.toString());
                mAdapter.updateEquityList(equities);
            }
        });
        return root;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }
        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }