package com.newclass.youkumenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView lv_data;
    private SearchView sv_list;

    private List<String> list;
    private MyFilter filter;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            list.add("一般数据" + i);
        }

        filter = new MyFilter();


        lv_data = (ListView) findViewById(R.id.lv_data);
        adapter = new MyAdapter();
        lv_data.setAdapter(adapter);
        lv_data.setTextFilterEnabled(true);


        sv_list = (SearchView) findViewById(R.id.sv_list);
        sv_list.setSubmitButtonEnabled(true);
        sv_list.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
        sv_list.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lv_data.setFilterText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                lv_data.setFilterText(query);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyAdapter extends BaseAdapter implements Filterable {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(MainActivity.this);
            view.setText(list.get(position));
            return view;
        }

        @Override
        public Filter getFilter() {
            return filter;
        }
    }

    private class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.contains(constraint)) {
                    iterator.remove();
                }
            }
            adapter.notifyDataSetChanged();
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        }
    }
}
