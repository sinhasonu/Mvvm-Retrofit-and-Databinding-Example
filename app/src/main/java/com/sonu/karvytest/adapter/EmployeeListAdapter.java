package com.sonu.karvytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sonu.karvytest.EmployeeListModel;
import com.sonu.karvytest.R;
import com.sonu.karvytest.databinding.ItemEmployeeListBinding;
import com.sonu.karvytest.viewmodel.EmployeeListViewModel;

/**
 * Created by Sonu Sinha on 20/09/2020.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {

    private Context context;
    private EmployeeListViewModel employeeListViewModel;

    public EmployeeListAdapter(Context context, EmployeeListViewModel employeeListViewModel) {
        this.context = context;
        this.employeeListViewModel = employeeListViewModel;
    }

    @NonNull
    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEmployeeListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_employee_list, parent, false);
        binding.clItemRoot.setOnClickListener(view -> {
            employeeListViewModel.getNavigator().onListItemClicked(binding.getEmployeeListModel());
        });

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListAdapter.ViewHolder holder, int position) {
        EmployeeListModel.DataBean item = employeeListViewModel.getLiveEmployeeListResponse().getValue().getData().get(position);
        holder.bind(item);
    }


    @Override
    public int getItemCount() {
        if (employeeListViewModel.getLiveEmployeeListResponse() != null && employeeListViewModel.getLiveEmployeeListResponse().getValue() != null
                && employeeListViewModel.getLiveEmployeeListResponse().getValue().getData() != null)
            return employeeListViewModel.getLiveEmployeeListResponse().getValue().getData().size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemEmployeeListBinding binding;

        public ViewHolder(@NonNull ItemEmployeeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EmployeeListModel.DataBean item) {
            binding.setEmployeeListModel(item);
            binding.executePendingBindings();
        }
    }

    public void updateList() {
        notifyDataSetChanged();
    }
}
