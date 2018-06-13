package com.example.mysto.wrcsearchfilter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView listshowrcy;
    LinearLayoutManager linearLayoutManager;
    List<Item> productlists = new ArrayList<>();

    MainActivityAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productlists.add(new Item("cb1000r", R.drawable.cb1000r, "Full"));
        productlists.add(new Item("cbr1000rr", R.drawable.cbr1000rr, "Full"));
        productlists.add(new Item("diavel", R.drawable.diavel, "Full"));
        productlists.add(new Item("diavel_x", R.drawable.diavel_x, "Full"));
        productlists.add(new Item("gladius", R.drawable.gladius, "A2"));
        productlists.add(new Item("h2r", R.drawable.h2r, "Full"));
        productlists.add(new Item("mt07", R.drawable.mt07, "A2"));
        productlists.add(new Item("street_triple", R.drawable.street_triple, "A2"));
        productlists.add(new Item("sv650", R.drawable.sv650, "A2"));
        productlists.add(new Item("z1000", R.drawable.z1000, "Full"));
        productlists.add(new Item("z650", R.drawable.z650, "A2"));
        productlists.add(new Item("z900", R.drawable.z900, "Full"));
        productlists.add(new Item("z900 A2", R.drawable.z900, "A2"));

        listshowrcy = findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter= new MainActivityAdapter(productlists, MainActivity.this);
        listshowrcy.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuIem=menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuIem.getActionView();
        changeSearchViewTextColor(searchView, Color.WHITE);
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                myActionMenuIem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<Item> filtermodelist=filter(productlists, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }

    private List<Item> filter(List<Item> pl, String query) {

        query=query.toLowerCase();
        final List<Item> filteredModeList=new ArrayList<>();
        for (Item model:pl) {
            final String name = model.getName().toLowerCase();
            final String type = model.getType().toLowerCase();
            if(name.contains(query)) {
                filteredModeList.add(model);
            } else if(type.contains(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    private void changeSearchViewTextColor(View view, int color) {
        if(view != null) {
            if(view instanceof TextView) {
                ((TextView) view).setTextColor(color);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for(int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i), color);
                }
            }
        }
    }


}
