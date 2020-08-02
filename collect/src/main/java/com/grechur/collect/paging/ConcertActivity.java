package com.grechur.collect.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.grechur.collect.R;

public class ConcertActivity extends AppCompatActivity {
    ConcertDao concertDao;
    LiveData<PagedList<Concert>> allStudentsLive;
    ConcertAdapter1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_activity_concert);

        ConcertViewModel viewModel = new ViewModelProvider(this).get(ConcertViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.concert_list);
        adapter = new ConcertAdapter1();
        viewModel.concertList.observe(this, adapter::submitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        concertDao = ConcertDatabase.getInstance(this).concertDao();
//        allStudentsLive = new LivePagedListBuilder<>(concertDao.concertsByDate(),10).build();
//        allStudentsLive.observe(this, new Observer<PagedList<Concert>>() {
//            @Override
//            public void onChanged(PagedList<Concert> concerts) {
//                adapter.submitList(concerts);
//            }
//        });
//        allStudentsLive.observe(this,adapter::submitList);
    }


}